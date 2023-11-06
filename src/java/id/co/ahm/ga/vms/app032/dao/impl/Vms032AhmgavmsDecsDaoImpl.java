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
        List<Vms032VoShowData> vos = new ArrayList<>();
	Map<String, String> sortMap = new HashMap<>();
        
        sortMap.put("ahmgavms032p01StatusSort", "");
        sortMap.put("ahmgavms032p01DeclarationTypeSort", "");
        sortMap.put("ahmgavms032p01VersionSort", "");
        sortMap.put("ahmgavms032p01HtmlIndonesiaSort", "");
        sortMap.put("ahmgavms032p01HtmlInggrisSort", "");
        sortMap.put("ahmgavms032p01SequenceSort", "");
             
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT DISTINCT "
		+ "(CASE " 
                + "WHEN TRUNC(DE.DENDEFF) < TRUNC(SYSDATE) THEN " 
                + "    'TIDAK AKTIF' " 
                + "WHEN DE.VSTATUS = 'Y' THEN " 
                + "    'AKTIF' " 
                + "WHEN DE.VSTATUS = 'D' THEN " 
                + "    'DRAFT' " 
                + "ELSE " 
                + "    '-' " 
                + "END) VSTATUS, "
		+ "(SELECT D.VITEMDESC "
		+ "FROM AHMMOERP_DTLSETTINGS D "
		+ "WHERE D.RSET_VID = 'VMS_DECLR'"
		+ "AND D.VITEMCODE = DE.VDECTYPE) VDECTYPE, "
		+ "DE.VVERSION, DE.VBODYID, DE.VBODYEN, DE.VSEQ "
		+ "FROM AHMGAVMS_MSTDECLARS DE "
		+ "WHERE 1 = 1 ");

		if (!input.getSearch().get("status").toString().equalsIgnoreCase("")) {
                    if (input.getSearch().get("status").toString().equalsIgnoreCase("N")) {
                        sql.append("AND TRUNC(DE.DENDEFF) < TRUNC(SYSDATE) ");
                    } else {
                        sql.append("AND DE.VSTATUS = '").append(input.getSearch().get("status").toString().toUpperCase()).append("' ");
                        sql.append("AND TRUNC(DE.DENDEFF) > TRUNC(SYSDATE) - 1 ");
                    }
                }
		
		if (!input.getSearch().get("declarationType").toString().equalsIgnoreCase("")) {
                    sql.append("AND DE.VDECTYPE = '").append(input.getSearch().get("declarationType").toString().toUpperCase()).append("' ");
                }
		
		voSetter(input);
		
            orderClause(input, sql, sortMap, getParam);
            Query query = getCurrentSession().createSQLQuery(sql.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());
        try{
           List<Object[]> list = query.list();
           if (list.size() > 0) {
                Object[] obj;
                int i = 0;
                for(Object object : list) {
                    obj = (Object[]) object;
                    Vms032VoShowData vo = new Vms032VoShowData();
                    vo.setStatus((String) obj[0]);
                    vo.setDeclarationType((String) obj[1]);
                    vo.setVersion((String) obj[2]);
                    vo.setHtmlIndonesia((String) obj[3]);
                    vo.setHtmlInggris((String) obj[4]);
                    vo.setSequence((String) obj[5]);
                    vo.setRowNum(i);
					
                    i++;
                    vos.add(vo);
                }
           }
		}catch (Exception e) {
            return vos;
        }
		return vos;
    }
    
    @Override
    public int getMonitoringCount(DtoParamPaging input) {
        try {
			StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
                + "FROM AHMGAVMS_MSTDECLARS DE "
                + "WHERE 1 = 1 ");
		if (!input.getSearch().get("status").toString().equalsIgnoreCase("")) {
            if (input.getSearch().get("status").toString().equalsIgnoreCase("N")) {
                sql.append("AND TRUNC(DE.DENDEFF) < TRUNC(SYSDATE) ");
            } else {
                sql.append("AND DE.VSTATUS = '").append(input.getSearch().get("status").toString().toUpperCase()).append("' ");
                sql.append("AND TRUNC(DE.DENDEFF) > TRUNC(SYSDATE) - 1 ");
            }
        }
		
		if (!input.getSearch().get("declarationType").toString().equalsIgnoreCase("")) {
            sql.append("AND DE.VDECTYPE = '").append(input.getSearch().get("declarationType").toString().toUpperCase()).append("' ");
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
	
	private void voSetter(DtoParamPaging input) {
        try {
            if (input.getSort() == null) {

            } else {
                String param = input.getSort();

                switch (param) {
                    case "status":
                        getParam = "VSTATUS";
                        break;
                    case "declarationType":
                        getParam = "VDECTYPE";
                        break;
                    case "version":
                        getParam = "DE.VVERSION";
                        break;
                    case "sequence":
                        getParam = "DE.VSEQ";
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
    public int getCountData(DtoParamPaging input) {
        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
                    + "FROM AHMGAVMS_HISDECLARS "
                    + "WHERE VDECTYPE = '" + input.getSearch().get("declarationType").toString() + "'");
            SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
            List<BigDecimal> list = sqlQuery.list();
            return (Integer) list.get(0).intValueExact();
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public int getVersionData(DtoParamPaging input) {
        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
                    + "FROM AHMGAVMS_HISDECLARS "
                    + "WHERE VDECTYPE = '" + input.getSearch().get("declarationType").toString() + "'");
            SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
            List<BigDecimal> list = sqlQuery.list();
            return (Integer) list.get(0).intValueExact();
        } catch (Exception e) {
            return 0;
        }
    }
}
