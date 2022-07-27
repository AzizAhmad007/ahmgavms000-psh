/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dao.HrHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022ahmhrntmHdrotsempsDao")
public class Vms022AhmhrntmHdrotsempsDaoImpl extends HrHibernateDao<AhmhrntmHdrotsemps, AhmhrntmHdrotsempsPk> implements Vms022AhmhrntmHdrotsempsDao{    

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
    
        public List<Vms022VoMonitoring> getSearchData2() {
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

        Query query = getCurrentSession().createSQLQuery(sqlQuery.toString());

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
    
    public int countSearchData2() {
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
}
