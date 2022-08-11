/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlprmgbls;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app022.util.Vms022QueryUtil;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.dao.HrHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.AhmStringUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.text.StrBuilder;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022ahmhrntmDtlprmgblsDao")
public class Vms022AhmhrntmDtlprmgblsDaoImpl extends HrHibernateDao<AhmhrntmDtlprmgbls, String> implements Vms022AhmhrntmDtlprmgblsDao {

    @Override
    public List<Vms022VoLov> lovPlant(DtoParamPaging input, Boolean isMonitoring) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT VPGBLCD, VPGBLNM "
                + "FROM AHMHRNTM_DTLPRMGBLS "
                + "WHERE VPGBLCD LIKE 'PG10%' ");

        if (!isMonitoring) {
            sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VPGBLCD,VPGBLNM", "OUTSOURCE");
            sql = Vms022QueryUtil.setSortParamLov(sql, input, "id,name", "OUTSOURCE");

        } else {
            sql.append(" ORDER BY VPGBLCD ");
        }

        Query query = getCurrentSession().createSQLQuery(sql.toString());

        if (!isMonitoring) {
            query.setFirstResult(input.getOffset());
            query.setMaxResults(input.getLimit());
        }

        List<Vms022VoLov> ress = new ArrayList<>();
        try {
            query.setResultTransformer(new ResultTransformer() {
                public Object transformTuple(Object[] os, String[] strings) {

                    Vms022VoLov v = new Vms022VoLov();
                    v.setId((String) os[0]);
                    if (os[1] != null) {
                        v.setName((String) os[1]);
                    } else {
                        v.setName("");
                    }
                    v.setStat("N");

                    return v;
                }

                public List transformList(List list) {
                    return list;
                }
            });

            return query.list();
        } catch (GenericJDBCException | SQLGrammarException z) {
            return ress;
        }
    }

    @Override
    public List<Vms022VoLov> getPlant(String outid, String nik) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT "
                + "     A.NSEQ, "
                + "     A.VPLANT, "
                + "     B.VPGBLNM "
                + " FROM "
                + " AHMHRNTM_HDROTSEMPS C "
                + " INNER JOIN AHMHRNTM_DTLOTSREGS A ON C.VOTSID = A.VOTSID and C.VPERSID = A.VPERSID "
                + " INNER JOIN AHMHRNTM_DTLPRMGBLS B ON A.VPLANT = B.VPGBLCD "
                + " WHERE"
                + "     A.VREGID in('PLNT','GATE') "
                + "     AND C.VOTSID = :VOTSID "
                + "     AND C.VPERSID = :VPERSID ");

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());

        sqlQuery.setParameter("VOTSID", outid)
                .setParameter("VPERSID", nik);

        List queryResult = sqlQuery.list();
        List<Vms022VoLov> vo = new ArrayList<>();
        if (queryResult.size() > 0) {
            Object[] obj;

            for (Object object : queryResult) {
                obj = (Object[]) object;
                Vms022VoLov get = new Vms022VoLov();

                get.setSeq((BigDecimal) obj[0]);
                get.setId((String) obj[1]);
                get.setName((String) obj[2]);
                vo.add(get);
            }
        }
        return vo;
    }

    @Override
    public List<Vms022VoLov> getGate(String outid, String nik) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT "
                + "     A.NSEQ, "
                + "     A.VGATE, "
                + "     D.VPGBLNM, "
                + "     D.VVAL "
                + " FROM "
                + " AHMHRNTM_HDROTSEMPS C "
                + " INNER JOIN AHMHRNTM_DTLOTSREGS A ON C.VOTSID = A.VOTSID and C.VPERSID = A.VPERSID "
                + " INNER JOIN AHMHRNTM_DTLPRMGBLS D ON A.VGATE = D.VPGBLCD "
                + " WHERE"
                + "     A.VREGID in('PLNT','GATE') "
                + "     AND C.VOTSID = :VOTSID "
                + "     AND C.VPERSID = :VPERSID ");

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());

        sqlQuery.setParameter("VOTSID", outid)
                .setParameter("VPERSID", nik);

        List queryResult = sqlQuery.list();
        List<Vms022VoLov> vo = new ArrayList<>();
        if (queryResult.size() > 0) {
            Object[] obj;

            for (Object object : queryResult) {
                obj = (Object[]) object;
                Vms022VoLov get = new Vms022VoLov();

                get.setSeq((BigDecimal) obj[0]);
                get.setId((String) obj[1]);
                get.setCode((String) obj[2]);
                get.setName((String) obj[3]);
                vo.add(get);
            }
        }
        return vo;
    }
}
