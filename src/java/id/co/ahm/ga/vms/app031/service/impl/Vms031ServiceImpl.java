/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.service.impl;

import static groovy.xml.dom.DOMCategory.list;
import id.co.ahm.ga.vms.app000.model.AhmgavmsDtlblst;
import id.co.ahm.ga.vms.app000.model.AhmgavmsDtlblstPk;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrblst;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrblstPk;
import id.co.ahm.ga.vms.app031.dao.Vms031AhmgavmsDtlblstDao;
import id.co.ahm.ga.vms.app031.dao.Vms031AhmgavmsHdrblstDao;
import id.co.ahm.ga.vms.app031.service.Vms031Service;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovInput;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovOutput;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.DtoHelper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import id.co.ahm.ga.vms.app031.dao.Vms031AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app031.dao.Vms031ObjectDao;
import id.co.ahm.ga.vms.app031.exception.Vms031Exception;
import id.co.ahm.ga.vms.app031.rest.view.Vms031DownloadTemplateView;
import id.co.ahm.ga.vms.app031.util.upload.Vms031UploadConfigDTO;
import id.co.ahm.ga.vms.app031.util.validator.Vms031BasicValidatorService;
import id.co.ahm.ga.vms.app031.vo.Vms031VoErrorUpload;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovPic;
import id.co.ahm.ga.vms.app031.vo.Vms031VoMonitoring;
import id.co.ahm.ga.vms.app031.vo.Vms031VoUploadFileBlaclist;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import id.co.ahm.jxf.vo.VoPstUserCred;
import id.co.ahm.jxf.vo.VoUserCred;
import java.math.BigDecimal;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import java.util.Base64;
import static java.util.Collections.list;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.apache.zookeeper.Environment.list;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@Transactional(readOnly = true)
@Service(value = "vms031Service")
public class Vms031ServiceImpl implements Vms031Service {
    
    public final static String pathServer = "";
    
    @Autowired
    @Qualifier("vms031AhmmoerpDtlsettingsDao")
    private Vms031AhmmoerpDtlsettingsDao vms031AhmmoerpDtlsettingsDao;
    
    @Autowired
    @Qualifier("vms031AhmgavmsHdrblstDao")
    private Vms031AhmgavmsHdrblstDao vms031AhmgavmsHdrblstDao;
    
    @Autowired
    @Qualifier("vms031AhmgavmsDtlblstDao")
    private Vms031AhmgavmsDtlblstDao vms031AhmgavmsDtlblstDao;
    
    @Autowired
    @Qualifier(value = "vms031BasicValidatorService")
    private Vms031BasicValidatorService basicValidatorService;
    
    @Autowired
    @Qualifier("vms031ObjectDao")
    private Vms031ObjectDao vms031ObjectDao;

    @Override
    public DtoResponseWorkspace showIdType(DtoParamPaging input) {
        List<Vms031VoLovOutput> data = vms031AhmmoerpDtlsettingsDao.LovBlstCardType(input);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
    }
    
    @Override
    public DtoResponseWorkspace showHeadIdType(DtoParamPaging input) {
        List<Vms031VoLovOutput> data = vms031AhmmoerpDtlsettingsDao.LovHeadBlstCardType(input);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
    }
    
    @Override
    public DtoResponseWorkspace showDetailIdType(DtoParamPaging input) {
         List<Vms031VoLovOutput> data = vms031AhmmoerpDtlsettingsDao.LovDtlBlstCardType(input);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
    }

