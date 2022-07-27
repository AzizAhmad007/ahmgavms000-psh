/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmMstpicots;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmMstpicotsDao;
import id.co.ahm.jxf.dao.HrHibernateDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022ahmhrntmMstpicotsDao")
public class Vms022AhmhrntmMstpicotsDaoImpl extends HrHibernateDao<AhmhrntmMstpicots, String> implements Vms022AhmhrntmMstpicotsDao{

}
