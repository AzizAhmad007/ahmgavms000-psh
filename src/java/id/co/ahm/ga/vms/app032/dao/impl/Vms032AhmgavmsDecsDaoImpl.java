/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app032.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsDecs;
import id.co.ahm.ga.vms.app032.constant.Vms032Constant;
import id.co.ahm.ga.vms.app032.dao.Vms032AhmgavmsDecsDao;
import id.co.ahm.ga.vms.app032.vo.Vms032VoShowData;
import id.co.ahm.ga.vms.app032.vo.Vms032VoShowPlant;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.AhmStringUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Hitoshi Mario Naga M
 */
@Repository("vms032AhmgavmsDecsDao")
public class Vms032AhmgavmsDecsDaoImpl extends DefaultHibernateDao<AhmgavmsDecs, String> implements Vms032AhmgavmsDecsDao {

    private String getParam;
    
    @Override
    public List<Vms032VoShowData> getMonitoring(DtoParamPaging input) {;
        Map<String, String> sortMap = new HashMap<>();
        
        sortMap.put("ahmgavms032p01StatusSort", "");
        sortMap.put("ahmgavms032p01DeclarationTypeSort", "");
        sortMap.put("ahmgavms032p01VersionSort", "");
        sortMap.put("ahmgavms032p01HtmlIndonesiaSort", "");
        sortMap.put("ahmgavms032p01HtmlInggrisSort", "");
        sortMap.put("ahmgavms032p01SequenceSort", "");
        
        String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";
        String declarationType = AhmStringUtil.hasValue(input.getSearch().get("declarationType")) ? (input.getSearch().get("declarationType") + "").toUpperCase() : "";
        String version = AhmStringUtil.hasValue(input.getSearch().get("version")) ? (input.getSearch().get("version") + "").toUpperCase() : "";
        String htmlIndonesia = AhmStringUtil.hasValue(input.getSearch().get("htmlIndonesia")) ? (input.getSearch().get("htmlIndonesia") + "").toUpperCase() : "";
        String htmlInggris = AhmStringUtil.hasValue(input.getSearch().get("htmlInggris")) ? (input.getSearch().get("htmlInggris") + "").toUpperCase() : "";
        String sequence = AhmStringUtil.hasValue(input.getSearch().get("sequence")) ? (input.getSearch().get("sequence") + "").toUpperCase() : "";
        
        StringBuilder sql = new StringBuilder();
        
//        sql.append("SELECT DISTINCT "
//                + "VSTATUS, "
//                + "VDECTYPE, "
//                + "VVERSION, "
//                + "VBODYID, "
//                + "VBODYEN, "
//                + "VSEQ "
//        );
//        
        sql.append("SELECT DISTINCT "
//                + "VSTATUS, "
                + "VDECTYPE, "
//                + "VPLANTID, "
//                + "VTITLE, "
                + "VVERSION, "
                + "VBODYID, "
                + "VBODYEN, "
                + "VSEQ "
        );
        
        
        
        sql.append("FROM AHMGAVMS_MSTDECLARS ");
        sql.append("WHERE 1 = 1 ");
        
        if (!StringUtils.isBlank(status)) {
            sql.append("AND VSTATUS = '")
                    .append(status)
                    .append("' ");
        }
        if (!StringUtils.isBlank(declarationType)) {
            sql.append("AND VDECTYPE = '")
                    .append(declarationType)
                    .append("' ");
        }
        if (!StringUtils.isBlank(version)) {
            sql.append("AND VVERSION = '")
                    .append(version)
                    .append("' ");
        }
        if (!StringUtils.isBlank(sequence)) {
            sql.append("AND VSEQ = '")
                    .append(sequence)
                    .append("' ");
        }
        orderClause(input, sql, sortMap, getParam);
        Query query = getCurrentSession().createSQLQuery(sql.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());
        
           List<Object[]> list = query.list();
           List<Vms032VoShowData> result = new ArrayList<>();
           if (list.size() > 0) {
                Object[] obj;
                int i = 0;
                for(Object object : list) {
                    obj = (Object[]) object;
                    Vms032VoShowData vo = new Vms032VoShowData();
//                    vo.setStatus((String) obj[0]);
                    vo.setDeclarationType((String) obj[0]);
                    vo.setVersion((String) obj[1]);
                    vo.setHtmlIndonesia((String) obj[2]);
                    vo.setHtmlInggris((String) obj[3]);
                    vo.setSequence((String) obj[4]);
                    vo.setRowNum(i);
                    i++;
                    result.add(vo);
                }
           }
        return result;
    }
    
