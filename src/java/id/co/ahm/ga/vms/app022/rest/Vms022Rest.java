/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.rest;

import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPshUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    //success
    @RequestMapping(value = "test-mamank", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePagingWorkspace searchMonitoring(@RequestHeader(value = "token", defaultValue = "") String token
            ) {
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, null, null, null, 0);
    }
    
    //not fix yet
    @RequestMapping(value = "get-roles-by-userid", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse getPlantByUserId(
            @RequestHeader(value = "token", defaultValue = "") String token) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        System.out.println("==== value dari vousercred = " + user);
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
    
    @RequestMapping(value = "show-plant", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showPlant(@RequestHeader(value = "token") String token) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms022Service.showPlant();
    }
    
    //success
    @RequestMapping(value = "monitoring", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showMonitoring(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging dto) {
        VoUserCred user = tokenPshUtil.getUserCred(token);

        return vms022Service.showMonitoring(dto);
    }
    
    //success
    @RequestMapping(value = "approve-single", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace approvingSingle(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoMonitoring getdata) {
        VoUserCred user = tokenPshUtil.getUserCred(token);

        return vms022Service.approve(getdata, user);
//        return "success";

    }
    
    //success
    @RequestMapping(value = "reject-single", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace rejectingSingle(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoMonitoring getdata) {
        VoUserCred user = tokenPshUtil.getUserCred(token);

        return vms022Service.reject(getdata, user);
    }
     
    @RequestMapping(value = "approve", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace approving(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody List<Vms022VoMonitoring> input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        
        return vms022Service.approving(input, user);
    }
    
    
    @RequestMapping(value = "reject", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace rejecting(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody List<Vms022VoMonitoring> getdata) {
        VoUserCred user = tokenPshUtil.getUserCred(token);

        return vms022Service.rejecting(getdata, user);
    }
    
}
