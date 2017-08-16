package org.templateproject.items.oauth2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.templateproject.items.oauth2.entity.IMenu;
import org.templateproject.items.oauth2.service.base.SimpleBaseCrudService;
import org.templateproject.items.oauth2.support.pojo.bo.ZTreeBO;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Liurongqi on 2017/7/21.
 */
@Service
@Transactional
public class MenuService extends SimpleBaseCrudService<IMenu, Integer> {

    /**
     * 根据role_id查询返回ZTree
     *
     * @param request
     * @return
     */
    public List<ZTreeBO> selectMenu(HttpServletRequest request) throws Exception {
        int pId = Integer.parseInt(request.getParameter("pId"));
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        String sql = "SELECT * FROM T_OAUTH_MENU  WHERE T_OAUTH_MENU.ROLE_ID = ? AND T_OAUTH_MENU.PARENT_ID = ?";
        List<IMenu> iMenus = mysql.findListBeanByArray(sql, IMenu.class, roleId, pId);
        if (0 == pId) {
            iMenus.add(IMenu.root());
        }
        return menuListToZTree(iMenus);
    }


    public List<ZTreeBO> menuListToZTree(List<IMenu> menuList) throws Exception {
        List<ZTreeBO> zTreeList = new LinkedList<>();
        for (IMenu next : menuList) {
            ZTreeBO ztree = new ZTreeBO();
            ztree.setId(next.getId().toString());
            ztree.setpId(next.getParentId().toString());
            ztree.setName(next.getName());
            ztree.setOpen(true);

            //根据是否有子节点设置isParent属性
            if (checkIsParent(next.getId())) {
                ztree.setisParent(true);
            } else {
                ztree.setisParent(false);
            }
            zTreeList.add(ztree);
        }
        return zTreeList;
    }

    public boolean checkIsParent(int id) throws Exception {
        String sql = "SELECT COUNT(*) FROM T_OAUTH_MENU WHERE 1=1 AND T_OAUTH_MENU.PARENT_ID = ?";
        long count = mysql.queryNumberByArray(sql, Long.class, id);
        return count != 0;
    }
}
