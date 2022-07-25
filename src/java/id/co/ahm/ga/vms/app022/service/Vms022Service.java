/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service;

import id.co.ahm.ga.vms.app022.vo.Vms022VoParam;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWaste;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWasteDetail;
import id.co.ahm.ga.vms.app022.vo.Vms022VoDetail;
import id.co.ahm.ga.vms.app022.vo.Vms022VoRegulation;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author developer
 */
public interface Vms022Service {

    DtoResponseWorkspace getRunningNumber(Vms022VoParam vms022VoParam);

    DtoResponsePagingWorkspace search(DtoParamPaging dtoParamPaging);

    DtoResponseWorkspace view(String wasteId);

    DtoResponseWorkspace edit(Vms022VoWaste vo, String userId);

    DtoResponseWorkspace add(Vms022VoWaste vo, List<Vms022VoWasteDetail> vos, String userId);

    DtoResponseWorkspace deactivate(String wasteId, String userid);

    DtoResponsePagingWorkspace searchDetail(DtoParamPaging dtoParamPaging);

    DtoResponseWorkspace viewDetail(DtoParamPaging dtoParamPaging);

    DtoResponseWorkspace editDetail(Vms022VoWasteDetail vo, String userId);

    DtoResponseWorkspace addDetail(List<Vms022VoWasteDetail> vo, String userId);

    DtoResponseWorkspace deleteDetail(String wasteId, Integer seq, String userId);

    DtoResponseWorkspace getWastedId(VoUserCred userCred);

    Workbook download(DtoParamPaging param);

    DtoResponseWorkspace validateUomByWasteId(String wasteId);

    DtoResponse getRoleByUserLogin(String plants, VoUserCred user);

    DtoResponseWorkspace addNewDetail(List<Vms022VoWasteDetail> vo, String userId);

    DtoResponseWorkspace getPlants(Vms022VoWaste vo, String userId);

    DtoResponseWorkspace getPlantTps(Vms022VoWaste vo, String userId);

    DtoResponseWorkspace regulations(Vms022VoRegulation vo, String userId);
    
    DtoResponseWorkspace validatePlantTps(Vms022VoDetail vo, String userId);

}
