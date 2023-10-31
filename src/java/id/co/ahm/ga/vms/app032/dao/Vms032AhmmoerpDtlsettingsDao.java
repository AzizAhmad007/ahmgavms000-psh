/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app032.dao;

import id.co.ahm.ga.vms.app032.vo.Vms032VoLov;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;


/**
 *
 * @author Hitoshi Mario Naga M
 */
public interface Vms032AhmmoerpDtlsettingsDao extends DefaultDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk> {
    public List<Vms032VoLov> lovStatusType(DtoParamPaging input);
    public List<Vms032VoLov> lovDecType(DtoParamPaging input);
}
