/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlotsregsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.ga.vms.app022.exception.Vms022Exception;
import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.vo.Vms022VoFileAttachment;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jx.b2e.app000.dao.Ahmitb2eMstusrrolesDao;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrroles;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrrolesPk;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    @Qualifier("ahmitb2eMstusrrolesDao")
    private Ahmitb2eMstusrrolesDao ahmitb2eMstusrrolesDao;

    @Autowired
    @Qualifier("vms022ObjectDao")
    private Vms022ObjectDao vms022ObjectDao;
//
    @Autowired
    @Qualifier("vms022ahmhrntmDtlprmgblsDao")
    private Vms022AhmhrntmDtlprmgblsDao vms022ahmhrntmDtlprmgblsDao;
//
    @Autowired
    @Qualifier("vms022ahmhrntmHdrotsempsDao")
    private Vms022AhmhrntmHdrotsempsDao vms022ahmhrntmHdrotsempsDao;

    @Autowired
    @Qualifier("vms022ahmhrntmDtlotsregsDao")
    private Vms022AhmhrntmDtlotsregsDao vms022ahmhrntmDtlotsregsDao;
//    

    private String userId(VoUserCred user) {
        String domain = "";
        if (user == null) {
            return null;
        }
        if (!StringUtils.isEmpty(user.getDomain())) {
            domain = user.getDomain() + "\\";
        }
        return domain + user.getUsername();
    }

    @Override
    public DtoResponse getRoleByUserLogin(String plants, VoUserCred user) {
        System.out.println("================" + "SERVICE_IMPL | mau tau ini isinya apa " + "================");
        System.out.println("================" + userId(user) + "================");
        List<Ahmitb2eMstusrroles> roles = ahmitb2eMstusrrolesDao.getListUserRole(userId(user));
        System.out.println("================" + roles + "================");
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

    @Override
    public DtoResponseWorkspace showPlant() {
        List<Vms022VoLov> list = vms022ahmhrntmDtlprmgblsDao.lovPlant(null, true);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, list);
    }

    @Override
    public DtoResponseWorkspace showMonitoring(DtoParamPaging dto) {
        List<Vms022VoMonitoring> list = vms022ahmhrntmHdrotsempsDao.getSearchData2(dto);
        int count = vms022ahmhrntmHdrotsempsDao.countSearchData2(dto);
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "YOI", null, list, count);
    }

    @Override
    public DtoResponsePaging monitoring(DtoParamPaging input, VoUserCred userCred) {
        List<Vms022VoMonitoring> datas = new ArrayList<>();
        int count = 0;
        LinkedHashMap<String, Object> reqObj = (LinkedHashMap<String, Object>) input.getSearch();

        String outId = reqObj.get("outId") != null ? reqObj.get("outId").toString() : "";
        String outName = reqObj.get("outName") != null ? reqObj.get("outName").toString() : "";
        String outType = reqObj.get("outType") != null ? reqObj.get("outType").toString() : "";
        String beginDate = reqObj.get("beginDate") != null ? reqObj.get("beginDate").toString() : "";
        String endDate = reqObj.get("endDate") != null ? reqObj.get("endDate").toString() : "";
        String pic = reqObj.get("pic") != null ? reqObj.get("pic").toString() : "";
        String nik = reqObj.get("nik") != null ? reqObj.get("nik").toString() : "";
        String company = reqObj.get("company") != null ? reqObj.get("company").toString() : "";
        String outStatus = reqObj.get("outStatus") != null ? reqObj.get("outStatus").toString() : "";
        String passNumber = reqObj.get("passNumber") != null ? reqObj.get("passNumber").toString() : "";

        if (StringUtils.isBlank(outId) && StringUtils.isBlank(outName) && StringUtils.isBlank(outType) && StringUtils.isBlank(beginDate)
                && StringUtils.isBlank(endDate) && StringUtils.isBlank(pic) && StringUtils.isBlank(nik) && StringUtils.isBlank(company)
                && StringUtils.isBlank(outStatus) && StringUtils.isBlank(passNumber)) {
            return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, datas, 0);
        } else {
            datas = vms022ahmhrntmHdrotsempsDao.getSearchData(input, userCred.getUserid());

            if (!datas.isEmpty()) {
                for (Vms022VoMonitoring vo : datas) {
                    if (StringUtils.isBlank(vo.getCompanyName()) && StringUtils.isBlank(vo.getCompany())) {
                        List<Vms022VoLov> compNameList = vms022ObjectDao.lovCompExternal(input, userCred.getUserid(), "FILTER");

                        if (!compNameList.isEmpty()) {
                            vo.setCompanyName(compNameList.get(0).getName());
                        } else {
                            vo.setCompanyName("Company Code tidak ditemukan");
                        }
                    }
                    if (!StringUtils.isBlank(vo.getFileNameKtp())) {
                        byte[] bFileKtp = readBytesFromFile(pathServer + vo.getFileNameKtp());
                        vo.setFileKtp(Base64.getEncoder().encodeToString(bFileKtp));
                    }

                    List<Vms022VoFileAttachment> listVacs = new ArrayList<>();
                    List<String> flVacs = vms022ahmhrntmDtlotsregsDao.getFileName(vo.getOutId(), vo.getPersId(), "VC");
                    if (!flVacs.isEmpty()) {
                        for (String v : flVacs) {
                            Vms022VoFileAttachment dtVac = new Vms022VoFileAttachment();

                            byte[] bFileVac = readBytesFromFile(pathServer + v);
                            dtVac.setName(Base64.getEncoder().encodeToString(bFileVac));

                            listVacs.add(dtVac);
                        }

                        vo.setFileVaccines(listVacs);
                    }

                    List<Vms022VoFileAttachment> listAttcs = new ArrayList<>();
                    List<String> flAttc = vms022ahmhrntmDtlotsregsDao.getFileName(vo.getOutId(), vo.getPersId(), "VC");
                    if (!flAttc.isEmpty()) {
                        for (String v : flVacs) {
                            Vms022VoFileAttachment dtVac = new Vms022VoFileAttachment();

                            byte[] bFileVac = readBytesFromFile(pathServer + v);
                            dtVac.setName(Base64.getEncoder().encodeToString(bFileVac));

                            listAttcs.add(dtVac);
                        }

                        vo.setFileVaccines(listAttcs);
                    }
                }
            }

            count = vms022ahmhrntmHdrotsempsDao.countSearchData(input, userCred.getUserid());

            return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, datas, count);
        }
    }

    private byte[] readBytesFromFile(String pathFile) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {
            File file = new File(pathFile);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return bytesArray;
    }

    @Override
    public DtoResponseWorkspace approve(Vms022VoMonitoring getdata, VoUserCred userCred) {
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((getdata.getId()));
                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        mp.setVotsstts("Active");
                        
                        vms022ahmhrntmHdrotsempsDao.update(mp);
                        vms022ahmhrntmHdrotsempsDao.flush();
                        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "Approve success", null, null);
                    } 
                    else {
                        throw new Vms022Exception("Failed Approve Data Cause mp nya JEBOD when Updating");
                    }
    }
    
    @Override
    public DtoResponseWorkspace approving(List<Vms022VoMonitoring> getdata, VoUserCred userCred) {
        if (!getdata.isEmpty()) {
            for (Vms022VoMonitoring vo : getdata) {
                AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                pk.setRotsempshs((vo.getId()));
                AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                if (mp != null) {
                    mp.setVotsstts("Active");
//                        mp.setVotsstts("Waiting for Approval Security");
                    mp.setLastModBy(userCred.getUserid());
                    vms022ahmhrntmHdrotsempsDao.update(mp);
                    vms022ahmhrntmHdrotsempsDao.flush();
                }
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "Approve success", null, null);
        } else {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, "Failed Approve data", null, null);
        }
    }

    @Override
    public DtoResponseWorkspace reject(Vms022VoMonitoring getdata, VoUserCred userCred) {
                try {
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((getdata.getId()));
                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        mp.setVotsstts("Reject");
                        vms022ahmhrntmHdrotsempsDao.update(mp);
                        vms022ahmhrntmHdrotsempsDao.flush();
                    }
                } catch (Exception e) {
                    throw new Vms022Exception("Failed Reject Data Cause error when Updating");
                }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "Reject success", null, null);
    }
    
    @Override
    public DtoResponseWorkspace rejecting(List<Vms022VoMonitoring> getdata, VoUserCred userCred) {
        if (!getdata.isEmpty()) {
            for (Vms022VoMonitoring vo : getdata) {
                AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                pk.setRotsempshs((vo.getId()));
                AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                if (mp != null) {
                    mp.setVotsstts("Reject");
//                        mp.setVotsstts("Waiting for Approval Security");
                    mp.setLastModBy(userCred.getUserid());
                    vms022ahmhrntmHdrotsempsDao.update(mp);
                    vms022ahmhrntmHdrotsempsDao.flush();
                }
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "Reject success", null, null);
        } else {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, "Failed Reject data", null, null);
        }
    }

}
