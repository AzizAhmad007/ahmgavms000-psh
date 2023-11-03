/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.service;

import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPic;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;

/**
 *
 * @author Nurvan Afandi
 */
public interface Vms030Service {
    
    public DtoResponseWorkspace showPlant(DtoParamPaging input);
    
    public DtoResponseWorkspace showPlantForm(DtoParamPaging input);
    
    public DtoResponseWorkspace showVisitorType(DtoParamPaging input);
    
    public DtoResponseWorkspace showVisitorTypeForm(DtoParamPaging input);
    
    public DtoResponseWorkspace showStatus(DtoParamPaging input);
    
    public DtoResponseWorkspace showStatusForm(DtoParamPaging input);
    
    public DtoResponseWorkspace showDocType(DtoParamPaging input);
    
    public DtoResponseWorkspace showPic(DtoParamPaging input);
    
    public DtoResponsePagingWorkspace showTable(DtoParamPaging input);
    
    public DtoResponseWorkspace submitReference(DtoParamPaging input, VoUserCred user);
    
    public DtoResponseWorkspace saveReference(DtoParamPaging input, VoUserCred user);
    
    public DtoResponseWorkspace getExcel(DtoParamPaging dtoParam);
    
}
