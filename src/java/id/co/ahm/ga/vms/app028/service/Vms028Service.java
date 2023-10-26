/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app028.service;

import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;

/**
 *
 * @author kahfi
 */
public interface Vms028Service {

    public DtoResponseWorkspace showPlant(DtoParamPaging input);

    public DtoResponseWorkspace showParticipant(DtoParamPaging input);

    public DtoResponseWorkspace showResult(DtoParamPaging input);

    public DtoResponseWorkspace showTypeSI(DtoParamPaging input);

    public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input);

    public DtoResponseWorkspace updateResult(DtoParamPaging input, VoUserCred user);
    
}
