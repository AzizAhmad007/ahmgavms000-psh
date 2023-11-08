/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.dao;

import id.co.ahm.ga.vms.app034.vo.Vms034VoLovOutput;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author kahfi
 */
public interface Vms034AhmmoerpDtlsettingsDao extends DefaultDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk>{

    public List<Vms034VoLovOutput> lovVisitorType(DtoParamPaging input);

    public List<Vms034VoLovOutput> lovPlant(DtoParamPaging input);

    public List<Vms034VoLovOutput> lovLocation(DtoParamPaging input);

    public List<Vms034VoLovOutput> lovIdCardTypeAll(DtoParamPaging input);

    public List<Vms034VoLovOutput> lovIdCardTypeFilter(DtoParamPaging input);

    public List<Vms034VoLovOutput> lovStatusCheck(DtoParamPaging input);
    
}
