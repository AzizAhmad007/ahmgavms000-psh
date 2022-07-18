/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.dao;

import id.co.ahm.ga.vms.app000.model.AhmhrntmMstpicots;
import id.co.ahm.ga.vms.app021.vo.Vms021VoLov;
import id.co.ahm.ga.vms.app021.vo.Vms021VoLovNrp;
import id.co.ahm.jxf.dao.HrDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author ayik.op
 */
public interface AhmhrntmMstpicotsDao extends HrDao<AhmhrntmMstpicots, String>{
    
    public int countLovNrp(DtoParamPaging input);
    
    public List<Vms021VoLov> lovNrp(DtoParamPaging input);
    
    public List<Vms021VoLovNrp> getPicAhm(String outType, List<String> plants);
}