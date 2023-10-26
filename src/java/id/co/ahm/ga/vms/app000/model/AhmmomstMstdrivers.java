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
 * @author Kahfi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMSDTMS_MSTDRIVERS")
public class AhmmomstMstdrivers extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "VDRIVERID")
    private String vdriverid;
    
    @Column(name = "VKTPID")
    private String vktpid;
    
    @Column(name = "VNAME")
    private String vname;
    
    @Column(name = "VEMAIL")
    private String vemail;
    
    @Column(name = "VSIMID")
    private String vsimid;
    
    @Column(name = "VNOHP")
    private String vnohp;
    
    @Column(name = "VUSERNAME")
    private String vusername;
    
    @Column(name = "VFTYPEKTP")
    private String vfttypektp;
    
    @Column(name = "VFNAMEKTP")
    private String vfnamektp;
    
    @Column(name = "VFEXTKTP")
    private String vfextktp;
    
    @Column(name = "VFPATHKTP")
    private String vfpathktp;
    
    @Column(name = "VFTYPESIM")
    private String vftypesim;
    
    @Column(name = "VFNAMESIM")
    private String vfnamesim;
    
    @Column(name = "VFEXTSIM")
    private String vfextsim;
    
    @Column(name = "VFPATHSIM")
    private String vfpathsim;
    
    @Column(name = "VFTYPEFOTO")
    private String vftypefoto;
    
    @Column(name = "VFNAMEFOTO")
    private String vfnamefoto;
    
    @Column(name = "VFEXTFOTO")
    private String vfextfoto;
    
    @Column(name = "VFPATHFOTO")
    private String vfpathfoto;
    
    @Column(name = "VFTYPEOTH")
    private String vftypelain;
    
    @Column(name = "VFNAMEOTH")
    private String vfnamelain;
    
    @Column(name = "VFEXTOTH")
    private String vfextlain;
    
    @Column(name = "VFPATHOTH")
    private String vfpathlain;

    public String getVdriverid() {
        return vdriverid;
    }

    public void setVdriverid(String vdriverid) {
        this.vdriverid = vdriverid;
    }

    public String getVktpid() {
        return vktpid;
    }

    public void setVktpid(String vktpid) {
        this.vktpid = vktpid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVemail() {
        return vemail;
    }

    public void setVemail(String vemail) {
        this.vemail = vemail;
    }

    public String getVsimid() {
        return vsimid;
    }

    public void setVsimid(String vsimid) {
        this.vsimid = vsimid;
    }

    public String getVnohp() {
        return vnohp;
    }

    public void setVnohp(String vnohp) {
        this.vnohp = vnohp;
    }

    public String getVusername() {
        return vusername;
    }

    public void setVusername(String vusername) {
        this.vusername = vusername;
    }

    public String getVfttypektp() {
        return vfttypektp;
    }

    public void setVfttypektp(String vfttypektp) {
        this.vfttypektp = vfttypektp;
    }

    public String getVfnamektp() {
        return vfnamektp;
    }

    public void setVfnamektp(String vfnamektp) {
        this.vfnamektp = vfnamektp;
    }

    public String getVfextktp() {
        return vfextktp;
    }

    public void setVfextktp(String vfextktp) {
        this.vfextktp = vfextktp;
    }

    public String getVfpathktp() {
        return vfpathktp;
    }

    public void setVfpathktp(String vfpathktp) {
        this.vfpathktp = vfpathktp;
    }

    public String getVftypesim() {
        return vftypesim;
    }

    public void setVftypesim(String vftypesim) {
        this.vftypesim = vftypesim;
    }

    public String getVfnamesim() {
        return vfnamesim;
    }

    public void setVfnamesim(String vfnamesim) {
        this.vfnamesim = vfnamesim;
    }

    public String getVfextsim() {
        return vfextsim;
    }

    public void setVfextsim(String vfextsim) {
        this.vfextsim = vfextsim;
    }

    public String getVfpathsim() {
        return vfpathsim;
    }

    public void setVfpathsim(String vfpathsim) {
        this.vfpathsim = vfpathsim;
    }

    public String getVftypefoto() {
        return vftypefoto;
    }

    public void setVftypefoto(String vftypefoto) {
        this.vftypefoto = vftypefoto;
    }

    public String getVfnamefoto() {
        return vfnamefoto;
    }

    public void setVfnamefoto(String vfnamefoto) {
        this.vfnamefoto = vfnamefoto;
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

    public String getVftypelain() {
        return vftypelain;
    }

    public void setVftypelain(String vftypelain) {
        this.vftypelain = vftypelain;
    }

    public String getVfnamelain() {
        return vfnamelain;
    }

    public void setVfnamelain(String vfnamelain) {
        this.vfnamelain = vfnamelain;
    }

    public String getVfextlain() {
        return vfextlain;
    }

    public void setVfextlain(String vfextlain) {
        this.vfextlain = vfextlain;
    }

    public String getVfpathlain() {
        return vfpathlain;
    }

    public void setVfpathlain(String vfpathlain) {
        this.vfpathlain = vfpathlain;
    }
}