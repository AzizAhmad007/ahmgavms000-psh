/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.rest.view;

import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.exception.Vms022Exception;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/**
 *
 * @author ayik.op
 */
public class Vms022DownloadTemplateView extends AbstractXlsView{

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        String pathFile = "";
        InputStream is;
        try {
            Map<String, Object> map = (Map<String, Object>) model.get("param");
            VoUserCred user = (VoUserCred) map.get("user");
            if ("AHM".equalsIgnoreCase(user.getName())) {
                pathFile = Vms022Constant.TEMPLATE_PATH_UPLOAD;
            } else {
                pathFile = Vms022Constant.TEMPLATE_PATH_UPLOAD_EXTERNAL;
            }
            is = new FileInputStream(pathFile);
            Workbook wb = WorkbookFactory.create(is);
            is.close();
            return wb;
        } catch (FileNotFoundException ex) {
            throw new Vms022Exception("Failed to get file for download");
        } catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
            ex.printStackTrace();
            throw new Vms022Exception("Failed to get file for download");
        }
    }
    
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse res) throws Exception {
        res.setHeader("Content-Disposition", "attachment;filename=\"Template Upload Registration Partner & Outsource.xlsx\"");
    }
    
}
