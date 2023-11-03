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
import id.co.ahm.ga.vms.app031.exception.Vms031Exception;
import id.co.ahm.ga.vms.app031.rest.view.Vms031DownloadTemplateView;
import id.co.ahm.ga.vms.app031.util.upload.Vms031UploadConfigDTO;
import id.co.ahm.ga.vms.app031.util.validator.Vms031BasicValidatorService;
import id.co.ahm.ga.vms.app031.vo.Vms031VoErrorUpload;
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
    public DtoResponseWorkspace submitKaryawan(DtoParamPaging input, VoUserCred user) {
        try {
            Date tglStartEffective = DateUtil.stringToDate((String) input.getSearch().get("tglStartEffective"), "dd-MMM-yyyy");
            Date tglEndEffective = DateUtil.stringToDate((String) input.getSearch().get("tglEndEffective"), "dd-MMM-yyy");
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            //VALIDASI INPUT DATA TIDAK BOLEH KOSONG
            if (input.getSearch().get("nrp").toString().equalsIgnoreCase("")
                    || input.getSearch().get("nik").toString().equalsIgnoreCase("")
                    || input.getSearch().get("nama").toString().equalsIgnoreCase("")
                    || input.getSearch().get("jenisKelamin").toString().equalsIgnoreCase("")
                    || input.getSearch().get("alamatKtp").toString().equalsIgnoreCase("")
                    || input.getSearch().get("alamatDomisili").toString().equalsIgnoreCase("")
                    || input.getSearch().get("tipeKaryawanBlacklist").toString().equalsIgnoreCase("")
                    || input.getSearch().get("namaPerusahaan").toString().equalsIgnoreCase("")
                    || input.getSearch().get("alasanBlacklist").toString().equalsIgnoreCase("")
                    || input.getSearch().get("typeFoto").toString().equalsIgnoreCase("")
                    || input.getSearch().get("namaFoto").toString().equalsIgnoreCase("")
                    || input.getSearch().get("extensionFoto").toString().equalsIgnoreCase("")
                    || input.getSearch().get("pathFoto").toString().equalsIgnoreCase("")
                    || input.getSearch().get("tglStartEffective").toString().equalsIgnoreCase("")
                    || input.getSearch().get("tglEndEffective").toString().equalsIgnoreCase("")) {
                Map<String, Object> msg = new HashMap<>();
                msg.put("validate", "Field ini tidak boleh kosong");
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, msg, null);
            } else {

                //COUNT VALIDASI NIK
                //BigDecimal val = vms031AhmgavmsHdrblstDao.validateNik(input);
                //MENCARI BERDASARKAN NIK
                //BigDecimal pk = vms031AhmgavmsHdrblstDao.getNik(input);
                AhmgavmsHdrblstPk pk = new AhmgavmsHdrblstPk();
                pk.setHeaderId((Integer) input.getSearch().get("headerId"));
                AhmgavmsHdrblst data = new AhmgavmsHdrblst();
                data = vms031AhmgavmsHdrblstDao.findOne(pk);
                if (data == null) {
                    //INSERT DATA
                    AhmgavmsHdrblst hdr = new AhmgavmsHdrblst();
                    AhmgavmsHdrblstPk hdrPK = new AhmgavmsHdrblstPk();
                    hdr.setAhmgavmsHdrblstPk(hdrPK);
                    hdr.setNrp(input.getSearch().get("nrp").toString());
                    hdr.setNik(input.getSearch().get("nik").toString());
                    hdr.setNama(input.getSearch().get("nama").toString());
                    hdr.setJenisKelamin(input.getSearch().get("jenisKelamin").toString());
                    hdr.setAlamatKtp(input.getSearch().get("alamatKtp").toString());
                    hdr.setAlamatDomisili(input.getSearch().get("alamatDomisili").toString());
                    hdr.setTipeKaryawanBlacklist(input.getSearch().get("tipeKaryawanBlacklist").toString());
                    hdr.setNamaPerusahaan(input.getSearch().get("namaPerusahaan").toString());
                    hdr.setAlasanBlacklist(input.getSearch().get("alasanBlacklist").toString());
                    hdr.setTypeFoto(input.getSearch().get("typeFoto").toString());
                    hdr.setNamaFoto(input.getSearch().get("namaFoto").toString());
                    hdr.setExtensionFoto(input.getSearch().get("extensionFoto").toString());
                    hdr.setPathFoto(input.getSearch().get("pathFoto").toString());
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
                    data.setNrp(input.getSearch().get("nrp").toString());
                    data.setNik(input.getSearch().get("nik").toString());
                    data.setNama(input.getSearch().get("nama").toString());
                    data.setJenisKelamin(input.getSearch().get("jenisKelamin").toString());
                    data.setAlamatKtp(input.getSearch().get("alamatKtp").toString());
                    data.setAlamatDomisili(input.getSearch().get("alamatDomisili").toString());
                    data.setTipeKaryawanBlacklist(input.getSearch().get("tipeKaryawanBlacklist").toString());
                    data.setNamaPerusahaan(input.getSearch().get("namaPerusahaan").toString());
                    data.setAlasanBlacklist(input.getSearch().get("alasanBlacklist").toString());
                    data.setTypeFoto(input.getSearch().get("typeFoto").toString());
                    data.setNamaFoto(input.getSearch().get("namaFoto").toString());
                    data.setExtensionFoto(input.getSearch().get("extensionFoto").toString());
                    data.setPathFoto(input.getSearch().get("pathFoto").toString());
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
    public DtoResponseWorkspace submitPengunjung(DtoParamPaging input, VoUserCred user) {
        try {
            Date tglStartEffective = DateUtil.stringToDate((String) input.getSearch().get("tglStartEffective"), "dd-MMM-yyyy");
            Date tglEndEffective = DateUtil.stringToDate((String) input.getSearch().get("tglEndEffective"), "dd-MMM-yyy");
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            //VALIDASI INPUT DATA TIDAK BOLEH KOSONG
            if ( input.getSearch().get("nik").toString().equalsIgnoreCase("")
                    || input.getSearch().get("nama").toString().equalsIgnoreCase("")
                    || input.getSearch().get("jenisKelamin").toString().equalsIgnoreCase("")
                    || input.getSearch().get("alamatKtp").toString().equalsIgnoreCase("")
                    || input.getSearch().get("alamatDomisili").toString().equalsIgnoreCase("")
                    || input.getSearch().get("tipeKaryawanBlacklist").toString().equalsIgnoreCase("")
                    || input.getSearch().get("namaPerusahaan").toString().equalsIgnoreCase("")
                    || input.getSearch().get("alasanBlacklist").toString().equalsIgnoreCase("")
                    || input.getSearch().get("typeFoto").toString().equalsIgnoreCase("")
                    || input.getSearch().get("namaFoto").toString().equalsIgnoreCase("")
                    || input.getSearch().get("extensionFoto").toString().equalsIgnoreCase("")
                    || input.getSearch().get("pathFoto").toString().equalsIgnoreCase("")
                    || input.getSearch().get("tglStartEffective").toString().equalsIgnoreCase("")
                    || input.getSearch().get("tglEndEffective").toString().equalsIgnoreCase("")) {
                Map<String, Object> msg = new HashMap<>();
                msg.put("validate", "Field ini tidak boleh kosong");
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, msg, null);
            } else {

                //COUNT VALIDASI NIK
                //BigDecimal val = vms031AhmgavmsHdrblstDao.validateNik(input);
                //MENCARI BERDASARKAN NIK
                //BigDecimal pk = vms031AhmgavmsHdrblstDao.getNik(input);
                AhmgavmsHdrblstPk pk = new AhmgavmsHdrblstPk();
                pk.setHeaderId((Integer) input.getSearch().get("headerId"));
                AhmgavmsHdrblst data = new AhmgavmsHdrblst();
                data = vms031AhmgavmsHdrblstDao.findOne(pk);
                if (data == null) {
                    //INSERT DATA
                    AhmgavmsHdrblst hdr = new AhmgavmsHdrblst();
                    AhmgavmsHdrblstPk hdrPK = new AhmgavmsHdrblstPk();
                    hdr.setAhmgavmsHdrblstPk(hdrPK);
                    hdr.setNik(input.getSearch().get("nik").toString());
                    hdr.setNama(input.getSearch().get("nama").toString());
                    hdr.setJenisKelamin(input.getSearch().get("jenisKelamin").toString());
                    hdr.setAlamatKtp(input.getSearch().get("alamatKtp").toString());
                    hdr.setAlamatDomisili(input.getSearch().get("alamatDomisili").toString());
                    hdr.setTipeKaryawanBlacklist(input.getSearch().get("tipeKaryawanBlacklist").toString());
                    hdr.setNamaPerusahaan(input.getSearch().get("namaPerusahaan").toString());
                    hdr.setAlasanBlacklist(input.getSearch().get("alasanBlacklist").toString());
                    hdr.setTypeFoto(input.getSearch().get("typeFoto").toString());
                    hdr.setNamaFoto(input.getSearch().get("namaFoto").toString());
                    hdr.setExtensionFoto(input.getSearch().get("extensionFoto").toString());
                    hdr.setPathFoto(input.getSearch().get("pathFoto").toString());
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
                    data.setNik(input.getSearch().get("nik").toString());
                    data.setNama(input.getSearch().get("nama").toString());
                    data.setJenisKelamin(input.getSearch().get("jenisKelamin").toString());
                    data.setAlamatKtp(input.getSearch().get("alamatKtp").toString());
                    data.setAlamatDomisili(input.getSearch().get("alamatDomisili").toString());
                    data.setTipeKaryawanBlacklist(input.getSearch().get("tipeKaryawanBlacklist").toString());
                    data.setNamaPerusahaan(input.getSearch().get("namaPerusahaan").toString());
                    data.setAlasanBlacklist(input.getSearch().get("alasanBlacklist").toString());
                    data.setTypeFoto(input.getSearch().get("typeFoto").toString());
                    data.setNamaFoto(input.getSearch().get("namaFoto").toString());
                    data.setExtensionFoto(input.getSearch().get("extensionFoto").toString());
                    data.setPathFoto(input.getSearch().get("pathFoto").toString());
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
                dtl.setJenisKartuIdentitas(input.getSearch().get("jenisKartuIdentitas").toString());
                dtl.setNoIdentitas(input.getSearch().get("noIdentitas").toString());
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
                data.setJenisKartuIdentitas(input.getSearch().get("jenisKartuIdentitas").toString());
                data.setNoIdentitas(input.getSearch().get("jenisKartuIdentitas").toString());
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

    
    
    
    
}
