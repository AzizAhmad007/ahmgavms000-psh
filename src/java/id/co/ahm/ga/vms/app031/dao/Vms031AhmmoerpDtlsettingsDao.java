/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao;

import id.co.ahm.ga.vms.app031.vo.Vms031VoLovInput;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovOutput;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public interface Vms031AhmmoerpDtlsettingsDao extends DefaultDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk> {
    
    public List<Vms031VoLovOutput> LovBlstCardType(DtoParamPaging input);
    
    public List<Vms031VoLovOutput> lovBlstStatus(DtoParamPaging input);
    
    public List<Vms031VoLovOutput> lovBlstType(DtoParamPaging input);

    public List<Vms031VoLovOutput> lovBlstGender(DtoParamPaging input);

}
