/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app032.service;

import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;



/**
 *
 * @author Hitoshi Mario Naga M
 */
public interface Vms032Service {
    public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input);
    
    public DtoResponseWorkspace submitDeclarationMaster(DtoParamPaging input, VoUserCred user);
    
//    public DtoResponseWorkspace draftDeclaration(DtoParamPaging input, VoUserCred user);
    
    public DtoResponseWorkspace showStatus(DtoParamPaging input);
    
    public DtoResponseWorkspace showType(DtoParamPaging input);
    
    public DtoResponseWorkspace deleteDeclaration(DtoParamPaging input);
}
