package id.co.ahm.ga.vms.app022.service.impl;
 
import id.co.ahm.ga.vms.app000.model.AhmgaersMstcharwastes;
import id.co.ahm.ga.vms.app000.model.AhmgaersMstdtlwastes;
import id.co.ahm.ga.vms.app000.model.AhmgaersMstwastes;
import id.co.ahm.ga.vms.app000.model.AhmgaersMstwasthis;
import id.co.ahm.ga.vms.app000.model.AhmgawmsTxnpckgs;
import id.co.ahm.ga.vms.app000.model.AhmgawmsTxnscanouts;
import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgaersMstcharwastesDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgaersMstdtlwastesDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgaersMstwastesDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgaersMstwasthisDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgawmsTxnpckgsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgawmsTxnscanoutsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022DefaultDao;
import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.util.Vms022DownloadConfigDTO;
import id.co.ahm.ga.vms.app022.util.Vms022DownloadProcessor;
import id.co.ahm.ga.vms.app022.vo.Vms022VoDetail;
import id.co.ahm.ga.vms.app022.vo.Vms022VoParam;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWaste;
import id.co.ahm.ga.vms.app022.vo.Vms022VoRegulation;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWasteDetail;
import id.co.ahm.jx.b2e.app000.dao.Ahmitb2eMstusrrolesDao;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrroles;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrrolesPk;
import id.co.ahm.jx.constant.AppEnum;
import id.co.ahm.jx.exception.RestCommonException;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.service.AhmmoerpHdrsettingsService;
import id.co.ahm.jx.sys.app000.service.AhmmoerpTxnrunnumsService;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoMessageWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author developer
 */
@Service("vms022Service")
@Transactional(readOnly = false)
public class Vms022ServiceImpl implements Vms022Service {

    @Autowired
    @Qualifier("vms022AhmgaersMstwastesDao")
    private Vms022AhmgaersMstwastesDao vms022AhmgaersMstwastesDao;

    @Autowired
    @Qualifier("vms022AhmgaersMstdtlwastesDao")
    private Vms022AhmgaersMstdtlwastesDao vms022AhmgaersMstdtlwastesDao;

    @Autowired
    @Qualifier("vms022AhmgaersMstcharwastesDao")
    private Vms022AhmgaersMstcharwastesDao vms022AhmgaersMstcharwastesDao;

    @Autowired
    @Qualifier("ahmmoerpTxnrunnumsService")
    private AhmmoerpTxnrunnumsService ahmmoerpTxnrunnumsService;

    @Autowired
    @Qualifier(value = "ahmmoerpHdrsettingsService")
    private AhmmoerpHdrsettingsService ahmmoerpHdrsettingsService;

    @Autowired
    @Qualifier(value = "vms022DefaultDao")
    private Vms022DefaultDao vms022DefaultDao;

    @Autowired
    @Qualifier("ahmitb2eMstusrrolesDao")
    private Ahmitb2eMstusrrolesDao ahmitb2eMstusrrolesDao;

    @Autowired
    @Qualifier("vms022AhmgawmsTxnpckgsDao")
    private Vms022AhmgawmsTxnpckgsDao vms022AhmgawmsTxnpckgsDao;

    @Autowired
    @Qualifier("vms022AhmgawmsTxnscanoutsDao")
    private Vms022AhmgawmsTxnscanoutsDao vms022AhmgawmsTxnscanoutsDao;

    @Autowired
    @Qualifier("vms022AhmgaersMstwasthisDao")
    private Vms022AhmgaersMstwasthisDao vms022AhmgaersMstwasthisDao;

    public static final String DETAIL_REGULASI = "Untuk detail kode regulasi silakan periksa di Master Data Waste";
    public static final String SERVICE_ID = "vms022";

