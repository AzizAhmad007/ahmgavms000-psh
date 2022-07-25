/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author administator
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNMEAPLNTS")
public class AhmgaersTxnmeaplnts extends DefaultEntityImpl implements Serializable {

    @EmbeddedId
    private AhmgaersTxnmeaplntsPk ahmgaersTxnmeaplntsPk;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AhmmomscMstplants.class)
    @JoinColumn(name = "VPLANTID", referencedColumnName = "VPLANTID", insertable = false, updatable = false)
    private AhmmomscMstplants ahmmomscMstplants;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AhmgaersTxnmeasures.class)
    @JoinColumn(name = "VPLANTMEACD", referencedColumnName = "VMEACODE", insertable = false, updatable = false)
    private AhmgaersTxnmeasures ahmgaersTxnmeasures;

    @Column(name = "VMEAPLNTID")
    private String vmeaplntid;

    @Column(name = "VSTATUS")
    private String vstatus;

    @Column(name = "VCOMMENT")
    private String vcomment;

    public AhmgaersTxnmeaplntsPk getAhmgaersTxnmeaplntsPk() {
        return ahmgaersTxnmeaplntsPk;
    }

    public void setAhmgaersTxnmeaplntsPk(AhmgaersTxnmeaplntsPk ahmgaersTxnmeaplntsPk) {
        this.ahmgaersTxnmeaplntsPk = ahmgaersTxnmeaplntsPk;
    }

    public AhmgaersTxnmeasures getAhmgaersTxnmeasures() {
        return ahmgaersTxnmeasures;
    }

    public void setAhmgaersTxnmeasures(AhmgaersTxnmeasures ahmgaersTxnmeasures) {
        this.ahmgaersTxnmeasures = ahmgaersTxnmeasures;
    }

    public AhmmomscMstplants getAhmmomscMstplants() {
        return ahmmomscMstplants;
    }

    public void setAhmmomscMstplants(AhmmomscMstplants ahmmomscMstplants) {
        this.ahmmomscMstplants = ahmmomscMstplants;
    }

    public String getVmeaplntid() {
        return vmeaplntid;
    }

    public void setVmeaplntid(String vmeaplntid) {
        this.vmeaplntid = vmeaplntid;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public String getVcomment() {
        return vcomment;
    }

    public void setVcomment(String vcomment) {
        this.vcomment = vcomment;
    }
}
