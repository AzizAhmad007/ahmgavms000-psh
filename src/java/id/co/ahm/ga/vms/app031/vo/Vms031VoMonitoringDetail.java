/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.vo;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031VoMonitoringDetail {
    private Vms031VoMonitoring voMonitoring;
    private String jenisKartuIdentitas;
    private String noIdentitas;

    public Vms031VoMonitoring getVoMonitoring() {
        return voMonitoring;
    }

    public void setVoMonitoring(Vms031VoMonitoring voMonitoring) {
        this.voMonitoring = voMonitoring;
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
