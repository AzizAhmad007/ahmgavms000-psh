/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author reza.mr
 */
@Entity
@Table(name = "AHMITB2E_MSTUSRROLES")
public class Vms022Ahmitb2eMstusrroles implements Serializable {

    @EmbeddedId
    private Vms022Ahmitb2eMstusrrolesPk ahmitb2eMstusrrolesPk;

    @Column(name = "DBEGINEFF")
    private Date dbegineff;

    @Column(name = "DLASTEFF")
    private Date dlasteff;

    public Date getDbegineff() {
        return dbegineff;
    }

    public void setDbegineff(Date dbegineff) {
        this.dbegineff = dbegineff;
    }

    public Date getDlasteff() {
        return dlasteff;
    }

    public void setDlasteff(Date dlasteff) {
        this.dlasteff = dlasteff;
    }

    public Vms022Ahmitb2eMstusrrolesPk getAhmitb2eMstusrrolesPk() {
        return ahmitb2eMstusrrolesPk;
    }

    public void setAhmitb2eMstusrrolesPk(Vms022Ahmitb2eMstusrrolesPk ahmitb2eMstusrrolesPk) {
        this.ahmitb2eMstusrrolesPk = ahmitb2eMstusrrolesPk;
    }
    
    
}
