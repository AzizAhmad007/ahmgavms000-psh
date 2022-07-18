/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.rest;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.ahm.ga.vms.app021.rest.view.Vms021ExportDataView;
import id.co.ahm.ga.vms.app021.service.Vms021Service;
import id.co.ahm.ga.vms.app021.vo.Vms021VoMonitoring;
import id.co.ahm.ga.vms.app021.vo.Vms021VoSubmit;
//import id.co.ahm.jx.uam.app000.service.UserUtilsService;
//import id.co.ahm.jx.uam.app000.vo.VoRole;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPstUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoPstUserCred;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ayik.op
 */
@RestController
@RequestMapping("/ga/vms021")
public class Vms021Rest {
    
    @Autowired
    @Qualifier(value = "vms021Service")
    private Vms021Service vms021Service;

    @Autowired
    @Qualifier(value = "tokenPstUtil")
    private TokenPstUtil tokenPstUtil;
    
//    @Autowired
//    @Qualifier("userUtilsService")
//    private UserUtilsService userUtilsService;
    
    public final static String pathServer = "D:\\Download\\img\\";
    
    //Lov Outsource
    @RequestMapping(value = "show-outsource", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePaging showOutsource(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);
        
        return vms021Service.showOutsource(input, userCred);
    }
    
    //Lov Company
    @RequestMapping(value = "show-company", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePaging showCompany(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);
        
        return vms021Service.showCompany(input, userCred);
    }
    
