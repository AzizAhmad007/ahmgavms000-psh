/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsLogemails;
import id.co.ahm.ga.vms.app026.dao.Vms026AhmgavmsLogemailsDao;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fsant
 */
@Repository("vms026AhmgavmsLogemailsDao")
public class Vms026AhmgavmsLogemailsDaoImpl extends DefaultHibernateDao<AhmgavmsLogemails, Integer> implements Vms026AhmgavmsLogemailsDao{
    
}
