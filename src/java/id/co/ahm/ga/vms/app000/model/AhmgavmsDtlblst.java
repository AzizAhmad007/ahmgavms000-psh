/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMGAVMS_DTLBLCKLSTS")
public class AhmgavmsDtlblst extends DefaultEntityImpl implements Serializable{

    @EmbeddedId
    private AhmgavmsDtlblstPk ahmgavmsDtlBlstPk;
    
    @Column(name = "NIDHDR")
    private Integer headerId;
    
    @Column(name = "VIDTYPE",length=50)
    private String jenisKartuIdentitas;
    
    @Column(name = "VIDNO",length=50)
    private String noIdentitas;

    public AhmgavmsDtlblstPk getAhmgavmsDtlBlstPk() {
        return ahmgavmsDtlBlstPk;
    }

    public void setAhmgavmsDtlBlstPk(AhmgavmsDtlblstPk ahmgavmsDtlBlstPk) {
        this.ahmgavmsDtlBlstPk = ahmgavmsDtlBlstPk;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getJenisKartuIdentitas() {
        return jenisKartuIdentitas;
    }

    public void setJenisKartuIdentitas(String jenisKartuIdentitas) {
        this.jenisKartuIdentitas = jenisKartuIdentitas;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    
}
