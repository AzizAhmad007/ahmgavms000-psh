/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.vo;

import java.math.BigDecimal;

/**
 *
 * @author ayik.op
 */
public class Vms021VoFileAttachment {
    
    private BigDecimal nseq;
    private String name;
    private String path;
    private String type;
    private String ext;

    public BigDecimal getNseq() {
        return nseq;
    }

    public void setNseq(BigDecimal nseq) {
        this.nseq = nseq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
    
    
}
