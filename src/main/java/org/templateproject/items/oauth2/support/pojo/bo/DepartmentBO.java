package org.templateproject.items.oauth2.support.pojo.bo;

import org.templateproject.items.oauth2.support.annotation.query.QueryColumn;
import org.templateproject.items.oauth2.support.annotation.query.QueryTable;
import org.templateproject.items.oauth2.support.enumerate.Operator;
import org.templateproject.items.oauth2.support.pojo.PageQueryBO;

/**
 * 部门管理页搜素面查询对象
 * Created by wuwenbin on 2017/08/04.
 */
@QueryTable("tod")
public class DepartmentBO extends PageQueryBO {

    private String name;  //部门名称

    @QueryColumn(value = "parent_id", operation = Operator.EQ)
    private Integer parentId;  //父节点id

    @QueryColumn(operation = Operator.EQ)
    private Boolean enabled;   //是否可用


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}