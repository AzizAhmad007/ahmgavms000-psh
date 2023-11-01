/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHisblst;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHisblstPk;
import id.co.ahm.ga.vms.app031.dao.Vms031AhmgavmsHisblstDao;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@Repository("vms031AhmgavmsHisblstDao")
public class Vms031AhmgavmsHisblstDaoImpl extends DefaultHibernateDao<AhmgavmsHisblst, AhmgavmsHisblstPk> implements Vms031AhmgavmsHisblstDao {
    
    public static final StringBuilder NIK_HISBLACKLIST = new StringBuilder("SELECT DISTINCT VNIK FROM AHMGAVMS_HDRBLACKLST " +
                        "WHERE VNIK =:VNIK "); 

    @Override
    public String getNik(DtoParamPaging id) {
        StringBuilder sql = new StringBuilder(NIK_HISBLACKLIST);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameter("VNIK", id.getSearch().get("nik").toString());

        return (String) sqlQuery.uniqueResult();
    }
    
    
}
