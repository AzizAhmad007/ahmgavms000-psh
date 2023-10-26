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
 * @author kahfi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMGAVMS_HDRCHIEFS")
public class AhmgavmsHdrchiefs extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "VINVITNO", nullable = false, length = 50)
    private String vinvitno;
    
    @Column(name = "VMASTERNO")
    private String vmasterno;
    
    @Column(name = "VNAME")
    private String vname;
    
    @Column(name = "VCOMPANY")
    private String vcompany;
    
    @Column(name = "VEMAIL")
    private String vemail;
    
    @Column(name = "VNOHP")
    private String vnohp;
    
    @Column(name = "NQUOTA")
    private Integer nquota;
    
    @Column(name = "VINVLINK")
    private String vinvlink;

    public String getVinvitno() {
        return vinvitno;
    }

    public void setVinvitno(String vinvitno) {
        this.vinvitno = vinvitno;
    }

    public String getVmasterno() {
        return vmasterno;
    }

    public void setVmasterno(String vmasterno) {
        this.vmasterno = vmasterno;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVcompany() {
        return vcompany;
    }

    public void setVcompany(String vcompany) {
        this.vcompany = vcompany;
    }

    public String getVemail() {
        return vemail;
    }

    public void setVemail(String vemail) {
        this.vemail = vemail;
    }

    public String getVnohp() {
        return vnohp;
    }

    public void setVnohp(String vnohp) {
        this.vnohp = vnohp;
    }

    public Integer getNquota() {
        return nquota;
    }

    public void setNquota(Integer nquota) {
        this.nquota = nquota;
    }

    public String getVinvlink() {
        return vinvlink;
    }

    public void setVinvlink(String vinvlink) {
        this.vinvlink = vinvlink;
    }
    
    
}
