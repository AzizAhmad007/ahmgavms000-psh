/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitor;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dao.HrHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import id.co.ahm.jxf.model.DefaultEntityImpl;
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
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022ahmhrntmHdrotsempsDao")
public class Vms022AhmhrntmHdrotsempsDaoImpl extends HrHibernateDao<AhmhrntmHdrotsemps, AhmhrntmHdrotsempsPk> implements Vms022AhmhrntmHdrotsempsDao {

    private String getParam;

    @Override
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

        String votsid = AhmStringUtil.hasValue(input.getSearch().get("outId")) ? (input.getSearch().get("outId") + "").toUpperCase() : "";
        String vname = AhmStringUtil.hasValue(input.getSearch().get("outName")) ? (input.getSearch().get("outName") + "").toUpperCase() : "";
        String vpersid = AhmStringUtil.hasValue(input.getSearch().get("nik")) ? (input.getSearch().get("nik") + "").toUpperCase() : "";
        String begineff = AhmStringUtil.hasValue(input.getSearch().get("beginDate")) ? (input.getSearch().get("beginDate") + "").toUpperCase() : "";
        String endeff = AhmStringUtil.hasValue(input.getSearch().get("endDate")) ? (input.getSearch().get("endDate") + "").toUpperCase() : "";
        String idcard = AhmStringUtil.hasValue(input.getSearch().get("passNumber")) ? (input.getSearch().get("passNumber") + "").toUpperCase() : "";
        String outtype = AhmStringUtil.hasValue(input.getSearch().get("outType")) ? (input.getSearch().get("outType") + "").toUpperCase() : "";
        String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
        String outstat = AhmStringUtil.hasValue(input.getSearch().get("outStatus")) ? (input.getSearch().get("outStatus") + "").toUpperCase() : "";
        String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
        String vacstat = AhmStringUtil.hasValue(input.getSearch().get("vacStatus")) ? (input.getSearch().get("vacStatus") + "").toUpperCase() : "";
        String pic = AhmStringUtil.hasValue(input.getSearch().get("pic")) ? (input.getSearch().get("pic") + "").toUpperCase() : "";

        sqlQuery.append("SELECT  "
                + "    A.VOTSID as OUTID,  "
                + "    A.VNAME as OUTNAME,  "
                + "    A.VPERSID as PERSID,  "
                + "    A.VOTSTYPE as OUTTYPE,  "
                + "    D.VPGBLNM as OUTTYPENAME,   "
                + "    A.VCOMPANY as COMPANY,  "
                + "    CASE WHEN F.VPGBLNM is not null THEN COALESCE(F.VPGBLNM, '') "
                + "     ELSE COALESCE(Z.VVENDORDESC, '') END AS COMPANYNAME, "
                + "    A.VOTSSTTS as OUTSTATUS,  "
                + "    B.VPLANT as AREA,  "
                + "    E.VPGBLNM as AREANAME,  "
                + "    A.VVACSTTS as VACSTATUS,  "
                + "    A.DBGNEFFDT as BEGINDATE,  "
                + "    A.DENDEFFDT as ENDDATE,  "
                + "    A.NAHMCARDORI as PASSNUMBER,  "
                + "    A.DPASSEXP as PASSEXPIRY,  "
                + "    A.VMODI as MODIFIED,  "
                + "    A.DMODI as MODIFIEDDATE,  "
                + "    A.VEMPPHONE as PHONENO,  "
                + "    A.VPHOTOID as FILENAMEKTP,  "
                + "    A.VRBSUPLAY as SUPPLIER,  "
                + "    A.VJOBDTL as JOBDETAIL,  "
                + "    A.VABSRDR as ABSENCERDR,  "
                + "    A.VCANTEEN as CANTEEN,  "
                + "    A.VSECGATE as SECGATE,  "
                + "    A.VNOTE as NOTES,  "
                + "    A.VPHOTONAME as FILENAMEPHOTO,  "
                + "    A.VVACTYPE as VACTYPE,  "
                + "    G.VPGBLNM as VACTYPENAME,  "
                + "    A.DLASTVAC as VACDATE,  "
                + "    A.VVACDTL as VACSUMMARY,  "
                + "    A.VNTVS as VACNOTE,  "
                + "    A.VAPPDISCLM as DISCLAIMER,  "
                + "    RAWTOHEX(A.ROTSEMPSHS) as ID,  "
                + "    A.BPHOTO as FOTO  "
                + "FROM   "
                + "    AHMHRNTM_HDROTSEMPS A  ");

