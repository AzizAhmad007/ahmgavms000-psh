/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service.impl;

import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlotsregsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.jx.b2e.app000.dao.Ahmitb2eMstusrrolesDao;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrroles;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrrolesPk;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author reza.mr
 */
@Transactional(readOnly = true)
@Service(value = "vms022Service")
public class Vms022ServiceImpl implements Vms022Service {

    public final static String pathServer = "/data/AHMGA/VMS/Registrasi/";
    //public final static String pathServer = "D:\\Download\\";

    @Autowired
    @Qualifier("vms022ObjectDao")
    private Vms022ObjectDao vms022ObjectDao;

    @Autowired
    @Qualifier("vms022ahmhrntmDtlprmgblsDao")
    private Vms022AhmhrntmDtlprmgblsDao vms022ahmhrntmDtlprmgblsDao;


    @Autowired
    @Qualifier("vms022ahmhrntmHdrotsempsDao")
    private Vms022AhmhrntmHdrotsempsDao vms022ahmhrntmHdrotsempsDao;


    @Autowired
    @Qualifier("vms022ahmhrntmDtlotsregsDao")
    private Vms022AhmhrntmDtlotsregsDao vms022ahmhrntmDtlotsregsDao;
    
    @Autowired
    @Qualifier("ahmitb2eMstusrrolesDao")
    private Ahmitb2eMstusrrolesDao ahmitb2eMstusrrolesDao;

    //getRoleByUserLogin Phase begin
    @Override
    public DtoResponse getRoleByUserLogin(String plants, VoUserCred user) {
        List<Ahmitb2eMstusrroles> roles = ahmitb2eMstusrrolesDao.getListUserRole(userId(user));
        List<String> useroles = new ArrayList<>();
        List<String> userPlant = new ArrayList<>();
        HashMap<String, Object> h = new HashMap<>();
        if (roles != null && roles.size() > 0) {
            for (Ahmitb2eMstusrroles r : roles) {
                Ahmitb2eMstusrrolesPk k = r.getAhmitb2eMstusrrolesPk();
                String role = k.getVroleid();
                Pattern p = Pattern.compile("[A-Z_]+([0-9]+)");
                Matcher m = p.matcher(role);
                if (m.matches() && m.groupCount() > 0) {
                    String plant = m.group(1);
                    userPlant.add(plant);
                }
                useroles.add(role);
            }
        }
        h.put("roles", String.join(" ", useroles));
        h.put("plants", String.join(",", userPlant));
        h.put("plants_db", plants);
        if (!userPlant.isEmpty()) {
            plants = String.join(",", userPlant);
        } else {
            plants = "00000";
        }

        List<String> l = new ArrayList<>();
        if (!StringUtils.isEmpty(plants)) {
            l = vms022ObjectDao.getPlantsByUserId(plants);
        } 

        boolean ok = (l != null && !l.isEmpty());
        StatusMsgEnum e = (ok) ? StatusMsgEnum.SUKSES : StatusMsgEnum.GAGAL;

        if (!ok) {
            h.put("error", "Plant user tidak ditemukan");
            h.put("plantIds", "");
        } else {
            h.put("success", "Plant user ada");
            h.put("plantIds", l);
        }

        return DtoHelper.constructResponse(e, h, useroles);
    }
    
    private String userId(VoUserCred user) {
        String domain = ""; 
        if (user==null) return null;
        if (!StringUtils.isEmpty(user.getDomain())) 
            domain = user.getDomain() + "\\";
        return domain + user.getUsername();         
    }
    
    
    //getRoleByUserLogin Phase end

}
