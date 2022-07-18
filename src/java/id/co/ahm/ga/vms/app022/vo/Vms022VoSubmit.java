/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.vo;

import java.util.List;

/**
 *
 * @author ayik.op
 */
public class Vms022VoSubmit {
    
    private String id;
    private String outId;
    private String outName;
    private String phoneNo;
    private String nik;
    private String oldNik;
    private String outTypeId;
    private String outTypeName;
    private String supplier;
    private String companyId;
    private String companyName;
    private String job;
    private String vacStatus;
    private String vacType;
    private String vacDate;
    private Boolean isAccessAbsReader;
    private Boolean isAccessCanteen;
    private Boolean isAccessSecGate;
    private String beginDate;
    private String endDate;
    private String note;
    private String sumVacStatus;
    private String vacNotes;
    private Boolean disclaimer;
    private Boolean isNewKtp;
    private Boolean isNewPhoto;
    private Boolean isNewPersid;
    
    private List<Vms022VoLov> listPlants;
    private List<String> listPlantDelete;
    private List<Vms022VoLov> listGate;
    private List<String> listGateDelete;
    
    //foto
    private String fileNamePhoto;
    private byte[] bFileNamePhoto;
    
    //ktp
    private String fileNameKtp;
    private byte[] bFileNameKtp;
    
    //fileVaccine
    private String fileNameVac1;
    private byte[] bFileNameVac1;
    private String fileNameVac2;
    private byte[] bFileNameVac2;
    private String fileNameVac3;
    private byte[] bFileNameVac3;
    
    //fileAttch
    private String fileNameAttch1;
    private byte[] bfileNameAttch1;
    private String fileNameAttch2;
    private byte[] bfileNameAttch2;
    private String fileNameAttch3;
    private byte[] bfileNameAttch3;
    
    private List<String> fileVacDeletes;
    private List<String> fileDocDeletes;
    private List<String> fileVacExist;
    private List<String> fileDocExist;
    
    private List<Vms022VoMonitoring> listInactiveData;

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getOutName() {
        return outName;
    }

