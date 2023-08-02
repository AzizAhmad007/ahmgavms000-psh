/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.hr.AhmhrntmDtlprmgbls;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.jxf.dao.HrDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.List;

/**
 *
 * @author reza.mr
 */
public interface Vms022AhmhrntmDtlprmgblsDao extends HrDao<AhmhrntmDtlprmgbls, String> {

    public List<Vms022VoLov> lovPlant(DtoParamPaging input, Boolean isMonitoring);

    public List<Vms022VoLov> lovPlantInternal(DtoParamPaging input);

    public List<Vms022VoLov> getPlant(String outid, String nik);

    public String getPlantForExcel(String outid, String nik, String nrp, String role);

    public String getPlantIDForExcel(String outid, String nik);

    public List<Vms022VoLov> getGate(String outid, String nik);

    public String getGateForExcel(String outid, String nik);

    public String getGateForApprove(String outid, String nik);

}
