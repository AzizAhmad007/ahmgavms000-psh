/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.util;

import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.AhmStringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ayik.op
 */
public class Vms022QueryUtil {
    
    public static StringBuilder setSearchParamMonitoringTable(StringBuilder sql, Map<String, Object> filters, String columnName) {
                
        if (filters != null && filters.size() > 0) {
                for (Map.Entry<String, Object> filter : filters.entrySet()) {
                    String key = filter.getKey();
                    String value = (String) filter.getValue();

                    if (key.equalsIgnoreCase("outId")) {
                        sql.append(" AND LOWER(A.VOTSID) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("outName")) {
                        sql.append(" AND LOWER(A.VNAME) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("nik")) {
                        sql.append(" AND LOWER(A.VPERSID) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("outType")) {
                        sql.append(" AND LOWER(D.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("pic", key)) {
                        sql.append(" AND LOWER(C.VNRP) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }
                    else if (key.equalsIgnoreCase("outStatus")) {
                        if(!"ALL".equalsIgnoreCase(value)){
                            sql.append(" AND LOWER(A.VOTSSTTS) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                        }
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("plant", key)) { 
                        if(!"PG10-00".equalsIgnoreCase(value)){
                            sql.append(" AND LOWER(E.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                        }
                    } else if (key.equalsIgnoreCase("passNumber")) {
                        sql.append(" AND LOWER(A.NAHMCARDORI) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("vacStatus")) {
                        if(!"ALL".equalsIgnoreCase(value)){
                            sql.append(" AND LOWER(A.VVACSTTS) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                        }                        
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("outIdFilter", key)) {
                        sql.append(" AND LOWER(A.VOTSID) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("outNameFilter", key)) {
                        sql.append(" AND LOWER(A.VNAME) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("nikFilter", key)) {
                        sql.append(" AND LOWER(A.VPERSID) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("outTypeFilter", key)) {
                        sql.append(" AND LOWER(D.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }
                    else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("statusFilter", key)) {
                        sql.append(" AND LOWER(A.VOTSSTTS) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }  else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("plantFilter", key)) {
                        sql.append(" AND LOWER(E.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }  else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("vacStatusFilter", key)) {
                        sql.append(" AND LOWER(A.VVACSTTS) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }  else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("beginDateFilter", key)) {
                        sql.append(" AND TRUNC(A.DBGNEFFDT) = '").append(value.toLowerCase()).append("'");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("endDateFilter", key)) {
                        sql.append(" AND TRUNC(A.DENDEFFDT) = '").append(value.toLowerCase()).append("'");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("passNumberFilter", key)) {
                        sql.append(" AND LOWER(A.NAHMCARDORI) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }  else if (StringUtils.isNotEmpty(value) &&  StringUtils.equalsIgnoreCase("passExpiryFilter", key)) {
                        sql.append(" AND TRUNC(A.DPASSEXP) = '").append(value.toLowerCase()).append("'");
                    }  else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("modifyByFilter", key)) {
                        sql.append(" AND LOWER(A.VMODI) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("modifyDateFilter", key)) {
                        sql.append(" AND TRUNC(A.DMODI) = '").append(value.toLowerCase()).append("'");
                    }   
                }
        }
        return sql;
    }
    
    public static StringBuilder setSearchParamLov(StringBuilder sql, Map<String, Object> filters, String columnName, String type) {

        String[] col = columnName.split(",");
        if (filters != null && filters.size() > 0) {
            for (Map.Entry<String, Object> filter : filters.entrySet()) {

                String key = filter.getKey();
                String value = (String) filter.getValue();
                
                if("PLANT".equalsIgnoreCase(type)){
                    if (key.equalsIgnoreCase("any")) {
                        sql.append(" AND (LOWER(").append(col[0]).append(") like LOWER('%").append(value.toLowerCase()).append("%') ");
                        sql.append(" OR LOWER(").append(col[1]).append(") like LOWER('%").append(value.toLowerCase()).append("%')) ");
                    } else if (key.equalsIgnoreCase("id")) {
                        sql.append(" AND lower(A.VNRP) like lower('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("name")) {
                        sql.append(" AND lower(B.NAME) name lower('%").append(value.toLowerCase()).append("%')");
                    }
                } else if("OUTSOURCE".equalsIgnoreCase(type)){
                    if (key.equalsIgnoreCase("any")) {
                        sql.append(" AND (LOWER(").append(col[0]).append(") like LOWER('%").append(value.toLowerCase()).append("%') ");
                        sql.append(" OR LOWER(").append(col[1]).append(") like LOWER('%").append(value.toLowerCase()).append("%')) ");
                    } else if (key.equalsIgnoreCase("id")) {
                        sql.append(" AND lower(VPGBLCD) like lower('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("name")) {
                        sql.append(" AND lower(VPGBLNM) like lower('%").append(value.toLowerCase()).append("%')");
                    }
                } else if ("GATE".equalsIgnoreCase(type)) {
                    if (key.equalsIgnoreCase("any")) {
                        sql.append(" AND (LOWER(").append(col[0]).append(") like LOWER('%").append(value.toLowerCase()).append("%') ");
                        sql.append(" OR LOWER(").append(col[1]).append(") like LOWER('%").append(value.toLowerCase()).append("%') ");
                        sql.append(" OR LOWER(").append(col[2]).append(") like LOWER('%").append(value.toLowerCase()).append("%')) ");
                    } else if (key.equalsIgnoreCase("id")) {
                        sql.append(" AND lower(VPGBLCD) like lower('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("code")) {
                        sql.append(" AND lower(VPGBLNM) like lower('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("name")) {
                        sql.append(" AND lower(VVAL) like lower('%").append(value.toLowerCase()).append("%')");
                    }
                }
                
            }
        }

        return sql;

    }
    
    public static StringBuilder setSortParamLov(StringBuilder sql, DtoParamPaging input, String columnName, String type) {

        if (input.getSort() != null) {
            List<String> order = new ArrayList<>();
            sql.append(" ORDER BY ");
            String[] col = columnName.split(",");
            
            if("PLANT".equalsIgnoreCase(type)){
                if (input.getOrder().equals(CommonConstant.ASC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" A.VNRP ASC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" B.NAME ASC ");
                    }
                } else if (input.getOrder().equals(CommonConstant.DESC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" A.VNRP DESC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" B.NAME DESC ");
                    }
                } else {
                    order.add(" ORDER BY A.VNRP ");
                }
            } else if("OUTSOURCE".equalsIgnoreCase(type)){
                if (input.getOrder().equals(CommonConstant.ASC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" VPGBLCD ASC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" VPGBLNM ASC ");
                    }
                } else if (input.getOrder().equals(CommonConstant.DESC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" VPGBLCD DESC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" VPGBLNM DESC ");
                    }
                } else {
                    order.add(" ORDER BY VPGBLCD ");
                }
            } else if("COMPANY".equalsIgnoreCase(type)){
                
            } else if ("GATE".equalsIgnoreCase(type)) {
                if (input.getOrder().equals(CommonConstant.ASC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" VPGBLCD ASC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" VPGBLNM ASC ");
                    } else if (input.getSort().equals(col[2])) {
                        addCommas(order);
                        order.add(" VVAL ASC ");
                    }
                } else if (input.getOrder().equals(CommonConstant.DESC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" VPGBLCD DESC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" VPGBLNM DESC ");
                    } else if (input.getSort().equals(col[2])) {
                        addCommas(order);
                        order.add(" VVAL DESC ");
                    }
                } else {
                    order.add(" ORDER BY VPGBLCD ");
                }
            }

            if (CollectionUtils.isNotEmpty(order)) {
                for (String string : order) {
                    sql.append(string);
                }
            }
        }

        return sql;

    }
    
    private static void addCommas(List<String> list) {
        if (list.size() > 0) {
            list.add(" , ");
        }

    }
    
    public static String decorateSqlQueryWithSearchLOV(String sql,
            Map<String, Object> filters, String[] params) {
        StringBuilder sqlQuery = new StringBuilder(sql);
        if (filters != null) {
            filters.entrySet().stream()
                    .filter((filter) -> (StringUtils.equalsIgnoreCase(CommonConstant.ANY, filter.getKey())))
                    .map((filter) -> filter.getValue().toString())
                    .map((valueStr) -> buildWhereQueryLikeForAllProperty(params, AhmStringUtil.splitBySpace(valueStr)))
                    .filter((whereQuery) -> (StringUtils.isNotBlank(whereQuery)))
                    .forEachOrdered((whereQuery) -> {
                        if (params.length == 1) {
                            sqlQuery.append("WHERE ");
                        } else if (params.length > 1) {
                            sqlQuery.append("AND ");
                        }
                        sqlQuery.append(whereQuery);
                    });
        }

        return sqlQuery.toString();
    }

    public static String buildWhereQueryLikeForAllProperty(String[] prop, String[] listValueLike) {
        if (listValueLike == null || listValueLike.length <= 0 || prop == null || prop.length <= 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("(")
                    .append(buildWhereQueryLikeForAllProperty(prop, listValueLike[0]));
            for (int i = 1; i < listValueLike.length; i++) {
                sb.append(" AND ")
                        .append(buildWhereQueryLikeForAllProperty(prop, listValueLike[i]));
            }
            sb.append(")");
            return sb.toString();
        }
    }    

    public static String buildWhereQueryLikeForAllProperty(String[] lisProp, String valueLike) {
        if (lisProp == null || lisProp.length <= 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append("UPPER(")
                    .append(lisProp[0])
                    .append(") like UPPER('%")
                    .append(valueLike)
                    .append("%') ");
            for (int i = 1; i < lisProp.length; i++) {
                sb.append(" OR  UPPER(")
                        .append(lisProp[i])
                        .append(") like UPPER('%")
                        .append(valueLike)
                        .append("%') ");
            }
            sb.append(")");
            return sb.toString();
        }
    }
   
}
