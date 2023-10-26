/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.dao;

import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.ga.vms.app026.vo.Vms026VoLovOutput;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kahfi
 */
public interface Vms026AhmmoerpDtlsettingsDao extends DefaultDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk>{

    public List<Vms026VoLovOutput> lovVisitorType(DtoParamPaging input);

    public Map<String, Object> getMasterNo();

    public String getInvLink(String invNo);
    
    public List<Vms026VoLovOutput> lovPlant(DtoParamPaging input);
    
    public List<Vms026VoLovOutput> lovBuilding(DtoParamPaging input);

    public List<Vms026VoLovOutput> lovFloor(DtoParamPaging input);

    public List<Vms026VoLovOutput> lovStatus(DtoParamPaging input);

    public List<Vms026VoLovOutput> lovLocation(DtoParamPaging input);
    
}
