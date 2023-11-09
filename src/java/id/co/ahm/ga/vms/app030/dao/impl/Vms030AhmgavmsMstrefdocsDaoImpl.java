/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsMstrefdocs;
import id.co.ahm.ga.vms.app000.model.AhmgavmsMstrefdocsPk;
import static id.co.ahm.ga.vms.app030.constant.Vms030Constant.GET_LINK;
import id.co.ahm.ga.vms.app030.vo.Vms030VoTableResult;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import id.co.ahm.ga.vms.app030.dao.Vms030AhmgavmsMstrefdocsDao;
import id.co.ahm.ga.vms.app030.vo.Vms030VoMonitoringEmail;
import id.co.ahm.jxf.security.CryptoSecurity;
import id.co.ahm.jxf.util.AhmStringUtil;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nurvan Afandi
 */
@Repository("vms030AhmgavmsMstrefdocsDao")
public class Vms030AhmgavmsMstrefdocsDaoImpl extends DefaultHibernateDao<AhmgavmsMstrefdocs, AhmgavmsMstrefdocsPk> 
        implements Vms030AhmgavmsMstrefdocsDao {
    
    private String getParam;
    
    @Override
    public List<Vms030VoTableResult> getTable(DtoParamPaging input) {
        List<Vms030VoTableResult> result = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        
        sortMap.put("ahmgavms030VisitorTypeSort", "");
        sortMap.put("ahmgavms030NoDocSort", "");
        sortMap.put("ahmgavms030StatusSort", "");
        sortMap.put("ahmgavms030DocTypeSort","");
        sortMap.put("ahmgavms030PlantSort", "");
        sortMap.put("ahmgavms030CompanySort", "");
        sortMap.put("ahmgavms030NrpSort", "");
        sortMap.put("ahmgavms030EmailSort", "");
        sortMap.put("ahmgavms030DateStartSort", "");
        sortMap.put("ahmgavms030DateEndSort", "");
        
        String noDoc = AhmStringUtil.hasValue(input.getSearch().get("noDoc")) ? (input.getSearch().get("noDoc") + "").toUpperCase() : "";
        String plant = AhmStringUtil.hasValue(input.getSearch().get("plantCode")) ? (input.getSearch().get("plantCode") + "").toUpperCase() : "";
        String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorTypeCode")) ? (input.getSearch().get("visitorTypeCode") + "").toUpperCase() : "";
        String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
        String status = AhmStringUtil.hasValue(input.getSearch().get("statusCode")) ? (input.getSearch().get("statusCode") + "").toUpperCase() : "";
        String nrp = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
        String email = AhmStringUtil.hasValue(input.getSearch().get("email")) ? (input.getSearch().get("email") + "").toLowerCase() : "";
        String docType = AhmStringUtil.hasValue(input.getSearch().get("docType")) ? (input.getSearch().get("docType") + "").toUpperCase() : "";
        String dateStart = AhmStringUtil.hasValue(input.getSearch().get("dateStart")) ? (input.getSearch().get("dateStart") + "").toUpperCase() : "";
        String dateEnd = AhmStringUtil.hasValue(input.getSearch().get("dateEnd")) ? (input.getSearch().get("dateEnd") + "").toUpperCase() : "";
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM (");
        
        sql.append("SELECT "
                + "     A.VTYPE AS VTYPE, "
                + "     (SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPE_PRTCP' "
                + "         AND B.VITEMCODE = A.VTYPE) VTYPEDESC, "
                + "     A.VSTATUS AS VSTATUS, "
                + "     (CASE "
                + "         WHEN A.VSTATUS = 'Y' THEN "
                + "             'AKTIF' "
                + "         WHEN A.VSTATUS = 'N' THEN "
                + "             'TIDAK AKTIF' "
                + "         WHEN A.VSTATUS = 'D' THEN "
                + "             'DRAFT' "
                + "         ELSE "
                + "             '-' "
                + "     END) VSTATUSDESC, "
                + "     A.VREFDOCNO AS VREFDOCNO, "
                + "     A.VWORKDESC AS VWORKDESC, "
                + "     (SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPDOC_SI' "
                + "         AND B.VITEMCODE = 'MEMO') VDOCTYPE, "
                + "      A.VPLANTID AS VPLANTID, "
                + "     (SELECT C.VDESC FROM AHMMOSCD_MSTAGPLANTS C "
                + "         WHERE C.VPLANTVAR2 = A.VPLANTID) VPLANTDESC, "
                + "     A.VCOMPANY AS VCOMPANY, "
                + "     A.VPICNRP AS VPICNRP, "
                + "     D.VEMAIL AS VEMAIL, "
                + "     A.DWORKSTART AS DWORKSTART, "
                + "     A.DWORKEND AS DWORKEND "
                + "FROM AHMGAVMS_MSTREFDOCS A "
                + "JOIN AHMMOERP_MSTKARYAWANS D "
                + "     ON A.VPICNRP = TO_CHAR(D.IIDNRP) "
                + "UNION "
                + "SELECT "
                + "     (SELECT B.VITEMCODE FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPE_PRTCP' "
                + "         AND B.VITEMCODE = 'KTR') VTYPE, "
                + "     (SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPE_PRTCP' "
                + "         AND B.VITEMCODE = 'KTR') VTYPEDESC, "
                + "     (CASE "
                + "         WHEN TRUNC(A.DENDJOB) < TRUNC(SYSDATE) THEN "
                + "             'N' "
                + "         WHEN TRUNC(A.DSTARTJOB) < TRUNC(A.DENDJOB) THEN "
                + "             'Y' "
                + "         ELSE "
                + "             '-' "
                + "     END) VSTATUS, "
                + "     (CASE "
                + "         WHEN TRUNC(A.DENDJOB) < TRUNC(SYSDATE) THEN "
                + "             'TIDAK AKTIF' "
                + "         WHEN TRUNC(A.DSTARTJOB) < TRUNC(A.DENDJOB) THEN "
                + "             'AKTIF' "
                + "         ELSE "
                + "             '-' "
                + "     END) VSTATUSDESC, "
                + "     A.VIKPID AS VREFDOCNO, "
                + "     A.VPROJDTL AS VWORKDESC, "
                + "     (SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPDOC_SI' "
                + "         AND B.VITEMCODE = 'IKP') VDOCTYPE, "
                + "     A.VPLANTID AS VPLANTID, "
                + "     (SELECT C.VDESC FROM AHMMOSCD_MSTAGPLANTS C "
                + "         WHERE C.VPLANTVAR2 = A.VPLANTID) VPLANTDESC, "
                + "     A.VSUPPLDESC AS VCOMPANY, "
                + "     TO_CHAR(A.VPICNRPID) AS VPICNRP, "
                + "     D.VEMAIL AS VEMAIL, "
                + "     A.DSTARTJOB AS DWORKSTART, "
                + "     A.DENDJOB AS DWORKEND "
                + "FROM AHMGAWPM_HDRIKPS A "
                + "JOIN AHMMOERP_MSTKARYAWANS D "
                + "     ON TO_CHAR(A.VPICNRPID) = TO_CHAR(D.IIDNRP) "
                + ") ");
        
        sql.append("WHERE 1 = 1 ");
        
        if (!StringUtils.isBlank(visitorType)) {
            sql.append("AND VTYPE = '")
                    .append(visitorType)
                    .append("' ");
        }
        if (!StringUtils.isBlank(noDoc)) {
            sql.append("AND VREFDOCNO LIKE '%")
                    .append(noDoc)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(status)) {
            sql.append("AND VSTATUS = '")
                    .append(status)
                    .append("' ");
        }
        if (!StringUtils.isBlank(docType)) {
            sql.append("AND VDOCTYPE = '")
                    .append(docType)
                    .append("' ");
        }
        if (!StringUtils.isBlank(plant)) {
            sql.append("AND VPLANTID = '")
                    .append(plant)
                    .append("' ");
        }
        if (!StringUtils.isBlank(company)) {
            sql.append("AND VCOMPANY LIKE '%")
                    .append(company)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(nrp)) {
            sql.append("AND VPICNRP LIKE '%")
                    .append(nrp)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(dateStart)) {
            sql.append("AND DWORKSTART BETWEEN TO_DATE('")
                .append(dateStart)
                .append("', 'DD-MM-YYYY') ")
                .append("AND TO_DATE('")
                .append(dateEnd)
                .append("', 'DD-MM-YYYY') ");
        }
        if (!StringUtils.isBlank(dateEnd)) {
            sql.append("AND DWORKEND BETWEEN TO_DATE('")
                    .append(dateStart)
                    .append("', 'DD-MM-YYYY') AND TO_DATE('")
                    .append(dateEnd)
                    .append("', 'DD-MM-YYYY') ");
        }
        if (!StringUtils.isBlank(email)) {
            sql.append("AND VEMAIL LIKE '%")
                    .append(email)
                    .append("%' ");
        }
        
        voSetter(input);
        orderClause(input, sql, sortMap, getParam);
        
        Query query = getCurrentSession().createSQLQuery(sql.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());
        try {
            List<Object[]> list = query.list();
        
            if (list.size() > 0) {
                Object[] obj;
                int i = 0;
                for(Object object : list) {
                    obj = (Object[]) object;
                    Vms030VoTableResult vo = new Vms030VoTableResult();
                    vo.setVisitorTypeCode((String) obj[0]);
                    vo.setVisitorType((String) obj[1]);
                    vo.setStatusCode((String) obj[2]);
                    vo.setStatus((String) obj[3]);
                    vo.setNoDoc((String) obj[4]);
                    vo.setWorkDesc((String) obj[5]);
                    vo.setDocType((String) obj[6]);
                    vo.setPlantCode((String) obj[7]);
                    vo.setPlant((String) obj[8]);
                    vo.setCompany((String) obj[9]);
                    vo.setNrp((String) obj[10]);
                    vo.setEmail((String) obj[11]);
                    vo.setDateStart(((Date) obj[12]));
                    vo.setDateStartText(DateUtil.dateToString((Date) obj[12], "dd-MMM-yyyy"));
                    vo.setDateEnd(((Date) obj[13]));
                    vo.setDateEndText(DateUtil.dateToString((Date) obj[13], "dd-MMM-yyyy"));
                    vo.setRowNum(i);
                    i++;
                    vo.setLink(getLink(noDoc));
                    result.add(vo);
                }
            }
        } catch (Exception e) {
            return result;
        }
        return result;   
    }

    @Override
    public int getTableCount(DtoParamPaging input) {
        try {
            String noDoc = AhmStringUtil.hasValue(input.getSearch().get("noDoc")) ? (input.getSearch().get("noDoc") + "").toUpperCase() : "";
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plantCode")) ? (input.getSearch().get("plantCode") + "").toUpperCase() : "";
            String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorTypeCode")) ? (input.getSearch().get("visitorTypeCode") + "").toUpperCase() : "";
            String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
            String status = AhmStringUtil.hasValue(input.getSearch().get("statusCode")) ? (input.getSearch().get("statusCode") + "").toUpperCase() : "";
            String nrp = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
            String email = AhmStringUtil.hasValue(input.getSearch().get("email")) ? (input.getSearch().get("email") + "").toLowerCase() : "";
            String docType = AhmStringUtil.hasValue(input.getSearch().get("docType")) ? (input.getSearch().get("docType") + "").toUpperCase() : "";
            String dateStart = AhmStringUtil.hasValue(input.getSearch().get("dateStart")) ? (input.getSearch().get("dateStart") + "").toUpperCase() : "";
            String dateEnd = AhmStringUtil.hasValue(input.getSearch().get("dateEnd")) ? (input.getSearch().get("dateEnd") + "").toUpperCase() : "";

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT COUNT (0) FROM ( ");
            
            sql.append("SELECT "
                + "     A.VTYPE AS VTYPE, "
                + "     (SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPE_PRTCP' "
                + "         AND B.VITEMCODE = A.VTYPE) VTYPEDESC, "
                + "     A.VSTATUS AS VSTATUS, "
                + "     (CASE "
                + "         WHEN A.VSTATUS = 'Y' THEN "
                + "             'AKTIF' "
                + "         WHEN A.VSTATUS = 'N' THEN "
                + "             'TIDAK AKTIF' "
                + "         WHEN A.VSTATUS = 'D' THEN "
                + "             'DRAFT' "
                + "         ELSE "
                + "             '-' "
                + "     END) VSTATUSDESC, "
                + "     A.VREFDOCNO AS VREFDOCNO, "
                + "     A.VWORKDESC AS VWORKDESC, "
                + "     (SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPDOC_SI' "
                + "         AND B.VITEMCODE = 'MEMO') VDOCTYPE, "
                + "      A.VPLANTID AS VPLANTID, "
                + "     (SELECT C.VDESC FROM AHMMOSCD_MSTAGPLANTS C "
                + "         WHERE C.VPLANTVAR2 = A.VPLANTID) VPLANTDESC, "
                + "     A.VCOMPANY AS VCOMPANY, "
                + "     A.VPICNRP AS VPICNRP, "
                + "     D.VEMAIL AS VEMAIL, "
                + "     A.DWORKSTART AS DWORKSTART, "
                + "     A.DWORKEND AS DWORKEND "
                + "FROM AHMGAVMS_MSTREFDOCS A "
                + "JOIN AHMMOERP_MSTKARYAWANS D "
                + "     ON A.VPICNRP = TO_CHAR(D.IIDNRP) "
                + "UNION "
                + "SELECT "
                + "     (SELECT B.VITEMCODE FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPE_PRTCP' "
                + "         AND B.VITEMCODE = 'KTR') VTYPE, "
                + "     (SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPE_PRTCP' "
                + "         AND B.VITEMCODE = 'KTR') VTYPEDESC, "
                + "     (CASE "
                + "         WHEN TRUNC(A.DENDJOB) < TRUNC(SYSDATE) THEN "
                + "             'N' "
                + "         WHEN TRUNC(A.DSTARTJOB) < TRUNC(A.DENDJOB) THEN "
                + "             'Y' "
                + "         ELSE "
                + "             '-' "
                + "     END) VSTATUS, "
                + "     (CASE "
                + "         WHEN TRUNC(A.DENDJOB) < TRUNC(SYSDATE) THEN "
                + "             'TIDAK AKTIF' "
                + "         WHEN TRUNC(A.DSTARTJOB) < TRUNC(A.DENDJOB) THEN "
                + "             'AKTIF' "
                + "         ELSE "
                + "             '-' "
                + "     END) VSTATUSDESC, "
                + "     A.VIKPID AS VREFDOCNO, "
                + "     A.VPROJDTL AS VWORKDESC, "
                + "     (SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "         WHERE B.RSET_VID = 'VMS_TYPDOC_SI' "
                + "         AND B.VITEMCODE = 'IKP') VDOCTYPE, "
                + "     A.VPLANTID AS VPLANTID, "
                + "     (SELECT C.VDESC FROM AHMMOSCD_MSTAGPLANTS C "
                + "         WHERE C.VPLANTVAR2 = A.VPLANTID) VPLANTDESC, "
                + "     A.VSUPPLDESC AS VCOMPANY, "
                + "     TO_CHAR(A.VPICNRPID) AS VPICNRP, "
                + "     D.VEMAIL AS VEMAIL, "
                + "     A.DSTARTJOB AS DWORKSTART, "
                + "     A.DENDJOB AS DWORKEND "
                + "FROM AHMGAWPM_HDRIKPS A "
                + "JOIN AHMMOERP_MSTKARYAWANS D "
                + "     ON TO_CHAR(A.VPICNRPID) = TO_CHAR(D.IIDNRP) "
                + ") ");
        
            sql.append("WHERE 1 = 1 ");
          
            if (!StringUtils.isBlank(visitorType)) {
            sql.append("AND VTYPE = '")
                    .append(visitorType)
                    .append("' ");
            }
            if (!StringUtils.isBlank(noDoc)) {
                sql.append("AND VREFDOCNO LIKE '%")
                        .append(noDoc)
                        .append("%' ");
            }
            if (!StringUtils.isBlank(status)) {
                sql.append("AND VSTATUS = '")
                        .append(status)
                        .append("' ");
            }
            if (!StringUtils.isBlank(docType)) {
                sql.append("AND VDOCTYPE = '")
                        .append(docType)
                        .append("' ");
            }
            if (!StringUtils.isBlank(plant)) {
                sql.append("AND VPLANTID = '")
                        .append(plant)
                        .append("' ");
            }
            if (!StringUtils.isBlank(company)) {
                sql.append("AND VCOMPANY LIKE '%")
                        .append(company)
                        .append("%' ");
            }
            if (!StringUtils.isBlank(nrp)) {
                sql.append("AND VPICNRP LIKE '%")
                        .append(nrp)
                        .append("%' ");
            }
            if (!StringUtils.isBlank(dateStart)) {
                sql.append("AND DWORKSTART BETWEEN TO_DATE('")
                    .append(dateStart)
                    .append("', 'DD-MM-YYYY') ")
                    .append("AND TO_DATE('")
                    .append(dateEnd)
                    .append("', 'DD-MM-YYYY') ");
            }
            if (!StringUtils.isBlank(dateEnd)) {
                sql.append("AND DWORKEND BETWEEN TO_DATE('")
                        .append(dateStart)
                        .append("', 'DD-MM-YYYY') AND TO_DATE('")
                        .append(dateEnd)
                        .append("', 'DD-MM-YYYY') ");
            }
            if (!StringUtils.isBlank(email)) {
                sql.append("AND VEMAIL LIKE '%")
                        .append(email)
                        .append("%' ");
            }

            Query query = getCurrentSession().createSQLQuery(sql.toString())
                    .setFirstResult(input.getOffset())
                    .setMaxResults(input.getLimit());
            List<BigDecimal> list = query.list();
            return (Integer) list.get(0).intValueExact();
        } catch (Exception e) {
            return 0;
        }
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
                    case "visitorType":
                        getParam = "VTYPEDESC";
                        break;
                    case "noDoc":
                        getParam = "VREFDOCNO";
                        break;
                    case "status":
                        getParam = "VSTATUSDESC";
                        break;
                    case "docType":
                        getParam = "VDOCTYPE";
                        break;        
                    case "plant":
                        getParam = "VPLANTDESC";
                        break;
                    case "company":
                        getParam = "VCOMPANY";
                        break;
                    case "nrp":
                        getParam = "VPICNRP";
                        break;
                    case "dateStart":
                        getParam = "DWORKSTART";
                        break;
                    case "dateEnd":
                        getParam = "DWORKEND";
                        break;
                    case "email":
                        getParam = "VEMAIL";
                        break;
                    default:
                        getParam = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vms030VoMonitoringEmail> getMonitoringEmail(DtoParamPaging input) {
        List<Vms030VoMonitoringEmail> result = new ArrayList<>();
        
        String noDoc = AhmStringUtil.hasValue(input.getSearch().get("noDoc")) ? (input.getSearch().get("noDoc") + "").toUpperCase() : "";
        
        StringBuilder sql = new StringBuilder("SELECT VCODE, DCREA, VTO, "
                + "(CASE "
                + "WHEN VFLAG = '1' THEN "
                + "     'Success' "
                + "WHEN VFLAG = '0' THEN "
                + "     'Failed' "
                + "ELSE "
                + "     '-' "
                + "END) VSTATUS "
                + "FROM AHMGAVMS_LOGEMAILS "
                + "WHERE 1 = 1 ");
        if (!StringUtils.isBlank(noDoc)) {
            sql.append("AND VCODE = '")
                    .append(noDoc)
                    .append("' ");
        }
        
        Query query = getCurrentSession().createSQLQuery(sql.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());
        try {
            List<Object[]> list = query.list();
            if (list.size() > 0) {
                Object[] obj;
                int i = 0;
                for (Object object : list) {
                    obj = (Object[]) object;
                    Vms030VoMonitoringEmail vo = new Vms030VoMonitoringEmail();
                    vo.setNoDoc((String) obj[0]);
                    vo.setDateSend((String) DateUtil.dateToString((Date) obj[1], "dd-MMM-yyyy"));
                    vo.setEmailTo((String) obj[2]);
                    vo.setStatus((String) obj[3]);
                    vo.setRowNum(i);
                    result.add(vo);
                }
            }
            return result;
        } catch (Exception e) {
            return result;
        }
    }
    
    @Override
    public String getLink(String noDoc) {
        StringBuilder sql = new StringBuilder(GET_LINK);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<String> list = sqlQuery.list();
        String link = list.get(0);
        String token = CryptoSecurity.encrypt(noDoc);
        return link + "id=" + token;
    }
    
}
