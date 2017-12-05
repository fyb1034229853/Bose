package com.itheima.web.action;

import com.itheima.domain.base.Standard;
import com.itheima.domain.base.TakeTime;
import com.itheima.service.base.TakeTimeService;
import com.itheima.web.action.base.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class TakeTimeAction extends BaseAction<TakeTime>{
    @Autowired
    private TakeTimeService takeTimeService;
    @Action(value = "takeTime_findAll")
    public String findAll() throws IOException {
        List<TakeTime> list = takeTimeService.findAll();
        getList(list,null);
        return  null;
    }
}
