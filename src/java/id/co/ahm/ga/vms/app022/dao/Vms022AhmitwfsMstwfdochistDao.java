/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.wfs.AhmitwfsMstwfdochist;
import id.co.ahm.jxf.dao.WfsDao;
import java.io.Serializable;

/**
 *
 * @author reza.mr
 */
public interface Vms022AhmitwfsMstwfdochistDao extends WfsDao<AhmitwfsMstwfdochist, Serializable>{
    Boolean generateHistory (String seq, String username, String outId);
}
