/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.rest.view;

import id.co.ahm.ga.vms.app031.constant.Vms031Constant;
import id.co.ahm.ga.vms.app031.exception.Vms031Exception;
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
 * @author Ahmad Mukaram Aziz
 */
public class Vms031DownloadTemplateView extends AbstractXlsView {
     @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        String templateType = (String)model.get("templateType");
        String pathFile = "";
        if(templateType.equals(Vms031Constant.TEMPLATE_BLACKLIST)){
            pathFile = Vms031Constant.TEMPLATE_PATH_UPLOAD_BLACKLIST;
      
        }
        
        
        InputStream is;
        try {
            is = new FileInputStream(pathFile);
            Workbook wb = WorkbookFactory.create(is);
            is.close();
            return wb;
        } catch (FileNotFoundException ex) {
            throw new Vms031Exception("Failed to get file for download " + ex.getMessage());
        } catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
            throw new Vms031Exception("Failed to get file for download ");
        }
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        String templateType = (String)map.get("templateType");
        String fileName = "";
        if(templateType.equals(Vms031Constant.TEMPLATE_BLACKLIST)){
            fileName = Vms031Constant.TEMPLATE_NAME_BLACKLIST;
        
        hsr1.setHeader("Content-Disposition", "attachment;filename=\"/"+ fileName + Vms031Constant.TEMPLATE_EXTENSIONS +"\"");
    }
}
}    
