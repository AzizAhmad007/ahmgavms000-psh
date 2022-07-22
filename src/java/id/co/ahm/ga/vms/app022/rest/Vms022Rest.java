/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.rest;

import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPshUtil;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author reza.mr
 */
@RestController
@RequestMapping("/ga/vms022")
public class Vms022Rest {

    @Autowired
    @Qualifier(value = "vms022Service")
    private Vms022Service vms022Service;

    @Autowired
    @Qualifier(value = "tokenPshUtil")
    private TokenPshUtil tokenPshUtil;

//    public final static String pathServer = "D:\\Download\\img\\";

    //Lov Outsource
//    @RequestMapping(value = "show-outsource", method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    DtoResponsePaging showOutsource(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
//            @RequestBody DtoParamPaging input) {
//        VoUserCred userCred = tokenPshUtil.getUserCred(token);
//
//        return vms022Service.showOutsource(input, userCred);
//    }

    //Lov Company
//    @RequestMapping(value = "show-company", method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    DtoResponsePaging showCompany(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
//            @RequestBody DtoParamPaging input) {
//        VoUserCred userCred = tokenPshUtil.getUserCred(token);
//
//        return vms022Service.showCompany(input, userCred);
//    }

    //Lov Plant
    @RequestMapping(value = "show-plant", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showPlant(@RequestHeader(value = "token", defaultValue = "") String token) {
        return vms022Service.showPlant();
    }

    @RequestMapping(value = "lov-plant", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePaging lovPlant(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token);

        return vms022Service.lovPlant(input);
    }

    @RequestMapping(value = "lov-gate", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePaging lovGate(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token);

        return vms022Service.lovGate(input);
    }

    //Monitoring
    @RequestMapping(value = "monitoring", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse showMonitoring(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token);

        return vms022Service.monitoring(input, userCred);
    }
    
    @RequestMapping(value = "approve", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace approving(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody List<Vms022VoMonitoring> input) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token);
        
        return vms022Service.approve(input, userCred);
    }
    
    @RequestMapping(value = "reject", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace rejecting(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody List<Vms022VoMonitoring> input) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token);
        
        return vms022Service.reject(input, userCred);
    }
    

    @RequestMapping(value = "get-roles-by-userid", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse getPlantByUserId(
            @RequestHeader(value = "token", defaultValue = "") String token) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        List<String> roles = user.getListRole();
        List<String> plants = new ArrayList<>();
        if (roles != null && !roles.isEmpty()) {
            for (String s : roles) {
                Pattern p = Pattern.compile("[A-Z_]+([0-9]+)");
                Matcher m = p.matcher(s);                
                
                plants.add("'" + ((m.matches() && m.groupCount() > 0)? m.group(1): s) + "'");
            }
        }
        return vms022Service.getRoleByUserLogin(String.join(",", plants), user);
    }

    @RequestMapping(value = "export-excel", method = RequestMethod.GET)
    public void ModelAndView(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = "token", defaultValue = "") String token,
            @RequestParam Map<String, Object> mapParam) {
        DtoParamPaging input = new DtoParamPaging();
        input.setSearch(mapParam);

        SimpleDateFormat fmt = new SimpleDateFormat("YYYYMMddHHmmss");
        String filename = "Verification Personal Data Partner & Oursource_" + fmt.format(new Date());
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=" + filename + ".xls");
        try {
            OutputStream out = response.getOutputStream();
            Workbook wb = vms022Service.exportToExcelMainData(input);
            wb.write(out);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
