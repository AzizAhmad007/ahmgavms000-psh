/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsDtlvisits;
import id.co.ahm.ga.vms.app026.dao.Vms026AhmgavmsDtlvisitsDao;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kahfi
 */
@Repository("vms026AhmgavmsDtlvisitsDao")
public class Vms026AhmgavmsDtlvisitsDaoImpl extends DefaultHibernateDao<AhmgavmsDtlvisits, Integer> implements Vms026AhmgavmsDtlvisitsDao{
    
}
