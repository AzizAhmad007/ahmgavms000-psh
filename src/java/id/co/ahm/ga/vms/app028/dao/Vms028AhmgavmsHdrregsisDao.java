/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app028.dao;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrregsis;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrregsisPk;
import id.co.ahm.ga.vms.app028.vo.Vms028VoMonitoringOutput;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author kahfi
 */
public interface Vms028AhmgavmsHdrregsisDao extends DefaultDao<AhmgavmsHdrregsis, AhmgavmsHdrregsisPk>{

    public List<Vms028VoMonitoringOutput> getMonitoring(DtoParamPaging input);

    public int getMonitoringCount(DtoParamPaging input);
    
}
