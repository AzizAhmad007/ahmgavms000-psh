/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.service;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;

/**
 *
 * @author Kahfi
 */
public interface Vms026Service {
    
    public DtoResponseWorkspace showVisitorType(DtoParamPaging input);

    public DtoResponseWorkspace showPlant(DtoParamPaging input);

    public DtoResponseWorkspace showBuilding(DtoParamPaging input);

    public DtoResponseWorkspace showFloor(DtoParamPaging input);

    public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input);

    public DtoResponseWorkspace submitInvitation(DtoParamPaging input, VoUserCred user);

    public DtoResponseWorkspace saveInvitation(DtoParamPaging input, VoUserCred user);

    public DtoResponseWorkspace submitChief(DtoParamPaging input, VoUserCred user);

    public DtoResponseWorkspace showStatus(DtoParamPaging input);

    public DtoResponseWorkspace getMasterNo(DtoParamPaging input);

    public DtoResponseWorkspace getExcel(DtoParamPaging dtoParam);

    public DtoResponseWorkspace showLocation(DtoParamPaging input);

    public DtoResponseWorkspace deleteInvitation(DtoParamPaging input);
}