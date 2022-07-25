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
@Table(name = "AHMGAERS_TXNRGLASETS")
public class AhmgaersTxnrglasets extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    private AhmgaersTxnrglasetsPk primaryKey;
    
    @Column(name = "VTRRGLASSID")
    private String vtrrglassid;
    
    @ManyToOne(targetEntity = AhmgaersTxnpmtinass.class)
    @JoinColumn(name = "VPERMITIDAS", referencedColumnName = "VPERMITID", insertable = false, updatable = false)
    private AhmgaersTxnpmtinass rglAssetHeader;

    public AhmgaersTxnrglasetsPk getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(AhmgaersTxnrglasetsPk primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getVtrrglassid() {
        return vtrrglassid;
    }

    public void setVtrrglassid(String vtrrglassid) {
        this.vtrrglassid = vtrrglassid;
    }

    public AhmgaersTxnpmtinass getRglAssetHeader() {
        return rglAssetHeader;
    }

    public void setRglAssetHeader(AhmgaersTxnpmtinass rglAssetHeader) {
        this.rglAssetHeader = rglAssetHeader;
    }

      
}
