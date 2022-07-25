/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Developer
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNRGLAREAS")
public class AhmgaersTxnrglareas extends DefaultEntityImpl implements Serializable {
    
    @EmbeddedId
    private AhmgaersTxnrglareasPk ahmgaersTxnrglareasPk;

    public AhmgaersTxnrglareasPk getAhmgaersTxnrglareasPk() {
        return ahmgaersTxnrglareasPk;
    }

    public void setAhmgaersTxnrglareasPk(AhmgaersTxnrglareasPk ahmgaersTxnrglareasPk) {
        this.ahmgaersTxnrglareasPk = ahmgaersTxnrglareasPk;
    }

}
