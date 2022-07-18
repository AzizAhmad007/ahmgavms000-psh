/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app021.dao.AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app021.util.Vms021QueryUtil;
import id.co.ahm.ga.vms.app021.vo.Vms021VoMonitoring;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dao.HrHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayik.op
 */
@Repository("ahmhrntmHdrotsempsDao")
public class AhmhrntmHdrotsempsDaoImpl extends HrHibernateDao<AhmhrntmHdrotsemps, String> implements AhmhrntmHdrotsempsDao{
        
    @Override
    public List<AhmhrntmHdrotsemps> findByNIKs(String nik) {
        List<AhmhrntmHdrotsemps> results = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select VPERSID, VNAME from AHMHRNTM_HDROTSEMPS where "
                + " VPERSID = :NIK "
                + " AND (SYSDATE BETWEEN DBGNEFFDT AND DENDEFFDT) ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("NIK", nik);

        try {
            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object[] obj = (Object[]) lists.get(i);
                AhmhrntmHdrotsemps vo = new AhmhrntmHdrotsemps();

                vo.setVpersid(obj[0] == null ? "" : obj[0] + "");
                vo.setVname(obj[1] == null ? "" : obj[1] + "");

                results.add(vo);
            }
        } catch (SQLGrammarException | GenericJDBCException e) {
            return results;
        }
        