    @Override
    public DtoResponseWorkspace submitblacklist(DtoParamPaging input, VoUserCred user) {
        try {
            String nrp = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
            String nik = AhmStringUtil.hasValue(input.getSearch().get("nik")) ? (input.getSearch().get("nik") + "").toUpperCase() : "";
            String nama = AhmStringUtil.hasValue(input.getSearch().get("nama")) ? (input.getSearch().get("nama") + "").toUpperCase() : "";
            String jenisKelamin = AhmStringUtil.hasValue(input.getSearch().get("jenisKelamin")) ? (input.getSearch().get("jenisKelamin") + "").toUpperCase() : "";
            String alamatKtp = AhmStringUtil.hasValue(input.getSearch().get("alamatKtp")) ? (input.getSearch().get("alamatKtp") + "").toUpperCase() : "";
            String alamatDomisili = AhmStringUtil.hasValue(input.getSearch().get("alamatDomisili")) ? (input.getSearch().get("alamatDomisili") + "").toUpperCase() : "";
            String tipeKaryawanBlacklist = AhmStringUtil.hasValue(input.getSearch().get("tipeKaryawanBlacklist")) ? (input.getSearch().get("tipeKaryawanBlacklist") + "").toUpperCase() : "";
            String namaPerusahaan = AhmStringUtil.hasValue(input.getSearch().get("namaPerusahaan")) ? (input.getSearch().get("namaPerusahaan") + "").toUpperCase() : "";
            String alasanBlacklist = AhmStringUtil.hasValue(input.getSearch().get("alasanBlacklist")) ? (input.getSearch().get("alasanBlacklist") + "").toUpperCase() : "";
            String typeFoto = AhmStringUtil.hasValue(input.getSearch().get("typeFoto")) ? (input.getSearch().get("typeFoto") + "").toUpperCase() : "";
            String namaFoto = AhmStringUtil.hasValue(input.getSearch().get("namaFoto")) ? (input.getSearch().get("namaFoto") + "").toUpperCase() : "";
            String extensionFoto = AhmStringUtil.hasValue(input.getSearch().get("extensionFoto")) ? (input.getSearch().get("extensionFoto") + "").toUpperCase() : "";
            String pathFoto = AhmStringUtil.hasValue(input.getSearch().get("pathFoto")) ? (input.getSearch().get("pathFoto") + "").toUpperCase() : "";
            Date tglStartEffective = DateUtil.stringToDate((String) input.getSearch().get("tglStartEffective"), "dd-MMM-yyyy");
            Date tglEndEffective = DateUtil.stringToDate((String) input.getSearch().get("tglEndEffective"), "dd-MMM-yyy");
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            //VALIDASI INPUT DATA TIDAK BOLEH KOSONG
            if (nik.equalsIgnoreCase("")
                    || nama.equalsIgnoreCase("")
                    || jenisKelamin.equalsIgnoreCase("")
                    || alamatKtp.equalsIgnoreCase("")
                    || alamatDomisili.equalsIgnoreCase("")
                    || tipeKaryawanBlacklist.equalsIgnoreCase("")
                    || namaPerusahaan.equalsIgnoreCase("")
                    || alasanBlacklist.equalsIgnoreCase("")
                    || typeFoto.equalsIgnoreCase("")
                    || namaFoto.equalsIgnoreCase("")
                    || extensionFoto.equalsIgnoreCase("")
                    || pathFoto.equalsIgnoreCase("")
                    || tglStartEffective.toString().equalsIgnoreCase("")
                    || tglEndEffective.toString().equalsIgnoreCase("")) {
                
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("FIeld tidak boleh kosong"), null, null);
            } else {

                AhmgavmsHdrblstPk pk = new AhmgavmsHdrblstPk();
                pk.setHeaderId((Integer) input.getSearch().get("headerId"));
                AhmgavmsHdrblst data = new AhmgavmsHdrblst();
                data = vms031AhmgavmsHdrblstDao.findOne(pk);
                
                if (data == null) {
                    //INSERT DATA
                    AhmgavmsHdrblst hdr = new AhmgavmsHdrblst();
                    AhmgavmsHdrblstPk hdrPK = new AhmgavmsHdrblstPk();
                    hdr.setAhmgavmsHdrblstPk(hdrPK);
                    hdr.setNrp(nrp);
                    hdr.setNik(nik);
                    hdr.setNama(nama);
                    hdr.setJenisKelamin(jenisKelamin);
                    hdr.setAlamatKtp(alamatKtp);
                    hdr.setAlamatDomisili(alamatDomisili);
                    hdr.setTipeKaryawanBlacklist(tipeKaryawanBlacklist);
                    hdr.setNamaPerusahaan(namaPerusahaan);
                    hdr.setAlasanBlacklist(alasanBlacklist);
                    hdr.setTypeFoto(typeFoto);
                    hdr.setNamaFoto(namaFoto);
                    hdr.setExtensionFoto(extensionFoto);
                    hdr.setPathFoto(pathFoto);
                    hdr.setTglStartEffective(tglStartEffective);
                    hdr.setTglEndEffective(tglEndEffective);
                    hdr.setCreateBy(userId);
                    hdr.setCreateDate(new Date());
                    hdr.setLastModBy(userId);
                    hdr.setLastModDate(new Date());

                    vms031AhmgavmsHdrblstDao.save(hdr);
                    vms031AhmgavmsHdrblstDao.flush();

                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                } else {
                    //UPDATE DATE 
                    data.setNrp(nrp);
                    data.setNik(nik);
                    data.setNama(nama);
                    data.setJenisKelamin(jenisKelamin);
                    data.setAlamatKtp(alamatKtp);
                    data.setAlamatDomisili(alamatDomisili);
                    data.setTipeKaryawanBlacklist(tipeKaryawanBlacklist);
                    data.setNamaPerusahaan(namaPerusahaan);
                    data.setAlasanBlacklist(alasanBlacklist);
                    data.setTypeFoto(typeFoto);
                    data.setNamaFoto(namaFoto);
                    data.setExtensionFoto(extensionFoto);
                    data.setPathFoto(pathFoto);
                    data.setTglStartEffective(tglStartEffective);
                    data.setTglEndEffective(tglEndEffective);
                    data.setLastModBy(userId);
                    data.setLastModDate(new Date());

                    vms031AhmgavmsHdrblstDao.update(data);
                    vms031AhmgavmsHdrblstDao.flush();

                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                }
            }

        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }


