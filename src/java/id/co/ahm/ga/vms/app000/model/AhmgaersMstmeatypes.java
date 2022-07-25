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
 * @author developer
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="AHMGAERS_MSTMEATYPES")
public class AhmgaersMstmeatypes extends DefaultEntityImpl implements Serializable {
    
    @Id    
    @Column(name = "VMEATYPESID")
    private String vmeatypesid;
    
    @Column(name = "VMEATYPESNM")
    private String vmeatypesnm;
    
    @Column(name = "VMEATYPEDSC")
    private String vmeatypedsc;
    
    @Column(name = "VOPRTIME")
    private String voprtime;
    
    @Column(name = "VSTATUS")
    private String vstatus;

    public String getVmeatypesid() {
        return vmeatypesid;
    }

    public void setVmeatypesid(String vmeatypesid) {
        this.vmeatypesid = vmeatypesid;
    }

    public String getVmeatypesnm() {
        return vmeatypesnm;
    }

    public void setVmeatypesnm(String vmeatypesnm) {
        this.vmeatypesnm = vmeatypesnm;
    }

    public String getVmeatypedsc() {
        return vmeatypedsc;
    }

    public void setVmeatypedsc(String vmeatypedsc) {
        this.vmeatypedsc = vmeatypedsc;
    }

    public String getVoprtime() {
        return voprtime;
    }

    public void setVoprtime(String voprtime) {
        this.voprtime = voprtime;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

}