        sqlQuery.append("INNER JOIN ")
                .append("( ")
                .append("  SELECT "
                        + "    DISTINCT AA.VPLANT, AA.VOTSID, AA.VPERSID, "
                        + "    BB.VPGBLNM, BB.VPGBLCD "
                        + "  FROM AHMHRNTM_DTLOTSREGS AA, AHMHRNTM_DTLPRMGBLS BB, AHMHRNTM_MSTPICOTS CC"
                        + "  WHERE AA.VREGID = 'PLNT' "
                        + "  AND AA.VPLANT = BB.VPGBLCD "
                        + "  AND CC.VAREA = AA.VPLANT ");

        if (!StringUtils.isBlank(plant)) {
            sqlQuery.append(" AND AA.VPLANT = '")
                    .append(plant)
                    .append("' ");
        } else {
            sqlQuery.append(" AND AA.NSEQ = 1 ");
        }

        if (!StringUtils.isBlank(pic)) {
            sqlQuery.append(" AND CC.VNRP LIKE UPPER('%'||")
                    .append(pic)
                    .append("||'%') ");
        }

        sqlQuery.append(" ) B ON A.VOTSID = B.VOTSID and A.VPERSID = B.VPERSID ");

        sqlQuery.append("INNER JOIN AHMHRNTM_DTLPRMGBLS E on B.VPLANT = E.VPGBLCD  ")
                .append("INNER JOIN AHMHRNTM_DTLPRMGBLS D ON A.VOTSTYPE = D.VPGBLCD ")
                .append("LEFT JOIN AHMHRNTM_DTLPRMGBLS F ON A.VCOMPANY = F.VPGBLCD ")
                .append("LEFT JOIN AHMMOMSC_MSTVENDORS@ahmps Z ON A.VCOMPANY = Z.VVENDORID ")
                .append("LEFT JOIN AHMHRNTM_DTLPRMGBLS G ON A.VVACTYPE = G.VPGBLCD "
                        + "WHERE  "
                        + "    B.VOTSTYPE = A.VOTSTYPE "
                        + "    AND  "
                        + "    1 = 1 "
                        + "    AND  "
                        + "        UPPER(A.VOTSID) LIKE UPPER('%'||:votsid||'%')  ");

//                if (!StringUtils.isBlank(pic)) {
//                    sqlQuery.append(" AND A.VCREA = ")
//                            .append(userId).
//                            append("");
//                }
        sqlQuery.append("    AND  "
                + "        UPPER(A.VNAME) LIKE UPPER('%'||:vname||'%')  "
                + "    AND "
                + "        UPPER(A.VPERSID) LIKE UPPER('%'||:vpersid||'%') "
                + "    AND "
                + "        UPPER(A.NAHMCARDORI ) LIKE UPPER('%'||:idcard||'%') "
                + "    AND "
                + "        UPPER(A.VOTSTYPE ) LIKE UPPER('%'||:outtype||'%') "
                + "    AND "
                + "        UPPER(A.VCOMPANY ) LIKE UPPER('%'||:company||'%') "
                + "    AND "
                + "        (:outstat IS NULL OR UPPER(A.VOTSSTTS) LIKE '%'||:outstat||'%' ) "
                + "    AND "
                + "        (:plant IS NULL OR UPPER(B.VPLANT) LIKE '%'||:plant||'%' ) "
                + "    AND "
                + "        (:vacstat IS NULL OR UPPER(A.VVACSTTS) LIKE '%'||:vacstat||'%' )  "
        );
//        if (!StringUtils.isBlank(pic)) {
//            sqlQuery.append(" AND UPPER(C.VNRP) LIKE UPPER('%'||").append(pic).append("||'%') ");
//        }

        if (!StringUtils.isBlank(begineff) || !StringUtils.isBlank(endeff)) {
            sqlQuery.append(" AND (");

            if (!StringUtils.isBlank(begineff)) {
                sqlQuery.append(" ('")
                        .append(begineff)
                        .append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
            }
            if (!StringUtils.isBlank(endeff) && StringUtils.isBlank(begineff)) {
                sqlQuery.append(" ('")
                        .append(endeff)
                        .append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
            } else {
                sqlQuery.append(" OR ('")
                        .append(endeff)
                        .append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
            }
            if (!StringUtils.isBlank(begineff) && !StringUtils.isBlank(endeff)) {
                sqlQuery.append(" OR (A.DBGNEFFDT BETWEEN '")
                        .append(begineff).append("' AND '").append(endeff).append("') ")
                        .append(" OR (A.DENDEFFDT BETWEEN '")
                        .append(begineff).append("' AND '").append(endeff).append("') ");
            }
            sqlQuery.append(" ) ");
        }

