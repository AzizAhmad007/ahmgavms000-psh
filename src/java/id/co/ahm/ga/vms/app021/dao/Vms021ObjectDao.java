/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.dao;

import id.co.ahm.ga.vms.app021.vo.Vms021VoLov;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ayik.op
 */
public interface Vms021ObjectDao extends DefaultDao<Object, Serializable>{
    
    public int countLovCompExternal(DtoParamPaging input, String userId, String type);
    
    public List<Vms021VoLov> lovCompExternal(DtoParamPaging input, String userId, String type);
}
