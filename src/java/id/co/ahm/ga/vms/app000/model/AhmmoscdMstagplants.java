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
@Table(name = "AHMMOSCD_MSTAGPLANTS")
public class AhmmoscdMstagplants extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "VPLANTID", nullable = false, length = 30)
    private String vplantid;
    
    @Column(name = "VPLANTVAR1")
    private String vplantvar1;
    
    @Column(name = "VPLANTVAR2")
    private String vplantvar2;
    
    @Column(name = "VPLANTVAR3")
    private String vplantvar3;
    
    @Column(name = "VPLANTVAR4")
    private String vplantvar4;
    
    @Column(name = "VPLANTVAR5")
    private String vplantvar5;
    
    @Column(name = "VPLANTVAR6")
    private String vplantvar6;
    
    @Column(name = "VPLANTVAR7")
    private String vplantvar7;
    
    @Column(name = "VDESC")
    private String vdesc;
    
    @Column(name = "VAPPSNM")
    private String vappsnm;
    
    @Column(name = "DBEGINEFF")
    @Temporal(TemporalType.DATE)
    private Date dbegineff;
    
    @Column(name = "DENDEFF")
    @Temporal(TemporalType.DATE)
    private Date dendeff;

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public String getVplantvar1() {
        return vplantvar1;
    }

    public void setVplantvar1(String vplantvar1) {
        this.vplantvar1 = vplantvar1;
    }

    public String getVplantvar2() {
        return vplantvar2;
    }

    public void setVplantvar2(String vplantvar2) {
        this.vplantvar2 = vplantvar2;
    }

    public String getVplantvar3() {
        return vplantvar3;
    }

    public void setVplantvar3(String vplantvar3) {
        this.vplantvar3 = vplantvar3;
    }

    public String getVplantvar4() {
        return vplantvar4;
    }

    public void setVplantvar4(String vplantvar4) {
        this.vplantvar4 = vplantvar4;
    }

    public String getVplantvar5() {
        return vplantvar5;
    }

    public void setVplantvar5(String vplantvar5) {
        this.vplantvar5 = vplantvar5;
    }

    public String getVplantvar6() {
        return vplantvar6;
    }

    public void setVplantvar6(String vplantvar6) {
        this.vplantvar6 = vplantvar6;
    }

    public String getVplantvar7() {
        return vplantvar7;
    }

    public void setVplantvar7(String vplantvar7) {
        this.vplantvar7 = vplantvar7;
    }

    public String getVdesc() {
        return vdesc;
    }

    public void setVdesc(String vdesc) {
        this.vdesc = vdesc;
    }

    public String getVappsnm() {
        return vappsnm;
    }

    public void setVappsnm(String vappsnm) {
        this.vappsnm = vappsnm;
    }

    public Date getDbegineff() {
        return dbegineff;
    }

    public void setDbegineff(Date dbegineff) {
        this.dbegineff = dbegineff;
    }

    public Date getDendeff() {
        return dendeff;
    }

    public void setDendeff(Date dendeff) {
        this.dendeff = dendeff;
    }
}
