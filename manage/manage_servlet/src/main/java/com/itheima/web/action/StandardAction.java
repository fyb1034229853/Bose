package com.itheima.web.action;


import com.itheima.domain.base.Standard;
import com.itheima.service.base.StandardService;
import com.itheima.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("standardAction")
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class StandardAction extends BaseAction<Standard> {

    @Resource(name = "standardService")
    private StandardService standardService;



    @Action(value = "standard_save", results = {@Result(name = "success", location = "/pages/base/standard.html", type = "redirect")})
    public String save() {
//        model.setOperatingTime(new Date());
        System.out.println(model);
        standardService.save(model);
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
    @Action(value = "standard_findPage")
    public String findPage( ) throws IOException {
        Pageable pageable = new PageRequest(page-1,rows);
        Page<Standard> page=standardService.findPage(pageable);
       getPage(page,null);
        return null;
    }

    @Action(value = "standard_findAll")
    public String findAll() throws IOException {
        List<Standard> list = standardService.findAll();
        getList(list,null);
        return  null;
    }



}
