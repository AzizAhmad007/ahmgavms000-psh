/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.AhmhrntmMstpicots;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.jxf.dao.HrDao;
import java.util.List;

/**
 *
 * @author reza.mr
 */
public interface Vms022AhmhrntmMstpicotsDao extends HrDao<AhmhrntmMstpicots, String>{
    
    public List<Vms022VoLov> getPicAhm(String outType, String area);
    
    String getPicAhmForExcel(String outType, String area);
    
    Boolean isPicAvailable(String userId, String area, String type);
    
    String getPicAreaType(String nrp);
    
}
