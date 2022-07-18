/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.rest.advice;

import id.co.ahm.ga.vms.app021.exception.Vms021Exception;
import id.co.ahm.ga.vms.app021.rest.Vms021Rest;
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
@ControllerAdvice(basePackageClasses = Vms021Rest.class)
public class Vms021RestAdvice {
    
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

    @ExceptionHandler(Vms021Exception.class)
    @ResponseBody
    public DtoResponseWorkspace handleException(Vms021Exception e, HttpServletResponse response) {
        DtoResponsePagingWorkspace dto = new DtoResponsePagingWorkspace();
        dto.setData(new ArrayList());
        dto.setStatus(GAGAL);
        dto.setMessage(e.getErrMsg());
        dto.addMessage(e.getErr());
        return dto;
    }
}
