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
public class AhmgaersTxnrglasetsPk implements Serializable {
    @Column(name="VRGLIDAS")
    private String vrglidas;
    
    @Column(name="VPERMITIDAS")
    private String vpermitidas;

    public AhmgaersTxnrglasetsPk() {
    }
    
    public AhmgaersTxnrglasetsPk(String vrglidas, String vpermitidas) {
        this.vrglidas = vrglidas;
        this.vpermitidas = vpermitidas;
    }

    public String getVrglidas() {
        return vrglidas;
    }

    public void setVrglidas(String vrglidas) {
        this.vrglidas = vrglidas;
    }

    public String getVpermitidas() {
        return vpermitidas;
    }

    public void setVpermitidas(String vpermitidas) {
        this.vpermitidas = vpermitidas;
    }
    
    
}
