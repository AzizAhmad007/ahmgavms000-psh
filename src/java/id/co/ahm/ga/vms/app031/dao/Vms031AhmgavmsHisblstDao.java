/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrblst;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHisblst;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHisblstPk;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public interface Vms031AhmgavmsHisblstDao extends DefaultDao<AhmgavmsHisblst, AhmgavmsHisblstPk> {
       public String getNik(DtoParamPaging id);
       
}
