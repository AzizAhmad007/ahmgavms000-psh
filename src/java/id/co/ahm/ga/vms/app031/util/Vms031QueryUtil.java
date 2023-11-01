/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.util;

import id.co.ahm.ga.vms.app031.vo.Vms031VoMonitoring;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031QueryUtil {
    public static List<AhmmoerpDtlsettings> mappingResponseListSetting(List<Map<String,Object>> list){
        List<AhmmoerpDtlsettings> voList = new ArrayList<>();
        for(Map<String, Object> map : list) {
            AhmmoerpDtlsettings vo = new AhmmoerpDtlsettings();
            
            vo.setVitemname((String)map.get("VITEMNAME"));
            vo.setVitemdesc((String)map.get("VITEMDESC"));
            voList.add(vo);
        }
        return voList;
    }
     public static Vms031VoMonitoring setFilter(Map<String, Object> search) {
        Vms031VoMonitoring filterResult = new Vms031VoMonitoring();
        if (search != null && search.size() > 0) {

            for (Map.Entry<String, Object> filter : search.entrySet()) {
                String key = filter.getKey();
                String value = (String) filter.getValue();
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Nrp", key)) {
                    filterResult.setNrp(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Nama", key)) {
                    filterResult.setNama(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Jenis Kartu Identitas", key)) {
                    filterResult.setJenisKartuIdentitas(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("No Identitas", key)) {
                    filterResult.setNoIdentitas(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Nik", key)) {
                    filterResult.setNik(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Jenis Kelamin", key)) {
                    filterResult.setJenisKelamin(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Alamat Ktp", key)) {
                    filterResult.setAlamatKtp(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Alamat Domisili", key)) {
                    filterResult.setAlamatDomisili(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Tipe Karyawan Blacklist", key)) {
                    filterResult.setTipeKaryawanBlacklist(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Nama Perusahaan", key)) {
                    filterResult.setNamaPerusahaan(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Alasan Blacklist", key)) {
                    filterResult.setAlasanBlacklist(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Type Foto", key)) {
                    filterResult.setTypeFoto(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Nama Foto", key)) {
                    filterResult.setNamaFoto(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Extension Foto", key)) {
                    filterResult.setExtensionFoto(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Path Foto", key)) {
                    filterResult.setPathFoto(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Tanggal Start Effective", key)) {
                    filterResult.setTglStartEffectiveText(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("Tanggal End Effective", key)) {
                    filterResult.setTglEndEffectiveText(value);
                }
                
            }
        }
        return filterResult;
    }
    
    
    public static String doFormatNumberToStringWIthSeparator(BigDecimal param){
        
        String result = NumberFormat.getNumberInstance(Locale.GERMAN).format(param);
        return result;
    }
    
    public static List<Vms031VoMonitoring> mappingResponseMonitoringStockSh(List<Map<String,Object>> list){
        List<Vms031VoMonitoring> voList = new ArrayList<>();
        for(Map<String, Object> map : list) {
            Vms031VoMonitoring vo = new Vms031VoMonitoring();
            vo.setNrp((String)map.get("MNRP_NRP"));
            vo.setNama((String)map.get("MNAME_VNAME"));
            vo.setJenisKartuIdentitas((String)map.get("MIDTYPE_VIDTYPE"));
            vo.setNoIdentitas((String)map.get("MIDNO_VIDNO"));
            vo.setNik((String)map.get("MNIK_VNIK"));
            vo.setJenisKelamin((String)map.get("MGENDER_VGENDER"));
            vo.setAlamatKtp((String)map.get("MADKTP_VADRKTP"));
            vo.setAlamatDomisili((String)map.get("MADRDOM_VADRDOM"));
            vo.setTipeKaryawanBlacklist((String)map.get("MISEMP_VISEMP"));
            vo.setNamaPerusahaan((String)map.get("MCMPNY_VCMPNY"));
            vo.setAlasanBlacklist((String)map.get("MREASON_VREASON"));
            vo.setTypeFoto((String)map.get("MTYPEFOTO_VTYPEFOTO"));
            vo.setNamaFoto((String)map.get("MFNAMEFOT_VFNAMEFOTO"));
            vo.setExtensionFoto((String)map.get("MFEXTFOTO_VFEXTFOTO"));
            vo.setPathFoto((String)map.get("MFPATHFOTO_VFPATHFOTO"));
            vo.setTglStartEffective((Date)map.get("MDSTEFF_DSTEFF"));
            vo.setTglEndEffective((Date)map.get("MDENDEFF_DENDEFF"));
            
            voList.add(vo);
        }
        return voList;
    }
    
    public static String setParamQueryString(String sqlQuery, Vms031VoMonitoring filter){
        if(filter.getNrp()!= null && !filter.getNrp().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MNRP_NRP) LIKE :NRP ");
        }
        if(filter.getNama()!= null && !filter.getNama().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MNAME_VNAME) LIKE :VNAME ");
        }
        if(filter.getJenisKartuIdentitas()!= null && !filter.getJenisKartuIdentitas().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MIDTYPE_VIDTYPE) LIKE :VIDTYPE ");
        }
        if(filter.getNoIdentitas()!= null && !filter.getNoIdentitas().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MIDNO_VIDNO) LIKE :VIDNO ");
        }
        if(filter.getNik()!= null && !filter.getNik().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MNIK_VNIK) LIKE :VNIK ");
        }
        if(filter.getJenisKelamin()!= null && !filter.getJenisKelamin().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MGENDER_VGENDER) LIKE :VGENDER ");
        }
        if(filter.getAlamatKtp() != null && !filter.getAlamatKtp().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MADRKTP_VADRKTP) LIKE :VADRKTP ");
        }
        if(filter.getAlamatDomisili()!= null && !filter.getAlamatDomisili().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MADRDOM_VADRDOM) LIKE :VADRDOM ");
        }
        if(filter.getTipeKaryawanBlacklist()!= null && !filter.getTipeKaryawanBlacklist().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MISEMP_VISEMP) LIKE :VISEMP ");
        }
        if(filter.getNamaPerusahaan()!= null && !filter.getNamaPerusahaan().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MCMPNY_VCMPNY) LIKE :VCMPNY ");
        }
        if(filter.getAlasanBlacklist()!= null && !filter.getAlasanBlacklist().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MREASON_VREASON) LIKE :VREASON ");
        }
        if(filter.getTypeFoto()!= null && !filter.getTypeFoto().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MTYPEFOTO_VFTYPEFOTO) LIKE :VFTYPEFOTO ");
        }
        if(filter.getNamaFoto()!= null && !filter.getNamaFoto().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MNAMEFOTO_VFNAMEFOTO) LIKE :VFNAMEFOTO ");
        }
        if(filter.getExtensionFoto()!= null && !filter.getExtensionFoto().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MFEXTFOTO_VFEXTFOTO) LIKE :VFEXTFOTO ");
        }
        if(filter.getPathFoto()!= null && !filter.getPathFoto().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MFPATHFOTO_VFPATHFOTO) LIKE :VFPATHFOTO ");
        }
        if(filter.getTglStartEffectiveText()!= null && !filter.getTglStartEffectiveText().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MDSTEFF_DSTEFF) LIKE :DSTEFF ");
        }
        if(filter.getTglEndEffectiveText()!= null && !filter.getTglEndEffectiveText().isEmpty()){
            sqlQuery = sqlQuery.concat(" AND UPPER(BLACKLIST.MDENDEFF) LIKE :DENDEFF ");
        }
        return sqlQuery;
    }
    
    public static Query setParamQuery(Query q, Vms031VoMonitoring filter){
        if(filter.getNrp() != null && !filter.getNrp().isEmpty()){
            q.setParameter("NRP", "%" + filter.getNrp().toUpperCase() + "%");
        }
        if(filter.getNama()!= null && !filter.getNama().isEmpty()){
            q.setParameter("VNAME", "%" + filter.getNama().toUpperCase() + "%");
        }
        if(filter.getNik()!= null && !filter.getNik().isEmpty()){
            q.setParameter("VVNIK", "%" + filter.getNik().toUpperCase()+ "%");
        }
        if(filter.getJenisKartuIdentitas()!= null && !filter.getJenisKartuIdentitas().isEmpty()){
            q.setParameter("VVENDORDESC", "%" + filter.getJenisKartuIdentitas().toUpperCase()+ "%");
        }
        if(filter.getNik()!= null && !filter.getNik().isEmpty()){
            q.setParameter("VVNIK", "%" + filter.getNik().toUpperCase()+ "%");
        }
        if(filter.getJenisKartuIdentitas()!= null && !filter.getJenisKartuIdentitas().isEmpty()){
            q.setParameter("VIDTYPE", "%" + filter.getJenisKartuIdentitas().toUpperCase()+ "%");
        }
        if(filter.getNoIdentitas()!= null && !filter.getNoIdentitas().isEmpty()){
            q.setParameter("VIDNO", "%" + filter.getNik().toUpperCase()+ "%");
        }
        if(filter.getJenisKelamin()!= null && !filter.getJenisKelamin().isEmpty()){
            q.setParameter("VGENDER", "%" + filter.getJenisKelamin().toUpperCase()+ "%");
        }
        if(filter.getAlamatKtp()!= null && !filter.getAlamatKtp().isEmpty()){
            q.setParameter("VADRKTP", "%" + filter.getAlamatKtp().toUpperCase()+ "%");
        }
        if(filter.getAlamatDomisili()!= null && !filter.getAlamatDomisili().isEmpty()){
            q.setParameter("VADRDOM", "%" + filter.getAlamatDomisili().toUpperCase()+ "%");
        }
        if(filter.getTipeKaryawanBlacklist()!= null && !filter.getTipeKaryawanBlacklist().isEmpty()){
            q.setParameter("VISEMP", "%" + filter.getTipeKaryawanBlacklist().toUpperCase()+ "%");
        }
        if(filter.getNamaPerusahaan()!= null && !filter.getNamaPerusahaan().isEmpty()){
            q.setParameter("VCMPNY", "%" + filter.getNamaPerusahaan().toUpperCase()+ "%");
        }
        if(filter.getAlasanBlacklist()!= null && !filter.getAlasanBlacklist().isEmpty()){
            q.setParameter("VREASON", "%" + filter.getAlasanBlacklist().toUpperCase()+ "%");
        }
        if(filter.getTypeFoto()!= null && !filter.getTypeFoto().isEmpty()){
            q.setParameter("VFTYPEFOTO", "%" + filter.getTypeFoto().toUpperCase()+ "%");
        }
        if(filter.getNamaFoto()!= null && !filter.getNamaFoto().isEmpty()){
            q.setParameter("VFNAMEFOTO", "%" + filter.getNamaFoto().toUpperCase()+ "%");
        }
        if(filter.getExtensionFoto()!= null && !filter.getExtensionFoto().isEmpty()){
            q.setParameter("VFEXTFOTO", "%" + filter.getJenisKartuIdentitas().toUpperCase()+ "%");
        }
        if(filter.getPathFoto()!= null && !filter.getPathFoto().isEmpty()){
            q.setParameter("VFPATHFOTO", "%" + filter.getPathFoto().toUpperCase()+ "%");
        }
        if(filter.getTglStartEffectiveText()!= null && !filter.getTglStartEffectiveText().isEmpty()){
            q.setParameter("DSTEFF", "%" + filter.getTglStartEffectiveText().toUpperCase()+ "%");
        }
        if(filter.getTglEndEffectiveText()!= null && !filter.getTglEndEffectiveText().isEmpty()){
            q.setParameter("DENDEFF", "%" + filter.getTglEndEffectiveText().toUpperCase()+ "%");
        }
        return q;
    }
}
