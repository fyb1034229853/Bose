package com.itheima.web.action;

import com.itheima.domain.base.Courier;
import com.itheima.domain.base.Customer;
import com.itheima.domain.base.FixedArea;
import com.itheima.service.base.FixedAreaService;
import com.itheima.web.action.base.BaseAction;
import org.apache.activemq.tool.WebServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
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

import javax.jws.WebService;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class FixedArearAction extends BaseAction<FixedArea> {
    @Autowired
    private FixedAreaService fixedAreaService;


    @Action(value = "fixedAreaAction_save", results = {@Result(name = "success", location = "/pages/base/fixed_area.html", type = "redirect")})
    public String fixedAreaAction_save() {
        fixedAreaService.save(model);
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

    @Action(value = "fixedAreaPage")
    public String subArea_findPage() {
        ArrayList<FixedArea> list = new ArrayList<>();
        Pageable pageable = new PageRequest(page-1,rows);
        Page<FixedArea> page = fixedAreaService.findPage(pageable);
        getPage(page,new String[]{"subareas","couriers"});
        return null;
    }
    @Action(value = "fixedAreaUnCustomer")
    public String fixedAreaUnCustomer() {
        WebClient client = WebClient.create("http://localhost:8180/crm/webService/customer/findByUnbend");
        List<Customer>list = (List<Customer>) client
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .getCollection(Customer.class);
        getList(list,null);
        return null;
    }
    @Action(value = "fixedAreaCustomer")
    public String fixedAreaCustomer() {
        WebClient client = WebClient.create("http://localhost:8180/crm/webService/customer/findBybend");
        List<Customer> list =(List<Customer>) client
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .query("id", model.getId())
                .getCollection(Customer.class);
        getList(list,null);
        return null;
    }
    private List<Long> ids=new ArrayList<>();
    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    @Action(value = "fixed_bend", results = {@Result(name = "success", location = "/pages/base/fixed_area.html", type = "redirect")})
    public String bend(){
        if (StringUtils.isNotEmpty(model.getId()+"")) {
            WebClient client = WebClient.create("http://localhost:8180/crm/webService/customer/bend");
             client
                    .accept(MediaType.APPLICATION_JSON)
                    .query("fixedAreaId", model.getId())
                     .query("ids",ids)
                     .put(null);
        }
        return SUCCESS;
    }
    private Long couriersId;
    private Long takeTimeId;

    public void setTakeTimeId(Long takeTimeId) {
        this.takeTimeId = takeTimeId;
    }

    public void setCouriersId(Long couriersId) {
        this.couriersId = couriersId;
    }

    @Action(value = "fixedAreaAddCourier", results = {@Result(name = "success", location = "/pages/base/fixed_area.html", type = "redirect")})
    public String fixedAreaAddCourier(){
        Long id = model.getId();
        fixedAreaService.beanCourier(id,couriersId,takeTimeId);
        return SUCCESS;
    }

}
