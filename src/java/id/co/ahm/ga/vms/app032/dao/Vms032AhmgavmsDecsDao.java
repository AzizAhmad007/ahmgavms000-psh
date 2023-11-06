/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app032.dao;

import id.co.ahm.ga.vms.app000.model.AhmgavmsDecs;
import id.co.ahm.ga.vms.app032.vo.Vms032VoShowData;
import id.co.ahm.ga.vms.app032.vo.Vms032VoShowPlant;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;






/**
 *
 * @author Hitoshi Mario Naga M
 */
public interface Vms032AhmgavmsDecsDao extends DefaultDao<AhmgavmsDecs, String> {
    public List<Vms032VoShowData> getMonitoring(DtoParamPaging input);
    public int getMonitoringCount(DtoParamPaging input);
    public int getCountData(DtoParamPaging input);
    public int getVersionData(DtoParamPaging input);
}
