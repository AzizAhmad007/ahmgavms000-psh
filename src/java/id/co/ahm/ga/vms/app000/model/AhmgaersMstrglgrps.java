/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author A Roni Purwanto
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_MSTRGLGRPS")
public class AhmgaersMstrglgrps extends DefaultEntityImpl implements Serializable {

    @Id
    @Column(name = "VRGLGROUPID")
    private String vrglgroupid;

    @Column(name = "VLEVEL1")
    private String vlevel1;

    @Column(name = "vlevel2")
    private String vlevel2;

    @Column(name = "VSTATUS")
    private String vstatus;

    public String getVrglgroupid() {
        return vrglgroupid;
    }

    public void setVrglgroupid(String vrglgroupid) {
        this.vrglgroupid = vrglgroupid;
    }

    public String getVlevel1() {
        return vlevel1;
    }

    public void setVlevel1(String vlevel1) {
        this.vlevel1 = vlevel1;
    }

    public String getVlevel2() {
        return vlevel2;
    }

    public void setVlevel2(String vlevel2) {
        this.vlevel2 = vlevel2;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }
}
