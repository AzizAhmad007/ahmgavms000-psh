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
@Table(name = "AHMGAVMS_HISBLCKLSTS")
public class AhmgavmsHisblst extends DefaultEntityImpl implements Serializable{
    @EmbeddedId
    private AhmgavmsHdrblstPk ahmgavmsHdrblstPk;
    
    @Column(name = "VNIK",length=50)
    private String nik;

    @Column(name = "VNAME",length=50)
    private String nama;

    @Column(name = "VGENDER",length=50)
    private String jenisKelamin;

    @Column(name = "VADRESKTP",length=100)
    private String alamatKtp;

    @Column(name = "VADRESDOM",length=100)
    private String alamatDomisili;

    @Column(name = "VISEMP",length=1)
    private String tipeKaryawanBlacklist;
    
    @Column(name = "VCOMPANY",length=50)
    private String namaPerusahaan;

    @Column(name = "VREASON",length=100)
    private String alasanBlacklist;

    @Column(name = "VFTYPEFOTO")
    private String typeFoto;

    @Column(name = "VFNAMEFOTO")
    private String namaFoto;

    @Column(name = "VFEXTFOTO")
    private String extensionFoto;

    @Column(name = "VFPATHFOTO")
    private String pathFoto;

    @Column(name = "DSTARTEFF")
    private Date dateStart;

    @Column(name = "DENDEFF")
    private Date dateEnd;

    public AhmgavmsHdrblstPk getAhmgavmsHdrblstPk() {
        return ahmgavmsHdrblstPk;
    }

    public void setAhmgavmsHdrblstPk(AhmgavmsHdrblstPk ahmgavmsHdrblstPk) {
        this.ahmgavmsHdrblstPk = ahmgavmsHdrblstPk;
    }

   

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamatKtp() {
        return alamatKtp;
    }

    public void setAlamatKtp(String alamatKtp) {
        this.alamatKtp = alamatKtp;
    }

    public String getAlamatDomisili() {
        return alamatDomisili;
    }

    public void setAlamatDomisili(String alamatDomisili) {
        this.alamatDomisili = alamatDomisili;
    }

    public String getTipeKaryawanBlacklist() {
        return tipeKaryawanBlacklist;
    }

    public void setTipeKaryawanBlacklist(String tipeKaryawanBlacklist) {
        this.tipeKaryawanBlacklist = tipeKaryawanBlacklist;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getAlasanBlacklist() {
        return alasanBlacklist;
    }

    public void setAlasanBlacklist(String alasanBlacklist) {
        this.alasanBlacklist = alasanBlacklist;
    }

    public String getTypeFoto() {
        return typeFoto;
    }

    public void setTypeFoto(String typeFoto) {
        this.typeFoto = typeFoto;
    }

    public String getNamaFoto() {
        return namaFoto;
    }

    public void setNamaFoto(String namaFoto) {
        this.namaFoto = namaFoto;
    }

    public String getExtensionFoto() {
        return extensionFoto;
    }

    public void setExtensionFoto(String extensionFoto) {
        this.extensionFoto = extensionFoto;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    
}
