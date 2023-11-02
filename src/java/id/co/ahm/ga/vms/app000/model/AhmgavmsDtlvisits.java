/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author kahfi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMGAVMS_DTLVISITS")
public class AhmgavmsDtlvisits extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "NCDVISIT")
    private Integer ncdvisit;
    
    @Column(name = "VINVITNO")
    private String vinvitno;
    
    @Column(name = "VNIK")
    private String vnik;
    
    @Column(name = "VNAME")
    private String vname;
    
    @Column(name = "VIDTYPE")
    private String vidtype;
    
    @Column(name = "VNOPOL")
    private String vnopol;
    
    @Column(name = "VNOIKP")
    private String vnoikp;
    
    @Column(name = "VNOREFDOC")
    private String vnorefdoc;
    
    @Column(name = "VPREGFLAG")
    private String vpregflag;
    
    @Column(name = "VSNSFLAG")
    private String vsnsflag;
    
    @Column(name = "VPDPFLAG")
    private String vpdpflag;
    
    @Column(name = "VSTATUS")
    private String vstatus;

    public Integer getNcdvisit() {
        return ncdvisit;
    }

    public void setNcdvisit(Integer ncdvisit) {
        this.ncdvisit = ncdvisit;
    }

    public String getVinvitno() {
        return vinvitno;
    }

    public void setVinvitno(String vinvitno) {
        this.vinvitno = vinvitno;
    }

    public String getVnik() {
        return vnik;
    }

    public void setVnik(String vnik) {
        this.vnik = vnik;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVidtype() {
        return vidtype;
    }

    public void setVidtype(String vidtype) {
        this.vidtype = vidtype;
    }

    public String getVnopol() {
        return vnopol;
    }

    public void setVnopol(String vnopol) {
        this.vnopol = vnopol;
    }

    public String getVnoikp() {
        return vnoikp;
    }

    public void setVnoikp(String vnoikp) {
        this.vnoikp = vnoikp;
    }

    public String getVnorefdoc() {
        return vnorefdoc;
    }

    public void setVnorefdoc(String vnorefdoc) {
        this.vnorefdoc = vnorefdoc;
    }

    public String getVpregflag() {
        return vpregflag;
    }

    public void setVpregflag(String vpregflag) {
        this.vpregflag = vpregflag;
    }

    public String getVsnsflag() {
        return vsnsflag;
    }

    public void setVsnsflag(String vsnsflag) {
        this.vsnsflag = vsnsflag;
    }

    public String getVpdpflag() {
        return vpdpflag;
    }

    public void setVpdpflag(String vpdpflag) {
        this.vpdpflag = vpdpflag;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }
    
    
}