    @Override
    public DtoResponseWorkspace getRunningNumber(Vms022VoParam vms022VoParam) {
        Integer runningNumber = vms022AhmgaersMstwastesDao.getDataRunningNumber(vms022VoParam.getId(), vms022VoParam.getReset());

        vms022VoParam.setValue(runningNumber);

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, vms022VoParam);
    }

    @Override
    public DtoResponsePagingWorkspace search(DtoParamPaging dtoParamPaging) {
        int count = vms022AhmgaersMstwastesDao.count(dtoParamPaging);
        List<Vms022VoWaste> list = vms022AhmgaersMstwastesDao.search(dtoParamPaging);
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, null, null, list, count);
    }

    @Override
    public DtoResponsePagingWorkspace searchDetail(DtoParamPaging dtoParamPaging) {
        int count = vms022AhmgaersMstdtlwastesDao.count(dtoParamPaging);
        List<Vms022VoWasteDetail> list = vms022AhmgaersMstdtlwastesDao.search(dtoParamPaging);
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, null, null, list, count);
    }

    @Override
    public Workbook download(DtoParamPaging param) {
        Workbook wb = null;
        List<Vms022VoWaste> data = vms022AhmgaersMstwastesDao.searchForPrint(param);
        Map<String, Object> staticVal = new HashMap<>();
        if (StringUtils.isNotEmpty((String) param.getSearch().get("wasteType"))) {
            AhmmoerpDtlsettings sts = ahmmoerpHdrsettingsService.retrieveDetailSetting("AHMGAWMS_WUJUD", (String) param.getSearch().get("wasteType"));
            if (sts != null) {
                staticVal.put("B3", ": " + sts.getVitemname());
            } else {
                staticVal.put("B3", ": ");
            }
        }
        staticVal.put("B4", ": " + param.getSearch().get("wasteId"));
        staticVal.put("B5", ": " + param.getSearch().get("wasteDesc"));

        if (StringUtils.isNotEmpty((String) param.getSearch().get("entryNote"))) {
            AhmmoerpDtlsettings sts = ahmmoerpHdrsettingsService.retrieveDetailSetting("AHMGAWMS_ENTRYNT", (String) param.getSearch().get("entryNote"));
            if (sts != null) {
                staticVal.put("F3", ": " + sts.getVitemdesc());
            } else {
                staticVal.put("F3", ": ");
            }
        }

        if (StringUtils.isNotEmpty((String) param.getSearch().get("entryWeight"))) {
            AhmmoerpDtlsettings sts = ahmmoerpHdrsettingsService.retrieveDetailSetting("AHMGAWMS_ENTRYWGHT", (String) param.getSearch().get("entryWeight"));
            if (sts != null) {
                staticVal.put("F4", ": " + sts.getVitemname());
            } else {
                staticVal.put("F4", ": ");
            }
        }

        if (StringUtils.isNotEmpty((String) param.getSearch().get("status"))) {
            staticVal.put("F5", ": " + (String) param.getSearch().get("status"));
        } else {
            staticVal.put("F5", ": All");
        }
        AhmmoerpDtlsettings xls = ahmmoerpHdrsettingsService.retrieveDetailSetting("AHMGAERS_TEMPXLS", SERVICE_ID);
        String templatePath = (xls != null) ? xls.getVitemdesc() : null;

        Map<Integer, String> columnPropertyMap = new HashMap<>();

        columnPropertyMap.put(1, "wasteId");
        columnPropertyMap.put(2, "wasteDesc");
        columnPropertyMap.put(3, "wasteTypeDesc");
        columnPropertyMap.put(4, "b3NonB3Desc");
        columnPropertyMap.put(5, "characteristics");
        columnPropertyMap.put(6, "wasteValueDesc");
        columnPropertyMap.put(7, "transactionTypeDesc");
        columnPropertyMap.put(8, "ocNonOcDesc");
        columnPropertyMap.put(9, "storageLocationDesc");
        columnPropertyMap.put(10, "fullDayDisposalDesc");
        columnPropertyMap.put(11, "whStockCountingDesc");
        columnPropertyMap.put(12, "packagingTypeDesc");
        columnPropertyMap.put(13, "entryNoteDesc");
        columnPropertyMap.put(14, "entryWeightDesc");
        columnPropertyMap.put(15, "weightPerPackage");
        columnPropertyMap.put(16, "packgeFlgDesc");
        columnPropertyMap.put(17, "wasteOutTolerance");
        columnPropertyMap.put(18, "storeDuration");
        columnPropertyMap.put(19, "wbFlagDesc");
        columnPropertyMap.put(20, "uom");
        columnPropertyMap.put(21, "scFlagDesc");
        columnPropertyMap.put(22, "regulationCode");
        columnPropertyMap.put(23, "regulationCodeDesc");
        columnPropertyMap.put(24, "plantId");
        columnPropertyMap.put(25, "tpsId");
        columnPropertyMap.put(26, "wahoSloc");
        columnPropertyMap.put(27, "storeCapacity");
        columnPropertyMap.put(28, "statusDesc");
        columnPropertyMap.put(29, "modifiedDate");
        columnPropertyMap.put(30, "modifiedUser");
        Vms022DownloadConfigDTO configDTO = new Vms022DownloadConfigDTO();
        configDTO.setStaticStyleTemplate(null);
        configDTO.setStartColumn(1);
        configDTO.setEndColumn(30);
        configDTO.setStartDetailRow(8);
        configDTO.setNumberingColumn(1);
        configDTO.setIsAddNumbering(false);
        configDTO.setStaticData(staticVal);
        configDTO.setDetailMapping(columnPropertyMap);

        String templateName = "Vms022MasterDataWaste.xlsx";
        Vms022DownloadProcessor<Vms022VoWaste, Vms022VoWaste> proc
                = new Vms022DownloadProcessor<>(null, data, configDTO, templateName);
        if (templatePath != null) {
            proc.TEMPLATE_PACKAGE = templatePath;
        }

        try {
            wb = proc.generateExcels();
            param.getSearch().put(AppEnum.TEMPLATE_NAME.getValue(), templateName);
        } catch (IOException | InvocationTargetException ex) {
            Logger.getLogger(Vms022ServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RestCommonException(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Vms022ServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RestCommonException(ex.getMessage());
        }

        return wb;
    }

    private String getWasteId(String userid) {
        int number = ahmmoerpTxnrunnumsService.getNewValue("GAERS005", "IDWASTE", userid, null);
        String id = StringUtils.leftPad(String.valueOf(number), 5, "0");
        return "WD-" + id;
    }

    @Override
    public DtoResponseWorkspace getWastedId(VoUserCred userCred) {
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, getWasteId(userCred.getUserid()));
    }

    @Override
    public DtoResponseWorkspace view(String wasteId) {
        List<VoMessageWorkspace> errorList = new ArrayList<>();

        if (StringUtils.isBlank(wasteId)) {
            errorList.add(new VoMessageWorkspace("ahmgavms022_wasteId", Vms022Constant.NG_WASTE));
        }
        if (!errorList.isEmpty()) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_VALID, errorList, null);
        }

        Vms022VoWaste vo = vms022AhmgaersMstwastesDao.getDataById(wasteId);
        if (vo == null) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_DATA, null, null);
        }

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null, vo);
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace edit(Vms022VoWaste vo, String userId) {
        AhmgaersMstwastes item = vms022AhmgaersMstwastesDao.findOne(vo.getWasteId());
        if (item == null) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_DATA, null, null);
        }

        vms022AhmgaersMstwastesDao.evict(item);

        item.setVwastetype(vo.getWasteType());
        item.setVb3nonb3(vo.getB3NonB3());
        item.setVwastevl(vo.getWasteValue());
        item.setVtranstyp(vo.getTransactionType());
        item.setVocnonoc(vo.getOcNonOc());
        item.setVstorageloc(vo.getStorageLocation());
        item.setVfddispltrx(vo.getFullDayDisposal());
        item.setVwscreq(vo.getWhStockCounting());
        item.setVentrynt(vo.getEntryNote());
        item.setVentrywght(vo.getEntryWeight());
        item.setVpackage(vo.getPackagingType());
        item.setNwghtppkg(vo.getWeightPerPackage());
        item.setNwastetl(vo.getWasteOutTolerance());
        item.setNstordur(vo.getStoreDuration());
        item.setVwbflag(vo.getWbFlag());
        item.setVscflag(vo.getScFlag());
        item.setVwasteunit(vo.getUom());
        item.setVpackgeflg(vo.getPackgeFlg());

        if (!"".equals(vo.getCharacteristicCode()[0])) {
            vms022AhmgaersMstwastesDao.update(item, userId, SERVICE_ID);
            vms022AhmgaersMstcharwastesDao.delete(vo.getWasteId());

            for (String characteristicCode : vo.getCharacteristicCode()) {
                List<AhmgaersMstcharwastes> charList = vms022AhmgaersMstcharwastesDao.getCharWastes(vo.getWasteId(), characteristicCode);

                if (charList.size() > 0) {
                    for (AhmgaersMstcharwastes itemChar : charList) {
                        String vcharwaste = itemChar.getVcharwaste();
                        String vwasteid = itemChar.getVwasteid();
                        Date dinactive = itemChar.getDinactive();
                        if (vcharwaste.equals(characteristicCode)
                                && vwasteid.equals(vo.getWasteId())
                                && dinactive.equals(Vms022Constant.DEFAULT_MAX_DATE)) {
                            vms022AhmgaersMstcharwastesDao.update(itemChar, userId, SERVICE_ID);

                        } else if (dinactive.equals(Vms022Constant.DEFAULT_MAX_DATE) && characteristicCode.equals(vcharwaste)) {
                            vms022AhmgaersMstcharwastesDao.deleteByCharCode(vo.getWasteId(), characteristicCode);
                        }
                    }
                } else {
                    AhmgaersMstcharwastes charWaste = new AhmgaersMstcharwastes();
                    charWaste.setVwasteid(vo.getWasteId());
                    charWaste.setVcharwaste(characteristicCode);
                    charWaste.setDinactive(Vms022Constant.DEFAULT_MAX_DATE);
                    charWaste.setVstatus(Vms022Constant.STATUS_ACTIVE);
                    vms022AhmgaersMstcharwastesDao.save(charWaste, userId, SERVICE_ID);
                }
            }

        } else {
            vms022AhmgaersMstwastesDao.update(item, userId, SERVICE_ID);
            vms022AhmgaersMstcharwastesDao.delete(vo.getWasteId());
        }

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
    }

    private void setRegulationCode(AhmgaersMstwastes data, List<Vms022VoWasteDetail> listVo) {
        String addComma = ", ";
        List<String> reCode = new ArrayList<>();
        List<String> reDesc = new ArrayList<>();

        for (Vms022VoWasteDetail itemList : listVo) {
            String code = itemList.getRegulationCode();
            if (!StringUtils.isEmpty(code) && !"null".equals(code)) {
                reCode.add(code);
            }
            String desc = itemList.getRegulationCodeDesc();
            if (!StringUtils.isEmpty(desc) && !"null".equals(desc)) {
                reDesc.add(desc);
            }
        }

        data.setVrglcode(String.join(addComma, reCode));
        data.setVrglcodedsc(DETAIL_REGULASI);
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace add(Vms022VoWaste vo, List<Vms022VoWasteDetail> vos, String userId) {
        List<String> listS = new ArrayList<>();
        listS.add(String.format("isSapMatNo: %s", vo.isSapMatNo()));
        if (vo.isSapMatNo()) {
            AhmgaersMstwastes data = vms022AhmgaersMstwastesDao.findOne(vo.getWasteId());
            listS.add(String.format("status %s", (data != null) ? data.getVstatus() : "NULL"));
            if (data != null && Vms022Constant.ACTIVE.equals(data.getVstatus())) {
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, "ID Limbah " + vo.getWasteId() + " sudah ada", null, null);
            } else if (data != null && Vms022Constant.INACTIVE.equals(data.getVstatus())) {
                vos = vo.getDetail();
                vms022AhmgaersMstwastesDao.evict(data);
                voToModel(data, vo);
                setRegulationCode(data, vos);
                data.setVpackgeflg(vo.getPackgeFlg());
                Boolean updatenWaste;
                updatenWaste = vms022AhmgaersMstwastesDao.update(data, userId, SERVICE_ID);
                listS.add(String.format("updated waste: %s", updatenWaste));
                vms022AhmgaersMstcharwastesDao.delete(vo.getWasteId());
                listS.add(String.format("chars: %s", vo.getCharacteristicCode()[0]));
                if (!"".equals(vo.getCharacteristicCode()[0])) {
                    for (String characteristicCode : vo.getCharacteristicCode()) {
                        AhmgaersMstcharwastes charWaste = new AhmgaersMstcharwastes();
                        charWaste.setVwasteid(vo.getWasteId());
                        charWaste.setVcharwaste(characteristicCode);
                        charWaste.setDinactive(Vms022Constant.DEFAULT_MAX_DATE);
                        charWaste.setVstatus(Vms022Constant.STATUS_ACTIVE);
                        vms022AhmgaersMstcharwastesDao.save(charWaste, userId, SERVICE_ID);
                    }
                }
                DtoResponseWorkspace statusMsg = DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                if (vos != null && !vos.isEmpty()) {
                    statusMsg = addNewDetail(vos, userId);
                }
                statusMsg.setData(listS);
                return statusMsg;
            }
        }

        AhmgaersMstwastes item = new AhmgaersMstwastes(vo.getWasteId());
        voToModel(item, vo);
        setRegulationCode(item, vos);
        vos = vo.getDetail();

        item.setVpackgeflg(vo.getPackgeFlg());
        Boolean savedWaste;
        savedWaste = vms022AhmgaersMstwastesDao.save(item, userId, SERVICE_ID);
        listS.add(String.format("waste.save: %s", savedWaste));

        if (!"".equals(vo.getCharacteristicCode()[0])) {
            for (String characteristicCode : vo.getCharacteristicCode()) {
                AhmgaersMstcharwastes charWaste = new AhmgaersMstcharwastes();
                charWaste.setVwasteid(vo.getWasteId());
                charWaste.setVcharwaste(characteristicCode);
                charWaste.setDinactive(Vms022Constant.DEFAULT_MAX_DATE);
                charWaste.setVstatus(Vms022Constant.STATUS_ACTIVE);
                vms022AhmgaersMstcharwastesDao.save(charWaste, userId, SERVICE_ID);
            }
        }

        DtoResponseWorkspace statusMsg = DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
        if (vos != null && !vos.isEmpty()) {
            statusMsg = addNewDetail(vos, userId);

        }
        statusMsg.setData(listS);

        return statusMsg;
    }

    private void voToModel(AhmgaersMstwastes item, Vms022VoWaste vo) {
        item.setVwastedesc(vo.getWasteDesc());
        item.setVwastetype(vo.getWasteType());
        item.setVb3nonb3(vo.getB3NonB3());
        item.setVwastevl(vo.getWasteValue());
        item.setVtranstyp(vo.getTransactionType());
        item.setVocnonoc(vo.getOcNonOc());
        item.setVstorageloc(vo.getStorageLocation());
        item.setVfddispltrx(vo.getFullDayDisposal());
        item.setVwscreq(vo.getWhStockCounting());
        item.setVpackage(vo.getPackagingType());
        item.setVentrynt(vo.getEntryNote());
        item.setVentrywght(vo.getEntryWeight());
        item.setNwghtppkg(vo.getWeightPerPackage());
        item.setNwastetl(vo.getWasteOutTolerance());
        item.setNstordur(vo.getStoreDuration());
        item.setVwasteunit(vo.getUom());
        item.setDactive(new Date());
        item.setVwbflag(vo.getWbFlag());
        item.setVscflag(vo.getScFlag());
        item.setVstatus(Vms022Constant.ACTIVE);
        item.setVpackgeflg(vo.getPackgeFlg());
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace deactivate(String wasteId, String userid) {
        List<VoMessageWorkspace> errorList = new ArrayList<>();

        if (StringUtils.isBlank(wasteId)) {
            errorList.add(new VoMessageWorkspace("ahmgavms022_wasteId", Vms022Constant.NG_WASTE));
        }

        if (!errorList.isEmpty()) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_VALID, errorList, null);
        }

        AhmgaersMstwastes data = vms022AhmgaersMstwastesDao.findOne(wasteId);

        if (data != null) {
            vms022AhmgaersMstwastesDao.evict(data);
        } else {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, "Hanya status [ACTIVE] yang dapat dideactivate", null, null);
        }
        data.setVstatus(Vms022Constant.INACTIVE);
        vms022AhmgaersMstwastesDao.update(data, userid, SERVICE_ID);
        Date now = new Date();
        List<AhmgaersMstdtlwastes> dtlWastes = vms022AhmgaersMstdtlwastesDao.find(wasteId);
        for (AhmgaersMstdtlwastes dtl : dtlWastes) {
            dtl.setVstatus(Vms022Constant.INACTIVE);
            dtl.setDinactive(now);
            vms022AhmgaersMstdtlwastesDao.update(dtl, userid, SERVICE_ID);
        }

        List<AhmgaersMstcharwastes> charWastes = vms022AhmgaersMstcharwastesDao.find(wasteId);
        for (AhmgaersMstcharwastes dtl : charWastes) {
            dtl.setVstatus(Vms022Constant.STATUS_INACTIVE);
            dtl.setDinactive(now);
            vms022AhmgaersMstcharwastesDao.update(dtl, userid, SERVICE_ID);
        }

        List<AhmgawmsTxnpckgs> pckgs = vms022AhmgawmsTxnpckgsDao.findByWasteId(wasteId);
        for (AhmgawmsTxnpckgs pckg : pckgs) {
            pckg.setDwstinctv(now);
            vms022AhmgawmsTxnpckgsDao.update(pckg, userid, SERVICE_ID);
        }

        List<AhmgawmsTxnscanouts> scanouts = vms022AhmgawmsTxnscanoutsDao.findByWasteId(wasteId);
        for (AhmgawmsTxnscanouts scanout : scanouts) {
            scanout.setDinactive(now);
            vms022AhmgawmsTxnscanoutsDao.update(scanout, userid, SERVICE_ID);
        }

        AhmgaersMstwasthis his = new AhmgaersMstwasthis(wasteId, now);
        his.setVwastetype(data.getVwastetype());
        his.setVwastedesc(data.getVwastedesc());
        his.setVb3nonb3(data.getVb3nonb3());
        his.setVwastevl(ObjectUtils.firstNonNull(data.getVwastevl(), "0"));
        his.setVtranstyp(ObjectUtils.firstNonNull(data.getVtranstyp(), " "));
        his.setVocnonoc(data.getVocnonoc());
        his.setVstorageloc(data.getVstorageloc());
        his.setVrglcode(data.getVrglcode());
        his.setVrglcodedsc(data.getVrglcodedsc());
        his.setVfddispltrx(data.getVfddispltrx());
        his.setVwscreq(data.getVwscreq());
        his.setVpackage(ObjectUtils.firstNonNull(data.getVpackage(), " "));
        his.setVentrynt(ObjectUtils.firstNonNull(data.getVentrynt(), " "));
        his.setVentrywght(data.getVentrywght());
        his.setNwghtppkg(data.getNwghtppkg());
        his.setNwastetl(data.getNwastetl());
        his.setNstordur(ObjectUtils.firstNonNull(data.getNstordur(), BigDecimal.ZERO));
        his.setVstatus(data.getVstatus());
        his.setDactive(ObjectUtils.firstNonNull(data.getDactive(), now));
        his.setVwbflag(data.getVwbflag());
        his.setVscflag(data.getVscflag());
        his.setVwasteunit(data.getVwasteunit());

        vms022AhmgaersMstwasthisDao.save(his, userid, SERVICE_ID);

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);

    }

    @Override
    @Transactional(readOnly = true)
    public DtoResponseWorkspace viewDetail(DtoParamPaging dtoParamPaging) {
        int count = vms022AhmgaersMstdtlwastesDao.countViewDetail(dtoParamPaging);
        List<Vms022VoWasteDetail> vo = vms022AhmgaersMstdtlwastesDao.viewDetail(dtoParamPaging);
        if (vo == null) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_DATA, null, count);
        }

        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, null, null, vo, 0);

    }

    private void plantTpsValidation(Vms022VoWasteDetail vo, String userId, List<VoMessageWorkspace> errorList) {
        if (StringUtils.isBlank(vo.getPlantId())) {
            errorList.add(new VoMessageWorkspace("ahmgavms022_plantId", "ID Plant harus diisi"));
        } else {
            List<String> validPlants = vms022DefaultDao.getPlantsById(vo.getPlantId(), userId);
            if (validPlants == null || validPlants.isEmpty()) {
                errorList.add(new VoMessageWorkspace("ahmgavms022_plantId", "ID Plant [" + vo.getPlantId() + "] tidak ditemukan"));
            }
        }
        if (!StringUtils.isBlank(vo.getTpsId())) {
            List<String> validTps = vms022DefaultDao.getTpsById(vo.getTpsId(), vo.getPlantId(), userId);
            if (validTps == null || validTps.isEmpty()) {
                errorList.add(
                        new VoMessageWorkspace("ahmgavms022_tpsId", "ID TPS [" + vo.getTpsId()
                                + "] di Plant [" + vo.getPlantId() + "] tidak ditemukan"));
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace editDetail(Vms022VoWasteDetail vo, String userId) {

        List<VoMessageWorkspace> errorList = new ArrayList<>();

        if (StringUtils.isBlank(vo.getWasteId())) {
            errorList.add(new VoMessageWorkspace("ahmgavms022_wasteId", Vms022Constant.NG_WASTE));
        }

        if (vo.getSeq() == null) {
            errorList.add(new VoMessageWorkspace("ahmgavms022_wasteId", "ID LImbah Sequence harus diisi"));
        }
        plantTpsValidation(vo, userId, errorList);

        if (!errorList.isEmpty()) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_VALID, errorList, null);
        }

        AhmgaersMstdtlwastes item = vms022AhmgaersMstdtlwastesDao.findOne(vo.getWasteId(), Vms022Constant.DEFAULT_MAX_DATE, vo.getSeq());
        if (item == null) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_DATA, null, null);
        }
        vms022AhmgaersMstdtlwastesDao.evict(item);

        item.setVplantid(vo.getPlantId());
        item.setVtpsid(vo.getTpsId());
        item.setVrglcode(vo.getRegulationCode());
        item.setVrglcodedsc(vo.getRegulationCodeDesc());
        item.setVwahoslocid(vo.getWahoSloc());
        item.setNstorcap(vo.getStoreCapacity());

        vms022AhmgaersMstdtlwastesDao.update(item, userId, SERVICE_ID);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace deleteDetail(String wasteId, Integer seq, String userId) {
        List<VoMessageWorkspace> errorList = new ArrayList<>();
        if (StringUtils.isBlank(wasteId)) {
            errorList.add(new VoMessageWorkspace("ahmgavms022_wasteId", Vms022Constant.NG_WASTE));
        }
        if (seq == null) {
            errorList.add(new VoMessageWorkspace("ahmgavms022_wasteId", "ID LImbah Sequence harus diisi"));
        }
        if (!errorList.isEmpty()) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_VALID, errorList, null);
        }
        AhmgaersMstdtlwastes item = vms022AhmgaersMstdtlwastesDao.findOne(wasteId, Vms022Constant.DEFAULT_MAX_DATE, seq);
        if (item == null) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_DATA, null, null);
        }

        vms022AhmgaersMstdtlwastesDao.evict(item);
        vms022AhmgaersMstdtlwastesDao.delete(item);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace addNewDetail(List<Vms022VoWasteDetail> vos, String userId) {
        for (Vms022VoWasteDetail vo : vos) {
            Integer seq = vms022AhmgaersMstdtlwastesDao.getDetailSequence(vo.getWasteId());
            seq = seq == null ? 1 : seq + 1;
            AhmgaersMstdtlwastes item = new AhmgaersMstdtlwastes();

            item.setVwasteid(vo.getWasteId());
            item.setDinactive(Vms022Constant.DEFAULT_MAX_DATE);
            item.setNseqdtlwst(seq);
            item.setVplantid(vo.getPlantId());
            item.setVtpsid(vo.getTpsId());
            item.setVrglcode(vo.getRegulationCode());
            item.setVrglcodedsc(vo.getRegulationCodeDesc());
            item.setVwahoslocid(vo.getWahoSloc());
            item.setNstorcap(vo.getStoreCapacity());
            item.setVstatus(Vms022Constant.ACTIVE);

            vms022AhmgaersMstdtlwastesDao.save(item, userId, SERVICE_ID);
        }

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace addDetail(List<Vms022VoWasteDetail> vos, String userId) {
        for (Vms022VoWasteDetail vo : vos) {
            List<VoMessageWorkspace> errorList = new ArrayList<>();

            if (StringUtils.isBlank(vo.getWasteId())) {
                errorList.add(new VoMessageWorkspace("ahmgavms022_wasteId", Vms022Constant.NG_WASTE));
            }
            plantTpsValidation(vo, userId, errorList);

            if (!errorList.isEmpty()) {
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_VALID, errorList, null);
            }

            Integer seq = vms022AhmgaersMstdtlwastesDao.getDetailSequence(vo.getWasteId());
            seq = seq == null ? 1 : seq + 1;
            AhmgaersMstdtlwastes item = new AhmgaersMstdtlwastes();

            item.setVwasteid(vo.getWasteId());
            item.setDinactive(Vms022Constant.DEFAULT_MAX_DATE);
            item.setNseqdtlwst(seq);
            item.setVplantid(vo.getPlantId());
            item.setVtpsid(vo.getTpsId());
            item.setVrglcode(vo.getRegulationCode());
            item.setVrglcodedsc(vo.getRegulationCodeDesc());
            item.setVwahoslocid(vo.getWahoSloc());
            item.setNstorcap(vo.getStoreCapacity());
            item.setVstatus(Vms022Constant.ACTIVE);

            vms022AhmgaersMstdtlwastesDao.save(item, userId, SERVICE_ID);
        }

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
    }

    @Override
    public DtoResponseWorkspace validateUomByWasteId(String wasteId) {
        String result = vms022DefaultDao.getUomByWasteId(wasteId);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
    }

    @Override
    public DtoResponseWorkspace getPlants(Vms022VoWaste vo, String userId) {
        List<String> result = vms022DefaultDao.getPlantsById(vo.getPlantId(), userId);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
    }

    @Override
    public DtoResponseWorkspace getPlantTps(Vms022VoWaste vo, String userId) {
        String result = vms022DefaultDao.getPlantTps(vo.getPlantId(), vo.getTpsId(), userId);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
    }
    
    private String userId(VoUserCred user) {
        String domain = ""; 
        if (user==null) return null;
        if (!StringUtils.isEmpty(user.getDomain())) 
            domain = user.getDomain() + "\\";
        return domain + user.getUsername();         
    }

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
            l = vms022DefaultDao.getPlantsByUserId(plants);
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
    public DtoResponseWorkspace regulations(Vms022VoRegulation vo, String userId) {
        Vms022VoRegulation w = vms022AhmgaersMstdtlwastesDao.getRegulationsWaste(vo.getWasteId());

        AhmgaersMstwastes data = vms022AhmgaersMstwastesDao.findOne(vo.getWasteId());
        if (data == null) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, Vms022Constant.NG_DATA, null, null);
        }
        vms022AhmgaersMstwastesDao.evict(data);
        if (w != null) {
            data.setVrglcode(w.getRegulationCode());
            data.setVrglcodedsc(DETAIL_REGULASI);
        } else {
            data.setVrglcode("");
            data.setVrglcodedsc("");
        }
        vms022AhmgaersMstwastesDao.update(data, userId, SERVICE_ID);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, w);
    }

    @Override
    public DtoResponseWorkspace validatePlantTps(Vms022VoDetail vo, String userId) {
        List<VoMessageWorkspace> errorList = new ArrayList<>(); 
        
        if (StringUtils.isBlank(vo.getPlantId())) {
            errorList.add(new VoMessageWorkspace("plantId", "ID Plant harus diisi"));
        } else {
            List<String> validPlants = vms022DefaultDao.getPlantsById(vo.getPlantId(), userId);
            if (validPlants == null || validPlants.isEmpty()) {
                errorList.add(new VoMessageWorkspace("plantId", "ID Plant [" + vo.getPlantId() + "] tidak ditemukan"));
            }
        }
        if (!StringUtils.isBlank(vo.getTpsId())) {
            List<String> validTps = vms022DefaultDao.getTpsById(vo.getTpsId(), vo.getPlantId(), userId);
            if (validTps == null || validTps.isEmpty()) {
                errorList.add(
                        new VoMessageWorkspace("tpsId", "ID TPS [" + vo.getTpsId()
                                + "] di Plant [" + vo.getPlantId() + "] tidak ditemukan"));
            }
        }
        
        return DtoHelper.constructResponseWorkspace(
                ((errorList.isEmpty()) 
                ? StatusMsgEnum.SUKSES
                : StatusMsgEnum.GAGAL)
                , (errorList.isEmpty()) ? "Data ditemukan": Vms022Constant.NG_DATA
                , errorList, null);
    }
    
    

}
