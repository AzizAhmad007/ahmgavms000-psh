/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.vo;

import java.util.Date;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031VoMonitoring {
    private Integer headerId;
    private String status;
    private String tipeKaryawanBlacklist;
    private String nrp;
    private String jenisKartuIdentitas;
    private String noIdentitas;
    private String nik;
    private String nama;
    private String namaPerusahaan;
    private String jenisKelamin;
    private String alasanBlacklist;
    private String alamatKtp;
    private String alamatDomisili;
    private String typeFoto;
    private String namaFoto;
    private String extensionFoto;
    private String pathFoto;
    private Date tglStartEffective;
    private String tglStartEffectiveText;
    private Date tglEndEffective;
    private String tglEndEffectiveText;
    private String createBy;
    private String lastModBy;
    private Date createDate;
    private String createDateText;
    
    private Integer detailId;
    
    private Integer totalMonitoring;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastModBy() {
        return lastModBy;
    }

    public void setLastModBy(String lastModBy) {
        this.lastModBy = lastModBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateText() {
        return createDateText;
    }

    public void setCreateDateText(String createDateText) {
        this.createDateText = createDateText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getTglStartEffectiveText() {
        return tglStartEffectiveText;
    }

    public void setTglStartEffectiveText(String tglStartEffectiveText) {
        this.tglStartEffectiveText = tglStartEffectiveText;
    }

    public String getTglEndEffectiveText() {
        return tglEndEffectiveText;
    }

    public void setTglEndEffectiveText(String tglEndEffectiveText) {
        this.tglEndEffectiveText = tglEndEffectiveText;
    }
    
    public Integer getTotalMonitoring() {
        return totalMonitoring;
    }

    public void setTotalMonitoring(Integer totalMonitoring) {
        this.totalMonitoring = totalMonitoring;
    }


    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
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
    

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
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

    public Date getTglStartEffective() {
        return tglStartEffective;
    }

    public void setTglStartEffective(Date tglStartEffective) {
        this.tglStartEffective = tglStartEffective;
    }

    public Date getTglEndEffective() {
        return tglEndEffective;
    }

    public void setTglEndEffective(Date tglEndEffective) {
        this.tglEndEffective = tglEndEffective;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

   
}
