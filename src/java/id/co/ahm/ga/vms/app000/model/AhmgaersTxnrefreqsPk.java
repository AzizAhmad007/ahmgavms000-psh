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
 * @author Developer
 */
@Embeddable
public class AhmgaersTxnrefreqsPk implements Serializable {
    
    @Column(name = "VRFDTLRGLID")
    private String vrfdtlrglid;

    @Column(name = "XGAMSTRQ_VDTLRGLID")
    private String xgamstrqVdtlrglid;

    public AhmgaersTxnrefreqsPk() {
    }

    public AhmgaersTxnrefreqsPk(String prevRegulationDetailId, String nextRegulationDetailId) {
        this.vrfdtlrglid = nextRegulationDetailId;
        this.xgamstrqVdtlrglid = prevRegulationDetailId;
    }

    public String getVrfdtlrglid() {
        return vrfdtlrglid;
    }

    public void setVrfdtlrglid(String vrfdtlrglid) {
        this.vrfdtlrglid = vrfdtlrglid;
    }

    public String getXgamstrqVdtlrglid() {
        return xgamstrqVdtlrglid;
    }

    public void setXgamstrqVdtlrglid(String xgamstrqVdtlrglid) {
        this.xgamstrqVdtlrglid = xgamstrqVdtlrglid;
    }

}
