/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmMstpicots;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmMstpicotsDao;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.jxf.dao.HrHibernateDao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022AhmhrntmMstpicotsDao")
public class Vms022AhmhrntmMstpicotsDaoImpl extends HrHibernateDao<AhmhrntmMstpicots, String> implements Vms022AhmhrntmMstpicotsDao {

    @Override
    public List<Vms022VoLov> getPicAhm(String outType, String area) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT DISTINCT A.VNRP, C.NAME, B.VPGBLNM, C.VHANDPHONE  "
                + " FROM AHMHRNTM_MSTPICOTS A, AHMHRNTM_DTLPRMGBLS B, FMHRD_GENERAL_DATAS C "
                + " WHERE  "
                + " A.VOTSTYPE = :VOTSTYPE "
                + " AND  "
                + " SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT "
                + " AND B.VPGBLCD = A.VAREA "
                + " AND B.VPGBLCD LIKE 'PG10%' "
                + " AND A.VNRP = C.NRP "
                + " AND C.VEND_VND_CODE = 'AHM' "
                + " AND A.VAREA in :VAREA"
                + " AND (A.VRGSROLE IN ('PG91-01','PG91-03') " 
                + "     OR A.VRGSROLE IN ('PG91-02','PG91-03'))");

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());

        sqlQuery.setParameter("VOTSTYPE", outType)
                .setParameter("VAREA", area);

        List queryResult = sqlQuery.list();
        List<Vms022VoLov> vo = new ArrayList<>();
        if (queryResult.size() > 0) {
            Object[] obj;

            for (Object object : queryResult) {
                obj = (Object[]) object;
                Vms022VoLov get = new Vms022VoLov();

                get.setNrp((String) obj[0]);
                get.setName((String) obj[1]);
                get.setArea((String) obj[2]);
                get.setPhoneNum((String) obj[3]);
                vo.add(get);
            }
        }
        return vo;
    }

    @Override
    public String getPicAhmForExcel(String outType, String area) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT DISTINCT (A.VNRP), C.NAME, B.VPGBLNM, C.VHANDPHONE  "
                + " FROM AHMHRNTM_MSTPICOTS A, AHMHRNTM_DTLPRMGBLS B, FMHRD_GENERAL_DATAS C "
                + " WHERE  "
                + " A.VOTSTYPE = :VOTSTYPE "
                + " AND  "
                + " SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT "
                + " AND B.VPGBLCD = A.VAREA "
                + " AND B.VPGBLCD LIKE 'PG10%' "
                + " AND A.VNRP = C.NRP "
                + " AND C.VEND_VND_CODE = 'AHM' "
                + " AND A.VAREA = :VAREA "
                + " AND (A.VRGSROLE IN ('PG91-01','PG91-03') "
                + "      OR A.VRGSROLE IN ('PG91-02','PG91-03')) "
        );

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());

        sqlQuery.setParameter("VOTSTYPE", outType)
                .setParameter("VAREA", area);

        List queryResult = sqlQuery.list();
//        List<Vms022VoLov> vo = new ArrayList<>();
        String vo = "";
        if (queryResult.size() > 0) {
            Object[] obj;
            boolean limitText = true;

            for (Object object : queryResult) {
                obj = (Object[]) object;
                
                if (limitText) {
                    vo += obj[1].toString();
                    limitText = false;
                } else {
                    vo += "; " + obj[1].toString();
                }
            }
        }
        return vo;
    }
}
