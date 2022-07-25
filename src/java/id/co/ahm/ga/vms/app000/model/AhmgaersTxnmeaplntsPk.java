/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author administator
 */
@Embeddable
public class AhmgaersTxnmeaplntsPk implements Serializable {

    @Column(name = "VPLANTMEACD")
    private String vplantmeacd;

    @Column(name = "VPLANTID")
    private String vplantid;

    public AhmgaersTxnmeaplntsPk() {
    }

    public AhmgaersTxnmeaplntsPk(String vplantmeacd, String vplantid) {
        this.vplantmeacd = vplantmeacd;
        this.vplantid = vplantid;
    }

    public String getVplantmeacd() {
        return vplantmeacd;
    }

    public void setVplantmeacd(String vplantmeacd) {
        this.vplantmeacd = vplantmeacd;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

}
