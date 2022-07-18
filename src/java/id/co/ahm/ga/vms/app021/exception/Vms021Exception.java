/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.exception;

import id.co.ahm.jxf.vo.VoMessageWorkspace;

/**
 *
 * @author ayik.op
 */
public class Vms021Exception extends RuntimeException{
    
    private String errMsg;
    private VoMessageWorkspace err;

    public Vms021Exception(String message) {
        super(message);
        this.errMsg = message;
    }

    public Vms021Exception(String message, VoMessageWorkspace err) {
        super(message);
        this.errMsg = message;
        this.err = err;
    }

    public Vms021Exception(VoMessageWorkspace err) {
        super(err.getM());
        this.err = err;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public VoMessageWorkspace getErr() {
        return err;
    }

    public void setErr(VoMessageWorkspace err) {
        this.err = err;
    }
    
    
}
