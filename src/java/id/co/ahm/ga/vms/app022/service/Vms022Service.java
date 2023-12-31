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
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author reza.mr
 */
public interface Vms022Service {

    public DtoResponseWorkspace getFormAuthorization(VoUserCred userCred);

    public DtoResponseWorkspace showPlant(DtoParamPaging dto, VoUserCred userCred);

    public DtoResponseWorkspace showMonitoring(DtoParamPaging input, VoUserCred userCred);

    public DtoResponseWorkspace showPlant(Vms022VoLov input);

    public DtoResponseWorkspace showGate(Vms022VoLov input);

    public DtoResponseWorkspace showPicAhm(Vms022VoLov input);

    public DtoResponseWorkspace approve(Vms022VoMonitoring getdata, VoUserCred userCred);

    public DtoResponseWorkspace approving(List<Vms022VoMonitoring> getdata, VoUserCred userCred);

    public DtoResponseWorkspace reject(Vms022VoMonitoring getdata, VoUserCred userCred);

    public DtoResponseWorkspace rejecting(List<Vms022VoMonitoring> getdata, VoUserCred userCred);

    public DtoResponseWorkspace checkingDate(List<Vms022VoMonitoring> getdata, VoUserCred userCred);

    public DtoResponsePagingWorkspace getExcel(DtoParamPaging dto);

    public DtoResponse testing(DtoParamPaging dto);

    public Workbook exportData(DtoParamPaging dto);

}
