/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.ahm.ga.vms.app031.constant.Vms031Constant;
import id.co.ahm.ga.vms.app031.rest.view.Vms031ExportExcel;
import id.co.ahm.ga.vms.app031.rest.view.Vms031ExportExcelError;
import id.co.ahm.ga.vms.app031.service.Vms031Service;
import id.co.ahm.ga.vms.app031.vo.Vms031VoError;
import id.co.ahm.ga.vms.app031.vo.Vms031VoMonitoring;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovInput;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPshUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



/**
 *
 * @author Ahmad Mukaram Aziz
 */
@RestController
@RequestMapping("/ga/vms031")
public class Vms031Rest {
    @Autowired
    @Qualifier(value = "vms031Service")
    private Vms031Service vms031Service;
    
    @Autowired
    @Qualifier(value = "tokenPshUtil")
    private TokenPshUtil tokenPshUtil;
    
    @RequestMapping(value = "test", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(@RequestHeader(value = "token", defaultValue = "") String token){
        return "run App";
    }
    
    @RequestMapping(value = "lov-idtype", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showIdType(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input){
        return vms031Service.showIdType(input);
    }
    
    @RequestMapping(value = "lov-head-idtype", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showHeadIdType(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input){
        return vms031Service.showHeadIdType(input);
    }
    
    @RequestMapping(value = "lov-detail-idtype", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showDetailIdType(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input){
        return vms031Service.showDetailIdType(input);
    }
    
    @RequestMapping(value = "lov-status", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showStatus(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input){
        return vms031Service.showStatus(input);
    }
    
    @RequestMapping(value = "lov-blacklisttype", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showBlacklistType(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input){
        return vms031Service.showBlacklistType(input);
    }
    
    @RequestMapping(value = "lov-gender", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showGender(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input){
        return vms031Service.showGender(input);
    }
    
    @RequestMapping(value = "save-blacklist", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace saveKaryawanBlacklist(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input){
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms031Service.submitblacklist(input, user);
    }
    
//    @RequestMapping(value = "save-pengunjung-blst", method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    DtoResponseWorkspace savePengujungBlacklist(@RequestHeader(value = "token", defaultValue = "") String token,
//            @RequestBody DtoParamPaging input){
//        VoUserCred user = tokenPshUtil.getUserCred(token);
//        return vms031Service.submitPengunjung(input, user);
//    }
    
    @RequestMapping(value = "save-dtlblacklist", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace dtlblacklist(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input){
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms031Service.submitDetail(input, user);
    }
    
     @RequestMapping(value = "monitoring", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePagingWorkspace getMonitoring(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms031Service.showMonitoring(input);
    }
    
    @RequestMapping(value = "download-template-blacklist", method = RequestMethod.GET)
    public ModelAndView downloadTemplateStock(@RequestParam(value = CommonConstant.JXID, defaultValue = "") String token) {
        return vms031Service.downloadTemplate(Vms031Constant.TEMPLATE_BLACKLIST);
    }
    
    @RequestMapping(value = "export-to-excel-error", method = RequestMethod.GET)
    public ModelAndView exportExcelError(@RequestHeader(value = "JXID", defaultValue = "", required = true) String token,
            @RequestParam Map<String, Object> paramListVo) throws IOException {
        List<Vms031VoError> list = new ObjectMapper().readValue((String) paramListVo.get("list"), new TypeReference<List<Vms031VoError>>() {
        });
        Map<String, Object> src = new HashMap<>();
        src.put("data", list);
        return new ModelAndView(new Vms031ExportExcelError(), "data", src);
    }
    
    @RequestMapping(value = "export-excel",
            method = RequestMethod.GET)
    public ModelAndView exportExcel(
            @RequestParam(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestParam Map<String, Object> params) {
            
            return new ModelAndView(new Vms031ExportExcel(),"tx",this.vms031Service.exportData(params));
           
    }
}
