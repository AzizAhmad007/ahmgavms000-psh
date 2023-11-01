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
 * @author Ahmad Mukaram Aziz
 */
@Embeddable
public class AhmgavmsHisblstPk implements Serializable{
     @Column(name = "NIDHDR", nullable = false, unique = true)
    private String headerId;

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }
     
     
}
