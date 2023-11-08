/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author kahfi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMGAVMS_MSTREFDOCS")
public class AhmgavmsTxninouts extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "NIDCHECKIN")
    private BigDecimal nidcheckin;
    
    @Column(name = "VINVITNO")
    private String vinvitno;
    
    @Column(name = "VNIK")
    private String nik;
    
    @Column(name = "VNOHP")
    private String vnohp;
    
    @Column(name = "VEMAIL")
    private String vemail;
    
    @Column(name = "VNOPOL")
    private String vnopol;
    
    @Column(name = "VPLANTID")
    private String vplantid;
    
    @Column(name = "DSTARTDATE")
    @Temporal(TemporalType.DATE)
    private Date dstartdate;
    
    @Column(name = "DCHECKIN")
    @Temporal(TemporalType.DATE)
    private Date dcheckin;
    
    @Column(name = "DCHECKOUT")
    @Temporal(TemporalType.DATE)
    private Date dcheckout;
    
    @Column(name = "VIDLEFT")
    private String vidleft;
    
    @Column(name = "VIDLEFTNO")
    private String vidleftno;
    
    @Column(name = "VPASSCD")
    private String vpasscd;
    
    @Column(name = "VBUILDING")
    private String vbuilding;
    
    @Column(name = "VLOCPLANT")
    private String vlocplant;
    
    @Column(name = "VREASON")
    private String vreason;
    
    @Column(name = "VNOTES")
    private String vnotes;
    
    @Column(name = "VPREGFLAG")
    private String vpregflag;
    
    @Column(name = "VSTATUS")
    private String vstatus;

    public BigDecimal getNidcheckin() {
        return nidcheckin;
    }

    public void setNidcheckin(BigDecimal nidcheckin) {
        this.nidcheckin = nidcheckin;
    }

    public String getVinvitno() {
        return vinvitno;
    }

    public void setVinvitno(String vinvitno) {
        this.vinvitno = vinvitno;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getVnohp() {
        return vnohp;
    }

    public void setVnohp(String vnohp) {
        this.vnohp = vnohp;
    }

    public String getVemail() {
        return vemail;
    }

    public void setVemail(String vemail) {
        this.vemail = vemail;
    }

    public String getVnopol() {
        return vnopol;
    }

    public void setVnopol(String vnopol) {
        this.vnopol = vnopol;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public Date getDstartdate() {
        return dstartdate;
    }

    public void setDstartdate(Date dstartdate) {
        this.dstartdate = dstartdate;
    }

    public Date getDcheckin() {
        return dcheckin;
    }

    public void setDcheckin(Date dcheckin) {
        this.dcheckin = dcheckin;
    }

    public Date getDcheckout() {
        return dcheckout;
    }

    public void setDcheckout(Date dcheckout) {
        this.dcheckout = dcheckout;
    }

    public String getVidleft() {
        return vidleft;
    }

    public void setVidleft(String vidleft) {
        this.vidleft = vidleft;
    }

    public String getVidleftno() {
        return vidleftno;
    }

    public void setVidleftno(String vidleftno) {
        this.vidleftno = vidleftno;
    }

    public String getVpasscd() {
        return vpasscd;
    }

    public void setVpasscd(String vpasscd) {
        this.vpasscd = vpasscd;
    }

    public String getVbuilding() {
        return vbuilding;
    }

    public void setVbuilding(String vbuilding) {
        this.vbuilding = vbuilding;
    }

    public String getVlocplant() {
        return vlocplant;
    }

    public void setVlocplant(String vlocplant) {
        this.vlocplant = vlocplant;
    }

    public String getVreason() {
        return vreason;
    }

    public void setVreason(String vreason) {
        this.vreason = vreason;
    }

    public String getVnotes() {
        return vnotes;
    }

    public void setVnotes(String vnotes) {
        this.vnotes = vnotes;
    }

    public String getVpregflag() {
        return vpregflag;
    }

    public void setVpregflag(String vpregflag) {
        this.vpregflag = vpregflag;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }
    
    
}
