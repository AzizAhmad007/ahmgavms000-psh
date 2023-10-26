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
        String email = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
        String docType = AhmStringUtil.hasValue(input.getSearch().get("email")) ? (input.getSearch().get("email") + "").toUpperCase() : "";
        String dateStart = AhmStringUtil.hasValue(input.getSearch().get("dateStart")) ? (input.getSearch().get("dateStart") + "").toUpperCase() : "";
        String dateEnd = AhmStringUtil.hasValue(input.getSearch().get("dateEnd")) ? (input.getSearch().get("dateEnd") + "").toUpperCase() : "";
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT DISTINCT "
                + "A.VTYPE AS VTYPE, "
                + "A.VSTATUS AS VSTATUS, "
                + "A.VREFDOCNO AS VREFDOCNO, "
                + "A.VWORKDESC AS VWORKDESC, "
                + "'MEMO' VDOCTYPE, "
                + "B.VDESC AS VDESC, "
                + "A.VCOMPANY AS VCOMPANY, "
                + "A.VPICNRP AS VPICNRP, "
                + "C.VEMAIL AS VEMAIL, "
                + "A.DWORKSTART AS DWORKSTART, "
                + "A.DWORKEND AS DWORKEND "
        );
        
        sql.append("FROM AHMGAVMS_MSTREFDOCS A ");
        
        sql.append("INNER JOIN AHMMOSCD_MSTAGPLANTS B "
                + "ON A.VPLANTID = B.VPLANTVAR2 ");
        
        sql.append("INNER JOIN AHMMOERP_MSTKARYAWANS C "
                + "ON A.VPICNRP = TO_CHAR (C.IIDNRP) ");
        
        sql.append("WHERE 1 = 1 ");
        
        if (!StringUtils.isBlank(visitorType)) {
            sql.append("AND A.VTYPE = '")
                    .append(visitorType)
                    .append("' ");
        }
        if (!StringUtils.isBlank(noDoc)) {
            sql.append("AND A.VREFDOCNO = '")
                    .append(noDoc)
                    .append("' ");
        }
        if (!StringUtils.isBlank(status)) {
            sql.append("AND A.VSTATUS = '")
                    .append(status)
                    .append("' ");
        }
        if (!StringUtils.isBlank(docType)) {
            sql.append("AND A.VREFDOCNO LIKE '%")
                    .append(docType)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(plant)) {
            sql.append("AND B.VDESC = '")
                    .append(plant)
                    .append("' ");
        }
        if (!StringUtils.isBlank(company)) {
            sql.append("AND A.VCOMPANY = '")
                    .append(company)
                    .append("' ");
        }
        if (!StringUtils.isBlank(nrp)) {
            sql.append("AND A.VPICNRP = '")
                    .append(nrp)
                    .append("' ");
        }
        
        if (!StringUtils.isBlank(dateStart) || !StringUtils.isBlank(dateEnd)) {
            sql.append(" AND (");

            if (!StringUtils.isBlank(dateStart)) {
                sql.append(" ('")
                        .append(dateStart)
                        .append("' BETWEEN A.DWORKSTART AND A.DWORKEND)");
            }
            if (!StringUtils.isBlank(dateEnd) && StringUtils.isBlank(dateStart)) {
                sql.append(" ('")
                        .append(dateEnd)
                        .append("' BETWEEN A.DWORKSTART AND A.DWORKEND)");
            } else {
                sql.append(" OR ('")
                        .append(dateEnd)
                        .append("' BETWEEN A.DWORKSTART AND A.DWORKEND)");
            }
            if (!StringUtils.isBlank(dateStart) && !StringUtils.isBlank(dateEnd)) {
                sql.append(" OR (A.DWORKSTART BETWEEN '")
                        .append(dateStart).append("' AND '").append(dateEnd).append("') ")
                        .append(" OR (A.DWORKEND BETWEEN '")
                        .append(dateStart).append("' AND '").append(dateEnd).append("') ");
            }
            sql.append(" ) ");
        }
        
        if (!StringUtils.isBlank(email)) {
            sql.append("AND C.VEMAIL = '")
                    .append(email)
                    .append("' ");
        }
        
        sql.append("UNION ");
        
        sql.append("SELECT DISTINCT "
                + "'KONTRAKTOR' VTYPE, "
                + "'AKTIF' VSTATUS, "
                + "A.VNOIKP AS VREFDOCNO, "
                + "A.VPROJDTL AS VWORKDESC, "
                + "'IKP' VDOCTYPE, "
                + "B.VDESC AS VDESC, "
                + "A.VSUPPLDESC AS VCOMPANY, "
                + "TO_CHAR (A.VPICNRPID) AS VPICNRP, "
                + "C.VEMAIL AS VEMAIL, "
                + "A.DSTARTJOB AS DWORKSTART, "
                + "A.DENDJOB AS DWORKEND ");
        
        sql.append("FROM AHMGAWPM_HDRIKPS A ");
        
        sql.append("INNER JOIN AHMMOSCD_MSTAGPLANTS B "
                + "ON A.VPLANTID = B.VPLANTVAR2 ");
        
        sql.append("INNER JOIN AHMMOERP_MSTKARYAWANS C "
                + "ON A.VPICNRPID = TO_CHAR (C.IIDNRP) ");
        
        sql.append("WHERE 1 = 1 ");
        
        if (!StringUtils.isBlank(noDoc)) {
            sql.append("AND A.VNOIKP = '")
                    .append(noDoc)
                    .append("' ");
        }
        if (!StringUtils.isBlank(docType)) {
            sql.append("AND A.VNOIKP LIKE '%")
                    .append(docType)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(plant)) {
            sql.append("AND B.VDESC = '")
                    .append(plant)
                    .append("' ");
        }
        if (!StringUtils.isBlank(company)) {
            sql.append("AND A.VSUPPLDESC = '")
                    .append(company)
                    .append("' ");
        }
        if (!StringUtils.isBlank(nrp)) {
            sql.append("AND TO_CHAR (A.VPICNRPID) = '")
                    .append(nrp)
                    .append("' ");
        }
        if (!StringUtils.isBlank(dateStart) || !StringUtils.isBlank(dateEnd)) {
            sql.append(" AND (");

            if (!StringUtils.isBlank(dateStart)) {
                sql.append(" ('")
                        .append(dateStart)
                        .append("' BETWEEN A.DSTARTJOB AND A.DENDJOB)");
            }
            if (!StringUtils.isBlank(dateEnd) && StringUtils.isBlank(dateStart)) {
                sql.append(" ('")
                        .append(dateEnd)
                        .append("' BETWEEN A.DSTARTJOB AND A.DENDJOB)");
            } else {
                sql.append(" OR ('")
                        .append(dateEnd)
                        .append("' BETWEEN A.DSTARTJOB AND A.DENDJOB)");
            }
            if (!StringUtils.isBlank(dateStart) && !StringUtils.isBlank(dateEnd)) {
                sql.append(" OR (A.DSTARTJOB BETWEEN '")
                        .append(dateStart).append("' AND '").append(dateEnd).append("') ")
                        .append(" OR (A.DENDJOB BETWEEN '")
                        .append(dateStart).append("' AND '").append(dateEnd).append("') ");
            }
            sql.append(" ) ");
        }
        
        if (!StringUtils.isBlank(email)) {
            sql.append("AND C.VEMAIL = '")
                    .append(email)
                    .append("' ");
        }
        
        orderClause(input, sql, sortMap, getParam);
        
        Query query = getCurrentSession().createSQLQuery(sql.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());
        
        List<Object[]> list = query.list();
        
        List<Vms030VoTableResult> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
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
                result.add(vo);
            }
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
            String email = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
            String docType = AhmStringUtil.hasValue(input.getSearch().get("email")) ? (input.getSearch().get("email") + "").toUpperCase() : "";
            String dateStart = AhmStringUtil.hasValue(input.getSearch().get("dateStart")) ? (input.getSearch().get("dateStart") + "").toUpperCase() : "";
            String dateEnd = AhmStringUtil.hasValue(input.getSearch().get("dateEnd")) ? (input.getSearch().get("dateEnd") + "").toUpperCase() : "";

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT COUNT(0) ");

            sql.append("FROM AHMGAVMS_MSTREFDOCS A ");

            sql.append("INNER JOIN AHMMOSCD_MSTAGPLANTS B "
                    + "ON A.VPLANTID = B.VPLANTVAR2 ");

            sql.append("INNER JOIN AHMMOERP_MSTKARYAWANS C "
                    + "ON A.VPICNRP = TO_CHAR (C.IIDNRP) ");

            sql.append("WHERE 1 = 1 ");

            if (!StringUtils.isBlank(visitorType)) {
                sql.append("AND A.VTYPE = '")
                        .append(visitorType)
                        .append("' ");
            }
            if (!StringUtils.isBlank(noDoc)) {
                sql.append("AND A.VREFDOCNO = '")
                        .append(noDoc)
                        .append("' ");
            }
            if (!StringUtils.isBlank(status)) {
                sql.append("AND A.VSTATUS = '")
                        .append(status)
                        .append("' ");
            }
            if (!StringUtils.isBlank(docType)) {
                sql.append("AND A.VREFDOCNO LIKE '%")
                        .append(docType)
                        .append("%' ");
            }
            if (!StringUtils.isBlank(plant)) {
                sql.append("AND B.VDESC = '")
                        .append(plant)
                        .append("' ");
            }
            if (!StringUtils.isBlank(company)) {
                sql.append("AND A.VCOMPANY = '")
                        .append(company)
                        .append("' ");
            }
            if (!StringUtils.isBlank(nrp)) {
                sql.append("AND A.VPICNRP = '")
                        .append(nrp)
                        .append("' ");
            }

            if (!StringUtils.isBlank(dateStart) || !StringUtils.isBlank(dateEnd)) {
                sql.append(" AND (");

            if (!StringUtils.isBlank(dateStart)) {
                sql.append(" ('")
                        .append(dateStart)
                        .append("' BETWEEN A.DWORKSTART AND A.DWORKEND)");
            }
            if (!StringUtils.isBlank(dateEnd) && StringUtils.isBlank(dateStart)) {
                sql.append(" ('")
                        .append(dateEnd)
                        .append("' BETWEEN A.DWORKSTART AND A.DWORKEND)");
            } else {
                sql.append(" OR ('")
                        .append(dateEnd)
                        .append("' BETWEEN A.DWORKSTART AND A.DWORKEND)");
            }
            if (!StringUtils.isBlank(dateStart) && !StringUtils.isBlank(dateEnd)) {
                sql.append(" OR (A.DWORKSTART BETWEEN '")
                        .append(dateStart).append("' AND '").append(dateEnd).append("') ")
                        .append(" OR (A.DWORKEND BETWEEN '")
                        .append(dateStart).append("' AND '").append(dateEnd).append("') ");
            }
            sql.append(" ) ");
        }
        
        if (!StringUtils.isBlank(email)) {
            sql.append("AND C.VEMAIL = '")
                    .append(email)
                    .append("' ");
        }
        
        sql.append("UNION ");
        
        sql.append("SELECT COUNT(0) ");
        
        sql.append("FROM AHMGAWPM_HDRIKPS A ");
        
        sql.append("INNER JOIN AHMMOSCD_MSTAGPLANTS B "
                + "ON A.VPLANTID = B.VPLANTVAR2 ");
        
        sql.append("INNER JOIN AHMMOERP_MSTKARYAWANS C "
                + "ON A.VPICNRPID = TO_CHAR (C.IIDNRP) ");
        
        sql.append("WHERE 1 = 1 ");
        
        if (!StringUtils.isBlank(noDoc)) {
            sql.append("AND A.VNOIKP = '")
                    .append(noDoc)
                    .append("' ");
        }
        if (!StringUtils.isBlank(docType)) {
            sql.append("AND A.VNOIKP LIKE '%")
                    .append(docType)
                    .append("%' ");
        }
        if (!StringUtils.isBlank(plant)) {
            sql.append("AND B.VDESC = '")
                    .append(plant)
                    .append("' ");
        }
        if (!StringUtils.isBlank(company)) {
            sql.append("AND A.VSUPPLDESC = '")
                    .append(company)
                    .append("' ");
        }
        if (!StringUtils.isBlank(nrp)) {
            sql.append("AND TO_CHAR (A.VPICNRPID) = '")
                    .append(nrp)
                    .append("' ");
        }
        if (!StringUtils.isBlank(dateStart) || !StringUtils.isBlank(dateEnd)) {
            sql.append(" AND (");

            if (!StringUtils.isBlank(dateStart)) {
                sql.append(" ('")
                        .append(dateStart)
                        .append("' BETWEEN A.DSTARTJOB AND A.DENDJOB)");
            }
            if (!StringUtils.isBlank(dateEnd) && StringUtils.isBlank(dateStart)) {
                sql.append(" ('")
                        .append(dateEnd)
                        .append("' BETWEEN A.DSTARTJOB AND A.DENDJOB)");
            } else {
                sql.append(" OR ('")
                        .append(dateEnd)
                        .append("' BETWEEN A.DSTARTJOB AND A.DENDJOB)");
            }
            if (!StringUtils.isBlank(dateStart) && !StringUtils.isBlank(dateEnd)) {
                sql.append(" OR (A.DSTARTJOB BETWEEN '")
                        .append(dateStart).append("' AND '").append(dateEnd).append("') ")
                        .append(" OR (A.DENDJOB BETWEEN '")
                        .append(dateStart).append("' AND '").append(dateEnd).append("') ");
            }
            sql.append(" ) ");
        }
        
        if (!StringUtils.isBlank(email)) {
            sql.append("AND C.VEMAIL = '")
                    .append(email)
                    .append("' ");
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
    
}