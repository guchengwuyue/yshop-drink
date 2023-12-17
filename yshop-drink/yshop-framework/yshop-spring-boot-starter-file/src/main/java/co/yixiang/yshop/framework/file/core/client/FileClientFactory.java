package co.yixiang.yshop.framework.file.core.client;

public interface FileClientFactory {

    /**
     * 获得文件客户端
     *
     * @param configId 配置编号
     * @return 文件客户端
     */
    FileClient getFileClient(Long configId);

    /**
     * 创建文件客户端
     *
     * @param configId 配置编号
     * @param storage 存储器的枚举 {@link co.yixiang.yshop.framework.file.core.enums.FileStorageEnum}
     * @param config 文件配置
     */
    <Config extends FileClientConfig> void createOrUpdateFileClient(Long configId, Integer storage, Config config);

}