    public void setOutName(String outName) {
        this.outName = outName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getOutTypeId() {
        return outTypeId;
    }

    public void setOutTypeId(String outTypeId) {
        this.outTypeId = outTypeId;
    }

    public String getOutTypeName() {
        return outTypeName;
    }

    public void setOutTypeName(String outTypeName) {
        this.outTypeName = outTypeName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getVacStatus() {
        return vacStatus;
    }

    public void setVacStatus(String vacStatus) {
        this.vacStatus = vacStatus;
    }

    public String getVacType() {
        return vacType;
    }

    public void setVacType(String vacType) {
        this.vacType = vacType;
    }

    public String getVacDate() {
        return vacDate;
    }

    public void setVacDate(String vacDate) {
        this.vacDate = vacDate;
    }

    public Boolean getIsAccessAbsReader() {
        return isAccessAbsReader;
    }

    public void setIsAccessAbsReader(Boolean isAccessAbsReader) {
        this.isAccessAbsReader = isAccessAbsReader;
    }

    public Boolean getIsAccessCanteen() {
        return isAccessCanteen;
    }

    public void setIsAccessCanteen(Boolean isAccessCanteen) {
        this.isAccessCanteen = isAccessCanteen;
    }

    public Boolean getIsAccessSecGate() {
        return isAccessSecGate;
    }

    public void setIsAccessSecGate(Boolean isAccessSecGate) {
        this.isAccessSecGate = isAccessSecGate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSumVacStatus() {
        return sumVacStatus;
    }

    public void setSumVacStatus(String sumVacStatus) {
        this.sumVacStatus = sumVacStatus;
    }

    public String getVacNotes() {
        return vacNotes;
    }

    public void setVacNotes(String vacNotes) {
        this.vacNotes = vacNotes;
    }

    public Boolean getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(Boolean disclaimer) {
        this.disclaimer = disclaimer;
    }

    public List<Vms022VoLov> getListPlants() {
        return listPlants;
    }

    public void setListPlants(List<Vms022VoLov> listPlants) {
        this.listPlants = listPlants;
    }

    public List<Vms022VoLov> getListGate() {
        return listGate;
    }

    public void setListGate(List<Vms022VoLov> listGate) {
        this.listGate = listGate;
    }

    public String getFileNamePhoto() {
        return fileNamePhoto;
    }

    public void setFileNamePhoto(String fileNamePhoto) {
        this.fileNamePhoto = fileNamePhoto;
    }

    public String getFileNameKtp() {
        return fileNameKtp;
    }

    public void setFileNameKtp(String fileNameKtp) {
        this.fileNameKtp = fileNameKtp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getbFileNamePhoto() {
        return bFileNamePhoto;
    }

    public void setbFileNamePhoto(byte[] bFileNamePhoto) {
        this.bFileNamePhoto = bFileNamePhoto;
    }

    public byte[] getbFileNameKtp() {
        return bFileNameKtp;
    }

    public void setbFileNameKtp(byte[] bFileNameKtp) {
        this.bFileNameKtp = bFileNameKtp;
    }

    public String getFileNameVac1() {
        return fileNameVac1;
    }

    public void setFileNameVac1(String fileNameVac1) {
        this.fileNameVac1 = fileNameVac1;
    }

    public byte[] getbFileNameVac1() {
        return bFileNameVac1;
    }

    public void setbFileNameVac1(byte[] bFileNameVac1) {
        this.bFileNameVac1 = bFileNameVac1;
    }

    public String getFileNameVac2() {
        return fileNameVac2;
    }

    public void setFileNameVac2(String fileNameVac2) {
        this.fileNameVac2 = fileNameVac2;
    }

    public byte[] getbFileNameVac2() {
        return bFileNameVac2;
    }

    public void setbFileNameVac2(byte[] bFileNameVac2) {
        this.bFileNameVac2 = bFileNameVac2;
    }

    public String getFileNameVac3() {
        return fileNameVac3;
    }

    public void setFileNameVac3(String fileNameVac3) {
        this.fileNameVac3 = fileNameVac3;
    }

    public byte[] getbFileNameVac3() {
        return bFileNameVac3;
    }

    public void setbFileNameVac3(byte[] bFileNameVac3) {
        this.bFileNameVac3 = bFileNameVac3;
    }

    public String getFileNameAttch1() {
        return fileNameAttch1;
    }

    public void setFileNameAttch1(String fileNameAttch1) {
        this.fileNameAttch1 = fileNameAttch1;
    }

    public byte[] getBfileNameAttch1() {
        return bfileNameAttch1;
    }

    public void setBfileNameAttch1(byte[] bfileNameAttch1) {
        this.bfileNameAttch1 = bfileNameAttch1;
    }

    public String getFileNameAttch2() {
        return fileNameAttch2;
    }

    public void setFileNameAttch2(String fileNameAttch2) {
        this.fileNameAttch2 = fileNameAttch2;
    }

    public byte[] getBfileNameAttch2() {
        return bfileNameAttch2;
    }

    public void setBfileNameAttch2(byte[] bfileNameAttch2) {
        this.bfileNameAttch2 = bfileNameAttch2;
    }

    public String getFileNameAttch3() {
        return fileNameAttch3;
    }

    public void setFileNameAttch3(String fileNameAttch3) {
        this.fileNameAttch3 = fileNameAttch3;
    }

    public byte[] getBfileNameAttch3() {
        return bfileNameAttch3;
    }

    public void setBfileNameAttch3(byte[] bfileNameAttch3) {
        this.bfileNameAttch3 = bfileNameAttch3;
    }

    public Boolean getIsNewKtp() {
        return isNewKtp;
    }

    public void setIsNewKtp(Boolean isNewKtp) {
        this.isNewKtp = isNewKtp;
    }

    public Boolean getIsNewPhoto() {
        return isNewPhoto;
    }

    public void setIsNewPhoto(Boolean isNewPhoto) {
        this.isNewPhoto = isNewPhoto;
    }

    public List<String> getFileVacDeletes() {
        return fileVacDeletes;
    }

    public void setFileVacDeletes(List<String> fileVacDeletes) {
        this.fileVacDeletes = fileVacDeletes;
    }

    public List<String> getFileDocDeletes() {
        return fileDocDeletes;
    }

    public void setFileDocDeletes(List<String> fileDocDeletes) {
        this.fileDocDeletes = fileDocDeletes;
    }

    public List<String> getFileVacExist() {
        return fileVacExist;
    }

    public void setFileVacExist(List<String> fileVacExist) {
        this.fileVacExist = fileVacExist;
    }

    public List<String> getFileDocExist() {
        return fileDocExist;
    }

    public void setFileDocExist(List<String> fileDocExist) {
        this.fileDocExist = fileDocExist;
    }

    public List<String> getListPlantDelete() {
        return listPlantDelete;
    }

    public void setListPlantDelete(List<String> listPlantDelete) {
        this.listPlantDelete = listPlantDelete;
    }

    public List<String> getListGateDelete() {
        return listGateDelete;
    }

    public void setListGateDelete(List<String> listGateDelete) {
        this.listGateDelete = listGateDelete;
    }

    public Boolean getIsNewPersid() {
        return isNewPersid;
    }

    public void setIsNewPersid(Boolean isNewPersid) {
        this.isNewPersid = isNewPersid;
    }

    public String getOldNik() {
        return oldNik;
    }

    public void setOldNik(String oldNik) {
        this.oldNik = oldNik;
    }

    public List<Vms022VoMonitoring> getListInactiveData() {
        return listInactiveData;
    }

    public void setListInactiveData(List<Vms022VoMonitoring> listInactiveData) {
        this.listInactiveData = listInactiveData;
    }
    
    
    
}
