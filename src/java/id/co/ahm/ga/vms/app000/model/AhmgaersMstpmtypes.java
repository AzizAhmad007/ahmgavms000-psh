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
@Table(name="AHMGAERS_MSTPMTTYPES")
public class AhmgaersMstpmtypes extends DefaultEntityImpl implements Serializable {
    @Id    
    @Column(name = "VPMTTYPEID")
    private String vpmttypeid;
  
    @Column(name = "VPMTGROUP")
    private String vpmtgroup;
    
    @Column(name = "VPMTTYPENM")
    private String vpmttypenm;
    
    @Column(name = "VPMTDESC")
    private String vpmtdesc;
    
    @Column(name = "VSTATUS")
    private String vstatus;

    public String getVpmttypeid() {
        return vpmttypeid;
    }

    public void setVpmttypeid(String vpmttypeid) {
        this.vpmttypeid = vpmttypeid;
    }

    public String getVpmtgroup() {
        return vpmtgroup;
    }

    public void setVpmtgroup(String vpmtgroup) {
        this.vpmtgroup = vpmtgroup;
    }

    public String getVpmttypenm() {
        return vpmttypenm;
    }

    public void setVpmttypenm(String vpmttypenm) {
        this.vpmttypenm = vpmttypenm;
    }

    public String getVpmtdesc() {
        return vpmtdesc;
    }

    public void setVpmtdesc(String vpmtdesc) {
        this.vpmtdesc = vpmtdesc;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }
    
    
}
