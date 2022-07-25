package id.co.ahm.ga.vms.app022.rest;

import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.vo.Vms022VoParam;
import id.co.ahm.ga.vms.app022.vo.Vms022VoRegulation;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWaste;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWasteDetail;
import id.co.ahm.ga.vms.app022.vo.Vms022VoDetail;
import id.co.ahm.jx.constant.AppEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPshUtil;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

/**
 *
 * @author developer
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
        
    @RequestMapping(value = "getrunnum", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getRunningNumber(@RequestHeader(value = "token", defaultValue = "") String token, @RequestBody Vms022VoParam ers007VoParam) {
        return vms022Service.getRunningNumber(ers007VoParam);
    }
    
    @RequestMapping(value = "search", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePagingWorkspace search(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody DtoParamPaging dtoParam) {
        return vms022Service.search(dtoParam);
    }
    
    @RequestMapping(value = "view", method = {RequestMethod.POST, RequestMethod.GET},       
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace view(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestParam(name = "wasteId", required = true) String wasteId) {
        return vms022Service.view(wasteId);
    }
    
    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace add(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoWaste vo) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token);      
        return vms022Service.add(vo,vo.getDetail(), (userCred!=null) ? userCred.getUserid() : "");
    }
    
    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace edit(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoWaste vo) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token);      
        return vms022Service.edit(vo, (userCred!=null) ? userCred.getUserid():"");
    }
    
    @RequestMapping(value = "deactivate", method = {RequestMethod.POST, RequestMethod.GET},       
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace deactivate(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestParam(name = "wasteId", required = true) String wasteId) {
        VoUserCred user = tokenPshUtil.getUserCred(token);
        return vms022Service.deactivate(wasteId, (user!=null) ? user.getUserid():"");
    }
    
    @RequestMapping(value = "search-detail", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponsePagingWorkspace searchDetail(@RequestHeader(value = "token", defaultValue = "") String token,
             @RequestBody DtoParamPaging dtoParam) {
        return vms022Service.searchDetail(dtoParam);
    }
    
    @RequestMapping(value = "view-detail",  method = RequestMethod.POST,       
            consumes = (MediaType.APPLICATION_JSON_VALUE),
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace viewDetail(@RequestHeader(value = "token", defaultValue = "") String token, 
            @RequestBody DtoParamPaging dtoParam) {
        
        return vms022Service.viewDetail(dtoParam);
    }
    
    @RequestMapping(value = "delete-detail",   method = {RequestMethod.POST, RequestMethod.GET},          
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace deleteDetail(
            @RequestHeader(value = "token", defaultValue = "") String token, 
            @RequestParam(name = "wasteId", required = true) String wasteId,  @RequestParam(name = "seq", required = true) Integer seq) {  
        VoUserCred user = tokenPshUtil.getUserCred(token);
        String userid = (user!=null) ? user.getUserid(): "";
        return vms022Service.deleteDetail(wasteId, seq, userid);
    }
    
    @RequestMapping(value = "add-detail", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace addDetail(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody List<Vms022VoWasteDetail> vo) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token); 
        String userid = (userCred!=null) ? userCred.getUserid(): "";
        return vms022Service.addDetail(vo, userid);
    }
    
    @RequestMapping(value = "edit-detail", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace editDetail(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoWasteDetail vo) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token); 
        String userid = (userCred!=null) ? userCred.getUserid(): "";
        return vms022Service.editDetail(vo, userid);
    }
    
    @RequestMapping(value = "download", 
            method = {RequestMethod.POST, RequestMethod.GET})
    public void download(HttpServletRequest request,
            HttpServletResponse response,
            @RequestHeader(value = "token", defaultValue = "") String token,
            @RequestParam Map<String, Object> vo
    ) throws IOException 
    {
        DtoParamPaging param = new DtoParamPaging();
        param.setOffset(0);
        param.setLimit(0);
        if (vo.get("sort") != null) {
             param.setSort((String) vo.get("sort"));
        }
        if (vo.get("order") != null) {
             param.setOrder((String) vo.get("order"));
        }
        
        param.setSearch(vo);
            
        Workbook wb = vms022Service.download(param);     
        
        OutputStream out = response.getOutputStream();
        String filename = (String)param.getSearch().get(AppEnum.TEMPLATE_NAME.getValue());
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
       
            String[] exts = filename.split("\\.");
            String ext = exts[exts.length-1];
            filename = filename.substring(0, filename.length()-5);            
            response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(filename+"_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"."+ext)); 
                             
        wb.write(out);
        response.flushBuffer();
    }
    
    @RequestMapping(value = "get-wasted-id", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getWastedId(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoWaste vms022VoWaste) {
        VoUserCred userCred = tokenPshUtil.getUserCred(token);
        
        return vms022Service.getWastedId(userCred);
    }
    
    @RequestMapping(value = "get-uom", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getUomByWasteId(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoWaste vms022VoWaste) {        
        return vms022Service.validateUomByWasteId(vms022VoWaste.getWasteId());
    }
    
    @RequestMapping(value = "val-plants", method = RequestMethod.POST, 
               consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getPlants(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoWaste vms022VoWaste) {        
        VoUserCred u = tokenPshUtil.getUserCred(token); 
        String userid = (u!=null) ?  u.getUserid(): "";       
        return vms022Service.getPlants(vms022VoWaste, userid);
    }

    @RequestMapping(value = "val-tps", method = RequestMethod.POST, 
               consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace getTps(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoWaste vms022VoWaste) {        
         VoUserCred u = tokenPshUtil.getUserCred(token); 
        String userid = (u!=null) ?  u.getUserid(): "";
        return vms022Service.getPlantTps(vms022VoWaste, userid);
    }
    
    @RequestMapping(value = "validate-plant-tps", method = RequestMethod.POST, 
               consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody 
    DtoResponseWorkspace validatePlantTps(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoDetail vo) {        
        VoUserCred u = tokenPshUtil.getUserCred(token); 
        String userid = (u!=null) ?  u.getUserid(): "";  
        return vms022Service.validatePlantTps(vo, userid);
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
    
    @RequestMapping(value = "regulations", method = RequestMethod.POST, 
               consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseWorkspace regulations(@RequestHeader(value = "token", defaultValue = "") String token,
            @RequestBody Vms022VoRegulation vms022VoRegulation) {   
        VoUserCred u = tokenPshUtil.getUserCred(token); 
        String userid = (u!=null) ?  u.getUserid(): "";
        return vms022Service.regulations(vms022VoRegulation, userid);
    }
    
}
