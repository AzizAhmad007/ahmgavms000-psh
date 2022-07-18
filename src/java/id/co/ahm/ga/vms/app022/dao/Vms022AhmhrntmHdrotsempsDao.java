/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dao.HrDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author ayik.op
 */
public interface Vms022AhmhrntmHdrotsempsDao extends DefaultDao<AhmhrntmHdrotsemps, AhmhrntmHdrotsempsPk>{
    
    public List<Vms022VoMonitoring> getMonitoring(DtoParamPaging input, String userId, String type);
    
    public int countMonitoring(DtoParamPaging input, String userId);
    
    public List<Vms022VoMonitoring> getSearchData(DtoParamPaging input, String userId);
    
    public int countSearchData(DtoParamPaging input, String userId);
    
    public List<AhmhrntmHdrotsemps> findByNIKs(String nik);
    
    public int findByNIK(String nik);
    
    public List<AhmhrntmHdrotsemps> findByOtsId(String otsId);
    
    public List<AhmhrntmHdrotsemps> validateUpdate(String otsId, String nik);
    
    public List<Vms022VoMonitoring> getDataExcel(DtoParamPaging dto);
}
