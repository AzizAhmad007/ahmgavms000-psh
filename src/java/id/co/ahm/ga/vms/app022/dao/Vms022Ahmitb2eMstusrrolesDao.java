/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app022.model.Vms022Ahmitb2eMstusrroles;
import id.co.ahm.ga.vms.app022.model.Vms022Ahmitb2eMstusrrolesPk;
import id.co.ahm.ga.vms.app022.vo.Vms022VoFormAuthorization;
import id.co.ahm.jxf.dao.B2eDao;
import java.util.List;

/**
 *
 * @author reza.mr
 */
public interface Vms022Ahmitb2eMstusrrolesDao extends B2eDao<Vms022Ahmitb2eMstusrroles, Vms022Ahmitb2eMstusrrolesPk> {
    public List<Vms022VoFormAuthorization> getMainMenuData(String Userid);
}
