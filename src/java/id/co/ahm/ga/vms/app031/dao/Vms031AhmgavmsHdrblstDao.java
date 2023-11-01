/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrblst;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrblstPk;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovInput;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovOutput;
import id.co.ahm.ga.vms.app031.vo.Vms031VoMonitoring;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public interface Vms031AhmgavmsHdrblstDao extends DefaultDao<AhmgavmsHdrblst, AhmgavmsHdrblstPk>{
      
   public BigDecimal validateNik(DtoParamPaging input); 
   
   public BigDecimal getHeaderId(DtoParamPaging input);
   
   public BigDecimal getNik(DtoParamPaging id);
   
   public String getDivisi(DtoParamPaging input);
   
   public List<Vms031VoMonitoring> getMonitoring(DtoParamPaging input);
   
   public int getMonitoringCount(DtoParamPaging input);
   
   List<Vms031VoMonitoring> getDataExportExcel(Map<String, Object> param);
   
}

