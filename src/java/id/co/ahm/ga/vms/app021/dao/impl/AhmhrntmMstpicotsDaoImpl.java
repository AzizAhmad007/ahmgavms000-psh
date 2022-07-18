/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmMstpicots;
import id.co.ahm.ga.vms.app021.dao.AhmhrntmMstpicotsDao;
import id.co.ahm.ga.vms.app021.util.Vms021QueryUtil;
import id.co.ahm.ga.vms.app021.vo.Vms021VoLov;
import id.co.ahm.ga.vms.app021.vo.Vms021VoLovNrp;
import id.co.ahm.jxf.dao.HrHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayik.op
 */
@Repository("ahmhrntmMstpicotsDao")
public class AhmhrntmMstpicotsDaoImpl extends HrHibernateDao<AhmhrntmMstpicots, String> implements AhmhrntmMstpicotsDao{
        
    @Override
    public int countLovNrp(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT (*) FROM ( ")
                .append("SELECT DISTINCT (A.VNRP), B.NAME FROM AHMHRNTM_MSTPICOTS A, FMHRD_GENERAL_DATAS B "
                        + "WHERE A.VNRP = B.NRP "
                        + "AND B.VEND_VND_CODE = 'AHM' "
                        + "AND SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT ");

        sql = Vms021QueryUtil.setSearchParamLov(sql, input.getSearch(), "A.VNRP,B.NAME", "PLANT");

        sql.append(" )");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        Number results = 0;
        try {
            Object obj = (Object) query.uniqueResult();

            if (obj != null) {
                results = (Number) query.uniqueResult();
            }

            return results.intValue();
        } catch (SQLGrammarException e) {
            return results.intValue();
        }
    }

    @Override
    public List<Vms021VoLov> lovNrp(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT (A.VNRP), B.NAME FROM AHMHRNTM_MSTPICOTS A, FMHRD_GENERAL_DATAS B "
                + "WHERE A.VNRP = B.NRP "
                + "AND B.VEND_VND_CODE = 'AHM' "
                + "AND SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT ");

        sql = Vms021QueryUtil.setSearchParamLov(sql, input.getSearch(), "A.VNRP,B.NAME", "PLANT");
        sql = Vms021QueryUtil.setSortParamLov(sql, input, "id,name", "PLANT");

        Query query = getCurrentSession().createSQLQuery(sql.toString());

        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());
        List<Vms021VoLov> ress = new ArrayList<>();
        try {
            query.setResultTransformer(new ResultTransformer() {
                public Object transformTuple(Object[] os, String[] strings) {

                    Vms021VoLov v = new Vms021VoLov();
                    v.setId((String) os[0]);
                    if (os[1] != null) {
                        v.setName((String) os[1]);
                    } else {
                        v.setName("");
                    }

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
    public List<Vms021VoLovNrp> getPicAhm(String outType, List<String> plants) {
        List<Vms021VoLovNrp> results = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();
        Query query = null;

        sqlQuery.append("SELECT A.VNRP, C.NAME, B.VPGBLNM, C.VHANDPHONE "
                + "FROM AHMHRNTM_MSTPICOTS A, AHMHRNTM_DTLPRMGBLS B, FMHRD_GENERAL_DATAS C "
                + "WHERE A.VOTSTYPE = :VOTSTYPE "
                + "AND SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT "
                + "AND B.VPGBLCD = A.VAREA "
                + "AND B.VPGBLCD LIKE 'PG10%' "
                + "AND A.VNRP = C.NRP "
                + "AND C.VEND_VND_CODE = 'AHM' "
                + "AND A.VAREA IN (:VPLANT) ");

        query = getCurrentSession().createSQLQuery(sqlQuery.toString());
        query.setParameter("VOTSTYPE", outType);
        query.setParameterList("VPLANT", plants);

        try {

            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object[] obj = (Object[]) lists.get(i);
                Vms021VoLovNrp vo = new Vms021VoLovNrp();
                
                vo.setNrp(obj[0] == null ? "" : obj[0] + "");
                vo.setName(obj[1] == null ? "" : obj[1] + "");
                vo.setPlant(obj[2] == null ? "" : obj[2] + "");
                vo.setPhone(obj[3] == null ? "" : obj[3] + "");

                results.add(vo);
            }
            return results;
        } catch (SQLGrammarException | GenericJDBCException e) {
            return results;
        }
    }
}
