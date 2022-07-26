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
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jx.b2e.app000.dao.Ahmitb2eMstusrrolesDao;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrroles;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrrolesPk;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
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

//    public final static String pathServer = "/data/AHMGA/VMS/Registrasi/";
    //public final static String pathServer = "D:\\Download\\";

//    @Autowired
//    @Qualifier("vms022ObjectDao")
//    private Vms022ObjectDao vms022ObjectDao;
//
////    @Autowired
////    @Qualifier("vms022ahmhrntmDtlprmgblsDao")
////    private Vms022AhmhrntmDtlprmgblsDao vms022ahmhrntmDtlprmgblsDao;
//
//
//    @Autowired
//    @Qualifier("vms022ahmhrntmHdrotsempsDao")
//    private Vms022AhmhrntmHdrotsempsDao vms022ahmhrntmHdrotsempsDao;
//
//
////    @Autowired
////    @Qualifier("vms022ahmhrntmDtlotsregsDao")
////    private Vms022AhmhrntmDtlotsregsDao vms022ahmhrntmDtlotsregsDao;
//    
//    @Autowired
//    @Qualifier("ahmitb2eMstusrrolesDao")
//    private Ahmitb2eMstusrrolesDao ahmitb2eMstusrrolesDao;

}
