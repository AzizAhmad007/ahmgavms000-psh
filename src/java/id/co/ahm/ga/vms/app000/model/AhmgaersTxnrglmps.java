/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "AHMGAERS_TXNRGLMPS")
public class AhmgaersTxnrglmps extends DefaultEntityImpl implements Serializable{
    @EmbeddedId
    private AhmgaersTxnrglmpsPk primaryKey;
    
    @Column(name = "VTRRGLMPID")
    private String vtrrglmpid;

    @ManyToOne(targetEntity = AhmgaersTxnpmtinmps.class)
    @JoinColumn(name = "VPERMITIDMP", referencedColumnName = "VPERMITID", insertable = false, updatable = false)
    private AhmgaersTxnpmtinmps rglMpHeader;

    public AhmgaersTxnrglmpsPk getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(AhmgaersTxnrglmpsPk primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getVtrrglmpid() {
        return vtrrglmpid;
    }

    public void setVtrrglmpid(String vtrrglmpid) {
        this.vtrrglmpid = vtrrglmpid;
    }

    public AhmgaersTxnpmtinmps getRglMpHeader() {
        return rglMpHeader;
    }

    public void setRglMpHeader(AhmgaersTxnpmtinmps rglMpHeader) {
        this.rglMpHeader = rglMpHeader;
    }

    
}
