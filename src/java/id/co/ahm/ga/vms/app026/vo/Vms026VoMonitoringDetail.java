/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.vo;

import java.math.BigDecimal;

/**
 *
 * @author kahfi
 */
public class Vms026VoMonitoringDetail {
    
    private BigDecimal idVisit;
    private int rowNum;
    private String name;
    private String idType;
    private String noId;
    private String noHp;
    private int noUrut;

    public BigDecimal getIdVisit() {
        return idVisit;
    }

    public void setIdVisit(BigDecimal idVisit) {
        this.idVisit = idVisit;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNoId() {
        return noId;
    }

    public void setNoId(String noId) {
        this.noId = noId;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public int getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(int noUrut) {
        this.noUrut = noUrut;
    }
}
