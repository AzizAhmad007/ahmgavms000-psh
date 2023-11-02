/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.dao;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrinvits;
import id.co.ahm.ga.vms.app026.vo.Vms026VoMonitoringDetail;
import id.co.ahm.ga.vms.app026.vo.Vms026VoMonitoringOutput;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author kahfi
 */
public interface Vms026AhmgavmsHdrinvitsDao extends DefaultDao<AhmgavmsHdrinvits, String>{

    public int getMonitoringCount(DtoParamPaging input);

    public List<Vms026VoMonitoringOutput> getMonitoring(DtoParamPaging input);

    public List<Vms026VoMonitoringDetail> getMonitoringDetail(DtoParamPaging input);

    public int getMonitoringDetailCount(DtoParamPaging input);
    
}
