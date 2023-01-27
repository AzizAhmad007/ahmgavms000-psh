/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app022.dao.Vms022Ahmitb2eMstusrrolesDao;
import id.co.ahm.ga.vms.app022.model.Ahmitb2eMstusrroles;
import id.co.ahm.ga.vms.app022.model.Ahmitb2eMstusrrolesPk;
import id.co.ahm.ga.vms.app022.vo.Vms022VoFormAuthorization;
import id.co.ahm.jxf.dao.B2eHibernateDao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022Ahmitb2eMstusrrolesDao")
public class Vms022Ahmitb2eMstusrrolesDaoimpl extends B2eHibernateDao<Ahmitb2eMstusrroles, Ahmitb2eMstusrrolesPk> implements Vms022Ahmitb2eMstusrrolesDao{

    @Override
    public List<Vms022VoFormAuthorization> getMainMenuData(String Userid) {
        List<Vms022VoFormAuthorization> result = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append("Select VROLEID, VROLEDESC from AHMITB2E_MSTROLES "
        );
        Query query = getCurrentSession().createSQLQuery(sqlQuery.toString());

        query.setParameter("votsid", Userid);

        try {
            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object[] obj = (Object[]) lists.get(i);
                Vms022VoFormAuthorization vo = new Vms022VoFormAuthorization();

                vo.setRoleId(obj[0] == null ? "" : obj[0] + "");
                vo.setRoleDesc(obj[1] == null ? "" : obj[1] + "");

                result.add(vo);

            }
        } catch (Exception e) {
            return result;
        }
        return result;
    }
    
}
