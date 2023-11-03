/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model.hr;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kahfi
 */
@Entity
@Table(name = "FMHRD_GENERAL_DATAS")
public class FmhrdGeneralDatas {
    @Id
    @Column(name = "NRP")
    private BigDecimal nrp;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NAMA_DEPARTEMEN")
    private String namaDepartemen;

    @Column(name = "NAMA_DIVISI")
    private String namaDivisi;

    @Column(name = "NAMA_SEKSI")
    private String namaSeksi;

    @Column(name = "DP_HEAD_NRP")
    private String nrpHeadDepartemen;

    @Column(name = "DV_HEAD_NRP")
    private String nrpHeadDivisi;

    @Column(name = "DP_HEAD_NAME")
    private String namaHeadDepartemen;
    
    @Column(name = "TELEPHONE")
    private String telephone;

    public BigDecimal getNrp() {
        return nrp;
    }

    public void setNrp(BigDecimal nrp) {
        this.nrp = nrp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamaDepartemen() {
        return namaDepartemen;
    }

    public void setNamaDepartemen(String namaDepartemen) {
        this.namaDepartemen = namaDepartemen;
    }

    public String getNamaDivisi() {
        return namaDivisi;
    }

    public void setNamaDivisi(String namaDivisi) {
        this.namaDivisi = namaDivisi;
    }

    public String getNamaSeksi() {
        return namaSeksi;
    }

    public void setNamaSeksi(String namaSeksi) {
        this.namaSeksi = namaSeksi;
    }

    public String getNrpHeadDepartemen() {
        return nrpHeadDepartemen;
    }

    public void setNrpHeadDepartemen(String nrpHeadDepartemen) {
        this.nrpHeadDepartemen = nrpHeadDepartemen;
    }

    public String getNrpHeadDivisi() {
        return nrpHeadDivisi;
    }

    public void setNrpHeadDivisi(String nrpHeadDivisi) {
        this.nrpHeadDivisi = nrpHeadDivisi;
    }

    public String getNamaHeadDepartemen() {
        return namaHeadDepartemen;
    }

    public void setNamaHeadDepartemen(String namaHeadDepartemen) {
        this.namaHeadDepartemen = namaHeadDepartemen;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
}
