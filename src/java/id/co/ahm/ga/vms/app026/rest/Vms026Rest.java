/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.rest;

import id.co.ahm.ga.vms.app026.service.Vms026Service;
import id.co.ahm.ga.vms.app026.util.Vms026DateTimeUtil;
import id.co.ahm.ga.vms.app026.util.Vms026ExportExcel;
import id.co.ahm.ga.vms.app026.vo.Vms026VoDeleteInvitation;
import id.co.ahm.ga.vms.app026.vo.Vms026VoDeleteVisitor;
import id.co.ahm.ga.vms.app026.vo.Vms026VoMonitoringOutput;
import id.co.ahm.ga.vms.app026.vo.Vms026VoSendEmail;
import id.co.ahm.ga.vms.app026.vo.Vms026VoSubmitChief;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPshUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.Date;
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
import org.apache.commons.lang3.StringUtils;


/**
 *
 * @author Kahfi
 */
@RestController
@RequestMapping("/ga/vms026")
public class Vms026Rest {
    
    //service
    @Autowired
    @Qualifier(value = "vms026Service")
    private Vms026Service vms026Service;
    
    //tokenPshUtil
    @Autowired
    @Qualifier(value = "tokenPshUtil")
    private TokenPshUtil tokenPshUtil;
    
    @RequestMapping(value = "run-app", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String runApp(@RequestHeader(value = "token", defaultValue = "") String token) {
        return "Apps now running";
    }
    
    @RequestMapping(value = "lov-visitortype", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getVisitorType(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms026Service.showVisitorType(input);
    }
    
    @RequestMapping(value = "lov-plant", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPlant(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms026Service.showPlant(input);
    }
    
    @RequestMapping(value = "lov-building", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getBuilding(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms026Service.showBuilding(input);
    }
    
    @RequestMapping(value = "lov-floor", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getFloor(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms026Service.showFloor(input);
    }
    
    @RequestMapping(value = "lov-location", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getLocationSpecific(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms026Service.showLocation(input);
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
        String noHp = vms026Service.getNoHpUser(user.getUserid());
        getDetail.put("noHp", noHp);
        getDetail.put("token", token.toString());

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, getDetail);
    }
    
    @RequestMapping(value = "monitoring", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePagingWorkspace getMonitoring(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms026Service.showMonitoring(input);
    }
    
    @RequestMapping(value = "monitoring-detail", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePagingWorkspace getMonitoringDetail(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms026Service.showMonitoringDetail(input);
    }
    
    @RequestMapping(value = "submit-invitation", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace submitInvitation(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms026Service.submitInvitation(input, user);
    }
    
    @RequestMapping(value = "save-invitation", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace saveInvitation(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms026Service.saveInvitation(input, user);
    }
    
    @RequestMapping(value = "submit-chief", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace submitChief(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody List<Vms026VoSubmitChief> input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms026Service.submitChief(input, user);
    }
    
    @RequestMapping(value = "lov-status", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getStatus(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms026Service.showStatus(input);
    }
    
    @RequestMapping(value = "get-masterno", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getMasterNo(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms026Service.getMasterNo(input);
    }
    
    @RequestMapping(value = "get-reqbody", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getReqBody(@RequestHeader(value = "token", defaultValue = "") String token) {
        return vms026Service.getReqBody();
    }
    
    @RequestMapping(value = "export-excel", method = RequestMethod.GET)
    public ModelAndView exportInvitation(@RequestParam(name = "token", defaultValue = "") String token,
            @RequestParam(name = "in") String masterNo,
            @RequestParam(name = "st") String status,
            @RequestParam(name = "vt") String visitorType,
            @RequestParam(name = "pl") String plant,
            @RequestParam(name = "ls") String locSpec,
            @RequestParam(name = "sd") String startDate,
            @RequestParam(name = "ed") String endDate,
            @RequestParam(name = "nm") String nameVisitor,
            @RequestParam(name = "cp") String company,
            @RequestParam(name = "pa") String picAhm,
            @RequestParam(name = "sortCol") String sortCol,
            @RequestParam(name = "sort") String sort) {

        VoUserCred user = tokenPshUtil.getUserCred(token);
        String nrp;
        if (!picAhm.toString().equalsIgnoreCase("")) {
            nrp = picAhm;
        } else if (user == null) {
            nrp = "DEVELOPER";
        } else {
            nrp = user.getUserid();
        }

        if (StringUtils.isNotEmpty(startDate)) {
            Date effectiveDateFrom = Vms026DateTimeUtil.stringToDate(startDate);
            startDate = Vms026DateTimeUtil.dateToString("dd-MMM-yyyy", effectiveDateFrom);
        }

        if (StringUtils.isNotEmpty(endDate)) {
            Date effectiveDateTo = Vms026DateTimeUtil.stringToDate(endDate);
            endDate = Vms026DateTimeUtil.dateToString("dd-MMM-yyyy", effectiveDateTo);
        }

        Map<String, Object> search = new HashMap<>();
        search.put("masterNo", masterNo);
        search.put("status", status);
        search.put("visitorType", visitorType);
        search.put("plant", plant);
        search.put("locSpec", locSpec);
        search.put("startDate", startDate);
        search.put("endDate", endDate);
        search.put("company", company);
        search.put("nrppic", nrp);
        search.put("visitorName", nameVisitor);

        DtoParamPaging dtoParam = new DtoParamPaging();
        dtoParam.setOffset(0);
        dtoParam.setLimit(0);
        dtoParam.setSort(sortCol);
        dtoParam.setSearch(search);
        dtoParam.setOrder(sort);

        DtoResponseWorkspace dtoResponseWorkspace = vms026Service.getExcel(dtoParam);
        List<Vms026VoMonitoringOutput> vms026VoMonitor = (List<Vms026VoMonitoringOutput>) dtoResponseWorkspace.getData();
        ModelAndView modelAndView = new ModelAndView(new Vms026ExportExcel());
        modelAndView.addObject("dtoParam", dtoParam);
        modelAndView.addObject("data", vms026VoMonitor);

        return modelAndView;
    }
    
    @RequestMapping(value = "delete-invitation", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace deleteInvitation(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody List<Vms026VoDeleteInvitation> input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms026Service.deleteInvitation(input, token);
    }
    
    @RequestMapping(value = "delete-visitor", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace deleteVisitor(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody List<Vms026VoDeleteVisitor> input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms026Service.deleteVisitor(input, token);
    }
    
    @RequestMapping(value = "send-email", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace sendEmail(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody List<Vms026VoSendEmail> input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms026Service.sendEmail(input, user);
    }
}
