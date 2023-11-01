/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsMstrefdocs;
import id.co.ahm.ga.vms.app000.model.AhmgavmsMstrefdocsPk;
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
import id.co.ahm.jxf.util.AhmStringUtil;
import java.math.BigDecimal;
import org.hibernate.Query;
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
        String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
        String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorType")) ? (input.getSearch().get("visitorType") + "").toUpperCase() : "";
        String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
        String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";
        String nrp = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
        String email = AhmStringUtil.hasValue(input.getSearch().get("email")) ? (input.getSearch().get("email") + "").toLowerCase() : "";
        String docType = AhmStringUtil.hasValue(input.getSearch().get("docType")) ? (input.getSearch().get("docType") + "").toUpperCase() : "";
        String dateStart = AhmStringUtil.hasValue(input.getSearch().get("dateStart")) ? (input.getSearch().get("dateStart") + "").toUpperCase() : "";
        String dateEnd = AhmStringUtil.hasValue(input.getSearch().get("dateEnd")) ? (input.getSearch().get("dateEnd") + "").toUpperCase() : "";
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT "
                + "(SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "     WHERE B.RSET_VID = 'VMS_TYPE_PRTCP' "
                + "     AND B.VITEMCODE = A.VTYPE) VTYPEDESC, "
                + "(SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "     WHERE B.RSET_VID = 'VMS_STAT' "
                + "     AND B.VITEMCODE = A.VSTATUS) VSTATUS, "
                + "A.VREFDOCNO, "
                + "A.VWORKDESC, "
                + "(SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "     WHERE B.RSET_VID = 'VMS_TYPDOC_SI' "
                + "     AND B.VITEMCODE = 'MEMO') VDOCTYPE, "
                + "(SELECT C.VDESC FROM AHMMOSCD_MSTAGPLANTS C "
                + "     WHERE C.VPLANTVAR2 = A.VPLANTID) VPLANTDESC, "
                + "A.VCOMPANY, "
                + "A.VPICNRP, "
                + "D.VEMAIL, "
                + "A.DWORKSTART, "
                + "A.DWORKEND ");
        
        sql.append("FROM AHMGAVMS_MSTREFDOCS A ");
        
        sql.append("JOIN AHMMOERP_MSTKARYAWANS D "
                + "ON A.VPICNRP = D.IIDNRP ");
        
        sql.append("WHERE 1 = 1 ");
        
        if (!StringUtils.isBlank(visitorType)) {
            sql.append("AND A.VTYPE = '")
                    .append(visitorType)
                    .append("' ");
        }
        if (!StringUtils.isBlank(status)) {
            if (status.equalsIgnoreCase("N")) {
                sql.append("AND A.VSTATUS = 'N' ");
            }
            if (status.equalsIgnoreCase("Y")) {
                sql.append("AND A.VSTATUS = 'Y' ");
            }
            if (status.equalsIgnoreCase("D")) {
                sql.append("AND A.VSTATUS = 'D' ");
            }
        }
        if (!StringUtils.isBlank(noDoc)) {
            if (noDoc.equalsIgnoreCase("MEMO")) {
                sql.append("AND A.VREFDOCNO LIKE '%MEMO%'");
            }
            else {
                sql.append("AND A.VREFDOCNO LIKE '%-%'");
            }
        }
        if (!StringUtils.isBlank(docType)) {
            if (docType.equalsIgnoreCase("MEMO")) {
                sql.append("AND A.VREFDOCNO LIKE '%MEMO%'");
            }
            else {
                sql.append("AND A.VREFDOCNO LIKE '%-%'");
            }
        }
        if (!StringUtils.isBlank(plant)) {
            sql.append("AND A.VPLANTID = '")
                    .append(plant)
                    .append("' ");
        }
        if (!StringUtils.isBlank(company)) {
            sql.append("AND A.VCOMPANY LIKE '%")
                    .append(company)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(nrp)) {
            sql.append("AND A.VPICNRP LIKE '%")
                    .append(nrp)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(dateStart)) {
            sql.append("AND A.DWORKSTART BETWEEN TO_DATE('")
                .append(dateStart)
                .append("', 'DD-MM-YYYY') ")
                .append("AND TO_DATE('")
                .append(dateEnd)
                .append("', 'DD-MM-YYYY') ");
        }
        if (!StringUtils.isBlank(dateEnd)) {
            sql.append("AND A.DWORKEND BETWEEN TO_DATE('")
                    .append(dateStart)
                    .append("', 'DD-MM-YYYY') AND TO_DATE('")
                    .append(dateEnd)
                    .append("', 'DD-MM-YYYY') ");
        }
        if (!StringUtils.isBlank(email)) {
            sql.append("AND D.VEMAIL LIKE '%")
                    .append(email)
                    .append("%' ");
        }
        
        sql.append("UNION ");
        
        sql.append("SELECT "
                + "(SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "     WHERE B.RSET_VID = 'VMS_TYPE_PRTCP' "
                + "     AND B.VITEMCODE = 'KTR') VTYPEDESC, "
                + "(CASE "
                + "     WHEN TRUNC(A.DENDJOB) < TRUNC(SYSDATE) THEN "
                + "         'TIDAK AKTIF' "
                + "     WHEN TRUNC(A.DSTARTJOB) < TRUNC(A.DENDJOB) THEN "
                + "         'AKTIF' "
                + "     ELSE "
                + "         '-' "
                + "END) VSTATUS, "
                + "A.VIKPID AS VREFDOCNO, "
                + "A.VPROJDTL AS VWORKDESC, "
                + "(SELECT B.VITEMNAME FROM AHMMOERP_DTLSETTINGS B "
                + "     WHERE B.RSET_VID = 'VMS_TYPDOC_SI' "
                + "     AND B.VITEMCODE = 'IKP') VDOCTYPE, "
                + "(SELECT C.VDESC FROM AHMMOSCD_MSTAGPLANTS C "
                + "     WHERE C.VPLANTVAR2 = A.VPLANTID) VPLANTDESC, "
                + "A.VSUPPLDESC AS VCOMPANY, "
                + "TO_CHAR(A.VPICNRPID) AS VPICNRP, "
                + "D.VEMAIL, "
                + "A.DSTARTJOB AS DWORKSTART, "
                + "A.DENDJOB AS DWORKEND ");
     
        sql.append("FROM AHMGAWPM_HDRIKPS A ");
        
        sql.append("JOIN AHMMOERP_MSTKARYAWANS D "
                + "ON A.VPICNRPID = D.IIDNRP ");
        
        sql.append("WHERE 1 = 1 ");
        if (!StringUtils.isBlank(visitorType)) {    
            if (visitorType.equalsIgnoreCase("KTR")) {
                sql.append("AND A.VIKPID LIKE '%IKP%'");
            }
            else {
                sql.append("AND A.VIKPID LIKE '%-%'");
            }
        }
        if (!StringUtils.isBlank(status)) {
            if (status.equalsIgnoreCase("N")) {
                sql.append("AND TRUNC(A.DENDJOB) < TRUNC(SYSDATE) ");
            }
            if (status.equalsIgnoreCase("Y")) {
                sql.append("AND TRUNC(A.DENDJOB) > TRUNC(SYSDATE) + 1 ");
            }
            if (status.equalsIgnoreCase("D")) {
                sql.append("AND A.VIKPID LIKE '%-%'");
            }
        }
        if (!StringUtils.isBlank(noDoc)) {
            if (noDoc.equalsIgnoreCase("IKP")) {
                sql.append("AND A.VIKPID LIKE '%IKP%'");
            }
            else {
                sql.append("AND A.VIKPID LIKE '%-%'");
            }
        }
        if (!StringUtils.isBlank(docType)) {
            if (docType.equalsIgnoreCase("IKP")) {
                sql.append("AND A.VIKPID LIKE '%IKP%'");
            }
            else {
                sql.append("AND A.VIKPID LIKE '%-%'");
            }
        }
        if (!StringUtils.isBlank(plant)) {
            sql.append("AND A.VPLANTID = '")
                    .append(plant)
                    .append("' ");
        }
        if (!StringUtils.isBlank(company)) {
            sql.append("AND A.VSUPPLDESC LIKE '%")
                    .append(company)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(nrp)) {
            sql.append("AND A.VPICNRPID LIKE '%")
                    .append(nrp)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(dateStart)) {
            sql.append("AND A.DSTARTJOB BETWEEN TO_DATE('")
                    .append(dateStart)
                    .append("', 'DD-MM-YYYY') AND TO_DATE('")
                    .append(dateEnd)
                    .append("', 'DD-MM-YYYY') ");
        }
        if (!StringUtils.isBlank(dateEnd)) {
            sql.append("AND A.DENDJOB BETWEEN TO_DATE('")
                    .append(dateStart)
                    .append("', 'DD-MM-YYYY') AND TO_DATE('")
                    .append(dateEnd)
                    .append("', 'DD-MM-YYYY') ");
        }
        if (!StringUtils.isBlank(email)) {
            sql.append("AND D.VEMAIL LIKE '%")
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
                    vo.setVisitorType((String) obj[0]);
                    vo.setStatus((String) obj[1]);
                    vo.setNoDoc((String) obj[2]);
                    vo.setWorkDesc((String) obj[3]);
                    vo.setDocType((String) obj[4]);
                    vo.setPlant((String) obj[5]);
                    vo.setCompany((String) obj[6]);
                    vo.setNrp((String) obj[7]);
                    vo.setEmail((String) obj[8]);
                    vo.setDateStart(((Date) obj[9]));
                    vo.setDateStartText(DateUtil.dateToString((Date) obj[9], "dd-MMM-yyyy"));
                    vo.setDateEnd(((Date) obj[10]));
                    vo.setDateEndText(DateUtil.dateToString((Date) obj[10], "dd-MMM-yyyy"));
                    
                    i++;
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
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorType")) ? (input.getSearch().get("visitorType") + "").toUpperCase() : "";
            String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
            String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";
            String nrp = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
            String email = AhmStringUtil.hasValue(input.getSearch().get("email")) ? (input.getSearch().get("email") + "").toLowerCase() : "";
            String docType = AhmStringUtil.hasValue(input.getSearch().get("docType")) ? (input.getSearch().get("docType") + "").toUpperCase() : "";
            String dateStart = AhmStringUtil.hasValue(input.getSearch().get("dateStart")) ? (input.getSearch().get("dateStart") + "").toUpperCase() : "";
            String dateEnd = AhmStringUtil.hasValue(input.getSearch().get("dateEnd")) ? (input.getSearch().get("dateEnd") + "").toUpperCase() : "";

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT COUNT(0) ");

            sql.append("FROM AHMGAVMS_MSTREFDOCS A ");

            sql.append("JOIN AHMMOERP_MSTKARYAWANS D "
                + "ON A.VPICNRP = D.IIDNRP ");

            sql.append("WHERE 1 = 1 ");

            if (!StringUtils.isBlank(visitorType)) {
                sql.append("AND A.VTYPE = '")
                .append(visitorType)
                .append("' ");
            }
            if (!StringUtils.isBlank(status)) {
                if (status.equalsIgnoreCase("N")) {
                    sql.append("AND A.VSTATUS = 'N' ");
                }
                if (status.equalsIgnoreCase("Y")) {
                    sql.append("AND A.VSTATUS = 'Y' ");
                }
                if (status.equalsIgnoreCase("D")) {
                    sql.append("AND A.VSTATUS = 'D' ");
                }
            }
            if (!StringUtils.isBlank(noDoc)) {
                if (noDoc.equalsIgnoreCase("MEMO")) {
                    sql.append("AND A.VREFDOCNO LIKE '%MEMO%'");
                }
                else {
                    sql.append("AND A.VREFDOCNO LIKE '%-%'");
                }
            }
            if (!StringUtils.isBlank(docType)) {
                if (docType.equalsIgnoreCase("MEMO")) {
                    sql.append("AND A.VREFDOCNO LIKE '%MEMO%'");
                }
                else {
                    sql.append("AND A.VREFDOCNO LIKE '%-%'");
                }
            }
            if (!StringUtils.isBlank(plant)) {
                sql.append("AND A.VPLANTID = '")
                        .append(plant)
                        .append("' ");
            }
            if (!StringUtils.isBlank(company)) {
                sql.append("AND A.VCOMPANY LIKE '%")
                        .append(company)
                        .append("%' ");
            }
            if (!StringUtils.isBlank(nrp)) {
                sql.append("AND A.VPICNRP LIKE '%")
                        .append(nrp)
                        .append("%' ");
            }
            if (!StringUtils.isBlank(dateStart)) {
                sql.append("AND A.DWORKSTART BETWEEN TO_DATE('")
                    .append(dateStart)
                    .append("', 'DD-MM-YYYY') ")
                    .append("AND TO_DATE('")
                    .append(dateEnd)
                    .append("', 'DD-MM-YYYY') ");
            }
            if (!StringUtils.isBlank(dateEnd)) {
                sql.append("AND A.DWORKEND BETWEEN TO_DATE('")
                        .append(dateStart)
                        .append("', 'DD-MM-YYYY') AND TO_DATE('")
                        .append(dateEnd)
                        .append("', 'DD-MM-YYYY') ");
            }
            if (!StringUtils.isBlank(email)) {
                sql.append("AND D.VEMAIL LIKE '%")
                        .append(email)
                        .append("%' ");
            }
            
            sql.append("UNION ");

            sql.append("SELECT COUNT(0) ");

            sql.append("FROM AHMGAWPM_HDRIKPS A ");
            
            sql.append("JOIN AHMMOERP_MSTKARYAWANS D "
                + "ON A.VPICNRPID = D.IIDNRP ");
            
            sql.append("WHERE 1 = 1 ");
            
            if (!StringUtils.isBlank(visitorType)) {    
                if (visitorType.equalsIgnoreCase("KTR")) {
                    sql.append("AND A.VIKPID LIKE '%IKP%'");
                }
                else {
                    sql.append("AND A.VIKPID LIKE '%-%'");
                }
            }
            if (!StringUtils.isBlank(status)) {
                if (status.equalsIgnoreCase("N")) {
                    sql.append("AND TRUNC(A.DENDJOB) < TRUNC(SYSDATE) ");
                }
                if (status.equalsIgnoreCase("Y")) {
                    sql.append("AND TRUNC(A.DENDJOB) > TRUNC(SYSDATE) + 1 ");
                }
                if (status.equalsIgnoreCase("D")) {
                    sql.append("AND A.VIKPID LIKE '%-%'");
                }
            }
            if (!StringUtils.isBlank(noDoc)) {
                if (noDoc.equalsIgnoreCase("IKP")) {
                    sql.append("AND A.VIKPID LIKE '%IKP%'");
                }
                else {
                    sql.append("AND A.VIKPID LIKE '%-%'");
                }
            }
            if (!StringUtils.isBlank(docType)) {
                if (docType.equalsIgnoreCase("IKP")) {
                    sql.append("AND A.VIKPID LIKE '%IKP%'");
                }
                else {
                    sql.append("AND A.VIKPID LIKE '%-%'");
                }
            }
            if (!StringUtils.isBlank(plant)) {
                sql.append("AND A.VPLANTID = '")
                        .append(plant)
                        .append("' ");
            }
            if (!StringUtils.isBlank(company)) {
                sql.append("AND A.VSUPPLDESC LIKE '%")
                        .append(company)
                        .append("%' ");
            }
            if (!StringUtils.isBlank(nrp)) {
                sql.append("AND A.VPICNRPID LIKE '%")
                        .append(nrp)
                        .append("%' ");
            }
            if (!StringUtils.isBlank(dateStart)) {
                sql.append("AND A.DSTARTJOB BETWEEN TO_DATE('")
                        .append(dateStart)
                        .append("', 'DD-MM-YYYY') AND TO_DATE('")
                        .append(dateEnd)
                        .append("', 'DD-MM-YYYY') ");
            }
            if (!StringUtils.isBlank(dateEnd)) {
                sql.append("AND A.DENDJOB BETWEEN TO_DATE('")
                        .append(dateStart)
                        .append("', 'DD-MM-YYYY') AND TO_DATE('")
                        .append(dateEnd)
                        .append("', 'DD-MM-YYYY') ");
            }
            if (!StringUtils.isBlank(email)) {
                sql.append("AND D.VEMAIL LIKE '%")
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
                            getParam = "VTYPE";
                            break;
                        case "status":
                            getParam = "VSTATUS";
                            break;
                        case "noDoc":
                            getParam = "VREFDOCNO";
                            break;
                        case "workDesc":
                            getParam = "VWORKDESC";
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
                        case "email":
                            getParam = "VEMAIL";
                            break;
                        case "dateStartText":
                            getParam = "DWORKSTART";
                            break;
                        case "dateEndText":
                            getParam = "DWORKEND";
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
