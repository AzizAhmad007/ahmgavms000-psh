/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.hr.AhmhrntmMstpicots;
import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmMstpicotsDao;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.jxf.dao.HrHibernateDao;
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
                + " FROM AHMHRNTM_MSTPICOTS A, AHMHRNTM_DTLPRMGBLS B, FMHRD_GENERAL_DATAS C, AHMMOERP_MSTKARYAWANS@ahmps D "
                + " WHERE  "
                + " A.VOTSTYPE = :VOTSTYPE "
                + " AND TRUNC(SYSDATE) BETWEEN TRUNC(A.DBGNEFFDT) AND TRUNC(A.DENDEFFDT) "
                + " AND B.VPGBLCD = A.VAREA "
                + " AND B.VPGBLCD LIKE 'PG10%' "
                + " AND A.VNRP = C.NRP "
                + " AND A.VNRP = D.IIDNRP"
                + " AND C.VEND_VND_CODE = 'AHM' ");

        sql.append(" AND A.VAREA in (");
        sql.append(area);
        sql.append(")");

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());

        sqlQuery.setParameter("VOTSTYPE", outType);

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
                + " FROM AHMHRNTM_MSTPICOTS A, AHMHRNTM_DTLPRMGBLS B, FMHRD_GENERAL_DATAS C, AHMMOERP_MSTKARYAWANS@ahmps D "
                + " WHERE  "
                + " A.VOTSTYPE = :VOTSTYPE "
                + " AND  "
                + " SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT "
                + " AND B.VPGBLCD = A.VAREA "
                + " AND B.VPGBLCD LIKE 'PG10%' "
                + " AND A.VNRP = C.NRP "
                + " AND A.VNRP = D.IIDNRP"
                + " AND C.VEND_VND_CODE = 'AHM' ");

        sql.append(" AND A.VAREA in (");
        sql.append(area);
        sql.append(")");

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());

        sqlQuery.setParameter("VOTSTYPE", outType);

        List queryResult = sqlQuery.list();
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

    @Override
    public Boolean isPicAvailable(String userId, String area, String type) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT DISTINCT "
                + "    a.vnrp, "
                + "    a.varea, "
                + "    a.votstype, "
                + "    c.name, "
                + "    b.vpgblnm, "
                + "    c.vhandphone "
                + "FROM "
                + "    ahmhrntm_mstpicots a, "
                + "    ahmhrntm_dtlprmgbls b, "
                + "    fmhrd_general_datas c, "
                + "    AHMMOERP_MSTKARYAWANS@ahmps d "
                + "WHERE "
                + "        A.VOTSTYPE = :VOTSTYPE "
                + "    AND "
                + "        SYSDATE BETWEEN a.dbgneffdt AND a.dendeffdt "
                + "    AND "
                + "        b.vpgblcd = a.varea "
                + "    AND "
                + "        b.vpgblcd LIKE 'PG10%' "
                + "    AND "
                + "        a.vnrp = c.nrp "
                + "    AND "
                + "        a.vnrp = d.iidnrp "
                + "    AND "
                + "        c.vend_vnd_code = 'AHM' "
                + "    AND  "
                + "        A.VAREA in (:area) " );

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());

        sqlQuery.setParameter("area", area)
                .setParameter("VOTSTYPE", type);
        boolean result;
        if (sqlQuery.list().isEmpty() || sqlQuery.list().equals(0)) {
            result = false;
        } else {
            result = true;
        }
        return result;

    }

    @Override
    public String getPicAreaType(String nrp) {
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(Vms022Constant.SQL_GET_AREA_TYPE);

        sqlQuery.setParameter("NRP", nrp);

        List queryResult = sqlQuery.list();
        String vo = "";
        if (!queryResult.isEmpty()) {
            int i = 0;
            Object[] obj;

            vo += "AND ( ";

            for (Object object : queryResult) {
                obj = (Object[]) object;

                if (i == queryResult.size()) {
                    vo += "(MPO.VAREA = '" + (String) obj[1] + "' AND MPO.VOTSTYPE = '" + (String) obj[2] + "' ) ";
                } else {
                    vo += "(MPO.VAREA = '" + (String) obj[1] + "' AND MPO.VOTSTYPE = '" + (String) obj[2] + "' ) OR";
                }
                i++;
            }

            vo += " ) ";

        }
        return vo;
    }
}
