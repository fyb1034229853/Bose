package com.itheima.web.action;

import com.itheima.Utils.SmsUtils;
import com.itheima.domain.Customer;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer model=new Customer();
    @Override
    public Customer getModel() {
        return model;
    }
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Action(value ="Customer_regist",results = {@Result(name = "none",location = "/signup-fail.html",type = "redirect"),
            @Result(name = "success",location = "/signup-success.html",type = "redirect")})
    public String regist() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        String code = (String) session.getAttribute(model.getTelephone());
        if (StringUtils.isNotEmpty(checkcode)&&StringUtils.isNotEmpty(model.getTelephone())&&StringUtils.isNotEmpty(code)&&checkcode.equals(code)) {
            WebClient.create("http://localhost:8180/crm/webService/customer/save")
                    .accept(MediaType.APPLICATION_JSON)
                    .post(model);
            return SUCCESS;
        }
        return NONE;
    }
    @Action("Customer_code")
    public String code(){
        String mobilePhone = model.getTelephone();
        String code = RandomStringUtils.randomNumeric(4);
        System.out.println("验证码："+code);
        SmsUtils.sendSmsByWebService(mobilePhone,"尊敬的客户你好，您本次获取的验证码为："+code);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute(mobilePhone,code);
        return  null;
    }
}
