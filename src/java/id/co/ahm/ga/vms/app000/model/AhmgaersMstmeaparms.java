/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author developer
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_MSTMEAPARMS")
public class AhmgaersMstmeaparms extends DefaultEntityImpl implements Serializable {
    @Id
    @Column(name = "VMEAPARAMID")
    private String vmeaparamid;

    @Column(name = "MGAMEATY_VMEATYPESID")
    private String mgameatyVmeatypesid;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AhmgaersMstmeatypes.class)
    @JoinColumn(name = "MGAMEATY_VMEATYPESID", referencedColumnName = "VMEATYPESID", insertable = false, updatable = false)
    private AhmgaersMstmeatypes ahmgaersMstmeatypes;

    @Column(name = "VPARAMNAME")
    private String vparamname;

    @Column(name = "VCATEGORY")
    private String vcategory;

    @Column(name = "VUOM")
    private String vuom;

    @Column(name = "VSTNDRDTYPE")
    private String vstndrdtype;

    @Column(name = "FMAXVALUE", precision = 126, scale = 40)
    private BigDecimal fmaxvalue;
    
    @Column(name = "FMINVALUE", precision = 126, scale = 40)
    private BigDecimal fminvalue;
    
    @Column(name = "VVALUE")
    private String vvalue;

    public String getVmeaparamid() {
        return vmeaparamid;
    }

    public void setVmeaparamid(String vmeaparamid) {
        this.vmeaparamid = vmeaparamid;
    }

    public String getMgameatyVmeatypesid() {
        return mgameatyVmeatypesid;
    }

    public void setMgameatyVmeatypesid(String mgameatyVmeatypesid) {
        this.mgameatyVmeatypesid = mgameatyVmeatypesid;
    }

    public AhmgaersMstmeatypes getAhmgaersMstmeatypes() {
        return ahmgaersMstmeatypes;
    }

    public void setAhmgaersMstmeatypes(AhmgaersMstmeatypes ahmgaersMstmeatypes) {
        this.ahmgaersMstmeatypes = ahmgaersMstmeatypes;
    }

    public String getVparamname() {
        return vparamname;
    }

    public void setVparamname(String vparamname) {
        this.vparamname = vparamname;
    }

    public String getVcategory() {
        return vcategory;
    }

    public void setVcategory(String vcategory) {
        this.vcategory = vcategory;
    }

    public String getVuom() {
        return vuom;
    }

    public void setVuom(String vuom) {
        this.vuom = vuom;
    }

    public String getVstndrdtype() {
        return vstndrdtype;
    }

    public void setVstndrdtype(String vstndrdtype) {
        this.vstndrdtype = vstndrdtype;
    }

    public BigDecimal getFmaxvalue() {
        return fmaxvalue;
    }

    public void setFmaxvalue(BigDecimal fmaxvalue) {
        this.fmaxvalue = fmaxvalue;
    }

    public BigDecimal getFminvalue() {
        return fminvalue;
    }

    public void setFminvalue(BigDecimal fminvalue) {
        this.fminvalue = fminvalue;
    }

    public String getVvalue() {
        return vvalue;
    }

    public void setVvalue(String vvalue) {
        this.vvalue = vvalue;
    }

}
