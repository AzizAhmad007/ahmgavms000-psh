/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service;

import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author reza.mr
 */
public interface Vms022Service {
    
    DtoResponseWorkspace getFormAuthorization(VoUserCred userCred);
    
    DtoResponse getRoleByUserLogin(String plants, VoUserCred user);
    
    DtoResponseWorkspace showPlant();
    
    DtoResponsePaging monitoring(DtoParamPaging input, VoUserCred userCred);
    
    DtoResponseWorkspace showMonitoring(DtoParamPaging input);
    
    DtoResponseWorkspace showPlant(Vms022VoLov input);
    
    DtoResponseWorkspace showGate(Vms022VoLov input);
    
    DtoResponseWorkspace approve(Vms022VoMonitoring getdata, VoUserCred userCred);
    
    DtoResponseWorkspace approving(List<Vms022VoMonitoring> getdata, VoUserCred userCred);
    
    DtoResponseWorkspace reject(Vms022VoMonitoring getdata, VoUserCred userCred);
    
    DtoResponseWorkspace rejecting(List<Vms022VoMonitoring> getdata, VoUserCred userCred);

    Workbook exportToExcelMainData(DtoParamPaging dto);
    
    DtoResponseWorkspace getExcel(DtoParamPaging dto);
}
