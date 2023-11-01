/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao;

import id.co.ahm.ga.vms.app000.model.AhmgavmsDtlblst;
import id.co.ahm.ga.vms.app000.model.AhmgavmsDtlblstPk;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.math.BigDecimal;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public interface Vms031AhmgavmsDtlblstDao extends DefaultDao<AhmgavmsDtlblst, AhmgavmsDtlblstPk> {
    public int validateId(DtoParamPaging input);
    
    public Integer getDetailId(DtoParamPaging input);
    
    public String getExportExcel(DtoParamPaging input);
}