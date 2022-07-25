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
import id.co.ahm.jxf.dao.HrHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.math.BigDecimal;
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
@Repository("vms022ahmhrntmDtlprmgblsDao")
public class Vms022AhmhrntmDtlprmgblsDaoImpl extends HrHibernateDao<AhmhrntmDtlprmgbls, String> implements Vms022AhmhrntmDtlprmgblsDao{

    @Override
    public List<Vms022VoLov> lovPlant(DtoParamPaging input, Boolean isMonitoring) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT VPGBLCD, VPGBLNM "
                + "FROM AHMHRNTM_DTLPRMGBLS "
                + "WHERE VPGBLCD LIKE 'PG10%' ");
        
        if(!isMonitoring){
            sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VPGBLCD,VPGBLNM", "OUTSOURCE");
            sql = Vms022QueryUtil.setSortParamLov(sql, input, "id,name", "OUTSOURCE");

        } else {
            sql.append(" ORDER BY VPGBLCD ");
        }
        
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        if(!isMonitoring){
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
    public int countLovPlant(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT (*) FROM ( ")
                .append("SELECT VPGBLCD, VPGBLNM "
                        + "FROM AHMHRNTM_DTLPRMGBLS "
                        + "WHERE VPGBLCD LIKE 'PG10%' ");

        sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VPGBLCD,VPGBLNM", "OUTSOURCE");

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
    public List<Vms022VoLov> lovGate(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT VPGBLCD, VPGBLNM, VVAL "
                + "FROM AHMHRNTM_DTLPRMGBLS "
                + "WHERE VPGBLCD LIKE 'PG85%' ");

        sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VPGBLCD,VPGBLNM,VVAL", "GATE");
        sql = Vms022QueryUtil.setSortParamLov(sql, input, "id,code,name", "GATE");


        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());

        List<Vms022VoLov> ress = new ArrayList<>();
        try {
            query.setResultTransformer(new ResultTransformer() {
                public Object transformTuple(Object[] os, String[] strings) {

                    Vms022VoLov v = new Vms022VoLov();
                    v.setId((String) os[0]);
                    if (os[1] != null) {
                        v.setCode((String) os[1]);
                    } else {
                        v.setCode("");
                    }
                    if (os[2] != null) {
                        v.setName((String) os[2]);
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
    public int countLovGate(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT (*) FROM ( ");
                sql.append("SELECT VPGBLCD, VPGBLNM, VVAL "
                + "FROM AHMHRNTM_DTLPRMGBLS "
                + "WHERE VPGBLCD LIKE 'PG85%' ");

        sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VPGBLCD,VPGBLNM,VVAL", "GATE");

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
    public int countLovOutsource(DtoParamPaging input, String type, String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT (*) FROM ( ");
            if ("FILTER".equalsIgnoreCase(type)) {
                sql.append("SELECT VPGBLCD, VPGBLNM "
                        + "FROM AHMHRNTM_DTLPRMGBLS "
                        + "WHERE VPGBLCD LIKE 'PG08%' ");

                sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VPGBLCD,VPGBLNM", "OUTSOURCE");
            } else if ("INT".equalsIgnoreCase(type)) {
                sql.append("SELECT A.VOTSTYPE, B.VPGBLNM "
                        + "FROM AHMHRNTM_MSTPICOTS A, AHMHRNTM_DTLPRMGBLS B "
                        + "WHERE A.VOTSTYPE = B.VPGBLCD "
                        + "AND A.VRGSROLE IN ('PG91-01','PG91-03') "
                        + "AND A.VNRP = :USERID "
                        + "AND SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT ");

                sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "A.VOTSTYPE,B.VPGBLNM", "OUTSOURCE");
            } else {
                sql.append("SELECT DISTINCT A.VOTSTYPE, B.VPGBLNM "
                        + "FROM AHMHRNTM_MSTPICOTS A, AHMHRNTM_DTLPRMGBLS B "
                        + "WHERE A.VOTSTYPE = B.VPGBLCD "
                        + "AND A.VRGSROLE IN ('PG91-02','PG91-03') "
                        + "AND SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT ");

                sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "A.VOTSTYPE,B.VPGBLNM", "OUTSOURCE");
            }    

        sql.append(" )");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if ("INT".equalsIgnoreCase(type)) {
            query.setParameter("USERID", userId);
        }
        
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
    public List<Vms022VoLov> lovOutsource(DtoParamPaging input, String type, String userId) {
        StringBuilder sql = new StringBuilder();
        
        if("FILTER".equalsIgnoreCase(type)){
            sql.append("SELECT VPGBLCD, VPGBLNM "
                    + "FROM AHMHRNTM_DTLPRMGBLS "
                    + "WHERE VPGBLCD LIKE 'PG08%' ");
            
            sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VPGBLCD,VPGBLNM", "OUTSOURCE");
        } else if ("INT".equalsIgnoreCase(type)){
            sql.append("SELECT A.VOTSTYPE, B.VPGBLNM "
                    + "FROM AHMHRNTM_MSTPICOTS A, AHMHRNTM_DTLPRMGBLS B "
                    + "WHERE A.VOTSTYPE = B.VPGBLCD "
                    + "AND A.VRGSROLE IN ('PG91-01','PG91-03') "
                    + "AND A.VNRP = :USERID "
                    + "AND SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT ");
            
            sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "A.VOTSTYPE,B.VPGBLNM", "OUTSOURCE");
        } else {
            sql.append("SELECT DISTINCT A.VOTSTYPE, B.VPGBLNM "
                    + "FROM AHMHRNTM_MSTPICOTS A, AHMHRNTM_DTLPRMGBLS B "
                    + "WHERE A.VOTSTYPE = B.VPGBLCD "
                    + "AND A.VRGSROLE IN ('PG91-02','PG91-03') "
                    + "AND SYSDATE BETWEEN A.DBGNEFFDT AND A.DENDEFFDT ");
            
            sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "A.VOTSTYPE,B.VPGBLNM", "OUTSOURCE");
        }
        
        sql = Vms022QueryUtil.setSortParamLov(sql, input, "id,name", "OUTSOURCE");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if ("INT".equalsIgnoreCase(type)){
            query.setParameter("USERID", userId);
        }

        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());
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
    public int countLovCompInternal(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT (*) FROM ( ")
                .append("SELECT VPGBLCD, VPGBLNM "
                        + "FROM AHMHRNTM_DTLPRMGBLS "
                        + "WHERE VPGBLCD LIKE 'PG09%' "
                        + "AND SYSDATE BETWEEN DBGNEFFDT AND DENDEFFDT ");

        sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VPGBLCD,VPGBLNM", "OUTSOURCE");

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
    public List<Vms022VoLov> lovCompInternal(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT VPGBLCD, VPGBLNM "
                + "FROM AHMHRNTM_DTLPRMGBLS "
                + "WHERE VPGBLCD LIKE 'PG09%' "
                + "AND SYSDATE BETWEEN DBGNEFFDT AND DENDEFFDT ");

        sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VPGBLCD,VPGBLNM", "OUTSOURCE");
        sql = Vms022QueryUtil.setSortParamLov(sql, input, "id,name", "OUTSOURCE");

        Query query = getCurrentSession().createSQLQuery(sql.toString());

        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());
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
    public List<Vms022VoLov> lovVaccine(String type) {
        StringBuilder sql = new StringBuilder();
        
        if("VACTYPE".equalsIgnoreCase(type)){
            sql.append("SELECT VPGBLCD, VPGBLNM "
                    + "FROM AHMHRNTM_DTLPRMGBLS "
                    + "WHERE VPGBLCD LIKE 'PG95%' ORDER BY VPGBLCD ");
        }
        

        Query query = getCurrentSession().createSQLQuery(sql.toString());

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
    public List<Vms022VoLov> getPlantsGates(String outId, String nik, String type) {
        List<Vms022VoLov> results = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();
        Query query = null;

        sqlQuery.append("SELECT ")
                .append("A.NSEQ, ");
                if("PLNT".equalsIgnoreCase(type)){
                sqlQuery.append("A.VPLANT, ")
                        .append("B.VPGBLNM ");
                } else {
                sqlQuery.append("A.VGATE, ")
                        .append("D.VPGBLNM, ")
                        .append("D.VVAL ");
                }
                sqlQuery.append("FROM ")
                .append("AHMHRNTM_HDROTSEMPS C ")
                .append("INNER JOIN AHMHRNTM_DTLOTSREGS A ON C.VOTSID = A.VOTSID and C.VPERSID = A.VPERSID ");
                if("PLNT".equalsIgnoreCase(type)){
                    sqlQuery.append("INNER JOIN AHMHRNTM_DTLPRMGBLS B ON A.VPLANT = B.VPGBLCD ");
                } else {
                    sqlQuery.append("INNER JOIN AHMHRNTM_DTLPRMGBLS D ON A.VGATE = D.VPGBLCD ");
                }
                sqlQuery.append("WHERE ")
                .append("A.VREGID in('PLNT','GATE') ")
                .append("AND C.VOTSID = :VOTSID ")
                .append("AND C.VPERSID = :VPERSID ");

        query = getCurrentSession().createSQLQuery(sqlQuery.toString());
        query.setParameter("VOTSID", outId);
        query.setParameter("VPERSID", nik);

        try {

            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object[] obj = (Object[]) lists.get(i);
                Vms022VoLov vo = new Vms022VoLov();

                vo.setSeq((BigDecimal) obj[0]);
                vo.setId(obj[1] + "");
                if("PLNT".equalsIgnoreCase(type)){
                    vo.setName(obj[2] + "");
                } else {
                    vo.setCode(obj[2] + "");
                    vo.setName(obj[3] + "");
                }
                vo.setStat("E");

                results.add(vo);
            }
            return results;
        } catch (SQLGrammarException | GenericJDBCException e) {
            return results;
        }
    }
}
