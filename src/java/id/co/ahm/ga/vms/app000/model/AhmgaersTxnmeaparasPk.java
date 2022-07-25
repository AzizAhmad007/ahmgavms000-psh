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
 * @author Yusuf Yadi Surya
 */
@Embeddable
public class AhmgaersTxnmeaparasPk implements Serializable{

    @Column(name = "VMEAPARAMID")
    private String vmeaparamid;

    @Column(name = "VPARAVMEACD")
    private String vparavmeacd;

    public AhmgaersTxnmeaparasPk() {
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
}
