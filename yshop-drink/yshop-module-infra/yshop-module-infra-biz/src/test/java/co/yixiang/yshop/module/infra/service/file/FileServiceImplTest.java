package co.yixiang.yshop.module.infra.service.file;

import cn.hutool.core.io.resource.ResourceUtil;
import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.framework.common.util.object.ObjectUtils;
import co.yixiang.yshop.framework.file.core.client.FileClient;
import co.yixiang.yshop.framework.test.core.ut.BaseDbUnitTest;
import co.yixiang.yshop.framework.test.core.util.AssertUtils;
import co.yixiang.yshop.module.infra.controller.admin.file.vo.file.FilePageReqVO;
import co.yixiang.yshop.module.infra.dal.dataobject.file.FileDO;
import co.yixiang.yshop.module.infra.dal.mysql.file.FileMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

import java.time.LocalDateTime;

import static co.yixiang.yshop.framework.common.util.date.LocalDateTimeUtils.buildTime;
import static co.yixiang.yshop.framework.test.core.util.AssertUtils.assertServiceException;
import static co.yixiang.yshop.framework.test.core.util.RandomUtils.*;
import static co.yixiang.yshop.module.infra.enums.ErrorCodeConstants.FILE_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@Import({FileServiceImpl.class})
public class FileServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FileService fileService;

    @Resource
    private FileMapper fileMapper;

    @MockBean
    private FileConfigService fileConfigService;

    @Test
    public void testGetFilePage() {
        // mock 数据
        FileDO dbFile = randomPojo(FileDO.class, o -> { // 等会查询到
            o.setPath("yshop");
            o.setType("image/jpg");
            o.setCreateTime(buildTime(2021, 1, 15));
        });
        fileMapper.insert(dbFile);
        // 测试 path 不匹配
        fileMapper.insert(ObjectUtils.cloneIgnoreId(dbFile, o -> o.setPath("tudou")));
        // 测试 type 不匹配
        fileMapper.insert(ObjectUtils.cloneIgnoreId(dbFile, o -> {
            o.setType("image/png");
        }));
        // 测试 createTime 不匹配
        fileMapper.insert(ObjectUtils.cloneIgnoreId(dbFile, o -> {
            o.setCreateTime(buildTime(2020, 1, 15));
        }));
        // 准备参数
        FilePageReqVO reqVO = new FilePageReqVO();
        reqVO.setPath("yshop");
        reqVO.setType("jp");
        reqVO.setCreateTime((new LocalDateTime[]{buildTime(2021, 1, 10), buildTime(2021, 1, 20)}));

        // 调用
        PageResult<FileDO> pageResult = fileService.getFilePage(reqVO);
        // 断言
        assertEquals(1, pageResult.getTotal());
        assertEquals(1, pageResult.getList().size());
        AssertUtils.assertPojoEquals(dbFile, pageResult.getList().get(0));
    }

    @Test
    public void testCreateFile_success() throws Exception {
        // 准备参数
        String path = randomString();
        byte[] content = ResourceUtil.readBytes("file/erweima.jpg");
        // mock Master 文件客户端
        FileClient client = mock(FileClient.class);
        when(fileConfigService.getMasterFileClient()).thenReturn(client);
        String url = randomString();
        when(client.upload(same(content), same(path), eq("image/jpeg"))).thenReturn(url);
        when(client.getId()).thenReturn(10L);
        String name = "单测文件名";
        // 调用
        String result = fileService.createFile(name, path, content);
        // 断言
        assertEquals(result, url);
        // 校验数据
        FileDO file = fileMapper.selectOne(FileDO::getPath, path);
        assertEquals(10L, file.getConfigId());
        assertEquals(path, file.getPath());
        assertEquals(url, file.getUrl());
        assertEquals("image/jpeg", file.getType());
        assertEquals(content.length, file.getSize());
    }

    @Test
    public void testDeleteFile_success() throws Exception {
        // mock 数据
        FileDO dbFile = randomPojo(FileDO.class, o -> o.setConfigId(10L).setPath("tudou.jpg"));
        fileMapper.insert(dbFile);// @Sql: 先插入出一条存在的数据
        // mock Master 文件客户端
        FileClient client = mock(FileClient.class);
        when(fileConfigService.getFileClient(eq(10L))).thenReturn(client);
        // 准备参数
        Long id = dbFile.getId();

        // 调用
        fileService.deleteFile(id);
        // 校验数据不存在了
        assertNull(fileMapper.selectById(id));
        // 校验调用
        verify(client).delete(eq("tudou.jpg"));
    }

    @Test
    public void testDeleteFile_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> fileService.deleteFile(id), FILE_NOT_EXISTS);
    }

    @Test
    public void testGetFileContent() throws Exception {
        // 准备参数
        Long configId = 10L;
        String path = "tudou.jpg";
        // mock 方法
        FileClient client = mock(FileClient.class);
        when(fileConfigService.getFileClient(eq(10L))).thenReturn(client);
        byte[] content = new byte[]{};
        when(client.getContent(eq("tudou.jpg"))).thenReturn(content);

        // 调用
        byte[] result = fileService.getFileContent(configId, path);
        // 断言
        assertSame(result, content);
    }
}
