package com.itheima.web.action.base;

import com.itheima.domain.base.Area;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    public  T model;
    public BaseAction() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedTyp=(ParameterizedType)genericSuperclass;
        Type[] classtype = parameterizedTyp.getActualTypeArguments();
        Class clazz=(Class)classtype[0];
        try {
            model= (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPage(Page page,String[] exclude) {
        long total = page.getTotalElements();
        List<Area> content = page.getContent();
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows",content);
        map.put("total",total);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclude);
        String json= JSONObject.fromObject(map,jsonConfig).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void  getList(List list,String[] exclude){
        JsonConfig config = new JsonConfig();
        config.setExcludes(exclude);
        String json = JSONArray.fromObject(list,config).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public T getModel() {
        return model ;
    }
}
