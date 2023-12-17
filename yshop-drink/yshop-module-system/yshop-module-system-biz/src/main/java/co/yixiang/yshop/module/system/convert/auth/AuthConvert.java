package co.yixiang.yshop.module.system.convert.auth;

import co.yixiang.yshop.framework.common.util.collection.CollectionUtils;
import co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils;
import co.yixiang.yshop.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import co.yixiang.yshop.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import co.yixiang.yshop.module.system.api.social.dto.SocialUserBindReqDTO;
import co.yixiang.yshop.module.system.controller.admin.auth.vo.*;
import co.yixiang.yshop.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import co.yixiang.yshop.module.system.dal.dataobject.permission.MenuDO;
import co.yixiang.yshop.module.system.dal.dataobject.permission.RoleDO;
import co.yixiang.yshop.module.system.dal.dataobject.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.LoggerFactory;

import java.util.*;

import static co.yixiang.yshop.framework.common.util.collection.CollectionUtils.filterList;
import static co.yixiang.yshop.module.system.dal.dataobject.permission.MenuDO.ID_ROOT;

@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    AuthLoginRespVO convert(OAuth2AccessTokenDO bean);

    default AuthPermissionInfoRespVO convert(AdminUserDO user, List<RoleDO> roleList, List<MenuDO> menuList) {
        Long shopId = SecurityFrameworkUtils.getLoginUser().getShopId();
        return AuthPermissionInfoRespVO.builder()
            .user(AuthPermissionInfoRespVO.UserVO.builder().shopId(shopId).id(user.getId()).nickname(user.getNickname()).avatar(user.getAvatar()).build())
            .roles(CollectionUtils.convertSet(roleList, RoleDO::getCode))
            .permissions(CollectionUtils.convertSet(menuList, MenuDO::getPermission))
            .build();
    }

    AuthMenuRespVO convertTreeNode(MenuDO menu);

    /**
     * 将菜单列表，构建成菜单树
     *
     * @param menuList 菜单列表
     * @return 菜单树
     */
    default List<AuthMenuRespVO> buildMenuTree(List<MenuDO> menuList) {
        // 排序，保证菜单的有序性
        menuList.sort(Comparator.comparing(MenuDO::getSort));
        // 构建菜单树
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Long, AuthMenuRespVO> treeNodeMap = new LinkedHashMap<>();
        menuList.forEach(menu -> treeNodeMap.put(menu.getId(), AuthConvert.INSTANCE.convertTreeNode(menu)));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> !node.getParentId().equals(ID_ROOT)).forEach(childNode -> {
            // 获得父节点
            AuthMenuRespVO parentNode = treeNodeMap.get(childNode.getParentId());
            if (parentNode == null) {
                LoggerFactory.getLogger(getClass()).error("[buildRouterTree][resource({}) 找不到父资源({})]",
                    childNode.getId(), childNode.getParentId());
                return;
            }
            // 将自己添加到父节点中
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        return filterList(treeNodeMap.values(), node -> ID_ROOT.equals(node.getParentId()));
    }

    SocialUserBindReqDTO convert(Long userId, Integer userType, AuthSocialLoginReqVO reqVO);

    SmsCodeSendReqDTO convert(AuthSmsSendReqVO reqVO);

    SmsCodeUseReqDTO convert(AuthSmsLoginReqVO reqVO, Integer scene, String usedIp);

}
