/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model.hr;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author reza.mr
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMHRNTM_DTLPRMGBLS")
public class AhmhrntmDtlprmgbls extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "RDTLID", nullable = false)
    private String rdtlid;
    
    @Column(name = "RPRM_GBLID", nullable = false)
    private String rprmGblid;
    
    @Column(name = "VPGBLCD", length = 10)
    private String vpgblcd;
    
    @Column(name = "VPGBLNM", length = 100)
    private String vpgblnm;
    
    @Column(name = "VPGBLDSC", length = 100)
    private String vpgbldsc;
    
    @Column(name = "VVAL", length = 30)
    private String vval;
    
    @Column(name = "VREMARKS", length = 1000)
    private String vremarks;
    
    @Column(name = "DBGNEFFDT")
    private Date dbgneffdt;
    
    @Column(name = "DENDEFFDT")
    private Date dendeffdt;

    public String getRdtlid() {
        return rdtlid;
    }

    public void setRdtlid(String rdtlid) {
        this.rdtlid = rdtlid;
    }

    public String getRprmGblid() {
        return rprmGblid;
    }

    public void setRprmGblid(String rprmGblid) {
        this.rprmGblid = rprmGblid;
    }

    public String getVpgblcd() {
        return vpgblcd;
    }

    public void setVpgblcd(String vpgblcd) {
        this.vpgblcd = vpgblcd;
    }

    public String getVpgblnm() {
        return vpgblnm;
    }

    public void setVpgblnm(String vpgblnm) {
        this.vpgblnm = vpgblnm;
    }

    public String getVpgbldsc() {
        return vpgbldsc;
    }

    public void setVpgbldsc(String vpgbldsc) {
        this.vpgbldsc = vpgbldsc;
    }

    public String getVval() {
        return vval;
    }

    public void setVval(String vval) {
        this.vval = vval;
    }

    public String getVremarks() {
        return vremarks;
    }

    public void setVremarks(String vremarks) {
        this.vremarks = vremarks;
    }

    public Date getDbgneffdt() {
        return dbgneffdt;
    }

    public void setDbgneffdt(Date dbgneffdt) {
        this.dbgneffdt = dbgneffdt;
    }

    public Date getDendeffdt() {
        return dendeffdt;
    }

    public void setDendeffdt(Date dendeffdt) {
        this.dendeffdt = dendeffdt;
    }
    
    
}
