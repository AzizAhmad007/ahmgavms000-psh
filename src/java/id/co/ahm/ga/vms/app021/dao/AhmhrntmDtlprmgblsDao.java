/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.dao;

import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlprmgbls;
import id.co.ahm.ga.vms.app021.vo.Vms021VoLov;
import id.co.ahm.jxf.dao.HrDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author ayik.op
 */
public interface AhmhrntmDtlprmgblsDao extends HrDao<AhmhrntmDtlprmgbls, String>{
    
    public List<Vms021VoLov> lovPlant(DtoParamPaging input, Boolean isMonitoring);
    
    public int countLovPlant(DtoParamPaging input);
    
    public int countLovOutsource(DtoParamPaging input, String type, String userId);
    
    public List<Vms021VoLov> lovOutsource(DtoParamPaging input, String type, String userId);
    
    public int countLovCompInternal(DtoParamPaging input);
    
    public List<Vms021VoLov> lovCompInternal(DtoParamPaging input);
    
    public List<Vms021VoLov> lovGate(DtoParamPaging input);
    
    public int countLovGate(DtoParamPaging input);
    
    public List<Vms021VoLov> lovVaccine(String type);
    
    public List<Vms021VoLov> getPlantsGates(String outId, String nik, String type);
}
