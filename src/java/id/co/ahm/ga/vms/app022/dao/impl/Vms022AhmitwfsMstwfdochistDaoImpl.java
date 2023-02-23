/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.wfs.AhmitwfsMstwfdochist;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmitwfsMstwfdochistDao;
import id.co.ahm.jxf.dao.WfsHibernateDao;
import java.io.Serializable;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022AhmitwfsMstwfdochistDao")
public class Vms022AhmitwfsMstwfdochistDaoImpl extends WfsHibernateDao<AhmitwfsMstwfdochist, Serializable> implements Vms022AhmitwfsMstwfdochistDao {

    @Override
    public Boolean generateHistory(String seq, String username, String outId, String wflow, String hist) {
        try {

            java.util.Date datenow = new java.util.Date();
            int yearnow = datenow.getYear() + 1900;
            int monthnow = datenow.getMonth() + 1;
            int daynow = datenow.getDay();

            SQLQuery q = getCurrentSession().createSQLQuery("IN"
                    + "SERT INTO AHMITWFS_MSTWFDOCHIST (VWFGUID, VHISTID, VTASKID, VEVENTTYPE, VTASKRESULT, VNOTE, VDOCID, VCREA, DCREA)"
                    + "values("
                    + "\'" + wflow + "\',"
                    + "\'" + hist + "\',"
                    + "\'AHMGAVMS022\',"
                    + "\'WAITING_FOR_VERIFICATION\',"
                    + "\'Waiting For Verification\',"
                    + "\'Waiting For Verification\',"
                    + "\'" + outId + "\',"
                    + "\'" + username + "\',"
                    + "\'" + monthnow + "-" + daynow + "-" + yearnow + "\'"
                    + ")");
            q.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
