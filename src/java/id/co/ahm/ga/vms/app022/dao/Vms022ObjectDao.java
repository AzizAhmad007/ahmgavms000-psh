/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.jxf.dao.DefaultDao;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ayik.op
 */
public interface Vms022ObjectDao extends DefaultDao<Object, Serializable>{
    
    public List<String> getPlantsByUserId(String plants);
}
