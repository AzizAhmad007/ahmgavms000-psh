
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.constant;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031Constant {
    public static final Map<String, String> FIELD_SHOW_DATA = new HashMap<String, String>() {
        {
            put("sort_nrp", "BLACKLIST.MNRP_NRP");
            put("sort_nama", "BLACKLIST.MNAME_VNAME");
            put("sort_jenisKartuIdentitas", "BLACKLIST.MIDTYPE_VIDTYPE");
            put("sort_noIdenttias", "BLACKLIST.MIDNO_VIDNO");
            put("sort_nik", "BLACKLIST.MNIK_VNIK");
            put("sort_jenisKelamin", "BLACKLIST.MGENDER_VGENDER");
            put("sort_alamatKtp", "BLACKLIST.MADRKTP_VADRKTP");            
            put("sort_alamatDomisili", "BLACKLIST.MADRDOM_VADRDOM");
            put("sort_TipeKaryawanBlacklist", "BLACKLIST.MISEMP_VISEMP");
            put("sort_namaPerusahaan", "BLACKLIST.MCMPNY_VCMPNY");
            put("sort_alasanBlacklist", "BLACKLIST.MREASON_VREASON");
            put("sort_typeFoto", "BLACKLIST.MTYPEFOTO_VFTYPEFOTO");
            put("sort_namaFoto", "BLACKLIST.MNAMEFOTO_VFNAMEFOTO");
            put("sort_extensionFoto", "BLACKLIST.MFEXTFOTO_VFEXTFOTO");
            put("sort_pathFoto", "BLACKLIST.MFPATHFOTO_VFPATHFOTO");            
            put("sort_tglStartEffective", "BLACKLIST.MDSTEFF_DSTEFF");
            put("sort_tglEndEffective", "BLACKLIST.MDENDEFF");
        }
    };
    public static final String SQL_GET_MONITORING_BLACKLIST
            = "SELECT DISTINCT "
            + "HDRBLST.NIDHDR, DTBLST.NIDHDR,"
            + "HDRBLST.VNIK, DTLBLST.VNIK,"
            + "HDRBLST.VNAME,"
            + "HDRBLST.VGENDER,"
            + "HDRBLST.VADRKTP,"
            + "HDRBLST.VADRDOM,"
            + "HDRBLST.VISEMP,"
            + "HDRBLST.VCMPNY,"
            + "HDRBLST.DSTEFF,"
            + "HDRBLST.DENDEFF,"
            + "DTLBLST.VIDTYPE,"
            + "DTLBLST.VIDNO "
            + "FROM AHMGAVMS_HDRBLACKLST HDRBLST INNER JOIN AHMGAVMS_DTLBLACKLST DTLBLST "
            + "ON HDRBLST.VID_HEADER = DTLBLST.VID_DETAIL "
            + "WHERE (:NIDHDR IS NOT NULL OR UPPER(HDRBLST.NIDHDR) LIKE '%'||UPPER(:NIDHDR)||'%')"
            + "AND (:NIDDTL IS NOT NULL OR UPPER(HDRBLST.NIDDTL) LIKE '%'||UPPER(:NIDDTL)||'%')"
            + "AND (:VNIK IS NOT NULL OR UPPER(HDRBLST.VNIK) LIKE '%'||UPPER(:VNIK)||'%')"
            + "AND (:VIDTYPE IS NOT NULL OR UPPER(DTLBLST.VIDTYPE) LIKE '%'||UPPER(:VIDTYPE)||'%') "
            + "AND (:VIDNO IS NOT NULL OR UPPER(DTLBLST.VIDNO) LIKE '%'||UPPER(:VIDNO)||'%') "
            + "AND (UPPER(HDRBLST.DSTEFF) LIKE '%'||UPPER(:DSTEFF)||'%') "
            + "AND (UPPER(HDRBLST.DENDEFF) LIKE '%'||UPPER(:DENDEFF)||'%')  "
            + "AND (HDRBLST.VISEMP = :VISEMP)";
     
     public static final String SQL_MONITORING_BLACKLIST
            = "SELECT distinct "+
              "hdr.NIDHDR AS Header_ID" +
              "hdr.VNIK AS Header_VNIK," +
              "hdr.VNAME AS Header_VNAME," +
              "hdr.VGENDER AS Header_VGENDER," +
              "hdr.VADRKTP AS Header_VADRKTP," +
              "hdr.VADRDOM AS Header_VADRDOM," +
              "hdr.VISEMP AS Header_VISEMP," +
              "hdr.VCMPNY AS Header_VCMPNY," +
              "hdr.VREASON AS Header_VREASON," +
              "hdr.VFTYPEFOTO AS Header_VFTYPEFOTO," +
              "hdr.VFNAMEFOTO AS Header_VFNAMEFOTO," +
              "hdr.VFEXTFOTO AS Header_VFEXTFOTO," +
              "hdr.VFPATHFOTO AS Header_VFPATHFOTO," +
              "hdr.DSTEFF AS Header_DSTEFF," +
              "hdr.DENDEFF AS Header_DENDEFF," +
              "hdr.DCREA AS Header_DCREA," +
              "hdr.VCREA AS Header_VCREA," +
              "hdr.DMODI AS Header_DMODI," +
              "hdr.VMODI AS Header_VMODI,"+
              "dtl.NIDDTL AS Detail_NIDDTL,"+
              "dtl.VNIK AS Detail_VNIK," +             
              "dtl.VIDTYPE AS Detail_VIDTYPE," +
              "dtl.VIDNO AS Detail_VIDNO," +
              "dtl.DCREA AS Detail_DCREA," +
              "dtl.VCREA AS Detail_VCREA," +
              "dtl.DMODI AS Detail_DMODI," +
              "dtl.VMODI AS Detail_VMODI" +
              "FROM AHMGAVMS_HDRBLACKLST AS hdr" +
              "INNER JOIN AHMGAVMS_DTLBLACKLST AS dtl" +
              "ON hdr.NIDHDR = dtl.NIDHDR";
     
     public static final String SQL_GETNIK_BLACKLIST
             = "SELECT distinct" +
               "    hdr.VNIK AS Header_VNIK," +
               "    hdr.VNAME AS Header_VNAME," +
               "    hdr.VGENDER AS Header_VGENDER," +
               "    hdr.VADRKTP AS Header_VADRKTP," +
               "    hdr.VADRDOM AS Header_VADRDOM," +
               "    hdr.VISEMP AS Header_VISEMP," +
               "    hdr.VCMPNY AS Header_VCMPNY," +
               "    hdr.VREASON AS Header_VREASON," +
               "    hdr.VFTYPEFOTO AS Header_VFTYPEFOTO," +
               "    hdr.VFNAMEFOTO AS Header_VFNAMEFOTO," +
               "    hdr.VFEXTFOTO AS Header_VFEXTFOTO," +
               "    hdr.VFPATHFOTO AS Header_VFPATHFOTO," +
               "    hdr.DSTEFF AS Header_DSTEFF," +
               "    hdr.DENDEFF AS Header_DENDEFF," +
               "    hdr.DCREA AS Header_DCREA," +
               "    hdr.VCREA AS Header_VCREA," +
               "    hdr.DMODI AS Header_DMODI," +
               "    hdr.VMODI AS Header_VMODI," +
               "    dtl.VIDTYPE AS Detail_VIDTYPE," +
               "    dtl.VIDNO AS Detail_VIDNO," +
               "    dtl.DCREA AS Detail_DCREA," +
               "    dtl.VCREA AS Detail_VCREA," +
               "    dtl.DMODI AS Detail_DMODI," +
               "    dtl.VMODI AS Detail_VMODI," +
               "FROM AHMGAVMS_HDRBLST hdr" +
               "INNER JOIN AHMGAVMS_DTLBLST dtl" +
               "ON hdr.VNIK = dtl.VNIK" +
               "WHERE hdr.VNIK = dtl.VNIK";
     
     public static final String SQL_CONFIRM_ID
             = "SELECT * FROM AHMGAVMS_HDRBLST " +
               "WHERE VNIK = VNIK";
     
     public static final String DEFAULT_SORT = "BLACKLIST.DMODI";
    public static final String REMARK_NORMAL = "NORMAL";
    public static final String REMARK_OVER = "OVER";
    //Portal Dev
    public static final String TEMPLATE_PATH_UPLOAD_BLACKLIST = "/data/deploy/download/ahmgavms031/template/_S_AHMGAVMS031_Upload_Blacklist.xlsx";
    public static final String TEMPLATE_BLACKLIST = "BLACKLIST";
    public static final String TEMPLATE_NAME_BLACKLIST = "S_AHMGAVMS031_Upload_Blacklist";
    public static final String TEMPLATE_EXTENSIONS = ".xlsx";
    public static final String STRING = "string";
    public static final String COLUMN_NRP = "Nrp";
    public static final String COLUMN_NAMA = "Nama";
    public static final String COLUMN_NIK = "Nik";
    public static final String COLUMN_JENIS_KARTU_IDENTITAS = "Jenis Kartu Identitas";
    public static final String COLUMN_NO_IDENTITAS = "No Identias";
    public static final String COLUMN_JENIS_KELAMIN = "Jenis Kelamin";
    public static final String COLUMN_ALAMAT_KTP = "Alamat KTP";  
    public static final String COLUMN_ALAMAT_DOMISILI = "Alamat Domisili";
    public static final String COLUMN_TIPE_KARYAWAN_BLACKLIST = "Tipe Karyawan Blacklist";
    public static final String COLUMN_NAMA_PERUSAHAAN = "Nama Perusahaan";
    public static final String COLUMN_ALASAN_BLACKLIST = "Alasan Blacklist";
    public static final String COLUMN_TYPE_FOTO = "Type Foto";
    public static final String COLUMN_NAMA_FOTO = "Nama Foto";
    public static final String COLUMN_EXTENSION_FOTO = "Extension Foto";
    public static final String COLUMN_PATH_FOTO = "Path Foto";
    public static final String COLUMN_TGL_START_EFFECTIVE = "Tanggal Start Effective";
    public static final String COLUMN_TGL_END_EFFECTIVE = "Tanggal End Effective";
    public static final String UPLOAD_BLACKLIST = "BLACKLIST";
    public static final String FORMAT_DATE_YYMM = "yyMM";
    public static final String FORMAT_TRANSACTION_NO = "%s%05d";
    public static final String CUSTOM_ROLE_001 = "CR001";
    public static final String FILENAME_EXCEL = "S_AHMGAVMS031_Export_Excel";
    public static final String STYLE_REMARK_NORMAL = "<div class=\"w-100 h-100 btn btn-success flex flex-middle flex-center\">NORMAL</div>";
    public static final String STYLE_REMARK_OVER = "<div class=\"w-100 h-100 btn btn-danger flex flex-middle flex-center\">OVER</div>";
    public static final String REGEX_NUMBER_ONLY = "\\d+";
    public static final String VID_TYPE = "MOMSC_SHTXNTYPE";
    public static final String VITEMCODE_TYPE = "MONITORING";
    public static final String VID_INOUT = "MOMSC_INOUT";
    public static final String VITEMCODE_INOUT = "OUT";
    public static final String VID_MATERIAL_SARANAHANDLING = "MST321_JENIS_PACK";    
    public static final String DATE_FORMAT_DDMMMYYYY = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_DDMMMYYYYHHMMSS = "dd-MMM-yyyy HH:mm:ss";
    public static final String DATE_FORMAT_YYMM = "yyMM";
}
