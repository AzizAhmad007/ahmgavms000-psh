/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.dao;

import id.co.ahm.ga.vms.app000.model.AhmgavmsMstvisitors;
import id.co.ahm.jxf.dao.DefaultDao;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author kahfi
 */
public interface Vms034AhmgavmsMstvisitorsDao extends DefaultDao<AhmgavmsMstvisitors, BigDecimal>{

    public Map<String, Object> checkVisitorExist(String nik);
    
}
