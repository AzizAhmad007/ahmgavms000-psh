/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app032.rest;

import id.co.ahm.ga.vms.app032.service.Vms032Service;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPshUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
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
/**
 *
 * @author Hitoshi Mario Naga M
 */
@RestController
@RequestMapping("/ga/vms032")
public class Vms032Rest {
    
    //service
    @Autowired
    @Qualifier(value = "vms032Service")
    private Vms032Service vms032Service;
//    
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
    
    @RequestMapping(value = "dropdown-status", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getstatusDec(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms032Service.showStatus(input);
    }
    
    @RequestMapping(value = "dropdown-tipe", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getTypeDec(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms032Service.showType(input);
    }
    
    @RequestMapping(value = "dropdown-doc", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getTypeDoc(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms032Service.showDocType(input);
    }
    
//    @RequestMapping(value = "upload-test", method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    Vms032VoFileDtl uploadTest(@RequestHeader(value = "token", defaultValue = "") String token,
//            @RequestParam(name = "vPath", defaultValue = "") String pathServer,
//            @RequestParam(name = "vFile", defaultValue = "") MultipartFile fileToUpload,
//            @RequestParam(name = "vType", defaultValue = "") String tipeFile) {
//        
//        return vms032Service.uploadFileToServer(pathServer, fileToUpload, tipeFile);
//    }

    
    @RequestMapping(value = "submit-Declaration", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace submitDeclaration(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms032Service.submitDeclarationMaster(input, user);
    }
    
    @RequestMapping(value = "delete-Declaration", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getInvitation(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms032Service.deleteDeclaration(input);
    }
    @RequestMapping(value = "monitoring", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePagingWorkspace getMonitoring(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return vms032Service.showMonitoring(input);
    }
    @RequestMapping(value = "draft", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace draft(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms032Service.draftDeclaration(input, user);
    }
}
