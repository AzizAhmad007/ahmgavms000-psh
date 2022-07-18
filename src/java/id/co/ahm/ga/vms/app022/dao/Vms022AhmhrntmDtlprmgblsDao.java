/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlprmgbls;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.jxf.dao.HrDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author ayik.op
 */
public interface Vms022AhmhrntmDtlprmgblsDao extends HrDao<AhmhrntmDtlprmgbls, String>{
    
    public List<Vms022VoLov> lovPlant(DtoParamPaging input, Boolean isMonitoring);
    
    public int countLovPlant(DtoParamPaging input);
    
    public int countLovOutsource(DtoParamPaging input, String type, String userId);
    
    public List<Vms022VoLov> lovOutsource(DtoParamPaging input, String type, String userId);
    
    public int countLovCompInternal(DtoParamPaging input);
    
    public List<Vms022VoLov> lovCompInternal(DtoParamPaging input);
    
    public List<Vms022VoLov> lovGate(DtoParamPaging input);
    
    public int countLovGate(DtoParamPaging input);
    
    public List<Vms022VoLov> lovVaccine(String type);
    
    public List<Vms022VoLov> getPlantsGates(String outId, String nik, String type);
}