        return results;
    }
    
    @Override
    public int findByNIK(String nik) {
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) from AHMHRNTM_HDROTSEMPS where "
                + " VPERSID = :NIK "
                + " AND (SYSDATE BETWEEN DBGNEFFDT AND DENDEFFDT) ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("NIK", nik);
        
        Number results = -1;
        try {
            Object obj = (Object) query.uniqueResult();

            if (obj != null) {
                results = (Number) query.uniqueResult();
            }

            return results.intValue() == 0 ? -1 : results.intValue();
        } catch (SQLGrammarException e) {
            return results.intValue();
        }
    }
    
    @Override
    public List<AhmhrntmHdrotsemps> findByOtsId(String otsId) {
        Criteria criteria = getCurrentSession().createCriteria(AhmhrntmHdrotsemps.class, "ahmhrntmHdrotsemps");
        criteria.add(Restrictions.eq("votsid", otsId).ignoreCase());
        
        return criteria.list();
    }
    
    @Override
    public List<AhmhrntmHdrotsemps> validateUpdate(String otsId, String nik) {
        List<AhmhrntmHdrotsemps> results = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT VPERSID, VNAME FROM AHMHRNTM_HDROTSEMPS WHERE "
                + " VPERSID = :NIK "
                + " AND VOTSID != :VOTSID "
                + " AND (SYSDATE BETWEEN DBGNEFFDT AND DENDEFFDT) ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("VOTSID", otsId);
        query.setParameter("NIK", nik);

        try {
            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object[] obj = (Object[]) lists.get(i);
                AhmhrntmHdrotsemps vo = new AhmhrntmHdrotsemps();

                vo.setVpersid(obj[0] == null ? "" : obj[0] + "");
                vo.setVname(obj[1] == null ? "" : obj[1] + "");

                results.add(vo);
            }
        } catch (SQLGrammarException | GenericJDBCException e) {
            return results;
        }
        
        return results;
    }
    
    @Override
    public List<Vms021VoMonitoring> getMonitoring(DtoParamPaging input, String userId, String type) {
        List<Vms021VoMonitoring> results = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        StringBuilder sqlQuery = new StringBuilder();
        Query query = null;
        
        LinkedHashMap<String, Object> reqObj = (LinkedHashMap<String, Object>) input.getSearch();
        String pic = reqObj.get("pic") != null ? reqObj.get("pic").toString() : "";
        String beginDate = reqObj.get("beginDate") != null ? reqObj.get("beginDate").toString() : "";
        String endDate = reqObj.get("endDate") != null ? reqObj.get("endDate").toString() : "";
        String plant = reqObj.get("plant") != null ? reqObj.get("plant").toString() : "PG10-00";
        String plantFilter = reqObj.get("plantFilter") != null ? reqObj.get("plantFilter").toString() : "";
        
        sortMap.put("ahmgavms021p01OutIdSort", "A.VOTSID");
        sortMap.put("ahmgavms021p01OutNameSort", "A.VNAME");
        sortMap.put("ahmgavms021p01NikSort", "A.VPERSID");
        sortMap.put("ahmgavms021p01OutTypeSort", "D.VPGBLNM");
        sortMap.put("ahmgavms021p01CompanySort", "F.VPGBLNM");
        sortMap.put("ahmgavms021p01StatusSort", "A.VOTSSTTS");
        sortMap.put("ahmgavms021p01PlantSort", "E.VPGBLNM");
        sortMap.put("ahmgavms021p01VacStatusSort", "A.VVACSTTS");
        sortMap.put("ahmgavms021p01BeginDateSort", "A.DBGNEFFDT");
        sortMap.put("ahmgavms021p01EndDateSort", "A.DENDEFFDT");
        sortMap.put("ahmgavms021p01PassNumberSort", "A.NAHMCARDORI");
        sortMap.put("ahmgavms021p01PassExpirySort", "A.DPASSEXP");
        sortMap.put("ahmgavms021p01ModifySort", "A.VMODI");
        sortMap.put("ahmgavms021p01ModifyDateSort", "A.DMODI");
        
        sqlQuery.append("SELECT "
                + "A.VOTSID OUTID, "
                + "A.VNAME OUTNAME, "
                + "A.VPERSID PERSID, "
                + "A.VOTSTYPE OUTTYPE, "
                + "D.VPGBLNM OUTTYPENAME,  "
                + "A.VCOMPANY COMPANY, "
                + "COALESCE(F.VPGBLNM, '') COMPANYNAME, "
                + "A.VOTSSTTS OUTSTATUS, "
                + "B.VPLANT AREA, "
                + "E.VPGBLNM AREANAME, "
                + "A.VVACSTTS VACSTATUS, "
                + "A.DBGNEFFDT BEGINDATE, "
                + "A.DENDEFFDT ENDDATE, "
                + "A.NAHMCARDORI PASSNUMBER, "
                + "A.DPASSEXP PASSEXPIRY, "
                + "A.VMODI MODIFIED, "
                + "A.DMODI MODIFIEDDATE, "
                + "A.VEMPPHONE PHONENO, "
                + "A.VPHOTOID FILENAMEKTP, "
                + "A.VRBSUPLAY SUPPLIER, "
                + "A.VJOBDTL JOBDETAIL, "
                + "A.VABSRDR ABSENCERDR, "
                + "A.VCANTEEN CANTEEN, "
                + "A.VSECGATE SECGATE, "
                + "A.VNOTE NOTES, "
                + "A.VPHOTONAME FILENAMEPHOTO, "
                + "A.VVACTYPE VACTYPE, "
                + "G.VPGBLNM VACTYPENAME, "
                + "A.DLASTVAC VACDATE, "
                + "A.VVACDTL VACSUMMARY, "
                + "A.VNTVS VACNOTE, "
                + "A.VAPPDISCLM DISCLAIMER, "
                + "RAWTOHEX(A.ROTSEMPSHS) ID, "
                + "A.BPHOTO FOTO "
                + "FROM  "
                + "AHMHRNTM_HDROTSEMPS A ");
                if ("PG10-00".equalsIgnoreCase(plant) && StringUtils.isBlank(plantFilter)) {
                    sqlQuery.append("INNER JOIN ( "
                            + "    SELECT DISTINCT  "
                            + "    A.VPLANT,  "
                            + "    B.VPGBLNM,  "
                            + "    A.VOTSID,  "
                            + "    A.VPERSID,  "
                            + "    B.VPGBLCD,  "
                            + "    ROW_NUMBER() OVER ( PARTITION BY A.VOTSID ORDER BY NULL) AS R_NUM "
                            + "    FROM AHMHRNTM_DTLOTSREGS A, AHMHRNTM_DTLPRMGBLS B "
                            + "    WHERE A.VREGID = 'PLNT' "
                            + "    AND B.VPGBLCD = A.VPLANT) B on A.VOTSID = B.VOTSID and A.VPERSID = B.VPERSID "
                            + "INNER JOIN AHMHRNTM_DTLPRMGBLS E on B.VPLANT = E.VPGBLCD "); //--PLANT NAME
                } else {
                    sqlQuery.append("INNER JOIN AHMHRNTM_DTLOTSREGS B on A.VOTSID = B.VOTSID and A.VPERSID = B.VPERSID "
                            + "INNER JOIN AHMHRNTM_DTLPRMGBLS E on B.VPLANT = E.VPGBLCD "); //--PLANT NAME //JIKA PLANT FILTER NOT BLANK
                }
                if (!StringUtils.isBlank(pic)) {
                    sqlQuery.append(" INNER JOIN AHMHRNTM_MSTPICOTS C ON B.VPLANT = C.VAREA ");
                }
                sqlQuery.append(" INNER JOIN AHMHRNTM_DTLPRMGBLS D ON A.VOTSTYPE = D.VPGBLCD " //--OUT NAME
                + "LEFT JOIN AHMHRNTM_DTLPRMGBLS F ON A.VCOMPANY = F.VPGBLCD " //--COMP NAME
                + "LEFT JOIN AHMHRNTM_DTLPRMGBLS G ON A.VVACTYPE = G.VPGBLCD " //--VAC TYPE NAME
                + "WHERE "
                + "A.VCREA = :USERLOGIN ");
                if ("PG10-00".equalsIgnoreCase(plant) && StringUtils.isBlank(plantFilter)) {
                    sqlQuery.append("AND B.R_NUM = 1 ");
                } 
                sqlQuery.append("AND  "
                + "SYSDATE BETWEEN F.DBGNEFFDT AND F.DENDEFFDT ");
                if(!StringUtils.isBlank(beginDate) || !StringUtils.isBlank(endDate)){
                    sqlQuery.append(" AND (");
                    
                    if (!StringUtils.isBlank(beginDate)) {
                        sqlQuery.append(" ('").append(beginDate).append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
                    }
                    if (!StringUtils.isBlank(endDate) && StringUtils.isBlank(beginDate)) {
                        sqlQuery.append(" ('").append(endDate).append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
                    } else {
                        sqlQuery.append(" OR ('").append(endDate).append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
                    }     
                    if (!StringUtils.isBlank(beginDate) && !StringUtils.isBlank(endDate)) {
                        sqlQuery.append(" OR (A.DBGNEFFDT BETWEEN '")
                                .append(beginDate).append("' AND '").append(endDate).append("') ")
                                .append(" OR (A.DENDEFFDT BETWEEN '")
                                .append(beginDate).append("' AND '").append(endDate).append("') ");
                    }              
                    sqlQuery.append(" ) ");
                }
        
        sqlQuery = Vms021QueryUtil.setSearchParamMonitoringTable(sqlQuery, input.getSearch(), "");
        orderClause(input, sqlQuery, sortMap, "");

        query = getCurrentSession().createSQLQuery(sqlQuery.toString());
        query.setParameter("USERLOGIN", userId);
        if (StringUtils.isBlank(type)) {
            query.setFirstResult(input.getOffset());
            query.setMaxResults(input.getLimit());
        }
        try {
            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object[] obj = (Object[]) lists.get(i);
                Vms021VoMonitoring vo = new Vms021VoMonitoring();
                
                vo.setOutId(obj[0] == null ? "" : obj[0] + "");
                vo.setOutName(obj[1] == null ? "" : obj[1] + "");
                vo.setPersId(obj[2] == null ? "" : obj[2] + "");
                vo.setOutType(obj[3] == null ? "" : obj[3] + "");
                vo.setOutTypeName(obj[4] == null ? "" : obj[4] + "");
                vo.setCompany(obj[5] == null ? "" : obj[5] + "");
                vo.setCompanyName(obj[6] == null ? "" : obj[6] + "");
                vo.setOutStatus(obj[7] == null ? "" : obj[7] + "");
                vo.setArea(obj[8] == null ? "" : obj[8] + "");
                vo.setAreaName(obj[9] == null ? "" : obj[9] + "");
                vo.setVacStatus(obj[10] == null ? "" : obj[10] + "");
                if(obj[11] != null){
                    vo.setBeginDate((Date) obj[11]);
                    vo.setBeginDateText(DateUtil.dateToString((Date) obj[11], "dd-MMM-yyyy"));
                } else {
                    vo.setBeginDateText("");
                }
                if(obj[12] != null){
                    vo.setEndDate((Date) obj[12]);
                    vo.setEndDateText(DateUtil.dateToString((Date) obj[12], "dd-MMM-yyyy"));
                } else {
                    vo.setEndDateText("");
                }
                vo.setPassNumber(obj[13] == null ? "" : obj[13] + "");
                if (obj[14] != null) {
                    vo.setPassExpiryDate((Date) obj[14]);
                    vo.setPassExpiryDateText(DateUtil.dateToString((Date) obj[14], "dd-MMM-yyyy"));
                } else {
                    vo.setPassExpiryDateText("");
                }
                vo.setModifyBy(obj[15] == null ? "" : obj[15] + "");
                if (obj[16] != null) {
                    vo.setModifyDate((Date) obj[16]);
                    vo.setModifyDateText(DateUtil.dateToString((Date) obj[16], "dd-MMM-yyyy"));
                } else {
                    vo.setModifyDateText("");
                }
                vo.setPhoneNo(obj[17] == null ? "" : obj[17] + "");
                vo.setFileNameKtp(obj[18] == null ? "" : obj[18] + "");
                vo.setSupplier(obj[19] == null ? "" : obj[19] + "");
                vo.setJob(obj[20] == null ? "" : obj[20] + "");
                vo.setAccessReader(obj[21] == null ? "" : obj[21] + "");
                vo.setCanteen(obj[22] == null ? "" : obj[22] + "");
                vo.setSecurityGate(obj[23] == null ? "" : obj[23] + "");
                vo.setNote(obj[24] == null ? "" : obj[24] + "");
                vo.setFileNamePhoto(obj[25] == null ? "" : obj[25] + "");
                vo.setVacType(obj[26] == null ? "" : obj[26] + "");
                vo.setVacTypeName(obj[27] == null ? "" : obj[27] + "");
                if (obj[28] != null) {
                    vo.setVacDate((Date) obj[28]);
                    vo.setVacDateText(DateUtil.dateToString((Date) obj[28], "dd-MMM-yyyy"));
                } else {
                    vo.setVacDateText("");
                }
                vo.setVacSummary(obj[29] == null ? "" : obj[29] + "");
                vo.setVacNote(obj[30] == null ? "" : obj[30] + "");
                vo.setDiclaimer(obj[31] == null ? "" : obj[31] + "");
                vo.setId(obj[32] == null ? "" : obj[32] + "");
                if(obj[33] != null){
                    byte[] dt = null;
                    Blob tmp = (Blob) obj[33];
                    
                    try{
                        dt = tmp.getBytes(1, (int) tmp.length());
                        vo.setFilePhoto(Base64.getEncoder().encodeToString(dt));
                        
                    }catch (SQLException ex) {
                        Logger.getLogger(AhmhrntmHdrotsempsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    vo.setFilePhoto("");
                }
                
                results.add(vo);
            }
            return results;
        } catch (SQLGrammarException | GenericJDBCException e) {
            return results;
        } 
    }

    @Override
    public int countMonitoring(DtoParamPaging input, String userId) {
        StringBuilder sqlQuery = new StringBuilder();
        LinkedHashMap<String, Object> reqObj = (LinkedHashMap<String, Object>) input.getSearch();
        String pic = reqObj.get("pic") != null ? reqObj.get("pic").toString() : "";
        String beginDate = reqObj.get("beginDate") != null ? reqObj.get("beginDate").toString() : "";
        String endDate = reqObj.get("endDate") != null ? reqObj.get("endDate").toString() : "";
        String plant = reqObj.get("plant") != null ? reqObj.get("plant").toString() : "PG10-00";
        String plantFilter = reqObj.get("plantFilter") != null ? reqObj.get("plantFilter").toString() : "";
        
        sqlQuery.append("SELECT COUNT (*) FROM ( ");
        sqlQuery.append("SELECT "
                + "A.VOTSID OUTID, "
                + "A.VNAME OUTNAME, "
                + "A.VPERSID PERSID, "
                + "A.VOTSTYPE OUTTYPE, "
                + "D.VPGBLNM OUTTYPENAME,  "
                + "A.VCOMPANY COMPANY, "
                + "COALESCE(F.VPGBLNM, '') COMPANYNAME, "
                + "A.VOTSSTTS OUTSTATUS, "
                + "B.VPLANT AREA, "
                + "E.VPGBLNM AREANAME, "
                + "A.VVACSTTS VACSTATUS, "
                + "A.DBGNEFFDT BEGINDATE, "
                + "A.DENDEFFDT ENDDATE, "
                + "A.NAHMCARDORI PASSNUMBER, "
                + "A.DPASSEXP PASSEXPIRY, "
                + "A.VMODI MODIFIED, "
                + "A.DMODI MODIFIEDDATE, "
                + "A.VEMPPHONE PHONENO, "
                + "A.VPHOTOID FILENAMEKTP, "
                + "A.VRBSUPLAY SUPPLIER, "
                + "A.VJOBDTL JOBDETAIL, "
                + "A.VABSRDR ABSENCERDR, "
                + "A.VCANTEEN CANTEEN, "
                + "A.VSECGATE SECGATE, "
                + "A.VNOTE NOTES, "
                + "A.VPHOTONAME FILENAMEPHOTO, "
                + "A.VVACTYPE VACTYPE, "
                + "G.VPGBLNM VACTYPENAME, "
                + "A.DLASTVAC VACDATE, "
                + "A.VVACDTL VACSUMMARY, "
                + "A.VNTVS VACNOTE, "
                + "A.VAPPDISCLM DISCLAIMER, "
                + "RAWTOHEX(A.ROTSEMPSHS) ID, "
                + "A.BPHOTO FOTO "
                + "FROM  "
                + "AHMHRNTM_HDROTSEMPS A ");
        if ("PG10-00".equalsIgnoreCase(plant) && StringUtils.isBlank(plantFilter)) {
            sqlQuery.append("INNER JOIN ( "
                    + "    SELECT DISTINCT  "
                    + "    A.VPLANT,  "
                    + "    B.VPGBLNM,  "
                    + "    A.VOTSID,  "
                    + "    A.VPERSID,  "
                    + "    B.VPGBLCD,  "
                    + "    ROW_NUMBER() OVER ( PARTITION BY A.VOTSID ORDER BY NULL) AS R_NUM "
                    + "    FROM AHMHRNTM_DTLOTSREGS A, AHMHRNTM_DTLPRMGBLS B "
                    + "    WHERE A.VREGID = 'PLNT' "
                    + "    AND B.VPGBLCD = A.VPLANT) B on A.VOTSID = B.VOTSID and A.VPERSID = B.VPERSID "
                    + "INNER JOIN AHMHRNTM_DTLPRMGBLS E on B.VPLANT = E.VPGBLCD "); //--PLANT NAME
        } else {
            sqlQuery.append("INNER JOIN AHMHRNTM_DTLOTSREGS B on A.VOTSID = B.VOTSID and A.VPERSID = B.VPERSID "
                    + "INNER JOIN AHMHRNTM_DTLPRMGBLS E on B.VPLANT = E.VPGBLCD "); //--PLANT NAME //JIKA PLANT FILTER NOT BLANK
        }
        if (!StringUtils.isBlank(pic)) {
            sqlQuery.append(" INNER JOIN AHMHRNTM_MSTPICOTS C ON B.VPLANT = C.VAREA ");
        }
        sqlQuery.append(" INNER JOIN AHMHRNTM_DTLPRMGBLS D ON A.VOTSTYPE = D.VPGBLCD " //--OUT NAME
                + "LEFT JOIN AHMHRNTM_DTLPRMGBLS F ON A.VCOMPANY = F.VPGBLCD " //--COMP NAME
                + "LEFT JOIN AHMHRNTM_DTLPRMGBLS G ON A.VVACTYPE = G.VPGBLCD " //--VAC TYPE NAME
                + "WHERE "
                + "A.VCREA = :USERLOGIN ");
        if ("PG10-00".equalsIgnoreCase(plant) && StringUtils.isBlank(plantFilter)) {
            sqlQuery.append("AND B.R_NUM = 1 ");
        }
        sqlQuery.append("AND  "
                + "SYSDATE BETWEEN F.DBGNEFFDT AND F.DENDEFFDT ");
        if (!StringUtils.isBlank(beginDate) || !StringUtils.isBlank(endDate)) {
            sqlQuery.append(" AND (");

            if (!StringUtils.isBlank(beginDate)) {
                sqlQuery.append(" ('").append(beginDate).append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
            }
            if (!StringUtils.isBlank(endDate) && StringUtils.isBlank(beginDate)) {
                sqlQuery.append(" ('").append(endDate).append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
            } else {
                sqlQuery.append(" OR ('").append(endDate).append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
            }
            if (!StringUtils.isBlank(beginDate) && !StringUtils.isBlank(endDate)) {
                sqlQuery.append(" OR (A.DBGNEFFDT BETWEEN '")
                        .append(beginDate).append("' AND '").append(endDate).append("') ")
                        .append(" OR (A.DENDEFFDT BETWEEN '")
                        .append(beginDate).append("' AND '").append(endDate).append("') ");
            }
            sqlQuery.append(" ) ");
        }

        sqlQuery = Vms021QueryUtil.setSearchParamMonitoringTable(sqlQuery, input.getSearch(), "");
                
        sqlQuery.append(" )");
        Query query = getCurrentSession().createSQLQuery(sqlQuery.toString());
        query.setParameter("USERLOGIN", userId);
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

    private void orderClause(DtoParamPaging input, StringBuilder query, Map<String, String> clause, String defaultClause) {
        if (input.getSort() != null && !StringUtils.isEmpty(input.getSort())) {
            query.append(" ORDER BY ");
            if (input.getOrder() != null && !StringUtils.isEmpty(input.getOrder())) {
                Iterator<String> sorts = Arrays.asList(input.getSort().split(",")).iterator();
                do {
                    query.append(clause.get(sorts.next()));
                    if (input.getOrder().equalsIgnoreCase(CommonConstant.DESC)) {
                        query.append(" DESC");
                    } else {
                        query.append(" ASC");
                    }
                    if (sorts.hasNext()) {
                        query.append(",");
                    }
                } while (sorts.hasNext());
            } else {
                List<String> sorts = Arrays.asList(input.getSort().split(","))
                        .stream()
                        .map(s -> clause.get(s))
                        .collect(Collectors.toList());
                if (sorts.size() > 1) {
                    query.append(String.join(", ", sorts)).append(" ASC");
                } else {
                    query.append(clause.get(input.getSort())).append(" ASC");
                }
            }
        } else {
            if (AhmStringUtil.hasValue(defaultClause)) {
                query.append(" ORDER BY ").append(defaultClause);
            }
        }
    }
    

}
