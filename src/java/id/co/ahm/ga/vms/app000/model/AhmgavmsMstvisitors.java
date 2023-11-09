/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "AHMGAVMS_MSTVISITORS")
public class AhmgavmsMstvisitors extends DefaultEntityImpl implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "NID")
    private BigDecimal nid;
    
    @Column(name = "VNIK")
    private String vnik;
    
    @Column(name = "VNAME")
    private String vname;
    
    @Column(name = "VNOHP")
    private String vnohp;
    
    @Column(name = "VEMAIL")
    private String vemail;
    
    @Column(name = "VBOD")
    private Date vbod;
    
    @Column(name = "VCOMPANY")
    private String vcompany;
    
    @Column(name = "VADDRES")
    private String vaddres;
    
    @Column(name = "VRTRW")
    private String vrtrw;
    
    @Column(name = "VKEL")
    private String vkel;
    
    @Column(name = "VKEC")
    private String vkec;
    
    @Column(name = "VREL")
    private String vrel;
    
    @Column(name = "VPREGFLAG")
    private String vpregflag;
    
    @Column(name = "VFTYPEFOTO")
    private String vftypefoto;
    
    @Column(name = "VFNAMEFOTO")
    private String vfextfoto;
    
    @Column(name = "VFPATHFOTO")
    private String vfpathfoto;

    public BigDecimal getNid() {
        return nid;
    }

    public void setNid(BigDecimal nid) {
        this.nid = nid;
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

    public Date getVbod() {
        return vbod;
    }

    public void setVbod(Date vbod) {
        this.vbod = vbod;
    }

    public String getVcompany() {
        return vcompany;
    }

    public void setVcompany(String vcompany) {
        this.vcompany = vcompany;
    }

    public String getVaddres() {
        return vaddres;
    }

    public void setVaddres(String vaddres) {
        this.vaddres = vaddres;
    }

    public String getVrtrw() {
        return vrtrw;
    }

    public void setVrtrw(String vrtrw) {
        this.vrtrw = vrtrw;
    }

    public String getVkel() {
        return vkel;
    }

    public void setVkel(String vkel) {
        this.vkel = vkel;
    }

    public String getVkec() {
        return vkec;
    }

    public void setVkec(String vkec) {
        this.vkec = vkec;
    }

    public String getVrel() {
        return vrel;
    }

    public void setVrel(String vrel) {
        this.vrel = vrel;
    }

    public String getVpregflag() {
        return vpregflag;
    }

    public void setVpregflag(String vpregflag) {
        this.vpregflag = vpregflag;
    }

    public String getVftypefoto() {
        return vftypefoto;
    }

    public void setVftypefoto(String vftypefoto) {
        this.vftypefoto = vftypefoto;
    }

    public String getVfextfoto() {
        return vfextfoto;
    }

    public void setVfextfoto(String vfextfoto) {
        this.vfextfoto = vfextfoto;
    }

    public String getVfpathfoto() {
        return vfpathfoto;
    }

    public void setVfpathfoto(String vfpathfoto) {
        this.vfpathfoto = vfpathfoto;
    }
}
