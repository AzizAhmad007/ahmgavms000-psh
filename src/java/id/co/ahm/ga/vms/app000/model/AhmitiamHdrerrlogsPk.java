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
 * @author kholish
 */
@Embeddable
public class AhmitiamHdrerrlogsPk implements Serializable {

    @Column(name = "VEVENTID", nullable = false, length = 40)
    private String veventid;

    @Column(name = "VNRP", nullable = false, length = 10)
    private String vnrp;

    @Column(name = "VPROCESS", nullable = false, length = 30)
    private String vproccess;

    public String getVeventid() {
        return veventid;
    }

    public void setVeventid(String veventid) {
        this.veventid = veventid;
    }

    public String getVnrp() {
        return vnrp;
    }

    public void setVnrp(String vnrp) {
        this.vnrp = vnrp;
    }

    public String getVproccess() {
        return vproccess;
    }

    public void setVproccess(String vproccess) {
        this.vproccess = vproccess;
    }

}
