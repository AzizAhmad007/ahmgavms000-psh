package id.co.ahm.ga.vms.app022.exception;

import id.co.ahm.jxf.vo.VoMessageWorkspace;

/**
 *
 * @author administator
 */
public class Vms022Exception extends RuntimeException{
    
    private String errMsg;
    private VoMessageWorkspace err;

    public Vms022Exception(String message) {
        super(message);
        this.errMsg = message;
    }

    public Vms022Exception(String message, VoMessageWorkspace err) {
        super(message);
        this.errMsg = message;
        this.err = err;
    }

    public Vms022Exception(VoMessageWorkspace err) {
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
