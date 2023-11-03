/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.dao.impl;

import id.co.ahm.ga.vms.app000.model.hr.FmhrdGeneralDatas;
import id.co.ahm.ga.vms.app026.dao.Vms026FmhrdGeneralDatasDao;
import id.co.ahm.jxf.dao.HrHibernateDao;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kahfi
 */
@Repository("vms026FmhrdGeneralDatasDao")
public class Vms026FmhrdGeneralDatasDaoImpl extends HrHibernateDao<FmhrdGeneralDatas, BigDecimal> implements Vms026FmhrdGeneralDatasDao{
    
}
