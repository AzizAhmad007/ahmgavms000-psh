/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service;

import id.co.ahm.ga.vms.app022.vo.Vms022VoFileAttachment;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.ga.vms.app022.vo.Vms022VoSubmit;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoPstUserCred;
import id.co.ahm.jxf.vo.VoUserCred;
import java.math.BigDecimal;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ayik.op
 */
public interface Vms022Service {
    
    public DtoResponseWorkspace showPlant();
    
//    public DtoResponsePaging showNrp(DtoParamPaging input);
    
//    public DtoResponsePaging showOutsource(DtoParamPaging input, VoUserCred user);
    
//    public DtoResponsePaging showCompany(DtoParamPaging input, VoUserCred user);
    
    public DtoResponsePaging monitoring(DtoParamPaging input, VoUserCred user);
    
    public DtoResponsePaging lovPlant(DtoParamPaging input);
    
    public DtoResponsePaging lovGate(DtoParamPaging input);
    
    public DtoResponseWorkspace showVacType();
    
    public DtoResponse getRoles(String username);
    
    public DtoResponseWorkspace approve(List<Vms022VoMonitoring> getdata, VoUserCred voPstUserCred);
    
    public DtoResponseWorkspace reject(List<Vms022VoMonitoring> getdata, VoUserCred voPstUserCred);
    
    Workbook exportToExcelMainData(DtoParamPaging dto);

    
}
