/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.dao.impl;

import id.co.ahm.jxf.dao.HrHibernateDao;
import org.springframework.stereotype.Repository;
import id.co.ahm.ga.vms.app000.model.AhmhrntmMstuprnums;
import id.co.ahm.ga.vms.app000.model.AhmhrntmMstuprnumsPk;
import id.co.ahm.ga.vms.app021.dao.AhmhrntmMstuprnumsDao;

/**
 *
 * @author ayik.op
 */
@Repository("ahmhrntmMstuprnumsDao")
public class AhmhrntmMstuprnumsDaoImpl extends HrHibernateDao<AhmhrntmMstuprnums, AhmhrntmMstuprnumsPk> implements AhmhrntmMstuprnumsDao{
    
}
