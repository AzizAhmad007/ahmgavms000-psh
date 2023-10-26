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
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Nurvan Afandi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMGAVMS_MSTREFDOCS")
public class AhmgavmsMstrefdocs extends DefaultEntityImpl implements Serializable {
    
    @EmbeddedId
    private AhmgavmsMstrefdocsPk ahmgavmsMstrefdocsPk;
    
    @Column(name = "VWORKDESC")
    private String vworkdesc;
    
    @Column(name = "VPLANTID")        
    private String vplantid;
    
    @Column(name = "VTYPE")        
    private String vtype;
    
    @Column(name = "VCOMPANY")        
    private String vcompany;
    
    @Column(name = "VSTATUS")        
    private String vstatus;
    
    @Column(name = "DWORKSTART")
    @Temporal(TemporalType.DATE)
    private Date dworkstart;
    
    @Column(name = "DWORKEND")
    @Temporal(TemporalType.DATE)
    private Date dworkend;
            
    @Column(name = "VPICNRP")        
    private String vpicnrp;
    
    @Column(name = "VFTYPEDOC")        
    private String vftypedoc;
            
    @Column(name = "VFNAMEDOC")        
    private String vfnamedoc;
            
    @Column(name = "VFEXTDOC")        
    private String vfextdoc;
    
    @Column(name = "VFPATHDOC")        
    private String vfpathdoc;     

    public AhmgavmsMstrefdocsPk getAhmgavmsMstrefdocsPk() {
        return ahmgavmsMstrefdocsPk;
    }

    public void setAhmgavmsMstrefdocsPk(AhmgavmsMstrefdocsPk ahmgavmsMstrefdocsPk) {
        this.ahmgavmsMstrefdocsPk = ahmgavmsMstrefdocsPk;
    }

    public String getVworkdesc() {
        return vworkdesc;
    }

    public void setVworkdesc(String vworkdesc) {
        this.vworkdesc = vworkdesc;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getVcompany() {
        return vcompany;
    }

    public void setVcompany(String vcompany) {
        this.vcompany = vcompany;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public Date getDworkstart() {
        return dworkstart;
    }

    public void setDworkstart(Date dworkstart) {
        this.dworkstart = dworkstart;
    }

    public Date getDworkend() {
        return dworkend;
    }

    public void setDworkend(Date dworkend) {
        this.dworkend = dworkend;
    }

    public String getVpicnrp() {
        return vpicnrp;
    }

    public void setVpicnrp(String vpicnrp) {
        this.vpicnrp = vpicnrp;
    }

    public String getVftypedoc() {
        return vftypedoc;
    }

    public void setVftypedoc(String vftypedoc) {
        this.vftypedoc = vftypedoc;
    }

    public String getVfnamedoc() {
        return vfnamedoc;
    }

    public void setVfnamedoc(String vfnamedoc) {
        this.vfnamedoc = vfnamedoc;
    }

    public String getVfextdoc() {
        return vfextdoc;
    }

    public void setVfextdoc(String vfextdoc) {
        this.vfextdoc = vfextdoc;
    }

    public String getVfpathdoc() {
        return vfpathdoc;
    }

    public void setVfpathdoc(String vfpathdoc) {
        this.vfpathdoc = vfpathdoc;
    }

    
         
}
