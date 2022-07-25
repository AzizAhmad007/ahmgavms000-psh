/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author A Roni Purwanto
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNPMTINMPS")
public class AhmgaersTxnpmtinmps extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VPERMITID")
    private String vpermitid;
    
    @Column(name="VNRP")
    private String vnrp;
    
    @Column(name="VNAME")
    private String vname;

    @Column(name="VSECTIONID")
    private String vsectionid;
    
    @Column(name="VSECTIONNM")
    private String vsectionnm;
    
    @Column(name="VDEPRESID")
    private String vdepresid;
    
    @Column(name="VDEPRESNM")
    private String vdepresnm;
    
    @Column(name="VDIVISIONID")
    private String vdivisionid;
    
    @Column(name="VDIVISIONNM")
    private String vdivisionnm;
    
    @Column(name="VPLANTID")
    private String vplantid;
    
    @Column(name="VPLANTDES")
    private String vplantdesc;
    
    @Column(name="MGAPMTTY_VPMTTYPEID")
    private String mgapmttyVpmttypeid;
    
    @Column(name="VPMTTYPENM")
    private String vpmttypenm;
    
    @Column(name="VDOCNUMBR")
    private String vdocnumbr;
    
    @Column(name="VREVNUMBR")
    private String vrevnumbr;
    
    @Column(name="DEFFFROM")
    @Temporal(TemporalType.DATE)
    private Date defffrom;
    
    @Column(name="DEFFTO")
    @Temporal(TemporalType.DATE)
    private Date deffto;
    
    @Column(name="VSTATUSMP")
    private String vstatusmp;
    
    @Column(name="VSTATUSPMT")
    private String vstatusmpt;
    
    @Column(name="VSTATUSFLP")
    private String vstatusflp;
    
    @Column(name="VWFGUID")
    private String vwfguid;
    
    @Column(name="VTASKGUID")
    private String vtaskguid;
    
    @Column(name="CNOTES")
    private String cnotes;
    
    @OneToMany(mappedBy = "rglMpHeader", cascade = CascadeType.ALL)
    private List<AhmgaersTxnrglmps> regulationList;
    
    @OneToMany(mappedBy = "atpMpHeader", cascade = CascadeType.ALL)
    private List<AhmgaersTxnatpinmps> attachmentList;

    public String getVpermitid() {
        return vpermitid;
    }

    public void setVpermitid(String vpermitid) {
        this.vpermitid = vpermitid;
    }

    public String getVnrp() {
        return vnrp;
    }

    public void setVnrp(String vnrp) {
        this.vnrp = vnrp;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVsectionid() {
        return vsectionid;
    }

    public void setVsectionid(String vsectionid) {
        this.vsectionid = vsectionid;
    }

    public String getVsectionnm() {
        return vsectionnm;
    }

    public void setVsectionnm(String vsectionnm) {
        this.vsectionnm = vsectionnm;
    }

    public String getVdepresid() {
        return vdepresid;
    }

    public void setVdepresid(String vdepresid) {
        this.vdepresid = vdepresid;
    }

    public String getVdepresnm() {
        return vdepresnm;
    }

    public void setVdepresnm(String vdepresnm) {
        this.vdepresnm = vdepresnm;
    }

    public String getVdivisionid() {
        return vdivisionid;
    }

    public void setVdivisionid(String vdivisionid) {
        this.vdivisionid = vdivisionid;
    }

    public String getVdivisionnm() {
        return vdivisionnm;
    }

    public void setVdivisionnm(String vdivisionnm) {
        this.vdivisionnm = vdivisionnm;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public String getVplantdesc() {
        return vplantdesc;
    }

    public void setVplantdesc(String vplantdesc) {
        this.vplantdesc = vplantdesc;
    }

    public String getMgapmttyVpmttypeid() {
        return mgapmttyVpmttypeid;
    }

    public void setMgapmttyVpmttypeid(String mgapmttyVpmttypeid) {
        this.mgapmttyVpmttypeid = mgapmttyVpmttypeid;
    }

    public String getVpmttypenm() {
        return vpmttypenm;
    }

    public void setVpmttypenm(String vpmttypenm) {
        this.vpmttypenm = vpmttypenm;
    }

    public String getVdocnumbr() {
        return vdocnumbr;
    }

    public void setVdocnumbr(String vdocnumbr) {
        this.vdocnumbr = vdocnumbr;
    }

    public String getVrevnumbr() {
        return vrevnumbr;
    }

    public void setVrevnumbr(String vrevnumbr) {
        this.vrevnumbr = vrevnumbr;
    }

    public Date getDefffrom() {
        return defffrom;
    }

    public void setDefffrom(Date defffrom) {
        this.defffrom = defffrom;
    }

    public Date getDeffto() {
        return deffto;
    }

    public void setDeffto(Date deffto) {
        this.deffto = deffto;
    }

    public String getVstatusmp() {
        return vstatusmp;
    }

    public void setVstatusmp(String vstatusmp) {
        this.vstatusmp = vstatusmp;
    }

    public String getVstatusmpt() {
        return vstatusmpt;
    }

    public void setVstatusmpt(String vstatusmpt) {
        this.vstatusmpt = vstatusmpt;
    }

    public String getVstatusflp() {
        return vstatusflp;
    }

    public void setVstatusflp(String vstatusflp) {
        this.vstatusflp = vstatusflp;
    }

    public String getVwfguid() {
        return vwfguid;
    }

    public void setVwfguid(String vwfguid) {
        this.vwfguid = vwfguid;
    }

    public String getVtaskguid() {
        return vtaskguid;
    }

    public void setVtaskguid(String vtaskguid) {
        this.vtaskguid = vtaskguid;
    }

    public List<AhmgaersTxnrglmps> getRegulationList() {
        return regulationList;
    }

    public void setRegulationList(List<AhmgaersTxnrglmps> regulationList) {
        this.regulationList = regulationList;
    }

    public List<AhmgaersTxnatpinmps> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AhmgaersTxnatpinmps> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getCnotes() {
        return cnotes;
    }

    public void setCnotes(String cnotes) {
        this.cnotes = cnotes;
    }
}
