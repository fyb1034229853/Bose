package com.itheima.web.action;

import com.itheima.domain.base.Standard;
import com.itheima.domain.base.SubArea;
import com.itheima.service.base.SubAreaService;
import com.itheima.web.action.base.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default")
@Controller
public class SubAreaAction extends BaseAction<SubArea> {

    @Autowired
    private SubAreaService subAreaService;
    @Action(value = "subArea_save", results = {@Result(name = "success",location = "/pages/base/sub_area.html",type = "redirect")})
    public String save(){
        subAreaService.save(model);
        return SUCCESS;
    }
    private int page;
    private int rows;

    public void setPage(int page) {
        this.page = page;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    @Action(value = "subArea_findPage")
    public String findPage( ){
        Pageable pageable = new PageRequest(page-1,rows);
        Page<Standard> page=subAreaService.findPage(pageable);
        getPage(page,new  String[]{"subareas","fixedArea"});
        return null;
    }

}
