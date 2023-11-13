/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.service;


import id.co.ahm.ga.vms.app031.vo.Vms031VoMonitoring;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovInput;
import id.co.ahm.ga.vms.app031.vo.Vms031VoUploadFileBlaclist;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoPstUserCred;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public interface Vms031Service {
    
     public DtoResponseWorkspace showIdType(DtoParamPaging input);
     
     public DtoResponseWorkspace showHeadIdType(DtoParamPaging input);
     
     public DtoResponseWorkspace showDetailIdType(DtoParamPaging input);
     
     public DtoResponseWorkspace showStatus(DtoParamPaging input);
     
     public DtoResponseWorkspace showBlacklistType(DtoParamPaging input);
     
     public DtoResponseWorkspace showGender(DtoParamPaging input);
      
     public DtoResponseWorkspace submitblacklist(DtoParamPaging input, VoUserCred user);
     
//     public DtoResponseWorkspace submitPengunjung(DtoParamPaging input, VoUserCred user);
     
     public DtoResponseWorkspace submitDetail(DtoParamPaging input, VoUserCred user);
     
     public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input);
     
     ModelAndView downloadTemplate(String type);
     
     List<Vms031VoMonitoring> exportData(Map<String, Object> param);
     
     public DtoResponseWorkspace showPic(DtoParamPaging input);

     
//     DtoResponseWorkspace uploadBlacklist(Vms031VoUploadFileBlaclist param, VoPstUserCred user) throws Exception;
}