        voSetter(input);

        orderClause(input, sqlQuery, sortMap, getParam);

        Query query = getCurrentSession().createSQLQuery(sqlQuery.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());

        query.setParameter("votsid", votsid)
                .setParameter("vname", vname)
                .setParameter("vpersid", vpersid)
                //                .setParameter("begineff", begineff)
                //                .setParameter("endeff", endeff)
                .setParameter("idcard", idcard)
                .setParameter("outtype", outtype)
                .setParameter("company", company)
                .setParameter("outstat", outstat)
                .setParameter("plant", plant)
                .setParameter("vacstat", vacstat);

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
                vo.setRowNum(i);
                vo.setGateName("=========testing");

                result.add(vo);

            }
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    @Override
    public int countSearchData(DtoParamPaging input, String userId) {
        BigDecimal resultCount = BigDecimal.ZERO;
        StringBuilder sqlQuery = new StringBuilder();

        String votsid = AhmStringUtil.hasValue(input.getSearch().get("outId")) ? (input.getSearch().get("outId") + "").toUpperCase() : "";
        String vname = AhmStringUtil.hasValue(input.getSearch().get("outName")) ? (input.getSearch().get("outName") + "").toUpperCase() : "";
        String vpersid = AhmStringUtil.hasValue(input.getSearch().get("nik")) ? (input.getSearch().get("nik") + "").toUpperCase() : "";
        String begineff = AhmStringUtil.hasValue(input.getSearch().get("beginDate")) ? (input.getSearch().get("beginDate") + "").toUpperCase() : "";
        String endeff = AhmStringUtil.hasValue(input.getSearch().get("endDate")) ? (input.getSearch().get("endDate") + "").toUpperCase() : "";
        String idcard = AhmStringUtil.hasValue(input.getSearch().get("passNumber")) ? (input.getSearch().get("passNumber") + "").toUpperCase() : "";
        String outtype = AhmStringUtil.hasValue(input.getSearch().get("outType")) ? (input.getSearch().get("outType") + "").toUpperCase() : "";
        String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
        String outstat = AhmStringUtil.hasValue(input.getSearch().get("outStatus")) ? (input.getSearch().get("outStatus") + "").toUpperCase() : "";
        String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
        String vacstat = AhmStringUtil.hasValue(input.getSearch().get("vacStatus")) ? (input.getSearch().get("vacStatus") + "").toUpperCase() : "";
        String pic = AhmStringUtil.hasValue(input.getSearch().get("pic")) ? (input.getSearch().get("pic") + "").toUpperCase() : "";

        sqlQuery.append("SELECT COUNT(*) FROM (");
        sqlQuery.append("SELECT  "
                + "    A.VOTSID as OUTID,  "
                + "    A.VNAME as OUTNAME,  "
                + "    A.VPERSID as PERSID,  "
                + "    A.VOTSTYPE as OUTTYPE,  "
                + "    D.VPGBLNM as OUTTYPENAME,   "
                + "    A.VCOMPANY as COMPANY,  "
                + "    CASE WHEN F.VPGBLNM is not null THEN COALESCE(F.VPGBLNM, '') "
                + "     ELSE COALESCE(Z.VVENDORDESC, '') END AS COMPANYNAME, "
                + "    A.VOTSSTTS as OUTSTATUS,  "
                + "    B.VPLANT as AREA,  "
                + "    E.VPGBLNM as AREANAME,  "
                + "    A.VVACSTTS as VACSTATUS,  "
                + "    A.DBGNEFFDT as BEGINDATE,  "
                + "    A.DENDEFFDT as ENDDATE,  "
                + "    A.NAHMCARDORI as PASSNUMBER,  "
                + "    A.DPASSEXP as PASSEXPIRY,  "
                + "    A.VMODI as MODIFIED,  "
                + "    A.DMODI as MODIFIEDDATE,  "
                + "    A.VEMPPHONE as PHONENO,  "
                + "    A.VPHOTOID as FILENAMEKTP,  "
                + "    A.VRBSUPLAY as SUPPLIER,  "
                + "    A.VJOBDTL as JOBDETAIL,  "
                + "    A.VABSRDR as ABSENCERDR,  "
                + "    A.VCANTEEN as CANTEEN,  "
                + "    A.VSECGATE as SECGATE,  "
                + "    A.VNOTE as NOTES,  "
                + "    A.VPHOTONAME as FILENAMEPHOTO,  "
                + "    A.VVACTYPE as VACTYPE,  "
                + "    G.VPGBLNM as VACTYPENAME,  "
                + "    A.DLASTVAC as VACDATE,  "
                + "    A.VVACDTL as VACSUMMARY,  "
                + "    A.VNTVS as VACNOTE,  "
                + "    A.VAPPDISCLM as DISCLAIMER,  "
                + "    RAWTOHEX(A.ROTSEMPSHS) as ID,  "
                + "    A.BPHOTO as FOTO  "
                + "FROM   "
                + "    AHMHRNTM_HDROTSEMPS A  ");

        sqlQuery.append("INNER JOIN ")
                .append("( ")
                .append("  SELECT "
                        + "    DISTINCT AA.VPLANT, AA.VOTSID, AA.VPERSID, "
                        + "    BB.VPGBLNM, BB.VPGBLCD "
                        + "  FROM AHMHRNTM_DTLOTSREGS AA, AHMHRNTM_DTLPRMGBLS BB, AHMHRNTM_MSTPICOTS CC"
                        + "  WHERE AA.VREGID = 'PLNT' "
                        + "  AND AA.VPLANT = BB.VPGBLCD "
                        + "  AND CC.VAREA = AA.VPLANT ");

        if (!StringUtils.isBlank(plant)) {
            sqlQuery.append(" AND AA.VPLANT = '")
                    .append(plant)
                    .append("' ");
        } else {
            sqlQuery.append(" AND AA.NSEQ = 1 ");
        }

        if (!StringUtils.isBlank(pic)) {
            sqlQuery.append(" AND CC.VNRP LIKE UPPER('%'||")
                    .append(pic)
                    .append("||'%') ");
        }

        sqlQuery.append(" ) B ON A.VOTSID = B.VOTSID and A.VPERSID = B.VPERSID ");

        sqlQuery.append("INNER JOIN AHMHRNTM_DTLPRMGBLS E on B.VPLANT = E.VPGBLCD  ")
                .append("INNER JOIN AHMHRNTM_DTLPRMGBLS D ON A.VOTSTYPE = D.VPGBLCD ")
                .append("LEFT JOIN AHMHRNTM_DTLPRMGBLS F ON A.VCOMPANY = F.VPGBLCD ")
                .append("LEFT JOIN AHMMOMSC_MSTVENDORS@ahmps Z ON A.VCOMPANY = Z.VVENDORID ")
                .append("LEFT JOIN AHMHRNTM_DTLPRMGBLS G ON A.VVACTYPE = G.VPGBLCD "
                        + "WHERE  "
                        + "    B.VOTSTYPE = A.VOTSTYPE "
                        + "    AND  "
                        + "    1 = 1 "
                        + "    AND  "
                        + "        UPPER(A.VOTSID) LIKE UPPER('%'||:votsid||'%')  ");

//                if (!StringUtils.isBlank(pic)) {
//                    sqlQuery.append(" AND A.VCREA = ")
//                            .append(userId).
//                            append("");
//                }
        sqlQuery.append("    AND  "
                + "        UPPER(A.VNAME) LIKE UPPER('%'||:vname||'%')  "
                + "    AND "
                + "        UPPER(A.VPERSID) LIKE UPPER('%'||:vpersid||'%') "
                + "    AND "
                + "        UPPER(A.NAHMCARDORI ) LIKE UPPER('%'||:idcard||'%') "
                + "    AND "
                + "        UPPER(A.VOTSTYPE ) LIKE UPPER('%'||:outtype||'%') "
                + "    AND "
                + "        UPPER(A.VCOMPANY ) LIKE UPPER('%'||:company||'%') "
                + "    AND "
                + "        (:outstat IS NULL OR UPPER(A.VOTSSTTS) LIKE '%'||:outstat||'%' ) "
                + "    AND "
                + "        (:plant IS NULL OR UPPER(B.VPLANT) LIKE '%'||:plant||'%' ) "
                + "    AND "
                + "        (:vacstat IS NULL OR UPPER(A.VVACSTTS) LIKE '%'||:vacstat||'%' )  "
        );
//        if (!StringUtils.isBlank(pic)) {
//            sqlQuery.append(" AND UPPER(C.VNRP) LIKE UPPER('%'||").append(pic).append("||'%') ");
//        }

        if (!StringUtils.isBlank(begineff) || !StringUtils.isBlank(endeff)) {
            sqlQuery.append(" AND (");

            if (!StringUtils.isBlank(begineff)) {
                sqlQuery.append(" ('")
                        .append(begineff)
                        .append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
            }
            if (!StringUtils.isBlank(endeff) && StringUtils.isBlank(begineff)) {
                sqlQuery.append(" ('")
                        .append(endeff)
                        .append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
            } else {
                sqlQuery.append(" OR ('")
                        .append(endeff)
                        .append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT)");
            }
            if (!StringUtils.isBlank(begineff) && !StringUtils.isBlank(endeff)) {
                sqlQuery.append(" OR (A.DBGNEFFDT BETWEEN '")
                        .append(begineff).append("' AND '").append(endeff).append("') ")
                        .append(" OR (A.DENDEFFDT BETWEEN '")
                        .append(begineff).append("' AND '").append(endeff).append("') ");
            }
            sqlQuery.append(" ) ");
        }

        sqlQuery.append(" )");

        SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery.toString());
