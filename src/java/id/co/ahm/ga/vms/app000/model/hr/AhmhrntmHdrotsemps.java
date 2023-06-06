/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model.hr;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author reza.mr
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMHRNTM_HDROTSEMPS")
public class AhmhrntmHdrotsemps extends DefaultEntityImpl implements Serializable{
    
//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    @Column(name = "ROTSEMPSHS", nullable = false, unique = true)
//    private String rotsempshs;
    
    @EmbeddedId
    private AhmhrntmHdrotsempsPk ahmhrntmHdrotsempsPk;
    
    @Column(name = "VOTSID", length = 10, nullable = false)
    private String votsid;

    @Column(name = "VPERSID", length = 16, nullable = false)
    private String vpersid;

    @Column(name = "VNAME", length = 80)
    private String vname;

    @Column(name = "VBIRTHPLC", length = 40)
    private String vbirthplc;

    @Column(name = "DBIRTHDT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dbirthdt;
    
    @Column(name = "VCOMPANY", length = 25)
    private String vcompany;

    @Column(name = "VOTSTYPE", length = 25)
    private String votstype;

    @Column(name = "VLOCATION", length = 25)
    private String vlocation;

    @Column(name = "NAHMCARDID")
    private BigDecimal nahmcardid;
    
    @Column(name = "DBGNEFFDT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dbgneffdt;
    
    @Column(name = "DENDEFFDT")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dendeffdt; 
        
    @Column(name = "VPHOTOID", length = 100)
    private String vphotoid;

    @Column(name = "VABSRDR", length = 3)
    private String vabsrdr;

    @Column(name = "VSECGATE", length = 3)
    private String vsecgate;

    @Column(name = "VCANTEEN", length = 3)
    private String vcanteen;

    @Column(name = "VPHOTONAME", length = 100)
    private String vphotoname;

    @Lob
    @Column(name = "BPHOTO")
    private byte[] bphoto;

    @Column(name = "VNAMERDR", length = 30)
    private String vnamerdr;
  
    @Column(name = "NAHMCARDORI")
    private BigDecimal nahmcardori;
    
    @Column(name = "DPASSEXP")
    private Date dpassexp;
    
    @Column(name = "VEMPPHONE", length = 20)
    private String vempphone;

    @Column(name = "VREQNAME", length = 60)
    private String vreqname;

    @Column(name = "VREQPHONE", length = 20)
    private String vreqphone;

    @Column(name = "VOTSSTTS", length = 40)
    private String votsstts;

    @Column(name = "VVACSTTS", length = 40)
    private String vvacstts;

    @Column(name = "DLASTVAC")
    private Date dlastvac;
    
    @Column(name = "VVACTYPE", length = 40)
    private String vvactype;
   
    @Column(name = "VVACDTL", length = 40)
    private String vvacdtl;
    
    @Column(name = "VKTPNAME", length = 60)
    private String vktpname;

    @Column(name = "VCERTNAME", length = 60)
    private String vcertname;

    @Column(name = "VDOCNAME", length = 60)
    private String vdocname;

    @Column(name = "VCATEGORY", length = 25)
    private String vcategory;

    @Column(name = "VGATEACCLVL", length = 25)
    private String vgateacclvl;

    @Column(name = "DSTATUS")
    private Date dstatus;
    
    @Column(name = "DRETURN")
    private Date dreturn;
    
    @Column(name = "NRETURNRFID")
    private BigDecimal nreturnrfid;
    
    @Column(name = "VJOBDTL", length = 100)
    private String vjobdtl;

    @Column(name = "VNOTE", length = 500)
    private String vnote;

    @Column(name = "VNTVS", length = 500)
    private String vntvs;

    @Column(name = "VRBSUPLAY", length = 10)
    private String vrbsuplay;

    @Column(name = "VAPPDISCLM", length = 10)
    private String vappdisclm;

    @Column(name = "VNOTEREJC", length = 100)
    private String vnoterejc;
    
//    public String getRotsempshs() {
//        return rotsempshs;
//    }
//
//    public void setRotsempshs(String rotsempshs) {
//        this.rotsempshs = rotsempshs;
//    }

    public AhmhrntmHdrotsempsPk getAhmhrntmHdrotsempsPk() {
        return ahmhrntmHdrotsempsPk;
    }

    public void setAhmhrntmHdrotsempsPk(AhmhrntmHdrotsempsPk ahmhrntmHdrotsempsPk) {
        this.ahmhrntmHdrotsempsPk = ahmhrntmHdrotsempsPk;
    }

    public String getVotsid() {
        return votsid;
    }

    public void setVotsid(String votsid) {
        this.votsid = votsid;
    }

    public String getVpersid() {
        return vpersid;
    }

    public void setVpersid(String vpersid) {
        this.vpersid = vpersid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVbirthplc() {
        return vbirthplc;
    }

    public void setVbirthplc(String vbirthplc) {
        this.vbirthplc = vbirthplc;
    }

    public Date getDbirthdt() {
        return dbirthdt;
    }

    public void setDbirthdt(Date dbirthdt) {
        this.dbirthdt = dbirthdt;
    }

    public String getVcompany() {
        return vcompany;
    }

    public void setVcompany(String vcompany) {
        this.vcompany = vcompany;
    }

    public String getVotstype() {
        return votstype;
    }

    public void setVotstype(String votstype) {
        this.votstype = votstype;
    }

    public String getVlocation() {
        return vlocation;
    }

    public void setVlocation(String vlocation) {
        this.vlocation = vlocation;
    }

    public BigDecimal getNahmcardid() {
        return nahmcardid;
    }

    public void setNahmcardid(BigDecimal nahmcardid) {
        this.nahmcardid = nahmcardid;
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

    public String getVphotoid() {
        return vphotoid;
    }

    public void setVphotoid(String vphotoid) {
        this.vphotoid = vphotoid;
    }

    public String getVabsrdr() {
        return vabsrdr;
    }

    public void setVabsrdr(String vabsrdr) {
        this.vabsrdr = vabsrdr;
    }

    public String getVsecgate() {
        return vsecgate;
    }

    public void setVsecgate(String vsecgate) {
        this.vsecgate = vsecgate;
    }

    public String getVcanteen() {
        return vcanteen;
    }

    public void setVcanteen(String vcanteen) {
        this.vcanteen = vcanteen;
    }

    public String getVphotoname() {
        return vphotoname;
    }

    public void setVphotoname(String vphotoname) {
        this.vphotoname = vphotoname;
    }

    public byte[] getBphoto() {
        return bphoto;
    }

    public void setBphoto(byte[] bphoto) {
        this.bphoto = bphoto;
    }

    public String getVnamerdr() {
        return vnamerdr;
    }

    public void setVnamerdr(String vnamerdr) {
        this.vnamerdr = vnamerdr;
    }

    public BigDecimal getNahmcardori() {
        return nahmcardori;
    }

    public void setNahmcardori(BigDecimal nahmcardori) {
        this.nahmcardori = nahmcardori;
    }

    public Date getDpassexp() {
        return dpassexp;
    }

    public void setDpassexp(Date dpassexp) {
        this.dpassexp = dpassexp;
    }

    public String getVempphone() {
        return vempphone;
    }

    public void setVempphone(String vempphone) {
        this.vempphone = vempphone;
    }

    public String getVreqname() {
        return vreqname;
    }

    public void setVreqname(String vreqname) {
        this.vreqname = vreqname;
    }

    public String getVreqphone() {
        return vreqphone;
    }

    public void setVreqphone(String vreqphone) {
        this.vreqphone = vreqphone;
    }

    public String getVotsstts() {
        return votsstts;
    }

    public void setVotsstts(String votsstts) {
        this.votsstts = votsstts;
    }

    public String getVvacstts() {
        return vvacstts;
    }

    public void setVvacstts(String vvacstts) {
        this.vvacstts = vvacstts;
    }

    public Date getDlastvac() {
        return dlastvac;
    }

    public void setDlastvac(Date dlastvac) {
        this.dlastvac = dlastvac;
    }

    public String getVvactype() {
        return vvactype;
    }

    public void setVvactype(String vvactype) {
        this.vvactype = vvactype;
    }

    public String getVvacdtl() {
        return vvacdtl;
    }

    public void setVvacdtl(String vvacdtl) {
        this.vvacdtl = vvacdtl;
    }

    public String getVktpname() {
        return vktpname;
    }

    public void setVktpname(String vktpname) {
        this.vktpname = vktpname;
    }

    public String getVcertname() {
        return vcertname;
    }

    public void setVcertname(String vcertname) {
        this.vcertname = vcertname;
    }

    public String getVdocname() {
        return vdocname;
    }

    public void setVdocname(String vdocname) {
        this.vdocname = vdocname;
    }

    public String getVcategory() {
        return vcategory;
    }

    public void setVcategory(String vcategory) {
        this.vcategory = vcategory;
    }

    public String getVgateacclvl() {
        return vgateacclvl;
    }

    public void setVgateacclvl(String vgateacclvl) {
        this.vgateacclvl = vgateacclvl;
    }

    public Date getDstatus() {
        return dstatus;
    }

    public void setDstatus(Date dstatus) {
        this.dstatus = dstatus;
    }

    public Date getDreturn() {
        return dreturn;
    }

    public void setDreturn(Date dreturn) {
        this.dreturn = dreturn;
    }

    public BigDecimal getNreturnrfid() {
        return nreturnrfid;
    }

    public void setNreturnrfid(BigDecimal nreturnrfid) {
        this.nreturnrfid = nreturnrfid;
    }

    public String getVjobdtl() {
        return vjobdtl;
    }

    public void setVjobdtl(String vjobdtl) {
        this.vjobdtl = vjobdtl;
    }

    public String getVnote() {
        return vnote;
    }

    public void setVnote(String vnote) {
        this.vnote = vnote;
    }

    public String getVntvs() {
        return vntvs;
    }

    public void setVntvs(String vntvs) {
        this.vntvs = vntvs;
    }

    public String getVrbsuplay() {
        return vrbsuplay;
    }

    public void setVrbsuplay(String vrbsuplay) {
        this.vrbsuplay = vrbsuplay;
    }

    public String getVappdisclm() {
        return vappdisclm;
    }

    public void setVappdisclm(String vappdisclm) {
        this.vappdisclm = vappdisclm;
    }

    public String getVnoterejc() {
        return vnoterejc;
    }

    public void setVnoterejc(String vnoterejc) {
        this.vnoterejc = vnoterejc;
    }
    
}
