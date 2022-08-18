/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.rest;

import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.util.Vms022DateTimeUtil;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.ga.vms.app022.util.Vms022ExportExcel;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPshUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
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

    @RequestMapping(value = "getformauth", method = RequestMethod.POST, 
            consumes = {MediaType.APPLICATION_JSON_VALUE}, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getFormAuthorization(@RequestHeader(value = "token", defaultValue = "") String token) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token);

        return vms022Service.getFormAuthorization(userCred);
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

                plants.add("'" + ((m.matches() && m.groupCount() > 0) ? m.group(1) : s) + "'");
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
    
    //coba manggil getExcel
        @RequestMapping(value = "get-excel", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getExcel(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging dto) {
        VoUserCred user = tokenPshUtil.getUserCred(token);

        return vms022Service.getExcel(dto);
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

    @RequestMapping(value = "export-excel", method = RequestMethod.GET)
    public void ModelAndView(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam Map<String, Object> mapParam) {
        DtoParamPaging input = new DtoParamPaging();
        input.setSearch(mapParam);

        SimpleDateFormat fmt = new SimpleDateFormat("YYYYMMddHHmmss");
        String filename = "Verification Personal Data Partner & Outsource_" + fmt.format(new Date());
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

    @RequestMapping(value = "get-plants", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPlants(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoLov input) {

        return vms022Service.showPlant(input);
    }

    @RequestMapping(value = "get-gates", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getGates(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoLov input) {

        return vms022Service.showGate(input);
    }

    @RequestMapping(value = "get-pic-ahm", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPicAhm(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoLov input) {

        return vms022Service.showPicAhm(input);
    }

//    @RequestMapping(value = "download", 
//            method = {RequestMethod.POST, RequestMethod.GET})
//    public void download(HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestHeader(value = "token", defaultValue = "") String token,
//            @RequestParam Map<String, Object> vo
//    ) throws IOException 
//    {
//        DtoParamPaging param = new DtoParamPaging();
//        param.setOffset(0);
//        param.setLimit(0);
//        if (vo.get("sort") != null) {
//             param.setSort((String) vo.get("sort"));
//        }
//        if (vo.get("order") != null) {
//             param.setOrder((String) vo.get("order"));
//        }
//        
//        param.setSearch(vo);
//            
//        Workbook wb = vms022Service.download(param);     
//        
//        OutputStream out = response.getOutputStream();
//        String filename = (String)param.getSearch().get(AppEnum.TEMPLATE_NAME.getValue());
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//       
//            String[] exts = filename.split("\\.");
//            String ext = exts[exts.length-1];
//            filename = filename.substring(0, filename.length()-5);            
//            response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(filename+"_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"."+ext)); 
//                             
//        wb.write(out);
//        response.flushBuffer();
//    }
    @RequestMapping(value = "exreg", method = RequestMethod.POST)
    public ModelAndView exportRegulation(@RequestParam(name = "token", defaultValue = "") String token,
            @RequestParam(name = "oi") String outId,
            @RequestParam(name = "on") String outName,
            @RequestParam(name = "n") String persId,
            @RequestParam(name = "pf") String beginDateText,
            @RequestParam(name = "pt") String endDateText,
            @RequestParam(name = "pcn") String passNumber,
            @RequestParam(name = "pa") String pic,
            @RequestParam(name = "ot") String outType,
            @RequestParam(name = "oc") String company,
            @RequestParam(name = "os") String outStatus,
            @RequestParam(name = "p") String areaName,
            @RequestParam(name = "c19vs") String vacStatus) {

        if (StringUtils.isNotEmpty(beginDateText)) {
            Date effectiveDateFrom = Vms022DateTimeUtil.stringToDate(beginDateText);
            beginDateText = Vms022DateTimeUtil.dateToString("dd-MMM-yyyy", effectiveDateFrom);
        }

        if (StringUtils.isNotEmpty(endDateText)) {
            Date effectiveDateTo = Vms022DateTimeUtil.stringToDate(endDateText);
            endDateText = Vms022DateTimeUtil.dateToString("dd-MMM-yyyy", effectiveDateTo);
        }

        Map<String, Object> search = new HashMap<>();
        search.put("outId", outId);
        search.put("outName", outName);
        search.put("persId", persId);
        search.put("beginDateText", beginDateText);
        search.put("endDateText", endDateText);
        search.put("passNumber", passNumber);
        search.put("pic", pic);
        search.put("outType", outType);
        search.put("company", company);
        search.put("outStatus", outStatus);
        search.put("areaName", areaName);
        search.put("vacStatus", vacStatus);

        DtoParamPaging dtoParam = new DtoParamPaging();
        dtoParam.setOffset(-1);
        dtoParam.setLimit(-1);
        dtoParam.setSort(null);
        dtoParam.setSearch(search);
        dtoParam.setOrder("");

        DtoResponseWorkspace dtoResponseWorkspace = vms022Service.getExcel(dtoParam);
        List<Vms022VoMonitoring> vms022VoMonitoring = (List<Vms022VoMonitoring>) dtoResponseWorkspace.getData();
        System.out.println("============ value of vms022VoMonitoring = " + vms022VoMonitoring);

        ModelAndView modelAndView = new ModelAndView(new Vms022ExportExcel());
        modelAndView.addObject("dtoParam", dtoParam);
        modelAndView.addObject("data", vms022VoMonitoring);

        return modelAndView;
    }

}
