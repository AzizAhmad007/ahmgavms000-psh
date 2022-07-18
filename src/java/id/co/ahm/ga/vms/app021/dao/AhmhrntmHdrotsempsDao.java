/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.dao;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app021.vo.Vms021VoMonitoring;
import id.co.ahm.jxf.dao.HrDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author ayik.op
 */
public interface AhmhrntmHdrotsempsDao extends HrDao<AhmhrntmHdrotsemps, String>{
    
    public List<Vms021VoMonitoring> getMonitoring(DtoParamPaging input, String userId, String type);
    
    public int countMonitoring(DtoParamPaging input, String userId);
    
    public List<AhmhrntmHdrotsemps> findByNIKs(String nik);
    
    public int findByNIK(String nik);
    
    public List<AhmhrntmHdrotsemps> findByOtsId(String otsId);
    
    public List<AhmhrntmHdrotsemps> validateUpdate(String otsId, String nik);
}
