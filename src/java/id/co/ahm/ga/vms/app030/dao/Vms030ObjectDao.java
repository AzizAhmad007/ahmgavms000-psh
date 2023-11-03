/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.dao;

import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPic;
import id.co.ahm.jxf.dao.HrDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Nurvan Afandi
 */
public interface Vms030ObjectDao extends HrDao<Object, Serializable> {
    
    public List<Vms030VoLovPic> getPic(DtoParamPaging input);
    
}