//        Query query = getCurrentSession().createSQLQuery(sqlQuery.toString());

        query.setParameter("votsid", votsid)
                .setParameter("vname", vname)
                .setParameter("vpersid", vpersid)
                //                .setParameter("begineff", begineff)
                //                .setParameter("endeff", endeff)
                .setParameter("idcard", idcard)
                .setParameter("outtype", outtype)
                .setParameter("company", company)
                .setParameter("outstat", outstat)
                .setParameter("plant", plant)
                .setParameter("vacstat", vacstat);

        try {
            resultCount = (BigDecimal) query.uniqueResult();

        } catch (SQLGrammarException e) {
        }
        return resultCount.intValue();
//        return 0;
    }

    private void orderClause(DtoParamPaging input, StringBuilder query, Map<String, String> clause, String param) {
        if (input.getSort() != null && !StringUtils.isEmpty(input.getSort())) {
            query.append(" ORDER BY ");
            query.append(param + " ");
            if (input.getOrder() != null && !StringUtils.isEmpty(input.getOrder())) {
                query.append(input.getOrder() + " ");
            }
        }

    }

    private void voSetter(DtoParamPaging input) {
        try {
            if (input.getSort() == null) {

            } else {
                String param = input.getSort();

                switch (param) {
                    case "outId":
//                input.setSort("A.VOTSID");
                        getParam = "A.VOTSID";
                        break;
                    case "outName":
//                input.setSort("A.VNAME");
                        getParam = "A.VNAME";
                        break;
                    case "persId":
//                input.setSort("A.VPERSID");
                        getParam = "A.VPERSID";
                        break;
                    case "outTypeName":
//                input.setSort("D.VPGBLNM");
                        getParam = "D.VPGBLNM";
                        break;
                    case "companyName":
//                input.setSort("CASE WHEN F.VPGBLNM is not null THEN COALESCE(F.VPGBLNM, '')"
//                        + "     ELSE COALESCE(Z.VVENDORDESC, '') END");
                        getParam = "CASE WHEN F.VPGBLNM is not null THEN COALESCE(F.VPGBLNM, '')"
                                + "     ELSE COALESCE(Z.VVENDORDESC, '') END";
                        break;
                    case "outStatus":
//                input.setSort("A.VOTSSTTS");
                        getParam = "A.VOTSSTTS";
                        break;
                    case "areaName":
//                input.setSort("E.VPGBLNM");
                        getParam = "E.VPGBLNM";
                        break;
                    case "vacStatus":
//                input.setSort("A.VVACSTTS");
                        getParam = "A.VVACSTTS";
                        break;
                    case "beginDateText":
//                input.setSort("A.DBGNEFFDT");
                        getParam = "A.DBGNEFFDT";
                        break;
                    case "endDateText":
//                input.setSort("A.DENDEFFDT");
                        getParam = "A.DENDEFFDT";
                        break;
                    case "passNumber":
//                input.setSort("A.NAHMCARDORI");
                        getParam = "A.NAHMCARDORI";
                        break;
                    case "passExpiryDateText":
//                input.setSort("A.DPASSEXP");
                        getParam = "A.DPASSEXP";
                        break;
                    case "modifyBy":
//                input.setSort("A.VMODI");
                        getParam = "A.VMODI";
                        break;
                    case "modifyDateText":
//                input.setSort("A.DMODI");
                        getParam = "A.DMODI";
                        break;
                    default:
                        getParam = null;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
