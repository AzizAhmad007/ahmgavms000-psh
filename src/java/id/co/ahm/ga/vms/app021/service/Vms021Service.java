/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.service;

import id.co.ahm.ga.vms.app021.vo.Vms021VoFileAttachment;
import id.co.ahm.ga.vms.app021.vo.Vms021VoMonitoring;
import id.co.ahm.ga.vms.app021.vo.Vms021VoSubmit;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoPstUserCred;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ayik.op
 */
public interface Vms021Service {
    
    public DtoResponseWorkspace showPlant();
    
    public DtoResponsePaging showNrp(DtoParamPaging input);
    
    public DtoResponsePaging showOutsource(DtoParamPaging input, VoPstUserCred user);
    
    public DtoResponsePaging showCompany(DtoParamPaging input, VoPstUserCred user);
    
    public DtoResponsePaging monitoring(DtoParamPaging input, VoPstUserCred user);
    
    public DtoResponsePaging lovPlant(DtoParamPaging input);
    
    public DtoResponsePaging lovGate(DtoParamPaging input);
    
    public DtoResponseWorkspace showVacType();
    
    public DtoResponseWorkspace addItem(Vms021VoSubmit input, MultipartFile ktp, MultipartFile photo, List<MultipartFile> vacFiles, List<MultipartFile> attcFiles, VoPstUserCred user);
    
    public DtoResponseWorkspace updateItem(Vms021VoSubmit input, MultipartFile ktp, MultipartFile photo, List<MultipartFile> vacFiles, List<MultipartFile> attcFiles, VoPstUserCred user);
    
    public DtoResponseWorkspace upload(MultipartFile excel, MultipartFile ktp, MultipartFile photo, MultipartFile vacFiles, MultipartFile attcFiles, VoPstUserCred user);
    
    public DtoResponseWorkspace inActiveData(Vms021VoSubmit input, VoPstUserCred user);
    
    public ModelAndView downloadTemplate(VoPstUserCred userCred);
    
    public DtoResponse getExportData(DtoParamPaging input, VoPstUserCred userCred);
    
    public BigDecimal getNewValue(String vidname, BigDecimal vreset, String user);
    
    public DtoResponseWorkspace getPlantsGates(Vms021VoSubmit input);
    
    public Vms021VoFileAttachment uploadFileToServer(String pathServer, MultipartFile fileToUpload, String newFileName);
    
    public DtoResponseWorkspace getPicAhm(Vms021VoMonitoring input);
    
    public DtoResponseWorkspace getFiles(Vms021VoSubmit input);
}
