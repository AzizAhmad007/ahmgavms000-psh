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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Hitoshi Mario Naga M
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMGAVMS_HISDECLARS")
public class AhmgavmsHisDeclrs extends DefaultEntityImpl implements Serializable {
    @Id
    @Column(name = "VDECTYPE")
    private String vdecstype;
    
    @Column(name = "VPLANTID")
    private String vplantid;
    
    @Column(name = "VTITLE")
    private String vtitle;
    
    @Column(name = "VBODYID")
    private String vbodyid;
    
    @Column(name = "VBODYEN")
    private String vbodyen;
    
    @Column(name = "DSTARTEFF")
    @Temporal(TemporalType.DATE)
    private Date dstarteff;
    
    @Column(name = "DENDEFF")
    @Temporal(TemporalType.DATE)
    private Date dendeff;
    
    @Column(name = "VVERSION")
    private int vversion;
    
    @Column(name = "VSEQ")
    private String vseq;
    
    @Column(name = "VFTYPEDOC")
    private String vftypedoc;
    
    @Column(name = "VFNAMEDOC")
    private String vfnamedoc;
    
    @Column(name = "VFEXTDOC")
    private String vfextdoc;
    
    @Column(name = "VFPATHDOC")
    private String vfpathdoc;
    
    @Column(name = "VFTYPEFOTO")
    private String vftypefoto;
    
    @Column(name = "VFNAMEFOTO")
    private String vfnamefoto;
    
    @Column(name = "VFEXTFOTO")
    private String vfextfoto;
    
    @Column(name = "VFPATHFOTO")
    private String vfpathfoto;

//    @Column(name = "VSIZE")
//    private String vsize;

    public String getVdecstype() {
        return vdecstype;
    }

    public void setVdecstype(String vdecstype) {
        this.vdecstype = vdecstype;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getVbodyid() {
        return vbodyid;
    }

    public void setVbodyid(String vbodyid) {
        this.vbodyid = vbodyid;
    }

    public String getVbodyen() {
        return vbodyen;
    }

    public void setVbodyen(String vbodyen) {
        this.vbodyen = vbodyen;
    }

    public Date getDstarteff() {
        return dstarteff;
    }

    public void setDstarteff(Date dstarteff) {
        this.dstarteff = dstarteff;
    }

    public Date getDendeff() {
        return dendeff;
    }

    public void setDendeff(Date dendeff) {
        this.dendeff = dendeff;
    }

    public int getVversion() {
        return vversion;
    }

    public void setVversion(int vversion) {
        this.vversion = vversion;
    }

    public String getVseq() {
        return vseq;
    }

    public void setVseq(String vseq) {
        this.vseq = vseq;
    }

    public String getVftypedoc() {
        return vftypedoc;
    }

    public void setVftypedoc(String vftypedoc) {
        this.vftypedoc = vftypedoc;
    }

    public String getVfnamedoc() {
        return vfnamedoc;
    }

    public void setVfnamedoc(String vfnamedoc) {
        this.vfnamedoc = vfnamedoc;
    }

    public String getVfextdoc() {
        return vfextdoc;
    }

    public void setVfextdoc(String vfextdoc) {
        this.vfextdoc = vfextdoc;
    }

    public String getVfpathdoc() {
        return vfpathdoc;
    }

    public void setVfpathdoc(String vfpathdoc) {
        this.vfpathdoc = vfpathdoc;
    }

    public String getVftypefoto() {
        return vftypefoto;
    }

    public void setVftypefoto(String vftypefoto) {
        this.vftypefoto = vftypefoto;
    }

    public String getVfnamefoto() {
        return vfnamefoto;
    }

    public void setVfnamefoto(String vfnamefoto) {
        this.vfnamefoto = vfnamefoto;
    }

    public String getVfextfoto() {
        return vfextfoto;
    }

    public void setVfextfoto(String vfextfoto) {
        this.vfextfoto = vfextfoto;
    }

    public String getVfpathfoto() {
        return vfpathfoto;
    }

    public void setVfpathfoto(String vfpathfoto) {
        this.vfpathfoto = vfpathfoto;
    }

//    public String getVsize() {
//        return vsize;
//    }
//
//    public void setVsize(String vsize) {
//        this.vsize = vsize;
//    }
//    
}
