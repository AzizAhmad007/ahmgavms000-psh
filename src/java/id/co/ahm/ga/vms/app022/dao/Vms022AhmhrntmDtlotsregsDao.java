/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlotsregs;
import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlotsregsPk;
import id.co.ahm.jxf.dao.HrDao;
import java.util.List;

/**
 *
 * @author ayik.op
 */
public interface Vms022AhmhrntmDtlotsregsDao extends HrDao<AhmhrntmDtlotsregs, AhmhrntmDtlotsregsPk>{
    
    public int getLastSeq(String otsId, String nik, String regId);
    
    public List<String> getFileName(String otsId, String nik, String regId);
    
    public int getCount(String otsId, String nik, String regId);
    
    public List<String> getSeq(String otsId, String nik, String regId);
}
