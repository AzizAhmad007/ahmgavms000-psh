/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.dao;

import id.co.ahm.ga.vms.app000.model.AhmgavmsTxninouts;
import id.co.ahm.ga.vms.app034.vo.Vms034VoMonitoringOutput;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author kahfi
 */
public interface Vms034AhmgavmsTxninoutsDao extends DefaultDao<AhmgavmsTxninouts, BigDecimal>{

    public String isCheckIn(String nik);

    public List<Vms034VoMonitoringOutput> getMonitoring(DtoParamPaging input);

    public int getMonitoringCount(DtoParamPaging input);
    
}