    @Override
    public DtoResponseWorkspace submitDetail(DtoParamPaging input, VoUserCred user) {
        try {
            String jenisKartuIdentitas = AhmStringUtil.hasValue(input.getSearch().get("jenisKartuIdentitas")) ? (input.getSearch().get("jenisKartuIdentitas") + "").toUpperCase() : "";
            String noIdentitas = AhmStringUtil.hasValue(input.getSearch().get("noIdentitas")) ? (input.getSearch().get("noIdentitas") + "").toUpperCase() : "";
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }

//            Integer val = vms031AhmgavmsDtlblstDao.getDetailId(input);
//            BigDecimal pk = vms031AhmgavmsHdrblstDao.getHeaderId(input);
            AhmgavmsDtlblstPk pk = new AhmgavmsDtlblstPk();
            pk.setDetailId((Integer) input.getSearch().get("detailId"));
            AhmgavmsDtlblst data = new AhmgavmsDtlblst();
            data = vms031AhmgavmsDtlblstDao.findOne(pk);

            if (data == null) {
                //INSERT DETAIL 

                AhmgavmsDtlblst dtl = new AhmgavmsDtlblst();
                AhmgavmsDtlblstPk dtlPk = new AhmgavmsDtlblstPk();
                dtl.setAhmgavmsDtlBlstPk(dtlPk);
                dtl.setHeaderId((Integer) input.getSearch().get("headerId"));
                dtl.setJenisKartuIdentitas(jenisKartuIdentitas);
                dtl.setNoIdentitas(noIdentitas);
                dtl.setCreateBy(userId);
                dtl.setCreateDate(new Date());
                dtl.setLastModBy(userId);
                dtl.setLastModDate(new Date());

                vms031AhmgavmsDtlblstDao.save(dtl);
                vms031AhmgavmsDtlblstDao.flush();

                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);

            } else {

                //UPDATE DETAIL
                data.setHeaderId((Integer) input.getSearch().get("headerId"));
                data.setJenisKartuIdentitas(jenisKartuIdentitas);
                data.setNoIdentitas(noIdentitas);
                data.setLastModBy(userId);
                data.setLastModDate(new Date());

                vms031AhmgavmsDtlblstDao.update(data);
                vms031AhmgavmsDtlblstDao.flush();
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
            }

        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);

        }

    }

    @Override
    public DtoResponseWorkspace showStatus(DtoParamPaging input) { 
        List<Vms031VoLovOutput> data = vms031AhmmoerpDtlsettingsDao.lovBlstStatus(input);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
    }

    @Override
    public DtoResponseWorkspace showBlacklistType(DtoParamPaging input) {
        List<Vms031VoLovOutput> data = vms031AhmmoerpDtlsettingsDao.lovBlstType(input);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
    }

    @Override
    public DtoResponseWorkspace showGender(DtoParamPaging input) {
        List<Vms031VoLovOutput> data = vms031AhmmoerpDtlsettingsDao.lovBlstGender(input);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
    }

    @Override
    public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input) {
        try {
            List<Vms031VoMonitoring> list = vms031AhmgavmsHdrblstDao.getMonitoring(input);
            int countData = vms031AhmgavmsHdrblstDao.getMonitoringCount(input);
            //langsung return aja disini

            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, list, countData);
        } catch (Exception e) {
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, "GAGAL", null, null, 0);
        }
    }

    @Override
    public ModelAndView downloadTemplate(String type) {
        Map<String, Object> model = new HashMap<>();
        model.put("templateType", type);
        return new ModelAndView(new Vms031DownloadTemplateView(), model);
    }

    @Override
    public List<Vms031VoMonitoring> exportData(Map<String, Object> param) {
        List<Vms031VoMonitoring> voMonitoring = vms031AhmgavmsHdrblstDao.getDataExportExcel(param);
    return voMonitoring;
    }

