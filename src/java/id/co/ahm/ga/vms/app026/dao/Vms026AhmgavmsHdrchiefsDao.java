/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.dao;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrchiefs;
import id.co.ahm.ga.vms.app026.vo.Vms026VoSubmitChief;
import id.co.ahm.jxf.dao.DefaultDao;
import java.util.List;

/**
 *
 * @author kahfi
 */
public interface Vms026AhmgavmsHdrchiefsDao extends DefaultDao<AhmgavmsHdrchiefs, String>{

    public int validateQuota(Vms026VoSubmitChief input);

    public int getCountData(String input);

    public List<String> getInvitNoList(String masterNo);
    
}
