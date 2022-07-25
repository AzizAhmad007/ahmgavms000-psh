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
@Table(name = "AHMGAERS_MSTMEAPNTS")
public class AhmgaersMstmeapnts extends DefaultEntityImpl implements Serializable {

    @Id
    @Column(name = "VMEAPOINTID")
    private String vmeapointid;

    @Column(name = "VPLANTID")
    private String vplantid;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AhmmomscMstplants.class)
    @JoinColumn(name = "VPLANTID", referencedColumnName = "VPLANTID", insertable = false, updatable = false)
    private AhmmomscMstplants ahmmomscMstplants;

    @Column(name = "VPLANTDESC")
    private String vplantdesc;

    @Column(name = "VMEAPNTNAME")
    private String vmeapntname;

    @Column(name = "VDETAILLOC")
    private String vdetailloc;

    @Column(name = "VSTATUS")
    private String vstatus;

    public String getVmeapointid() {
        return vmeapointid;
    }

    public void setVmeapointid(String vmeapointid) {
        this.vmeapointid = vmeapointid;
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

    public AhmmomscMstplants getAhmmomscMstplants() {
        return ahmmomscMstplants;
    }

    public void setAhmmomscMstplants(AhmmomscMstplants ahmmomscMstplants) {
        this.ahmmomscMstplants = ahmmomscMstplants;
    }

    public String getVmeapntname() {
        return vmeapntname;
    }

    public void setVmeapntname(String vmeapntname) {
        this.vmeapntname = vmeapntname;
    }

    public String getVdetailloc() {
        return vdetailloc;
    }

    public void setVdetailloc(String vdetailloc) {
        this.vdetailloc = vdetailloc;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

}
