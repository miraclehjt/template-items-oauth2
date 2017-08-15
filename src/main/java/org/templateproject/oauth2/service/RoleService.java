package org.templateproject.oauth2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.templateproject.oauth2.entity.OauthRole;
import org.templateproject.oauth2.service.base.SimpleBaseCrudService;
import org.templateproject.oauth2.support.annotation.sql.SqlMapper;
import org.templateproject.oauth2.support.pojo.bo.RoleBo;
import org.templateproject.oauth2.support.pojo.bo.ZTreeBO;
import org.templateproject.oauth2.support.pojo.vo.RoleVO;
import org.templateproject.pojo.page.Page;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 继承AbstractBaseCrudService
 * Created by tuchen on 2017/7/12.
 */
@Service
@Transactional
@SqlMapper("role")
public class RoleService extends SimpleBaseCrudService<OauthRole, Integer> {

    /**
     * 通过名称模糊 查询Page
     *
     * @param page   page
     * @param roleBo roleBo
     * @return Page
     */
    public Page<RoleVO> findRolePage(Page<RoleVO> page, RoleBo roleBo) {
        return findPagination(page, RoleVO.class, sql(), roleBo);
    }


    /**
     * zTree形式的j角色树列表
     * 根
     *
     * @return role tree
     */
    public List<ZTreeBO> findRoleTree(String roleId, String systemModuleCode) {
        List<OauthRole> oauthRoles = mysql.findListBeanByArray(sql(), OauthRole.class, systemModuleCode);
        if ("0".equalsIgnoreCase(roleId))
            oauthRoles.add(OauthRole.root());
        return roleToZTree(oauthRoles);
    }


    /**
     * 將Role列表转为zTree树对象
     *
     * @param oauthRoles oauthRoles
     * @return List<ZTreeBO>
     */
    private List<ZTreeBO> roleToZTree(Collection<OauthRole> oauthRoles) {
        List<ZTreeBO> zTreeList = new LinkedList<>();
        for (OauthRole role : oauthRoles) {
            ZTreeBO zTree = new ZTreeBO();
            zTree.setId(role.getId().toString());
            zTree.setpId(role.getParentId().toString());
            zTree.setName(role.getCnName());
            zTree.setOpen(true);
            zTree.setisParent(checkIsParent(role.getId())); //根据是否有子节点设置isParent属性
            zTreeList.add(zTree);
        }
        return zTreeList;
    }

    /**
     * 根据节点id判断指定节点是否为父节点
     *
     * @param roleId roleId
     * @return 是否为父节点
     */

    private boolean checkIsParent(int roleId) {
        long count = mysql.queryNumberByArray(sql(), Long.class, roleId);
        return count != 0;
    }

}
