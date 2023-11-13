/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao.impl;

import id.co.ahm.ga.vms.app031.dao.Vms031ObjectDao;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovPic;
import id.co.ahm.jxf.dao.HrHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.AhmStringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@Repository("vms031ObjectDao")
public class Vms031ObjectDaoImpl extends HrHibernateDao<Object, Serializable> implements Vms031ObjectDao {

    @Override
    public List<Vms031VoLovPic> getPic(DtoParamPaging input) {
    String nrp = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT DISTINCT "
            + "TO_CHAR(A.NRP), "
            + "A.NAME, "
            + "A.NAMA_DIVISI, "
            + "A.NAMA_DEPARTEMEN, "
            + "A.NAMA_SUBDEPARTEMEN, "
            + "A.NAMA_SEKSI, "
            + "A.VEND_VND_CODE "
            + "FROM FMHRD_GENERAL_DATAS A ");
         
        sql.append("WHERE 1 = 1 ");
        
        sql.append("AND TO_CHAR(A.NRP) = '");
        sql.append(nrp);
        sql.append("'");
        
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms031VoLovPic> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;  
            for(Object object : list) {
                obj = (Object[]) object;
                Vms031VoLovPic vo = new Vms031VoLovPic();
                vo.setNrp((String) obj [0]);
                vo.setNama((String) obj[1]);
                vo.setDivisi((String) obj[2]);
                vo.setDepartemen((String) obj[3]);
                vo.setSubdepartemen((String) obj[4]);
                vo.setSeksi((String) obj[5]);
                vo.setNamaPerusahaan((String) obj[6]);
                
                result.add(vo);
            }
        }
        return result;    
    }
    
}
