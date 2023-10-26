/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.dao;

import id.co.ahm.ga.vms.app030.vo.Vms030VoLovDocType;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPic;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovVisitorType;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovVisitorTypeForm;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPlant;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPlantForm;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovStatus;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovStatusForm;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author Nurvan Afandi
 */
public interface Vms030AhmmoerpDtlsettingsDao extends DefaultDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk>{
    
    public List<Vms030VoLovPlant> lovPlant(DtoParamPaging input);
    
    public List<Vms030VoLovPlantForm> lovPlantForm(DtoParamPaging input);
    
    public List<Vms030VoLovVisitorType> lovVisitorType(DtoParamPaging input);
    
    public List<Vms030VoLovVisitorTypeForm> lovVisitorTypeForm(DtoParamPaging input);
    
    public List<Vms030VoLovStatus> lovStatus(DtoParamPaging input);
    
    public List<Vms030VoLovStatusForm> lovStatusForm(DtoParamPaging input);
    
    public List<Vms030VoLovDocType> lovDocType(DtoParamPaging input);
    
    public List<Vms030VoLovPic> getPic(String nrp);
}
