/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author reza.mr
 */
@Embeddable
public class AhmhrntmHdrotsempsPk implements Serializable {

    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ROTSEMPSHS", nullable = false, unique = true)
    private String rotsempshs;

    public String getRotsempshs() {
        return rotsempshs;
    }

    public void setRotsempshs(String rotsempshs) {
        this.rotsempshs = rotsempshs;
    }

}
