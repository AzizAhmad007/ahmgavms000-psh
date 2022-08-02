/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.vo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author reza.mr
 */
public class Vms022VoMonitoring {
    
    private int rowNum;
    private String id;
    private String outId;
    private String outName;
    private String persId;
    private String outType;
    private String outTypeName;
    private String company;
    private String companyName;
    private String outStatus;
    private String area;
    private String areaName;
    private String vacStatus;
    private Date beginDate;
    private String beginDateText;
    private Date endDate;
    private String endDateText;
    private String passNumber;
    private Date passExpiryDate;
    private String passExpiryDateText;
    private String modifyBy;
    private Date modifyDate;
    private String modifyDateText;
    private String phoneNo; 
    private String fileNamePhoto;
    private String filePhoto;
    private String supplier;
    private String job;
    private String accessReader;
    private String canteen;
    private String securityGate;
    private String note;
    private String fileNameKtp;
    private String fileKtp;
    private String vacType;
    private String vacTypeName;
    private Date vacDate;
    private String vacDateText;
    private String vacSummary;
    private String vacNote;
    private String diclaimer;
    private String gateName;
    private String pic;
    private String access;
    private String vmodi;
    private Date dmodi;
    
//    private List<Vms022VoLov> plants;
//    private List<Vms022VoLov> gates;
//    private List<Vms022VoLovNrp> nrps;
    private List<Vms022VoFileAttachment> fileVaccines;
//    private List<Vms022VoFileAttachment> fileSk;
    
    private List<Vms022VoMonitoring> monitoring;

    public String getVmodi() {
        return vmodi;
    }

    public void setVmodi(String vmodi) {
        this.vmodi = vmodi;
    }

    public Date getDmodi() {
        return dmodi;
    }

    public void setDmodi(Date dmodi) {
        this.dmodi = dmodi;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPersId() {
        return persId;
    }

    public void setPersId(String persId) {
        this.persId = persId;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getOutTypeName() {
        return outTypeName;
    }

    public void setOutTypeName(String outTypeName) {
        this.outTypeName = outTypeName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(String outStatus) {
        this.outStatus = outStatus;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getVacStatus() {
        return vacStatus;
    }

    public void setVacStatus(String vacStatus) {
        this.vacStatus = vacStatus;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getBeginDateText() {
        return beginDateText;
    }

    public void setBeginDateText(String beginDateText) {
        this.beginDateText = beginDateText;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndDateText() {
        return endDateText;
    }

    public void setEndDateText(String endDateText) {
        this.endDateText = endDateText;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    public Date getPassExpiryDate() {
        return passExpiryDate;
    }

    public void setPassExpiryDate(Date passExpiryDate) {
        this.passExpiryDate = passExpiryDate;
    }

    public String getPassExpiryDateText() {
        return passExpiryDateText;
    }

    public void setPassExpiryDateText(String passExpiryDateText) {
        this.passExpiryDateText = passExpiryDateText;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyDateText() {
        return modifyDateText;
    }

    public void setModifyDateText(String modifyDateText) {
        this.modifyDateText = modifyDateText;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFileNamePhoto() {
        return fileNamePhoto;
    }

    public void setFileNamePhoto(String fileNamePhoto) {
        this.fileNamePhoto = fileNamePhoto;
    }

    public String getFilePhoto() {
        return filePhoto;
    }

    public void setFilePhoto(String filePhoto) {
        this.filePhoto = filePhoto;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAccessReader() {
        return accessReader;
    }

    public void setAccessReader(String accessReader) {
        this.accessReader = accessReader;
    }

    public String getCanteen() {
        return canteen;
    }

    public void setCanteen(String canteen) {
        this.canteen = canteen;
    }

    public String getSecurityGate() {
        return securityGate;
    }

    public void setSecurityGate(String securityGate) {
        this.securityGate = securityGate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFileNameKtp() {
        return fileNameKtp;
    }

    public void setFileNameKtp(String fileNameKtp) {
        this.fileNameKtp = fileNameKtp;
    }

    public String getFileKtp() {
        return fileKtp;
    }

    public void setFileKtp(String fileKtp) {
        this.fileKtp = fileKtp;
    }

    public String getVacType() {
        return vacType;
    }

    public void setVacType(String vacType) {
        this.vacType = vacType;
    }

    public String getVacTypeName() {
        return vacTypeName;
    }

    public void setVacTypeName(String vacTypeName) {
        this.vacTypeName = vacTypeName;
    }

    public Date getVacDate() {
        return vacDate;
    }

    public void setVacDate(Date vacDate) {
        this.vacDate = vacDate;
    }

    public String getVacDateText() {
        return vacDateText;
    }

    public void setVacDateText(String vacDateText) {
        this.vacDateText = vacDateText;
    }

    public String getVacSummary() {
        return vacSummary;
    }

    public void setVacSummary(String vacSummary) {
        this.vacSummary = vacSummary;
    }

    public String getVacNote() {
        return vacNote;
    }

    public void setVacNote(String vacNote) {
        this.vacNote = vacNote;
    }

    public String getDiclaimer() {
        return diclaimer;
    }

    public void setDiclaimer(String diclaimer) {
        this.diclaimer = diclaimer;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

//    public List<Vms022VoLov> getPlants() {
//        return plants;
//    }
//
//    public void setPlants(List<Vms022VoLov> plants) {
//        this.plants = plants;
//    }
//
//    public List<Vms022VoLov> getGates() {
//        return gates;
//    }
//
//    public void setGates(List<Vms022VoLov> gates) {
//        this.gates = gates;
//    }
//
//    public List<Vms022VoLovNrp> getNrps() {
//        return nrps;
//    }
//
//    public void setNrps(List<Vms022VoLovNrp> nrps) {
//        this.nrps = nrps;
//    }
//
    public List<Vms022VoFileAttachment> getFileVaccines() {
        return fileVaccines;
    }

    public void setFileVaccines(List<Vms022VoFileAttachment> fileVaccines) {
        this.fileVaccines = fileVaccines;
    }
//
//    public List<Vms022VoFileAttachment> getFileSk() {
//        return fileSk;
//    }
//
//    public void setFileSk(List<Vms022VoFileAttachment> fileSk) {
//        this.fileSk = fileSk;
//    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public List<Vms022VoMonitoring> getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(List<Vms022VoMonitoring> monitoring) {
        this.monitoring = monitoring;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
    
}
