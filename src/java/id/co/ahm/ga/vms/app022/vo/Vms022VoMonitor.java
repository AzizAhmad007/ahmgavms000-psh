/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.vo;

import java.util.List;

/**
 *
 * @author reza.mr
 */
public class Vms022VoMonitor {
    
    private Integer totalMonitoring;
    private List<Vms022VoMonitoring> monitoring;

    public Integer getTotalMonitoring() {
        return totalMonitoring;
    }

    public void setTotalMonitoring(Integer totalMonitoring) {
        this.totalMonitoring = totalMonitoring;
    }

    public List<Vms022VoMonitoring> getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(List<Vms022VoMonitoring> monitoring) {
        this.monitoring = monitoring;
    }
    
    
    
}
