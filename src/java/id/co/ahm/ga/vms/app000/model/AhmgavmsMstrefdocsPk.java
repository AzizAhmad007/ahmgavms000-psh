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
 * @author Nurvan Afandi
 */
@Embeddable
public class AhmgavmsMstrefdocsPk implements Serializable {
    
    @Column(name = "VREFDOCNO", nullable = false, length = 50)
    private String vrefdocno;

    public String getVrefdocno() {
        return vrefdocno;
    }

    public void setVrefdocno(String vrefdocno) {
        this.vrefdocno = vrefdocno;
    }
    
    
}
