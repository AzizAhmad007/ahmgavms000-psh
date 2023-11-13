/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao;

import id.co.ahm.ga.vms.app031.vo.Vms031VoLovPic;
import id.co.ahm.jxf.dao.HrDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public interface Vms031ObjectDao extends HrDao<Object, Serializable> {
        public List<Vms031VoLovPic> getPic(DtoParamPaging input);
}
