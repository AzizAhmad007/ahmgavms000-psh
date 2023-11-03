/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.dao.impl;

import id.co.ahm.ga.vms.app030.dao.Vms030ObjectDao;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPic;
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
 * @author Nurvan Afandi
 */
@Repository("vms030ObjectDao")
public class Vms030ObjectDaoImpl extends HrHibernateDao<Object, Serializable> implements Vms030ObjectDao {

    @Override
    public List<Vms030VoLovPic> getPic(DtoParamPaging input) {
        String nrp = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT "
            + "TO_CHAR(A.NRP), "
            + "A.NAME, "
            + "A.NAMA_DIVISI, "
            + "A.NAMA_DEPARTEMEN, "
            + "A.NAMA_SUBDEPARTEMEN, "
            + "A.NAMA_SEKSI, "
            + "B.VEMAIL "
            + "FROM FMHRD_GENERAL_DATAS A "
            + "LEFT JOIN AHMMOERP_MSTKARYAWANS B "
            + "ON A.NRP = B.IIDNRP ");
         
        sql.append("WHERE A.NRP = '");
        sql.append(nrp);
        sql.append("'");
        
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms030VoLovPic> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;  
            for(Object object : list) {
                obj = (Object[]) object;
                Vms030VoLovPic vo = new Vms030VoLovPic();
                vo.setNrp((String) obj [0]);
                vo.setNama((String) obj[1]);
                vo.setDivisi((String) obj[2]);
                vo.setDepartment((String) obj[3]);
                vo.setSubdepartment((String) obj[4]);
                vo.setSeksi((String) obj[5]);
                vo.setEmail((String) obj[6]);
                
                result.add(vo);
            }
        }
        return result;
    }
     
}
