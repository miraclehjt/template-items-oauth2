package me.wuwenbin.items.oauth2.support.pojo.bo;

import me.wuwenbin.items.oauth2.support.annotation.query.QueryColumn;
import me.wuwenbin.items.oauth2.support.annotation.query.QueryTable;
import me.wuwenbin.items.oauth2.support.enumerate.Operator;
import me.wuwenbin.items.oauth2.support.pojo.BootstrapTableQuery;

/**
 * Created by zhangteng on 2017/7/19.
 * 页面资源表BO
 */
@QueryTable("topp")
public class PrivilegePageBo extends BootstrapTableQuery {

    private String name;  //资源模块名称

    private String enabled;  //是否可用

    @QueryColumn(value = "resource_module_id", operation = Operator.EQ)
    private String moduleId;  //资源模块ID

    @QueryColumn(value = "name", table = "tor")
    private String resourceName;  //资源名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
