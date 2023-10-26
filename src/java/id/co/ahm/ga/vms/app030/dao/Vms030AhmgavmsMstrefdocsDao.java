/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.dao;


import id.co.ahm.ga.vms.app000.model.AhmgavmsMstrefdocs;
import id.co.ahm.ga.vms.app000.model.AhmgavmsMstrefdocsPk;
import id.co.ahm.ga.vms.app030.vo.Vms030VoTableResult;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author Nurvan Afandi
 */
public interface Vms030AhmgavmsMstrefdocsDao extends DefaultDao<AhmgavmsMstrefdocs, AhmgavmsMstrefdocsPk> {
    
    public List<Vms030VoTableResult> getTable(DtoParamPaging input);
    
    public int getTableCount(DtoParamPaging input);
      
}
