/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.rest.advice;

import id.co.ahm.ga.vms.app022.exception.Vms022Exception;
import id.co.ahm.ga.vms.app022.rest.Vms022Rest;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.vo.VoMessageWorkspace;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ayik.op
 */
@ControllerAdvice(basePackageClasses = Vms022Rest.class)
public class Vms022RestAdvice {
    
    public static final String GAGAL = "0";
    public static final String SERVER_PROCESSING_ERROR = "Server Processing Error";
    public static final String ERROR = "error";
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DtoResponseWorkspace handleException(Exception e, HttpServletResponse response) {
        DtoResponsePagingWorkspace dto = new DtoResponsePagingWorkspace();
        dto.setData(new ArrayList());
        dto.setStatus(GAGAL);
        VoMessageWorkspace vo = new VoMessageWorkspace();
        vo.setF(ERROR);
        vo.setM(SERVER_PROCESSING_ERROR);
        dto.addMessage(vo);
        return dto;
    }

    @ExceptionHandler(Vms022Exception.class)
    @ResponseBody
    public DtoResponseWorkspace handleException(Vms022Exception e, HttpServletResponse response) {
        DtoResponsePagingWorkspace dto = new DtoResponsePagingWorkspace();
        dto.setData(new ArrayList());
        dto.setStatus(GAGAL);
        dto.setMessage(e.getErrMsg());
        dto.addMessage(e.getErr());
        return dto;
    }
}
