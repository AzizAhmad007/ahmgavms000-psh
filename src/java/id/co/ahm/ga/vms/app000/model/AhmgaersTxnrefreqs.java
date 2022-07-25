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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author developer
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNREFREQS")
public class AhmgaersTxnrefreqs extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    AhmgaersTxnrefreqsPk ahmgaersTxnrefreqsPk;
    
    public AhmgaersTxnrefreqsPk getAhmgaersTxnrefreqsPk() {
        return ahmgaersTxnrefreqsPk;
    }

    public void setAhmgaersTxnrefreqsPk(AhmgaersTxnrefreqsPk ahmgaersTxnrefreqsPk) {
        this.ahmgaersTxnrefreqsPk = ahmgaersTxnrefreqsPk;
    }

}
