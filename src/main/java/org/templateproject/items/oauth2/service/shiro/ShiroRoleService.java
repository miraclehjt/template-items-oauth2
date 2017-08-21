package org.templateproject.items.oauth2.service.shiro;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.templateproject.items.oauth2.entity.IRole;
import org.templateproject.items.oauth2.entity.IUser;
import org.templateproject.items.oauth2.service.base.SimpleBaseCrudService;
import org.templateproject.items.oauth2.util.UserUtils;

import java.util.*;

/**
 * shiro用户角色service
 * Created by Wuwenbin on 2017/7/17.
 */
@Service
@Transactional
public class ShiroRoleService extends SimpleBaseCrudService<IRole, Integer> {

    /**
     * 根据用户查找用户的角色集合
     *
     * @param user 用户对象
     * @return 用户角色集合
     */
    public Set<IRole> findRolesByUser(IUser user) {
        String sql = "SELECT tor.* FROM T_OAUTH_ROLE tor WHERE tor.ID IN" +
                " (SELECT tour.ROLE_ID FROM T_OAUTH_USER_ROLE tour WHERE tour.USER_ID = ?)" +
                " AND tor.ENABLED = 1";
        List<IRole> roles = mysql.findListBeanByArray(sql, IRole.class, user.getId());
        return new HashSet<>(roles);
    }

    /**
     * 根据用户id查找用户的角色集合
     *
     * @param userId 用户id
     * @return 用户角色集合
     */
    public Set<IRole> findRolesByUserId(int userId) {
        String sql = "SELECT tor.* FROM T_OAUTH_ROLE tor WHERE tor.ID IN" +
                " (SELECT tour.ROLE_ID FROM T_OAUTH_USER_ROLE tour WHERE tour.USER_ID = ?)" +
                " AND tor.ENABLED = 1";
        List<IRole> roles = mysql.findListBeanByArray(sql, IRole.class, userId);
        return new HashSet<>(roles);
    }

    /**
     * 根据用户id查询当前用户所有的角色名称集合信息
     *
     * @param userId 用户id
     * @return 用户角色名称集合
     */
    public Set<String> findRoleNamesByUserId(int userId) {
        String sql = "SELECT tor.name AS role_name FROM T_OAUTH_ROLE tor WHERE tor.ID IN" +
                " (SELECT tour.ROLE_ID FROM T_OAUTH_USER_ROLE tour WHERE tour.USER_ID = ?)" +
                " AND tor.ENABLED = 1";
        List<Map<String, Object>> roles = mysql.findListMapByArray(sql, userId);
        Set<String> roleNames = new HashSet<>(roles.size());
        for (Map<String, Object> m : roles) {
            roleNames.add(m.get("role_name").toString());
        }
        return roleNames;
    }

    /**
     * 查询当前登录用户的所有角色名
     *
     * @return 当前用户的所有角色信息集合
     */
    public Set<IRole> findCurrentUserRoles() {
        IUser user = UserUtils.getLoginUser();
        if (user == null)
            return Collections.emptySet();
        else {
            String sql = "SELECT tor.* FROM T_OAUTH_ROLE tor WHERE tor.ID IN" +
                    " (SELECT tour.ROLE_ID FROM T_OAUTH_USER_ROLE tour WHERE tour.USER_ID = ?)" +
                    " AND tor.ENABLED = 1";
            List<IRole> roles = mysql.findListBeanByArray(sql, IRole.class, user.getId());
            return new HashSet<>(roles);
        }
    }

}