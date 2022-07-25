/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.util.Vms022QueryUtil;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dao.HrHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.model.DefaultEntityImpl;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import java.math.BigDecimal;
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
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayik.op
 */
@Repository("vms022ahmhrntmHdrotsempsDao")
public class Vms022AhmhrntmHdrotsempsDaoImpl extends HrHibernateDao<AhmhrntmHdrotsemps, AhmhrntmHdrotsempsPk> implements Vms022AhmhrntmHdrotsempsDao {

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
    public List<Vms022VoMonitoring> getMonitoring(DtoParamPaging input, String userId, String type) {
        List<Vms022VoMonitoring> results = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        StringBuilder sqlQuery = new StringBuilder();
        Query query = null;

        LinkedHashMap<String, Object> reqObj = (LinkedHashMap<String, Object>) input.getSearch();
        String pic = reqObj.get("pic") != null ? reqObj.get("pic").toString() : "";
        String beginDate = reqObj.get("beginDate") != null ? reqObj.get("beginDate").toString() : "";
        String endDate = reqObj.get("endDate") != null ? reqObj.get("endDate").toString() : "";
        String plant = reqObj.get("plant") != null ? reqObj.get("plant").toString() : "PG10-00";
        String plantFilter = reqObj.get("plantFilter") != null ? reqObj.get("plantFilter").toString() : "";

        sortMap.put("ahmgavms022p01OutIdSort", "A.VOTSID");
        sortMap.put("ahmgavms022p01OutNameSort", "A.VNAME");
        sortMap.put("ahmgavms022p01NikSort", "A.VPERSID");
        sortMap.put("ahmgavms022p01OutTypeSort", "D.VPGBLNM");
        sortMap.put("ahmgavms022p01CompanySort", "F.VPGBLNM");
        sortMap.put("ahmgavms022p01StatusSort", "A.VOTSSTTS");
        sortMap.put("ahmgavms022p01PlantSort", "E.VPGBLNM");
        sortMap.put("ahmgavms022p01VacStatusSort", "A.VVACSTTS");
        sortMap.put("ahmgavms022p01BeginDateSort", "A.DBGNEFFDT");
        sortMap.put("ahmgavms022p01EndDateSort", "A.DENDEFFDT");
        sortMap.put("ahmgavms022p01PassNumberSort", "A.NAHMCARDORI");
        sortMap.put("ahmgavms022p01PassExpirySort", "A.DPASSEXP");
        sortMap.put("ahmgavms022p01ModifySort", "A.VMODI");
        sortMap.put("ahmgavms022p01ModifyDateSort", "A.DMODI");

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

        sqlQuery = Vms022QueryUtil.setSearchParamMonitoringTable(sqlQuery, input.getSearch(), "");
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
                Vms022VoMonitoring vo = new Vms022VoMonitoring();

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
                if (obj[11] != null) {
                    vo.setBeginDate((Date) obj[11]);
                    vo.setBeginDateText(DateUtil.dateToString((Date) obj[11], "dd-MMM-yyyy"));
                } else {
                    vo.setBeginDateText("");
                }
                if (obj[12] != null) {
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
                if (obj[33] != null) {
                    byte[] dt = null;
                    Blob tmp = (Blob) obj[33];

                    try {
                        dt = tmp.getBytes(1, (int) tmp.length());
                        vo.setFilePhoto(Base64.getEncoder().encodeToString(dt));

                    } catch (SQLException ex) {
                        Logger.getLogger(Vms022AhmhrntmHdrotsempsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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

        sqlQuery = Vms022QueryUtil.setSearchParamMonitoringTable(sqlQuery, input.getSearch(), "");

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

    public List<Vms022VoMonitoring> getSearchData(DtoParamPaging input, String userId) {
        List<Vms022VoMonitoring> result = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        StringBuilder sqlQuery = new StringBuilder();
        String tes = "";

        sortMap.put("ahmgavms022p01OutIdSort", "");
        sortMap.put("ahmgavms022p01OutNameSort", "");
        sortMap.put("ahmgavms022p01NikSort", "");
        sortMap.put("ahmgavms022p01OutTypeSort", "");
        sortMap.put("ahmgavms022p01CompanySort", "");
        sortMap.put("ahmgavms022p01StatusSort", "");
        sortMap.put("ahmgavms022p01PlantSort", "");
        sortMap.put("ahmgavms022p01VacStatusSort", "");
        sortMap.put("ahmgavms022p01BeginDateSort", "");
        sortMap.put("ahmgavms022p01EndDateSort", "");
        sortMap.put("ahmgavms022p01PassNumberSort", "");
        sortMap.put("ahmgavms022p01PassExpirySort", "");
        sortMap.put("ahmgavms022p01ModifySort", "");
        sortMap.put("ahmgavms022p01ModifyDateSort", "");

        sqlQuery.append("select distinct "
                + "    A.VOTSID as Outsource_ID, "
                + "    A.VNAME as Outsource_Name, "
                + "    A.VPERSID as NIK, "
                + "    A.VOTSTYPE as Outsource_Type, "
                + "    A.VCOMPANY as Outsource_Company, "
                + "    A.VOTSSTTS as Outsource_status, "
                + "    C.VPLANT as Plant, "
                + "    A.VVACSTTS as Covid19VaccineStatus, "
                + "    A.DBGNEFFDT as Periode_Begin, "
                + "    A.DENDEFFDT as Periode_End, "
                + "    A.NAHMCARDORI as PassNumberCard, "
                + "    A.DPASSEXP as PassCardExpDate, "
                + "    A.VMODI, "
                + "    A.DMODI, "
                + "    A.VEMPPHONE, "
                + "    A.VJOBDTL, "
                + "    A.VNOTE, "
                + "    A.VVACTYPE, "
                + "    A.DLASTVAC, "
                + "    A.VVACDTL, "
                + "    A.VNTVS, "
                + "    RAWTOHEX(A.ROTSEMPSHS) "
                + " from AHMHRNTM_HDROTSEMPS A, "
                + "AHMHRNTM_MSTPICOTS B, AHMHRNTM_DTLOTSREGS C "
                + " WHERE  "
                + "    (TRUNC(SYSDATE) NOT BETWEEN TRUNC(A.DBGNEFFDT) AND TRUNC(A.DENDEFFDT)) "
                + "    AND "
                + "( "
                + "                    UPPER(A.VOTSID) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VNAME) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VPERSID) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.DBGNEFFDT ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.DENDEFFDT ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.NAHMCARDID ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(B.VNRP ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VOTSTYPE ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VCOMPANY ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VOTSSTTS ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(C.VPLANT ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VVACSTTS ) LIKE UPPER('%'||''||'%')  "
                + "                    ) ");

        orderClause(input, sqlQuery, sortMap, null);
        Query query = getCurrentSession().createSQLQuery(sqlQuery.toString());

        if (StringUtils.isBlank("")) {
            query.setFirstResult(input.getOffset());
            query.setMaxResults(input.getLimit());
        }

        try {
            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object[] obj = (Object[]) lists.get(i);
                Vms022VoMonitoring vo = new Vms022VoMonitoring();

                vo.setOutId(obj[0] == null ? "" : obj[0] + "");
                vo.setOutName(obj[1] == null ? "" : obj[1] + "");
                vo.setPersId(obj[2] == null ? "" : obj[2] + "");
                vo.setOutType(obj[3] == null ? "" : obj[3] + ""); //kayanya yg ini buat id deh
                vo.setOutTypeName(obj[3] == null ? "" : obj[3] + "");
                vo.setCompany(obj[4] == null ? "" : obj[4] + ""); //kayanya yg ini buat id deh
                vo.setCompanyName(obj[4] == null ? "" : obj[4] + "");
                vo.setOutStatus(obj[5] == null ? "-" : obj[5] + "");
                vo.setArea(obj[6] == null ? "" : obj[6] + ""); //kayanya yg ini buat id deh
                vo.setAreaName(obj[6] == null ? "" : obj[6] + "");
                vo.setVacStatus(obj[7] == null ? "-" : obj[7] + "");
                vo.setBeginDate(obj[8] == null ? null : (Date) obj[8]);
                vo.setBeginDateText(obj[8] == null ? "" : DateUtil.dateToString((Date) obj[8], "dd-MMM-yyyy") + "");
                vo.setEndDate(obj[9] == null ? null : (Date) obj[9]);
                vo.setEndDateText(obj[9] == null ? "" : DateUtil.dateToString((Date) obj[9], "dd-MMM-yyyy") + "");
                vo.setPassNumber(obj[10] == null ? "" : obj[10] + "");
                vo.setPassExpiryDate(obj[11] == null ? null : (Date) obj[11]);
                vo.setPassExpiryDateText(obj[11] == null ? "-" : DateUtil.dateToString((Date) obj[11], "dd-MMM-yyyy") + "");
                vo.setModifyBy(obj[12] == null ? "" : obj[12] + ""); //ini vmodi
                vo.setDmodi(obj[13] == null ? null : (Date) obj[13]);
                vo.setModifyDateText(obj[13] == null ? "" : DateUtil.dateToString((Date) obj[13], "dd-MMM-yyyy") + ""); //ini dmodinya
                vo.setPhoneNo(obj[14] == null ? "-" : obj[14] + "");
                vo.setJob(obj[15] == null ? "-" : obj[15] + "");
                vo.setNote(obj[16] == null ? "-" : obj[16] + "");
                vo.setVacTypeName(obj[17] == null ? "-" : obj[17] + "");
                vo.setVacDateText(obj[18] == null ? "" : DateUtil.dateToString((Date) obj[18], "dd-MMM-yyyy") + "");
                vo.setVacSummary(obj[19] == null ? "-" : obj[19] + "");
                vo.setVacNote(obj[20] == null ? "-" : obj[20] + "");
                vo.setId(obj[21] == null ? "-" : obj[21] + "");//id

                result.add(vo);

            }
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    public int countSearchData(DtoParamPaging input, String userId) {
        BigDecimal resultCount = BigDecimal.ZERO;
        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append("SELECT COUNT(*) FROM (");
        sqlQuery.append("select distinct "
                + "    A.VOTSID as Outsource_ID, "
                + "    A.VNAME as Outsource_Name, "
                + "    A.VPERSID as NIK, "
                + "    A.VOTSTYPE as Outsource_Type, "
                + "    A.VCOMPANY as Outsource_Company, "
                + "    A.VOTSSTTS as Outsource_status, "
                + "    C.VPLANT as Plant, "
                + "    A.VVACSTTS as Covid19VaccineStatus, "
                + "    A.DBGNEFFDT as Periode_Begin, "
                + "    A.DENDEFFDT as Periode_End, "
                + "    A.NAHMCARDORI as PassNumberCard, "
                + "    A.DPASSEXP as PassCardExpDate, "
                + "    A.VMODI, "
                + "    A.DMODI "
                + "from AHMHRNTM_HDROTSEMPS A "
                + ", AHMHRNTM_MSTPICOTS B, AHMHRNTM_DTLOTSREGS C "
                + " WHERE  "
                + "    (TRUNC(SYSDATE) NOT BETWEEN TRUNC(A.DBGNEFFDT) AND TRUNC(A.DENDEFFDT)) "
                + "    AND "
                + "( "
                + "                    UPPER(A.VOTSID) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VNAME) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VPERSID) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.DBGNEFFDT ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.DENDEFFDT ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.NAHMCARDID ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(B.VNRP ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VOTSTYPE ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VCOMPANY ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VOTSSTTS ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(C.VPLANT ) LIKE UPPER('%'||''||'%') "
                + "                    or "
                + "                    UPPER(A.VVACSTTS ) LIKE UPPER('%'||''||'%')  "
                + "                    ) ");
        sqlQuery.append(" )");

        SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery.toString());

        try {
            resultCount = (BigDecimal) query.uniqueResult();

        } catch (SQLGrammarException e) {
        }
        return resultCount.intValue();
//        return 0;
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

    @Override
    public List<Vms022VoMonitoring> getDataExcel(DtoParamPaging dto) {
        List<Vms022VoMonitoring> results = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        StringBuilder sqlQuery = new StringBuilder();

//        sortMap.put("vnpwp", "VNPWP");
//        sortMap.put("vaddr", "VADDR");
//        sortMap.put("vcontaint", "VCONTAINT");
//        sortMap.put("mplantVplantid", "MPLANT_VPLANTID");
//        sortMap.put("dbegin", "DBEGIN");
//        sortMap.put("dend", "DEND");
//        sortMap.put("vitem", "VITEM");
        sqlQuery.append("select distinct "
                + " A.VOTSID, "
                + " A.VNAME, "
                + " A.VPERSID, "
                + " A.VOTSTYPE, "
                + " A.VCOMPANY, "
                + " A.VOTSSTTS, "
                + " C.VPLANT, "
                + " A.VVACSTTS, "
                + " A.DBGNEFFDT, "
                + " A.DENDEFFDT, "
                + " A.NAHMCARDORI, "
                + " A.DPASSEXP, "
                + " A.VMODI, "
                + " A.DMODI "
                + " FROM AHMHRNTM_HDROTSEMPS A, AHMHRNTM_DTLOTSREGS C "
                + " WHERE (TRUNC(SYSDATE) NOT BETWEEN TRUNC(A.DBGNEFFDT) AND TRUNC(A.DENDEFFDT)) "
                );
        Query query = getCurrentSession().createSQLQuery(sqlQuery.toString());

        orderClause(dto, sqlQuery, sortMap, null);

        try {
            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object[] obj = (Object[]) lists.get(i);
                Vms022VoMonitoring vo = new Vms022VoMonitoring();

                vo.setOutId(obj[0] == null ? "" : obj[0] + "");
                vo.setOutName(obj[1] == null ? "" : obj[1] + "");
                vo.setPersId(obj[2] == null ? "" : obj[2] + "");
                vo.setOutTypeName(obj[3] == null ? "" : obj[3] + "");
                vo.setCompanyName(obj[4] == null ? "" : obj[4] + "");
                vo.setOutStatus(obj[5] == null ? "" : obj[5] + "");
                vo.setAreaName(obj[6] == null ? "" : obj[6] + "");
                vo.setVacStatus(obj[7] == null ? "" : obj[7] + "");
                vo.setBeginDateText(obj[8] == null ? "" : obj[8] + "");
                vo.setEndDateText(obj[9] == null ? "" : obj[9] + "");
                vo.setPassNumber(obj[10] == null ? "" : obj[10] + "");
                vo.setPassExpiryDateText(obj[11] == null ? "" : obj[11] + "");
                vo.setModifyBy(obj[12] == null ? "" : obj[12] + "");
                vo.setModifyDateText(obj[13] == null ? "" : obj[13] + "");

                results.add(vo);

            }

            return results;
        } catch (Exception e) {
            return results;
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(DefaultEntityImpl deletedEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(DefaultEntityImpl newEntity, String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(DefaultEntityImpl editedEntity, String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(DtoParamPaging param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Criteria getCriteria(DtoParamPaging param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
