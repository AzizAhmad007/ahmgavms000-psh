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
import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022AhmhrntmHdrotsempsDao")
public class Vms022AhmhrntmHdrotsempsDaoImpl extends HrHibernateDao<AhmhrntmHdrotsemps, AhmhrntmHdrotsempsPk> implements Vms022AhmhrntmHdrotsempsDao{
    
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

    @Override
    public List<Vms022VoMonitoring> searchMonitoring(DtoParamPaging dtoParamPaging) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> bindparams = new HashMap<>();
        populateQuerySearchMonitoring(query, bindparams, dtoParamPaging);

        Map<String, String> sortMap = getSortMapMonitoring();
        decorateSortMonitoring(dtoParamPaging, query, sortMap);

        SQLQuery result = getCurrentSession().createSQLQuery(query.toString());

        bindparams.entrySet().forEach((bindparam) -> {
            result.setParameter(bindparam.getKey(), bindparam.getValue());
        });

        result.setFirstResult(dtoParamPaging.getOffset());
        result.setMaxResults(dtoParamPaging.getLimit());
        result.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        List<Map<String, Object>> resultList = result.list();

        List<Vms022VoMonitoring> voList = resultList.stream().map((row) -> {
            Vms022VoMonitoring vo = new Vms022VoMonitoring();
            vo.setOutId((String) row.get("Outsource_ID"));
            vo.setOutName((String) row.get("Outsource_Name"));
            vo.setPersId((String) row.get("NIK"));
            vo.setOutType((String) row.get("Outsource_Type"));
            vo.setOutTypeName((String) row.get("Outsource_Type"));
            vo.setCompany((String) row.get("Outsource_Company"));
            vo.setCompanyName((String) row.get("Outsource_Company"));
            vo.setOutStatus((String) row.get("Outsource_status, "));
            vo.setArea((String) row.get("Plant"));
            vo.setAreaName((String) row.get("Plant"));
            vo.setVacStatus((String) row.get("Covid19VaccineStatus"));
            vo.setBeginDate((Date) row.get("Periode_Begin"));
            vo.setBeginDateText((String) row.get("Periode_Begin"));
            vo.setEndDate((Date) row.get("Periode_End"));
            vo.setEndDateText((String) row.get("Periode_End"));
            vo.setPassNumber((String) row.get("PassNumberCard"));
            vo.setPassExpiryDate((Date) row.get("PassCardExpDate"));
            vo.setPassExpiryDateText((String) row.get("PassCardExpDate"));
            vo.setModifyBy((String) row.get("VMODI")); //ini vmodi
            vo.setDmodi((Date) row.get("DMODI"));
            vo.setModifyDateText((String) row.get("DMODI")); //ini dmodinya
            vo.setPhoneNo((String) row.get("VEMPPHONE"));
            vo.setJob((String) row.get("VJOBDTL"));
            vo.setNote((String) row.get("VNOTE"));
            vo.setVacTypeName((String) row.get("VVACTYPE"));
            vo.setVacDateText((String) row.get("DLASTVAC"));
            vo.setVacSummary((String) row.get("VVACDTL"));
            vo.setVacNote((String) row.get("VNTVS"));
            vo.setId((String) row.get("ROTSEMPSHS"));//id
            return vo;
        }).collect(Collectors.toList());

        return voList;

    }

    @Override
    public int countMonitoring(DtoParamPaging dtoParamPaging) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> bindparams = new HashMap<>();
        populateQuerySearchMonitoring(query, bindparams, dtoParamPaging);

        String countQuery = "SELECT COUNT(1) FROM ( " + query.toString() + ")";

        SQLQuery result = getCurrentSession().createSQLQuery(countQuery.toString());

        bindparams.entrySet().forEach((bindparam) -> {
            result.setParameter(bindparam.getKey(), bindparam.getValue());
        });

        Number results = (Number) result.uniqueResult();
        if (results == null) {
            results = 0;
        }

        return results.intValue();

    }
    
    

    public void populateQuerySearchMonitoring(StringBuilder query, Map<String, Object> bindparams, DtoParamPaging dtoParamPaging) {
        query.append(Vms022Constant.SQL_MONITORING);
        Map<String, Object> filters = dtoParamPaging.getSearch();
        if (filters == null || filters.isEmpty()) return;
        
//        String[] params = Ers005Constant.Qparam.split(" "); 
//        for (String p : params) {
//            String f = (String) filters.get(p);
//            bindparams.put(p, (StringUtils.isNotBlank(f)) ? f : "");
//        }
    }
    private void decorateSortMonitoring(DtoParamPaging dtoParamPaging, StringBuilder hql, Map<String, String> sortMap) {
        String sorting = dtoParamPaging.getSort();
        String ordering = dtoParamPaging.getOrder();
        if (!StringUtils.isEmpty(sorting) || !StringUtils.isEmpty(ordering)) {
            String omap = sortMap.get(sorting);
            if (!StringUtils.isEmpty(omap)) {
                hql.append(" ORDER BY ");
                hql.append(omap);
                hql.append((CommonConstant.DESC.equalsIgnoreCase(ordering)) ? " DESC " : " ASC ");
                return;
            }
        }
//        hql.append(" ORDER BY dactive DESC ");
    }
    
    

    private Map<String, String> getSortMapMonitoring() {
        String[] a = ("outId outName persId outType outTypeName company companyName outStatus area areaName vacStatus beginDate beginDateText endDate "
                + "endDateText passNumber passExpiryDate passExpiryDateText modifyBy dmodi modifyDateText phoneNo job note vacTypeName vacDateText "
                + "vacSummary vacNote id").split(" ");
        Map<String, String> r = new HashMap<>();
        for (int i = 0; i < a.length / 2; i++) {
            r.put(a[i * 2], a[i * 2 + 1]);
        }
        return r;
    }
    
}