//    @Override
//    public DtoResponseWorkspace uploadBlacklist(Vms031VoUploadFileBlaclist param, VoPstUserCred user) throws Exception {
//        String uploadType = param.getUploadType();
//        List<Vms031VoErrorUpload> errorMessageResult = new ArrayList<>();
//        List<Integer> suksesList = new ArrayList<>();
//        DtoResponseWorkspace dtoResult = new DtoResponseWorkspace();
//        dtoResult.setStatus("1");
//        dtoResult.setMessage("Upload Berhasil");
//
//        //  Validasi Exiisting File
//        if (param.getFile() == null) {
//            throw new Vms031Exception("No Uploaded File");
//        }
//         MultipartFile file = param.getFile();   
//        Vms031UploadConfigDTO dto = new Vms031UploadConfigDTO();
//        basicValidatorService.validateUploadFile(file);
// 
//        dto.setSheetNo(0);
//        dto.setStartRow(2);
//        dto.setColumnPropertyMap(new HashMap<>());
// 
//        Map<Integer, String> columnPropertyMap = dto.getColumnPropertyMap();
//        columnPropertyMap.put(1, "NRP");//A
//        columnPropertyMap.put(2, "JENIS_KARTU_IDENTITAS");//B
//        columnPropertyMap.put(3, "NO_IDENTITAS");//C
//        columnPropertyMap.put(4, "NAMA");//D
//        columnPropertyMap.put(1, "NIK");//E
//        columnPropertyMap.put(2, "JENIS_KELAMIN");//F
//        columnPropertyMap.put(3, "ALAMAT_KTP");//G
//        columnPropertyMap.put(4, "ALAMAT_DOMISILI");//H
//        columnPropertyMap.put(1, "TIPE_KARYAWAN_BLACKLIST");//I
//        columnPropertyMap.put(2, "NAMA_PERUSAHAAN");//J
//        columnPropertyMap.put(3, "ALASAN_BLACKLIST");//K
//        columnPropertyMap.put(4, "TYPE_FOTO");//L
//        columnPropertyMap.put(1, "NAMA_FOTO");//M
//        columnPropertyMap.put(2, "EXTENSION_FOTO");//N
//        columnPropertyMap.put(3, "PATH_FOTO");//O
//        columnPropertyMap.put(4, "TANGGAL_START_EFFECTIVE");//P
//        columnPropertyMap.put(4, "TANGGAL_ENDEFFECTIVE");//Q
//    }

    @Override
    public DtoResponseWorkspace showPic(DtoParamPaging input) {
       try {
            List<Vms031VoLovPic> data = vms031ObjectDao.getPic(input);
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, null, null, data, 1);
        } catch (Exception e) {
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, null, null, null, 0);
        }
    }

    
    
    
    
}

