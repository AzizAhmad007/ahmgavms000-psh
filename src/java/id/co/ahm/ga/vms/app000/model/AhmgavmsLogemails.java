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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author kahfi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMGAVMS_LOGEMAILS")
public class AhmgavmsLogemails extends DefaultEntityImpl implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "NID")
    private Integer nid;
    
    @Column(name = "VCODE")
    private String vcode;
    
    @Column(name = "VFROM")
    private String vfrom;
    
    @Column(name = "VTO")
    private String vto;
    
    @Column(name = "VFLAG")
    private String vflag;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getVfrom() {
        return vfrom;
    }

    public void setVfrom(String vfrom) {
        this.vfrom = vfrom;
    }

    public String getVto() {
        return vto;
    }

    public void setVto(String vto) {
        this.vto = vto;
    }

    public String getVflag() {
        return vflag;
    }

    public void setVflag(String vflag) {
        this.vflag = vflag;
    }
}
