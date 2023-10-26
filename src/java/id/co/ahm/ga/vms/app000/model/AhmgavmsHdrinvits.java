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
@Table(name = "AHMGAVMS_HDRINVITS")
public class AhmgavmsHdrinvits extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "VMASTERNO", nullable = false, length = 50)
    private String vmasterno;
    
    @Column(name = "DPLSTART")
    @Temporal(TemporalType.DATE)
    private Date dplstart;
    
    @Column(name = "DPLEND")
    @Temporal(TemporalType.DATE)
    private Date dplend;
    
    @Column(name = "VNRPPIC")
    private String vnrppic;
    
    @Column(name = "VPLANTID")
    private String vplantid;
    
    @Column(name = "VLOC")
    private String vloc;
    
    @Column(name = "VLOCSPEC")
    private String vlocspec;
    
    @Column(name = "VTYPE")
    private String vtype;
    
    @Column(name = "VPURPOSE")
    private String vpurpose;
    
    @Column(name = "VSTATUS")
    private String vstatus;

    public String getVmasterno() {
        return vmasterno;
    }

    public void setVmasterno(String vmasterno) {
        this.vmasterno = vmasterno;
    }

    public Date getDplstart() {
        return dplstart;
    }

    public void setDplstart(Date dplstart) {
        this.dplstart = dplstart;
    }

    public Date getDplend() {
        return dplend;
    }

    public void setDplend(Date dplend) {
        this.dplend = dplend;
    }

    public String getVnrppic() {
        return vnrppic;
    }

    public void setVnrppic(String vnrppic) {
        this.vnrppic = vnrppic;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public String getVloc() {
        return vloc;
    }

    public void setVloc(String vloc) {
        this.vloc = vloc;
    }

    public String getVlocspec() {
        return vlocspec;
    }

    public void setVlocspec(String vlocspec) {
        this.vlocspec = vlocspec;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getVpurpose() {
        return vpurpose;
    }

    public void setVpurpose(String vpurpose) {
        this.vpurpose = vpurpose;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }
    
    
}
