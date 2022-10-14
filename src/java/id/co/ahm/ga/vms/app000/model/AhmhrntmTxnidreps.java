/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author reza.mr
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "AHMHRNTM_TXNIDREPS")
public class AhmhrntmTxnidreps extends DefaultEntityImpl implements Serializable{
    
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "RIDREPSID", nullable = false, unique = true)
    private String ridrepsid;

    @Column(name = "VWRKORDERNO", length = 20)
    private String vwrkorderno;

    @Column(name = "VNRP", length = 10)
    private String vnrp;

    @Column(name = "NREASONREP")
    private BigDecimal nreasonrep;

    @Column(name = "NCLAIMEMP")
    private BigDecimal nclaimemp;

    @Column(name = "VREMARK", length = 1000)
    private String vremark;

    @Column(name = "VSTATUS", length = 10)
    private String vstatus;

    @Column(name = "VPCKUPSTS", length = 10)
    private String vpckupsts;

    @Column(name = "VCARDNAME", length = 50)
    private String vcardname; 

    public String getRidrepsid() {
        return ridrepsid;
    }

    public void setRidrepsid(String ridrepsid) {
        this.ridrepsid = ridrepsid;
    }

    public String getVwrkorderno() {
        return vwrkorderno;
    }

    public void setVwrkorderno(String vwrkorderno) {
        this.vwrkorderno = vwrkorderno;
    }

    public String getVnrp() {
        return vnrp;
    }

    public void setVnrp(String vnrp) {
        this.vnrp = vnrp;
    }

    public BigDecimal getNreasonrep() {
        return nreasonrep;
    }

    public void setNreasonrep(BigDecimal nreasonrep) {
        this.nreasonrep = nreasonrep;
    }

    public BigDecimal getNclaimemp() {
        return nclaimemp;
    }

    public void setNclaimemp(BigDecimal nclaimemp) {
        this.nclaimemp = nclaimemp;
    }

    public String getVremark() {
        return vremark;
    }

    public void setVremark(String vremark) {
        this.vremark = vremark;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public String getVpckupsts() {
        return vpckupsts;
    }

    public void setVpckupsts(String vpckupsts) {
        this.vpckupsts = vpckupsts;
    }

    public String getVcardname() {
        return vcardname;
    }

    public void setVcardname(String vcardname) {
        this.vcardname = vcardname;
    }
    
    
    
}
