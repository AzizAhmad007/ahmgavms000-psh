/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.service;
import id.co.ahm.ga.vms.app026.vo.Vms026VoDeleteInvitation;
import id.co.ahm.ga.vms.app026.vo.Vms026VoDeleteVisitor;
import id.co.ahm.ga.vms.app026.vo.Vms026VoSendEmail;
import id.co.ahm.ga.vms.app026.vo.Vms026VoSubmitChief;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.List;

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

    public DtoResponseWorkspace submitChief(List<Vms026VoSubmitChief> input, VoUserCred user);

    public DtoResponseWorkspace showStatus(DtoParamPaging input);

    public DtoResponseWorkspace getMasterNo(DtoParamPaging input);

    public DtoResponseWorkspace getExcel(DtoParamPaging dtoParam);

    public DtoResponseWorkspace showLocation(DtoParamPaging input);

    public DtoResponseWorkspace getReqBody();

    public DtoResponseWorkspace deleteInvitation(List<Vms026VoDeleteInvitation> input, String token);

    public DtoResponsePagingWorkspace showMonitoringDetail(DtoParamPaging input);

    public DtoResponseWorkspace deleteVisitor(List<Vms026VoDeleteVisitor> input, String token);

    public DtoResponseWorkspace sendEmail(List<Vms026VoSendEmail> input, VoUserCred user);

    public String getNoHpUser(String userid);

    public DtoResponseWorkspace sendEmailMultiple(Vms026VoSendEmail input, VoUserCred user);
}
