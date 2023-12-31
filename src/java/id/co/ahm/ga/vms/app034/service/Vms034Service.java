/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.service;

import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;

/**
 *
 * @author kahfi
 */
public interface Vms034Service {

    public DtoResponseWorkspace showVisitorType(DtoParamPaging input);

    public DtoResponseWorkspace showPlant(DtoParamPaging input);

    public DtoResponseWorkspace showLocation(DtoParamPaging input);

    public DtoResponseWorkspace showIdCardType(DtoParamPaging input);

    public DtoResponseWorkspace showIdCardTypeFilter(DtoParamPaging input);

    public DtoResponseWorkspace submitCheckIn(DtoParamPaging input, VoUserCred user);

    public DtoResponseWorkspace showStatusCheck(DtoParamPaging input);

    public DtoResponseWorkspace submitCheckOut(DtoParamPaging input, VoUserCred user);

    public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input);
    
}
