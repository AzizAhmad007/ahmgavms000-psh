/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.rest;

import id.co.ahm.ga.vms.app034.service.Vms034Service;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPshUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kahfi
 */
@RestController
@RequestMapping("/ga/vms034")
public class Vms034Rest {
    
    @Autowired
    @Qualifier(value = "vms034Service")
    private Vms034Service vms034Service;
    
    @Autowired
    @Qualifier(value = "tokenPshUtil")
    private TokenPshUtil tokenPshUtil;
    
    @RequestMapping(value = "run-app", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String runApp(@RequestHeader(value = "token", defaultValue = "") String token) {
        Date dt = new Date();
        Timestamp time = new Timestamp(dt.getTime());
        return "Apps now running " + dt + " " + time;
    }
    
    @RequestMapping(value = "lov-visitortype", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getVisitorType(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms034Service.showVisitorType(input);
    }
    
    @RequestMapping(value = "lov-plant", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPlant(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms034Service.showPlant(input);
    }
    
    @RequestMapping(value = "lov-location", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getLocationSpecific(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms034Service.showLocation(input);
    }
    
    @RequestMapping(value = "lov-status", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getStatusCheck(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms034Service.showStatusCheck(input);
    }
    
    @RequestMapping(value = "lov-id-card", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getIdCard(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms034Service.showIdCardType(input);
    }
    
    @RequestMapping(value = "lov-idcard-filter", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getIdCardFilter(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms034Service.showIdCardTypeFilter(input);
    }
    
    @RequestMapping(value = "get-user-detail", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getUserDetail(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging dto) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        Map<String, Object> getDetail = new HashMap();
        getDetail.put("username", user.getUsername());
        getDetail.put("userid", user.getUserid());
        getDetail.put("email", user.getEmail());
        getDetail.put("domain", user.getDomain());
        getDetail.put("role", user.getListRole());

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, getDetail);
    }
    
    @RequestMapping(value = "check-in", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace submitCheckIn(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms034Service.submitCheckIn(input, user);
    }
    
    @RequestMapping(value = "check-out", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace submitCheckOut(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms034Service.submitCheckOut(input, user);
    }
    
    @RequestMapping(value = "monitoring", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePagingWorkspace getMonitoring(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms034Service.showMonitoring(input);
    }
}
