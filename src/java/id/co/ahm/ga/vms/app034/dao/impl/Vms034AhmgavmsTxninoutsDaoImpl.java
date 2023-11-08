/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsTxninouts;
import id.co.ahm.ga.vms.app034.dao.Vms034AhmgavmsTxninoutsDao;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kahfi
 */
@Repository("vms034AhmgavmsTxninoutsDao")
public class Vms034AhmgavmsTxninoutsDaoImpl extends DefaultHibernateDao<AhmgavmsTxninouts, BigDecimal> implements Vms034AhmgavmsTxninoutsDao{
    
}
