package com.itheima.web.action;

import com.itheima.Utils.PinYin4jUtils;
import com.itheima.domain.base.Area;
import com.itheima.domain.base.Courier;
import com.itheima.domain.base.Standard;
import com.itheima.service.base.AreaService;
import com.itheima.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class AreaAction extends BaseAction<Area> {

    @Autowired
    private AreaService areaService;

    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    @Action(value = "area_saveimport", results = {@Result(name = "success", location = "/pages/base/area.html", type = "redirect")})
    public String saveImport() throws Exception {
        System.out.println("进来了");
        String paht = file.getAbsolutePath();
        System.out.println(paht);
        ArrayList<Area> list = new ArrayList<>();
        HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheetAt = book.getSheetAt(0);
        for (Row cells : sheetAt) {
            int num = cells.getRowNum();
            if (num == 0) {
                continue;
            }
            String shefe = cells.getCell(1).getStringCellValue().substring(0);
            String she = cells.getCell(2).getStringCellValue();
            String queyu = cells.getCell(3).getStringCellValue();
            String email = cells.getCell(4).getStringCellValue();
            shefe = shefe.substring(0, shefe.length() - 1);
            she = she.substring(0, she.length() - 1);
            queyu = queyu.substring(0, queyu.length() - 1);
            String citycode = PinYin4jUtils.hanziToPinyin(she, "");
            String[] headByString = PinYin4jUtils.getHeadByString(shefe + she + queyu);
            String shortcode = PinYin4jUtils.stringArrayToString(headByString);
            Area area = new Area();
            area.setCity(she);
            area.setCitycode(citycode);
            area.setDistrict(queyu);
            area.setPostcode(email);
            area.setShortcode(shortcode);
            area.setProvince(shefe);
            list.add(area);
        }
        areaService.saveImport(list);
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

    @Action("area_page")
    public String findPage() throws IOException {

        PageRequest pageable = new PageRequest(page - 1, rows);
        Page<Area> page = areaService.findPage(pageable);

        getPage(page, new String[]{"subareas"});
        return null;
    }
    private String q;

    public void setQ(String q) {
        this.q = q;
    }

    @Action("area_findAll")
    public String findAll() {
        List<Area> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(q)){
            list=areaService.findByQ(q);
        }else {
            list=areaService.findAll();
        }

        getList(list,new String[]{"subareas"});

        return null;
    }
}
