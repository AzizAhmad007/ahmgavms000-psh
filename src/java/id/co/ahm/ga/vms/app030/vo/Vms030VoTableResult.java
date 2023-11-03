/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.vo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Nurvan Afandi
 */
public class Vms030VoTableResult {
    
    private String visitorTypeCode;
    private String visitorType;
    private String statusCode;
    private String status;
    private String noDoc;
    private String workDesc;
    private String docType;
    private String plantCode;
    private String plant;
    private String company;
    private String nrp;
    private String email;
    private Date dateStart;
    private String dateStartText;
    private Date dateEnd;
    private String dateEndText;
    public int rowNum;
    private String fileNameAtc;
    private List<Vms030VoFileAttachment> fileAtc;

    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoDoc() {
        return noDoc;
    }

    public void setNoDoc(String noDoc) {
        this.noDoc = noDoc;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateStartText() {
        return dateStartText;
    }

    public void setDateStartText(String dateStartText) {
        this.dateStartText = dateStartText;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateEndText() {
        return dateEndText;
    }

    public void setDateEndText(String dateEndText) {
        this.dateEndText = dateEndText;
    }

    public String getFileNameAtc() {
        return fileNameAtc;
    }

    public void setFileNameAtc(String fileNameAtc) {
        this.fileNameAtc = fileNameAtc;
    }

    public List<Vms030VoFileAttachment> getFileAtc() {
        return fileAtc;
    }

    public void setFileAtc(List<Vms030VoFileAttachment> fileAtc) {
        this.fileAtc = fileAtc;
    }

    public String getVisitorTypeCode() {
        return visitorTypeCode;
    }

    public void setVisitorTypeCode(String visitorTypeCode) {
        this.visitorTypeCode = visitorTypeCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    
    
    
}
