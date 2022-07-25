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
@Table(name = "AHMGAERS_TXNPMTINASS")
public class AhmgaersTxnpmtinass extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VPERMITID")
    private String vpermitid;
    
    @Column(name="MGAASSET_VASSETNMBR")
    private String vassetnbr;
    
    @Column(name="VDESCNM")
    private String vdescnm;

    @Column(name="VDTLLOC")
    private String vdtloc;
    
    @Column(name="VPLANTID")
    private String vplantid; 
           
    @Column(name="VPLANTDES")
    private String vplantdes;
    
    @Column(name="VSRLNUMBR")
    private String vsrlNbr;
    
    @Column(name="VCOSTCENTER")
    private String vcostcenter;
    
    @Column(name="VDEPRESID")
    private String vdepresid;
    
    @Column(name="VDEPRESNM")
    private String vdepresnm;
    
    @Column(name="MGAPMTTY_VPMTTYPEID")
    private String mgapmattyVpmttypeid;
    
    @Column(name="MGAASSET_VSUBNUMBER")
    private String mgaassetVsubnbr;
    
    @Column(name="VPMTTYPENM")
    private String vpmttypenm;
    
    @Column(name="VDOCNUMBR")
    private String vdocNbr;
    
    @Column(name="VREVNUMBR")
    private String vrevnbr;
    
    @Column(name="DEFFFROM")
    @Temporal(TemporalType.DATE)
    private Date defffrom;
    
    @Column(name="DEFFTO")
    @Temporal(TemporalType.DATE)
    private Date deffto;
    
    @Column(name="VPURFUNC")
    private String vpurfunc;
    
    @Column(name="VSTATUSASS")
    private String vstatusass;
    
    @Column(name="VSTATUSPMT")
    private String vstatuspmt;
    
    @Column(name="VSTATUSFLP")
    private String vstatusflp;
    
    @Column(name="VWFGUID")
    private String vwfguid;
    
    @Column(name="VTASKGUID")
    private String vtaskguid;
    
    @Column(name="VCATPMT")
    private String vcatpmt;
    
    @Column(name="VASSETSNGF")
    private String vassetsngf;
    
    @OneToMany(mappedBy = "rglAssetHeader", cascade = CascadeType.ALL)
    private List<AhmgaersTxnrglasets> regulationList;
    
    @OneToMany(mappedBy = "atpAssetHeader", cascade = CascadeType.ALL)
    private List<AhmgaersTxnatpinass> attachmentList;

    public String getVpermitid() {
        return vpermitid;
    }

    public void setVpermitid(String vpermitid) {
        this.vpermitid = vpermitid;
    }

    public String getVassetnbr() {
        return vassetnbr;
    }

    public void setVassetnbr(String vassetnbr) {
        this.vassetnbr = vassetnbr;
    }

    public String getVdescnm() {
        return vdescnm;
    }

    public void setVdescnm(String vdescnm) {
        this.vdescnm = vdescnm;
    }

    public String getVdtloc() {
        return vdtloc;
    }

    public void setVdtloc(String vdtloc) {
        this.vdtloc = vdtloc;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public String getVplantdes() {
        return vplantdes;
    }

    public void setVplantdes(String vplantdes) {
        this.vplantdes = vplantdes;
    }

    public String getVsrlNbr() {
        return vsrlNbr;
    }

    public void setVsrlNbr(String vsrlNbr) {
        this.vsrlNbr = vsrlNbr;
    }

    public String getVcostcenter() {
        return vcostcenter;
    }

    public void setVcostcenter(String vcostcenter) {
        this.vcostcenter = vcostcenter;
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

    public String getMgapmattyVpmttypeid() {
        return mgapmattyVpmttypeid;
    }

    public void setMgapmattyVpmttypeid(String mgapmattyVpmttypeid) {
        this.mgapmattyVpmttypeid = mgapmattyVpmttypeid;
    }

    public String getMgaassetVsubnbr() {
        return mgaassetVsubnbr;
    }

    public void setMgaassetVsubnbr(String mgaassetVsubnbr) {
        this.mgaassetVsubnbr = mgaassetVsubnbr;
    }

    public String getVpmttypenm() {
        return vpmttypenm;
    }

    public void setVpmttypenm(String vpmttypenm) {
        this.vpmttypenm = vpmttypenm;
    }

    public String getVdocNbr() {
        return vdocNbr;
    }

    public void setVdocNbr(String vdocNbr) {
        this.vdocNbr = vdocNbr;
    }

    public String getVrevnbr() {
        return vrevnbr;
    }

    public void setVrevnbr(String vrevnbr) {
        this.vrevnbr = vrevnbr;
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

    public String getVpurfunc() {
        return vpurfunc;
    }

    public void setVpurfunc(String vpurfunc) {
        this.vpurfunc = vpurfunc;
    }

    public String getVstatusass() {
        return vstatusass;
    }

    public void setVstatusass(String vstatusass) {
        this.vstatusass = vstatusass;
    }

    public String getVstatuspmt() {
        return vstatuspmt;
    }

    public void setVstatuspmt(String vstatuspmt) {
        this.vstatuspmt = vstatuspmt;
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

    public List<AhmgaersTxnrglasets> getRegulationList() {
        return regulationList;
    }

    public void setRegulationList(List<AhmgaersTxnrglasets> regulationList) {
        this.regulationList = regulationList;
    }

    public List<AhmgaersTxnatpinass> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AhmgaersTxnatpinass> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getVcatpmt() {
        return vcatpmt;
    }

    public void setVcatpmt(String vcatpmt) {
        this.vcatpmt = vcatpmt;
    }

    public String getVassetsngf() {
        return vassetsngf;
    }

    public void setVassetsngf(String vassetsngf) {
        this.vassetsngf = vassetsngf;
    }
  
}
