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
public class AhmitiamDtlerrlogsPk implements Serializable {

    @Column(name = "RERLOG_VEVENTID", nullable = false, length = 40)
    private String veventid;

    @Column(name = "RERLOG_VNRP", nullable = false, length = 10)
    private String vnrp;

    @Column(name = "RERLOG_VPROCESS", nullable = false, length = 30)
    private String vprocess;

    @Column(name = "NSEQ", nullable = true, length = 3)
    private Number nseq;

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

    public String getVprocess() {
        return vprocess;
    }

    public void setVprocess(String vprocess) {
        this.vprocess = vprocess;
    }

    public Number getNseq() {
        return nseq;
    }

    public void setNseq(Number nseq) {
        this.nseq = nseq;
    }

}
