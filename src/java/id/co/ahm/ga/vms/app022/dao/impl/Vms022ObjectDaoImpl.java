/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.jxf.dao.HrHibernateDao;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayik.op
 */
@Repository("vms022ObjectDao")
public class Vms022ObjectDaoImpl extends HrHibernateDao<Object, Serializable> implements Vms022ObjectDao{
}
