/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.rest;

import id.co.ahm.ga.vms.app030.service.Vms030Service;
import id.co.ahm.ga.vms.app030.util.Vms030DateTimeUtil;
import id.co.ahm.ga.vms.app030.util.Vms030ExportExcel;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPic;
import id.co.ahm.ga.vms.app030.vo.Vms030VoTableResult;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPshUtil;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Nurvan Afandi
 */

@Controller
@RequestMapping("/ga/vms030")
public class Vms030Rest {
    
    @Autowired
    @Qualifier(value = "tokenPshUtil")
    private TokenPshUtil tokenPshUtil;

    @Autowired
    @Qualifier(value = "vms030Service")
    private Vms030Service vms030Service;
    
    @RequestMapping(value = "test-app", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String testApp(@RequestHeader(value = "token", defaultValue = "") String token
    ) {
        return "Now Testing App!";
    }
    
    @RequestMapping(value = "lov-plant", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPlant(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms030Service.showPlant(input);
    }
    
    @RequestMapping(value = "lov-visitortype", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getVisitorType(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms030Service.showVisitorType(input);
    }
    
    @RequestMapping(value = "lov-status", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getStatus(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms030Service.showStatus(input);
    }
    
    @RequestMapping(value = "lov-doctype", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getDocType(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms030Service.showDocType(input);
    }
    
    @RequestMapping(value = "lov-plant-form", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPlantForm(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms030Service.showPlantForm(input);
    }
    
    @RequestMapping(value = "lov-visitortype-form", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getVisitorForm(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms030Service.showStatusForm(input);
    }
    
    @RequestMapping(value = "lov-status-form", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getStatusForm(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms030Service.showStatus(input);
    }
    
    @RequestMapping(value = "show-pic", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPic(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms030Service.showPic(input);
    }
    
    @RequestMapping(value = "show-table", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePagingWorkspace showTable(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms030Service.showTable(input);
    }
    
    @RequestMapping(value = "save-reference", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace saveReference(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms030Service.saveReference(input, user);
    }
    
    @RequestMapping(value = "submit-reference", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace submitReference(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms030Service.submitReference(input, user);
    }
    
    @RequestMapping(value = "export-excel", method = RequestMethod.GET)
    public ModelAndView exportReference(@RequestParam(name = "token", defaultValue = "") String token,
            @RequestParam(name = "vt") String visitorType,
            @RequestParam(name = "st") String status,
            @RequestParam(name = "nd") String noDoc,
            @RequestParam(name = "wd") String workDesc,
            @RequestParam(name = "pl") String plant,
            @RequestParam(name = "cp") String company,
            @RequestParam(name = "np") String nrp,
            @RequestParam(name = "em") String email,
            @RequestParam(name = "sd") String dateStartText,
            @RequestParam(name = "ed") String dateEndText,
            @RequestParam(name = "sortCol") String sortCol,
            @RequestParam(name = "sort") String sort) {
        
        VoUserCred user = tokenPshUtil.getUserCred(token);
        String userId;
        if (user == null) {
            userId = "DEVELOPER";
        } else {
            userId = user.getUserid();
        }
        
        if (StringUtils.isNotEmpty(dateStartText)) {
            Date effectiveDateFrom = Vms030DateTimeUtil.stringToDate(dateStartText);
            dateStartText = Vms030DateTimeUtil.dateToString("dd-MMM-yyyy", effectiveDateFrom);
        }

        if (StringUtils.isNotEmpty(dateEndText)) {
            Date effectiveDateTo = Vms030DateTimeUtil.stringToDate(dateEndText);
            dateEndText = Vms030DateTimeUtil.dateToString("dd-MMM-yyyy", effectiveDateTo);
        }
        
        Map<String, Object> search = new HashMap<>();
        search.put("visitorType", visitorType);
        search.put("status", status);
        search.put("noDoc", noDoc);
        search.put("workDesc", workDesc);
        search.put("plant", plant);
        search.put("company", company);
        search.put("nrp", nrp);
        search.put("email", email);
        search.put("dateStart", dateStartText);
        search.put("dateEnd", dateEndText);


        DtoParamPaging dtoParam = new DtoParamPaging();
        dtoParam.setOffset(0);
        dtoParam.setLimit(0);
        dtoParam.setSort(sortCol);
        dtoParam.setSearch(search);
        dtoParam.setOrder(sort);
        
        DtoResponseWorkspace dtoResponseWorkspace = vms030Service.getExcel(dtoParam);
        List<Vms030VoTableResult> vms030VoTableResult = (List<Vms030VoTableResult>) dtoResponseWorkspace.getData();
        ModelAndView modelAndView = new ModelAndView(new Vms030ExportExcel());
        modelAndView.addObject("dtoParam", dtoParam);
        modelAndView.addObject("data", vms030VoTableResult);
        
        return modelAndView;
    }
             
}