    @Override
    public int getMonitoringCount(DtoParamPaging input) {
        try {
            String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";
            String declarationType = AhmStringUtil.hasValue(input.getSearch().get("declarationType")) ? (input.getSearch().get("declarationType") + "").toUpperCase() : "";
            String version = AhmStringUtil.hasValue(input.getSearch().get("version")) ? (input.getSearch().get("version") + "").toUpperCase() : "";
            String htmlIndonesia = AhmStringUtil.hasValue(input.getSearch().get("htmlIndonesia")) ? (input.getSearch().get("htmlIndonesia") + "").toUpperCase() : "";
            String htmlInggris = AhmStringUtil.hasValue(input.getSearch().get("htmlInggris")) ? (input.getSearch().get("htmlInggris") + "").toUpperCase() : "";
            String sequence = AhmStringUtil.hasValue(input.getSearch().get("sequence")) ? (input.getSearch().get("sequence") + "").toUpperCase() : "";

            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT COUNT(0) ");
            
            sql.append("FROM AHMGAVMS_MSTDECLARS ");
            
            sql.append("WHERE 1 = 1 ");
            
            if (!StringUtils.isBlank(status)) {
                sql.append("AND VSTATUS = '")
                        .append(status)
                        .append("' ");
            }
            if (!StringUtils.isBlank(declarationType)) {
                sql.append("AND VDECTYPE = '")
                        .append(declarationType)
                        .append("' ");
            }
            if (!StringUtils.isBlank(version)) {
                sql.append("AND VVERSION = '")
                        .append(version)
                        .append("' ");
            }
            if (!StringUtils.isBlank(sequence)) {
                sql.append("AND VSEQ = '")
                        .append(sequence)
                        .append("' ");
            }

                Query query = getCurrentSession().createSQLQuery(sql.toString())
                    .setFirstResult(input.getOffset())
                    .setMaxResults(input.getLimit());
                List<Object[]> list = query.list();
                Object[] obj = (Object[]) list.get(0);
                return (Integer) obj[0];
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

    @Override
    public int getCountData(DtoParamPaging input) {
        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
                    + "FROM AHMGAVMS_MSTDECLARS "
                    + "WHERE VDECTYPE = '" + input.getSearch().get("declarationType").toString() + "'");
            SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
            List<BigDecimal> list = sqlQuery.list();
            return (Integer) list.get(0).intValueExact();
        } catch (Exception e) {
            return 0;
        }
    }
//    @Override
//    public int getCountData(DtoParamPaging input) {
//        try {
//            StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
//                    + "FROM AHMGAVMS_MSTDECLARS "
//                    + "WHERE VDECTYPE = '" + input.getSearch().get("declarationType").toString() + "'"
//                    + "AND VVERSION = '" + input.getSearch().get("version").toString() +"'");
//            SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
//            List<BigDecimal> list = sqlQuery.list();
//            return (Integer) list.get(0).intValueExact();
//        } catch (Exception e) {
//            return 0;
//        }
//    }

    @Override
    public List<Vms032VoShowPlant> getPlant(DtoParamPaging input) {
        String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT VPLANTVAR2 ");
        sql.append("FROM AHMMOSCD_MSTAGPLANTS ");
        sql.append("ORDER BY VPLANTVAR2 ");
        
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        List<Vms032VoShowPlant> result = new ArrayList<>();
        if (list.size() > 0) {
                Object[] obj;
                for(Object object : list) {
                    obj = (Object[]) object;
                    Vms032VoShowPlant vo = new Vms032VoShowPlant();
                    vo.setPlantId((String) obj[0]);
                    result.add(vo);
                }
           }
        return result;
    }

}
