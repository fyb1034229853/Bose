package com.itheima.web.action;

import com.itheima.domain.base.Courier;
import com.itheima.domain.base.Standard;
import com.itheima.service.base.CourierService;
import com.itheima.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CourierAction extends BaseAction<Courier>{

    @Autowired
    private CourierService courierService;

    @Action(value = "courier_save",results = {@Result(name = "success",location = "/pages/base/courier.html",type = "redirect")})
    public String save( ){
        courierService.save(model);
        return  SUCCESS;
    }
    private int page ;
    private int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    @Action("courier_page")
    public String findPage() throws IOException {
        Specification<Courier> spaci = new Specification<Courier>() {
            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                String type = model.getType();
                String courierNum = model.getCourierNum();
                String company = model.getCompany();
                Standard standard = model.getStandard();
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotEmpty(type)){
                    Predicate p1 = cb.equal(root.get("type").as(String.class), type);
                    list.add(p1);
                }
                if (StringUtils.isNotEmpty(courierNum)){
                    Predicate p2 = cb.equal(root.get("courierNum").as(String.class), courierNum);
                    list.add(p2);
                }
                if (StringUtils.isNotEmpty(company)){
                    Predicate p3 = cb.like(root.get("company").as(String.class), "%"+company+"%");
                    list.add(p3);
                }
                if (standard!=null&&StringUtils.isNotEmpty(standard.getName())){
                    Join<Object, Object> join = root.join("standard");
                    Predicate p4 = cb.equal(join.get("name").as(String.class), standard.getName());
                    list.add(p4);
                }
                if (list.size()==0){
                    return null;
                }
                Predicate[] arr = new Predicate[list.size()];
                list.toArray(arr);
                return cb.and(arr);
            }
        };

        PageRequest pageable = new PageRequest(page - 1, rows);
        Page<Courier> page = courierService.findPage(spaci,pageable);

        getPage(page,new String[]{"fixedAreas","takeTime"});
        return null;
    }
    private String sid;

    public void setSid(String sid) {
        this.sid = sid;
    }
    @Action(value = "courier_update",results = {@Result(name = "success",location = "/pages/base/courier.html",type = "redirect")})
    public String updateDelet() {
        courierService.updaedelete(sid);
        return SUCCESS;
    }
    @Action(value = "courier_restor",results = {@Result(name = "success",location = "/pages/base/courier.html",type = "redirect")})
    public String updateRestor() {
        courierService.updaerestor(sid);
        return SUCCESS;
    }
    @Action(value = "courier_findAll")
    public String findAll() throws IOException {
        List<Courier> list = courierService.findAll();
        getList(list,new String[]{"fixedAreas"});
        return  null;
    }
}
