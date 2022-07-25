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
 * @author developer
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "AHMMOMSC_MSTPLANTS")
public class AhmmomscMstplants extends DefaultEntityImpl implements Serializable {

    @Id
    @Column(name = "VPLANTID")
    private String vplantid;

    @Column(name = "VPLANTDESC")
    private String vplantdesc;

    @Column(name = "VCITY")
    private String vcity;

    @Column(name = "DBEGINEFF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dbegineff;

    @Column(name = "DENDEFF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dendeff;

    public AhmmomscMstplants() {
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public String getVplantdesc() {
        return vplantdesc;
    }

    public void setVplantdesc(String vplantdesc) {
        this.vplantdesc = vplantdesc;
    }

    public String getVcity() {
        return vcity;
    }

    public void setVcity(String vcity) {
        this.vcity = vcity;
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

    @Override
    public String toString() {
        return "AhmmomscMstplants{" + "vplantid=" + vplantid + ", vplantdesc=" + vplantdesc + ", vcity=" + vcity + ", dbegineff=" + dbegineff + ", dendeff=" + dendeff + '}';
    }
}
