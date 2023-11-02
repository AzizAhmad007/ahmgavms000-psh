/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app028.dao;

import id.co.ahm.ga.vms.app028.vo.Vms028VoLovOutput;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author kahfi
 */
public interface Vms028AhmmoerpDtlsettingsDao extends DefaultDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk>{

    public List<Vms028VoLovOutput> lovPlant(DtoParamPaging input);

    public List<Vms028VoLovOutput> lovParticipant(DtoParamPaging input);

    public List<Vms028VoLovOutput> lovResult(DtoParamPaging input);

    public List<Vms028VoLovOutput> lovTypeSI(DtoParamPaging input);
     
}
