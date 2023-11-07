/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.dao.impl;

import id.co.ahm.ga.vms.app000.model.hr.FmhrdGeneralDatas;
import id.co.ahm.ga.vms.app030.dao.Vms030FmhrdGeneralDatasDao;
import id.co.ahm.jxf.dao.HrHibernateDao;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nurvan Afandi
 */
@Repository("vms030FmhrdGeneralDatasDao")
public class Vms030FmhrdGeneralDatasDaoImpl extends HrHibernateDao<FmhrdGeneralDatas, BigDecimal> implements Vms030FmhrdGeneralDatasDao {
    
}
