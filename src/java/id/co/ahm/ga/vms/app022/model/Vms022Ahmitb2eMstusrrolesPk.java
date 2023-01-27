/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.model;

import java.io.Serializable;
import javax.persistence.Column;

/**
 *
 * @author reza.mr
 */
public class Vms022Ahmitb2eMstusrrolesPk implements Serializable {

    @Column(name = "VROLEID")
    private String vroleid;

    @Column(name = "VUSERID")
    private String vuserid;

    public String getVroleid() {
        return vroleid;
    }

    public void setVroleid(String vroleid) {
        this.vroleid = vroleid;
    }

    public String getVuserid() {
        return vuserid;
    }

    public void setVuserid(String vuserid) {
        this.vuserid = vuserid;
    }
}
