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
public class AhmgaersTxnrglareasPk implements Serializable {
    
    @Column(name = "VAREA")
    private String varea;

    @Column(name = "MGARGLS_VRGLID")
    private String mgarglsVrglid;

    public AhmgaersTxnrglareasPk() {
    }

    public AhmgaersTxnrglareasPk(String varea, String mgarglsVrglid) {
        this.varea = varea;
        this.mgarglsVrglid = mgarglsVrglid;
    }

    public String getVarea() {
        return varea;
    }

    public void setVarea(String varea) {
        this.varea = varea;
    }

    public String getMgarglsVrglid() {
        return mgarglsVrglid;
    }

    public void setMgarglsVrglid(String mgarglsVrglid) {
        this.mgarglsVrglid = mgarglsVrglid;
    }

}
