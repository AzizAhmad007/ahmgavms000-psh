/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service;

import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.List;

/**
 *
 * @author reza.mr
 */
public interface Vms022Service {
    
    DtoResponse getRoleByUserLogin(String plants, VoUserCred user);
    
    DtoResponseWorkspace showPlant();
    
    DtoResponsePaging monitoring(DtoParamPaging input, VoUserCred userCred);
    
    DtoResponseWorkspace showMonitoring();
    
    DtoResponseWorkspace approve(Vms022VoMonitoring getdata, VoUserCred userCred);
    
    DtoResponseWorkspace approving(List<Vms022VoMonitoring> getdata, VoUserCred userCred);
    
    DtoResponseWorkspace reject(Vms022VoMonitoring getdata, VoUserCred userCred);
    
    DtoResponseWorkspace rejecting(List<Vms022VoMonitoring> getdata, VoUserCred userCred);

}
