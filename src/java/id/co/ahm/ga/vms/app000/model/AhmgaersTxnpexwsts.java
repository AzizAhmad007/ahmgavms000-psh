/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Yusuf Yadi Surya
 */
  
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNPEXWSTS")
public class AhmgaersTxnpexwsts 
        extends DefaultEntityImpl 
        implements Serializable{
    
    @EmbeddedId
    private AhmgaersTxnpexwstsPk ahmgaersTxnpexwstsPk;
        
    @Column(name = "XGASPKHD_VSPKHOLDID")
    private String xgaspkhdVspkholdid;
    
    @Column(name = "XGASPKHD_VB3ORNON")
    private String xgaspkhdVb3ornon;
    
    @Column(name = "XGASPKHD_VPERMITTYPE")
    private String xgaspkhdVpermittype;
    
    @Column(name = "XGASPKHD_VSPKHOLDCD")
    private String xgaspkhdVspkholdcd;
    
    @ManyToOne(fetch = FetchType.LAZY , targetEntity = AhmgaersTxnspkholds.class)
    @JoinColumns({
        @JoinColumn(name = "XGASPKHD_VSPKHOLDID", referencedColumnName = "VSPKHOLDID", insertable = false, updatable = false),
        @JoinColumn(name = "XGASPKHD_VB3ORNON", referencedColumnName = "VB3ORNON", insertable = false, updatable = false),
        @JoinColumn(name = "XGASPKHD_VPERMITTYPE", referencedColumnName = "VPERMITTYPE", insertable = false, updatable = false),
        @JoinColumn(name = "XGASPKHD_VSPKHOLDCD", referencedColumnName = "VSPKHOLDCD", insertable = false, updatable = false)
    })
    private AhmgaersTxnspkholds ahmgaersTxnspkholds;
    
    @Column(name = "CPERMITDESC")
    private byte[] cpermitdesc;
    
    @Column(name = "VDOCNUMBER")
    private String vdocnumber;
    
    @Column(name = "VREFFTO")
    private String vreffto;
    
    @Column(name = "VWSTVENDRCD")
    private String vwstvendrcd;
    
    @Column(name = "DEFFFROM")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date defffrom;
    
    @Column(name = "DEFFTO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deffto;
    
    @Column(name = "VSTATUSPMT")
    private String vstatuspmt;
    
    @Column(name = "VSTATUSFLP")
    private String vstatusflp;
    
    @Column(name = "VINFO")
    private String vinfo;

    public String getXgaspkhdVspkholdid() {
        return xgaspkhdVspkholdid;
    }

    public void setXgaspkhdVspkholdid(String xgaspkhdVspkholdid) {
        this.xgaspkhdVspkholdid = xgaspkhdVspkholdid;
    }

    public String getXgaspkhdVb3ornon() {
        return xgaspkhdVb3ornon;
    }

    public void setXgaspkhdVb3ornon(String xgaspkhdVb3ornon) {
        this.xgaspkhdVb3ornon = xgaspkhdVb3ornon;
    }

    public String getXgaspkhdVpermittype() {
        return xgaspkhdVpermittype;
    }

    public void setXgaspkhdVpermittype(String xgaspkhdVpermittype) {
        this.xgaspkhdVpermittype = xgaspkhdVpermittype;
    }

    public String getXgaspkhdVspkholdcd() {
        return xgaspkhdVspkholdcd;
    }

    public void setXgaspkhdVspkholdcd(String xgaspkhdVspkholdcd) {
        this.xgaspkhdVspkholdcd = xgaspkhdVspkholdcd;
    }

    public byte[] getCpermitdesc() {
        return cpermitdesc;
    }

    public void setCpermitdesc(byte[] cpermitdesc) {
        this.cpermitdesc = cpermitdesc;
    }

    public String getVdocnumber() {
        return vdocnumber;
    }

    public void setVdocnumber(String vdocnumber) {
        this.vdocnumber = vdocnumber;
    }

    public String getVreffto() {
        return vreffto;
    }

    public void setVreffto(String vreffto) {
        this.vreffto = vreffto;
    }

    public String getVwstvendrcd() {
        return vwstvendrcd;
    }

    public void setVwstvendrcd(String vwstvendrcd) {
        this.vwstvendrcd = vwstvendrcd;
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

    public String getVinfo() {
        return vinfo;
    }

    public void setVinfo(String vinfo) {
        this.vinfo = vinfo;
    }

    public AhmgaersTxnpexwstsPk getAhmgaersTxnpexwstsPk() {
        return ahmgaersTxnpexwstsPk;
    }

    public void setAhmgaersTxnpexwstsPk(AhmgaersTxnpexwstsPk ahmgaersTxnpexwstsPk) {
        this.ahmgaersTxnpexwstsPk = ahmgaersTxnpexwstsPk;
    }

    public AhmgaersTxnspkholds getAhmgaersTxnspkholds() {
        return ahmgaersTxnspkholds;
    }

    public void setAhmgaersTxnspkholds(AhmgaersTxnspkholds ahmgaersTxnspkholds) {
        this.ahmgaersTxnspkholds = ahmgaersTxnspkholds;
    }
}
