/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.jxf.dao.HrDao;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author reza.mr
 */
public interface Vms022ObjectDao extends HrDao<Object, Serializable>{
    
    public List<String> getPlantsByUserId(String plants);
    
}
