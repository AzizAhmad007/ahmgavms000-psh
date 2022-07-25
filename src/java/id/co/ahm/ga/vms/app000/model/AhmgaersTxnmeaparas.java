/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.ahm.ga.vms.app000.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author Yusuf Yadi Surya
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNMEAPARAS")
public class AhmgaersTxnmeaparas implements Serializable {
    
    @EmbeddedId
    private AhmgaersTxnmeaparasPk ahmgaersTxnmeaparasPk;
    
    @Column(name = "VMEAPARAMID", insertable = false, updatable = false)
    private String vmeaparamid;

    @Column(name = "VPARAVMEACD", insertable = false, updatable = false)
    private String vparavmeacd;
    
    @NotFound(action=NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY , targetEntity = AhmgaersMstmeaparms.class)
    @JoinColumn(name = "VMEAPARAMID", referencedColumnName = "VMEAPARAMID", insertable = false, updatable = false)
    private AhmgaersMstmeaparms ahmgaersMstmeaparms;

    public AhmgaersTxnmeaparas() {
    }

    public AhmgaersTxnmeaparasPk getAhmgaersTxnmeaparasPk() {
        return ahmgaersTxnmeaparasPk;
    }

    public void setAhmgaersTxnmeaparasPk(AhmgaersTxnmeaparasPk ahmgaersTxnmeaparasPk) {
        this.ahmgaersTxnmeaparasPk = ahmgaersTxnmeaparasPk;
    }

    public String getVmeaparamid() {
        return vmeaparamid;
    }

    public void setVmeaparamid(String vmeaparamid) {
        this.vmeaparamid = vmeaparamid;
    }

    public String getVparavmeacd() {
        return vparavmeacd;
    }

    public void setVparavmeacd(String vparavmeacd) {
        this.vparavmeacd = vparavmeacd;
    }

    public AhmgaersMstmeaparms getAhmgaersMstmeaparms() {
        return ahmgaersMstmeaparms;
    }

    public void setAhmgaersMstmeaparms(AhmgaersMstmeaparms ahmgaersMstmeaparms) {
        this.ahmgaersMstmeaparms = ahmgaersMstmeaparms;
    }
    
}
