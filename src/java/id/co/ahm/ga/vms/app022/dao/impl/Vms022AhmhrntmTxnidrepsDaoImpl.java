/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmTxnidreps;
import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmTxnidrepsDao;
import id.co.ahm.jxf.dao.HrHibernateDao;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022AhmhrntmTxnidrepsDao")
public class Vms022AhmhrntmTxnidrepsDaoImpl extends HrHibernateDao<AhmhrntmTxnidreps, String> implements Vms022AhmhrntmTxnidrepsDao{

    @Override
    public String getNseq() {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT " +
                    "MAX(RIGHT(VWRKORDERNO,6)) " +
                    "FROM AHMHRNTM_TXNIDREPS " +
                    "WHERE VWRKORDERNO LIKE 'OTR'|| " +
                    "'/'||TO_CHAR(SYSDATE, 'YYYY')|| " +
                    "'/'||TO_CHAR(SYSDATE, 'MM')|| " +
                    "'/'|| '%' ");

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());

        return (String) sqlQuery.uniqueResult();
    }

    @Override
    public String validateWorkOrder(String nrp) {
        StringBuilder sql = new StringBuilder();

        sql.append(Vms022Constant.SQL_VALIDATE_WORK_ORDER);

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameter("NRP", nrp);

        return (String) sqlQuery.uniqueResult() != null ? (String) sqlQuery.uniqueResult() : "";
        
    }
    
    
    
}