    //Lov Plant
    @RequestMapping(value = "show-plant", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showPlant(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token) {
        return vms021Service.showPlant();
    }
    
    @RequestMapping(value = "lov-plant", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePaging lovPlant(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);

        return vms021Service.lovPlant(input);
    }
    
    @RequestMapping(value = "lov-gate", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePaging lovGate(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);

        return vms021Service.lovGate(input);
    }
    
    //Lov Nrp
    @RequestMapping(value = "show-nrp", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePaging showNrp(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);
        
        return vms021Service.showNrp(input);
    }
    
    @RequestMapping(value = "show-vac-type", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace showVacType(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token) {
        return vms021Service.showVacType();
    }
    
    //Monitoring
    @RequestMapping(value = "monitoring", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse showMonitoring(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);
        
        return vms021Service.monitoring(input, userCred);
    }
    
    @RequestMapping(value = "show-user-login", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse showUserLogin(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token) {
        List<Map<String, Object>> creds = new ArrayList<>();
        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("type", userCred.getType());
        obj.put("partnerId", userCred.getPartnerid());
        obj.put("mdName", userCred.getPartnerName());
        //obj.put("userId", userCred.getUserid());
        //obj.put("userName", userCred.getUsername());
        if (userCred.getMdH1() != null && !userCred.getMdH1().isEmpty()) {
            obj.put("mdCode", userCred.getMdH1().get(0));
        } else if (userCred.getMdH2() != null && !userCred.getMdH2().isEmpty()) {
            obj.put("mdCode", userCred.getMdH2().get(0));
        } else if (userCred.getMdH3() != null && !userCred.getMdH3().isEmpty()) {
            obj.put("mdCode", userCred.getMdH3().get(0));
        } else {
            obj.put("mdCode", userCred.getPartnerid());
        }
        creds.add(obj);
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, creds);
    }
    
//    @RequestMapping(value = "check-user-role", method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    DtoResponse cekUserLogin(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token) {
//        List<Map<String, Object>> creds = new ArrayList<>();
//        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);
//        Map<String, Object> obj = new HashMap<String, Object>();
//        List<String> roles = new ArrayList<>();
//
//        List<VoRole> tempRoles = userUtilsService.getRolesByApplication("AHMGAVMS021", token);
//        if(!tempRoles.isEmpty()){
//            for(VoRole dt : tempRoles){
//                roles.add(dt.getRolename());
//            }
//        }
//        
//        String role = userUtilsService.getRolesByApplication("AHMGAVMS021", token).get(0).getRolename();
//        String tempRole = "";
//        if(roles.contains("RO_GAVMS_INREG") && roles.contains("RO_GAVMS_OUTREG")){
//            tempRole = "ALL";
//        } else if(roles.contains("RO_GAVMS_INREG")){
//            tempRole = "INTERNAL";
//        } else if(roles.contains("RO_GAVMS_OUTREG")) {
//            tempRole = "EXTERNAL";
//        } else {
//            tempRole = "";
//        }
//        obj.put("role", role);
//        creds.add(obj);
//        
//        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, creds);
//    }
            
    @RequestMapping(value = "export-excel", method = RequestMethod.GET)
    public ModelAndView exportTraining(
            @RequestParam(name = CommonConstant.JXID, required = true) String token, @RequestParam Map<String, Object> param) {
        Map<String, Object> src = new HashMap<>();
        DtoParamPaging input = new DtoParamPaging();
        input.setOrder((String) param.get("order"));
        input.setSort((String) param.get("sort"));
        input.setLimit(0);
        input.setOffset(0);
        param.remove("sort");
        param.remove("order");
        input.setSearch(param);

        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);

        DtoResponse res = this.vms021Service.getExportData(input, userCred);
        src.put("tx", res);
        src.put("user", userCred);
        src.put("input", param);
        src.put("type", "USER");
        return new ModelAndView(new Vms021ExportDataView(), "param", src);
    }

    @GetMapping(value = "download-template")
    public ModelAndView downloadTemplate(@RequestParam(name = CommonConstant.JXID, required = true) String token) {
        VoPstUserCred userCred = tokenPstUtil.getUserCred(token);
        return vms021Service.downloadTemplate(userCred);
    }
    
    @RequestMapping(value = "add-item", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace addItem(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestParam(name = "ktp") MultipartFile ktp, 
            @RequestParam(name = "photo") MultipartFile photo,
            @RequestParam(name = "bphoto") MultipartFile bphoto,
            @RequestParam(value = "vacFile") List<MultipartFile> fileVac,
            @RequestParam(value = "attFile") List<MultipartFile> fileAtt,
            @RequestParam("json") String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Vms021VoSubmit vo = mapper.readValue(json, Vms021VoSubmit.class);
        VoPstUserCred voPstUserCred = tokenPstUtil.getUserCred(token);
        
        vo.setbFileNamePhoto(bphoto.getBytes());
        return vms021Service.addItem(vo, ktp, photo, fileVac, fileAtt, voPstUserCred);
    }

    @RequestMapping(value = "update-item", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace updateItem(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestParam(name = "ktp") MultipartFile ktp,
            @RequestParam(name = "photo") MultipartFile photo,
            @RequestParam(name = "bphoto") MultipartFile bphoto,
            @RequestParam(value = "vacFile") List<MultipartFile> fileVac,
            @RequestParam(value = "attFile") List<MultipartFile> fileAtt,
            @RequestParam("json") String json) throws IOException{
        VoPstUserCred voPstUserCred = tokenPstUtil.getUserCred(token);
        ObjectMapper mapper = new ObjectMapper();
        Vms021VoSubmit vo = mapper.readValue(json, Vms021VoSubmit.class);
        if(!StringUtils.isBlank(bphoto.getOriginalFilename())){
            vo.setbFileNamePhoto(bphoto.getBytes());
        }
        
        return vms021Service.updateItem(vo, ktp, photo, fileVac, fileAtt, voPstUserCred);
    }

    @RequestMapping(value = "inactive-item", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace inActiveItems(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Vms021VoSubmit input) {
        VoPstUserCred voPstUserCred = tokenPstUtil.getUserCred(token);
        return vms021Service.inActiveData(input, voPstUserCred);
    }
    
    @RequestMapping(value = "upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoResponseWorkspace upload(@RequestHeader(name = CommonConstant.JXID) String token,
            @RequestParam(name = "excel") MultipartFile excel,
            @RequestParam(name = "fileKtp") MultipartFile fileKtp,
            @RequestParam(name = "filePhoto") MultipartFile filePhoto,
            @RequestParam(name = "fileVac") MultipartFile fileVac,
            @RequestParam(name = "fileAttch") MultipartFile fileAttch)throws IOException {
        //Set Param 
        //File Excel
        //File KTP Zip
        //File Photo Zip
        //File Cert Vac Zip
        //File Attch Zip
        return vms021Service.upload(excel,fileKtp,filePhoto,fileVac,fileAttch, tokenPstUtil.getUserCred(token));
    }
    
    @RequestMapping(value = "get-plants-gates", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPlantsGates(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Vms021VoSubmit input) {
        
        return vms021Service.getPlantsGates(input);
    }
    
    @RequestMapping(value = "get-pic-ahm", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPicAhm(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Vms021VoMonitoring input) {

        return vms021Service.getPicAhm(input);
    }
    
    @RequestMapping(value = "get-files", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getFiles(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Vms021VoSubmit input) {
        
        return vms021Service.getFiles(input);
    }
         
    @RequestMapping(value = "/get-image", method = RequestMethod.GET)
    public @ResponseBody
    byte[] getBackground(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token, @RequestParam Map<String, Object> param) throws IOException {
        String name = param.get("nameFoto") != null ? param.get("nameFoto").toString() : "";

        String filepath = pathServer + "pusbat.JPG";; //path + name + "." + ext;
        File file = new File(filepath);
        if (file.exists()) {
            FileInputStream in = new FileInputStream(file);
            return IOUtils.toByteArray(in);
        } else {
            throw new FileNotFoundException();
        }
    }
    
    //Export
    //Inactive
    //Upload
}
