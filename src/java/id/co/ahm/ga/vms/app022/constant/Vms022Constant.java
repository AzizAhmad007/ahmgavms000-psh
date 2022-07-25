/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.constant;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author teguh
 */
public class Vms022Constant {
    public static final String STATUS_ACTIVE = "0";
    public static final String STATUS_INACTIVE = "1";
    public static final Date DEFAULT_MAX_DATE = new GregorianCalendar(9999, Calendar.DECEMBER, 31).getTime();
    public static final String ACTIVE = "Active";
    public static final String INACTIVE= "Inactive";
    public static final String ANSI_DATE = "yyyyMMdd";
    public static final String SQL_MAX_DATE = "99991231";
    public static final String NG_VALID = "Gagal validasi Input";
    public static final String NG_DATA = "Data tidak ditemukan";
    public static final String NG_WASTE= "ID Limbah harus diisi";
    public static final String QSEARCH = 
  "SELECT " 
+ "  A.VWASTEID, " 
+ "  A.VWASTEDESC, " 
+ "  A.VWASTETYPE, " 
+ "  wu.VITEMNAME VWASTETYPEDESC, " 
+ "  A.VB3NONB3, " 
+ "  b3.VITEMNAME VB3NONB3DESC, " 
+ "  A.VWASTEVL, " 
+ "  wl.VITEMNAME VWASTEVLDESC, " 
+ "  A.VTRANSTYP, " 
+ "  tt.VITEMDESC VTRANSTYPDESC, " 
+ "  A.VENTRYNT, " 
+ "  nt.VITEMDESC VENTRYNTDESC, " 
+ "  A.VENTRYWGHT, " 
+ "  ew.VITEMNAME VENTRYWGHTDESC, " 
+ "  A.VSTORAGELOC, " 
+ "  A.VSTATUS, " 
+ "  S.VITEMNAME VSTATUSDESC, " 
+ "  COALESCE(A.DMODI,A.DACTIVE) DMOD, " 
+ "  A.DACTIVE, " 
+ "  B.VNAMA VMOD " 
+ "FROM AHMGAERS_MSTWASTES A " 
+ "JOIN ( " 
+ "    SELECT " 
+ "      NULLIF(TO_CHAR(:wasteId),'')   wasteId " 
+ "    , NULLIF(TO_CHAR(:wasteType),'') wasteType " 
+ "    , NULLIF(TO_CHAR(:status),'')    status " 
+ "    , NULLIF(TO_CHAR(:wasteDesc),'') wasteDesc " 
+ "    , NULLIF(TO_CHAR(:entryNote),'') entryNote " 
+ "    , NULLIF(TO_CHAR(:entryWeight), '') entryWeight " 
+ "    FROM dual " 
+ "   ) q ON 1=1 " 
+ "LEFT JOIN AHMMOERP_MSTKARYAWANS B ON TO_CHAR(B.IIDNRP) = COALESCE(A.VMODI, A.VCREA) " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS wu ON wu.RSET_VID = 'AHMGAWMS_WUJUD'     AND wu.VITEMCODE = A.VWASTETYPE " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS b3 ON b3.RSET_VID = 'AHMGAWMS_B3NONB3'   AND b3.VITEMCODE = A.VB3NONB3 " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS wl ON wl.RSET_VID = 'AHMGAWMS_WASTEVL'   AND wl.VITEMCODE = A.VWASTEVL " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS tt ON tt.RSET_VID = 'AHMGAWMS_TRANSTYP'  AND tt.VITEMCODE = A.VTRANSTYP " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS nt ON nt.RSET_VID = 'AHMGAWMS_ENTRYNT'   AND nt.VITEMCODE = A.VENTRYNT " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS ew ON ew.RSET_VID = 'AHMGAWMS_ENTRYWGHT' AND ew.VITEMCODE = A.VENTRYWGHT " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS S  ON s.RSET_VID  = 'AHMGAWMS_ERS005_STS'AND s.VITEMCODE = A.VSTATUS " 
+ "WHERE (q.wasteId IS NULL OR UPPER(A.VWASTEID) LIKE '%'||UPPER(q.wasteId)||'%') " 
+ "  AND (q.wasteType IS NULL OR A.VWASTETYPE LIKE '%'||q.wasteType||'%' ) " 
+ "  AND (q.status IS NULL OR A.vstatus = q.status) " 
+ "  AND (q.wasteDesc IS NULL OR upper(A.vwastedesc) LIKE '%' || UPPER(q.wasteDesc) || '%' ) " 
+ "  AND (q.entryNote IS NULL OR A.ventrynt LIKE '%' || q.entryNote || '%' ) " 
+ "  AND (q.entryWeight IS NULL OR A.ventrywght LIKE '%' || q.entryWeight || '%') " ;
    
    public static final String QPRINT  = 
  " SELECT " 
+ "  A.VWASTEID, " 
+ "  A.VWASTEDESC, " 
+ "  A.VWASTETYPE, " 
+ "  wu.VITEMNAME VWASTETYPEDESC, " 
+ "  A.VB3NONB3, " 
+ "  b3.VITEMNAME VB3NONB3DESC, " 
+ "  A.VWASTEVL, " 
+ "  wl.VITEMNAME VWASTEVLDESC, " 
+ "  A.VTRANSTYP, " 
+ "  A.VENTRYWGHT, " 
+ "  A.VENTRYNT, " 
+ "  A.VSTATUS, " 
+ "  tt.VITEMDESC VTRANSTYPDESC, " 
+ "  A.VOCNONOC, " 
+ "  A.VSTORAGELOC, " 
+ "  di.VITEMNAME VFDDISPLTRXDESC, " 
+ "  ws.VITEMNAME VWSCREQDESC, " 
+ "  pa.VITEMNAME VPACKAGEDESC, " 
+ "  nt.VITEMDESC VENTRYNTDESC, " 
+ "  ew.VITEMNAME VENTRYWGHTDESC, " 
+ "  A.NWGHTPPKG, " 
+ "  A.NWASTETL, " 
+ "  A.NSTORDUR, " 
+ "  tu.VITEMDESC VWBFLAGDESC, " 
+ "  A.VWASTEUNIT, " 
+ "  (SELECT ad.VITEMNAME FROM AHMMOERP_DTLSETTINGS ad WHERE ad.RSET_VID = 'AHMGAWMS_VSCFLAG' AND ad.VITEMCODE = A.VSCFLAG) AS VSCFLAGDESC, " 
+ "  C.VRGLCODE, " 
+ "  C.VRGLCODEDSC, " 
+ "  C.VPLANTID, " 
+ "  C.VTPSID, " 
+ "  C.VWAHOSLOCID, " 
+ "  C.NSTORCAP, " 
+ "  s.VITEMNAME VSTATUSDESC, " 
+ "  COALESCE(A.DMODI,A.DACTIVE) DMOD, " 
+ "  A.DACTIVE, " 
+ "  C.DINACTIVE, " 
+ "  B.VNAMA VMOD, " 
+ "  A.VPACKGEFLG \"packgeFlg\" , " 
+ "  pf.VITEMNAME \"packgeFlgDesc\", " 
+ "  cw.LISTCHARWASTE, " 
+ "  cw.LISTCHARWASTEDESC " 
+ "FROM AHMGAERS_MSTWASTES A " 
+ "JOIN (  " 
+ "    SELECT " 
+ "      NULLIF(TO_CHAR(:wasteId),'')   wasteId " 
+ "    , NULLIF(TO_CHAR(:wasteType),'') wasteType " 
+ "    , NULLIF(TO_CHAR(:status),'')    status " 
+ "    , NULLIF(TO_CHAR(:wasteDesc),'') wasteDesc " 
+ "    , NULLIF(TO_CHAR(:entryNote),'') entryNote " 
+ "    , NULLIF(TO_CHAR(:entryWeight), '') entryWeight  " 
+ "    FROM dual " 
+ "   ) q ON 1=1  " 
+ "LEFT JOIN AHMMOERP_MSTKARYAWANS B ON TO_CHAR(B.IIDNRP) = COALESCE(A.VMODI, A.VCREA) " 
+ "LEFT JOIN AHMGAERS_MSTWASTHIS H ON (lower(A.VSTATUS) = 'inactive') AND (A.VWASTEID = H.VWASTEID AND A.DACTIVE = H.DACTIVE) " 
+ "LEFT JOIN AHMGAERS_MSTDTLWASTES C " 
+ "       ON C.VWASTEID = A.VWASTEID " 
+ "      AND (   (lower(A.VSTATUS) = 'active' AND C.DINACTIVE = TO_DATE('99991231', 'YYYYMMDD')) " 
+ "           OR (H.DINACTIVE IS NOT NULL AND H.DINACTIVE = C.DINACTIVE )) " 
+ "LEFT JOIN ( " 
+ "    SELECT vwasteid, dinactivE,  listAGG(am.VCHARWASTE,', ') within group (order by am.VCHARWASTE desc) listcharwaste, LISTAGG(dc.VITEMDESC,', ') WITHIN GROUP (ORDER BY am.VCHARWASTE desc) LISTCHARWASTEDESC " 
+ "    FROM AHMGAERS_MSTCHARWASTES am " 
+ "    JOIN AHMMOERP_DTLSETTINGS dc ON  dc.RSET_VID = 'AHMGAWMS_WASTE_CHAR' AND am.VCHARWASTE = dc.VITEMCODE   " 
+ "    GROUP BY vwasteid, dinactive " 
+ ") cw ON cw.VWASTEID = A.VWASTEID  "
+ "    AND (   cw.dinactive = TO_DATE('99991231',  'YYYYMMDD') " 
+ "         OR (h.dinactive IS NOT NULL and cw.dinactive = h.dinactive)) " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS wu ON wu.RSET_VID = 'AHMGAWMS_WUJUD'     AND wu.VITEMCODE = A.VWASTETYPE " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS b3 ON b3.RSET_VID = 'AHMGAWMS_B3NONB3'   AND b3.VITEMCODE = A.VB3NONB3 " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS wl ON wl.RSET_VID = 'AHMGAWMS_WASTEVL'   AND wl.VITEMCODE = A.VWASTEVL " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS tt ON tt.RSET_VID = 'AHMGAWMS_TRANSTYP'  AND tt.VITEMCODE = A.VTRANSTYP " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS di on di.RSET_VID = 'AHMGAWMS_FDDISPLTRX'AND di.VITEMCODE = A.VFDDISPLTRX " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS ws ON ws.RSET_VID = 'AHMGAWMS_WSCREQ'    AND ws.VITEMCODE = A.VWSCREQ " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS pa on pa.RSET_VID = 'AHMGAWMS_PACKAGE'   AND pa.VITEMCODE = A.VPACKAGE " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS nt ON nt.RSET_VID = 'AHMGAWMS_ENTRYNT'   AND nt.VITEMCODE = A.VENTRYNT " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS ew ON ew.RSET_VID = 'AHMGAWMS_ENTRYWGHT' AND ew.VITEMCODE = A.VENTRYWGHT " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS S  ON s.RSET_VID  = 'AHMGAWMS_ERS005_STS'AND s.VITEMCODE = A.VSTATUS " 
+ "LEFT JOIN AHMMOERP_DTLSETTINGS tu on tu.RSET_VID = 'AHMGAWMS_TRANS_UNIT' AND tu.VITEMCODE = A.VWBFLAG " 
+ "LEFT JOIN aHMMOERP_DTLSETTINGS pf ON pf.RSET_VID = 'AHMGAWMS_PACKAGEFLG' AND pf.VITEMCODE = A.VPACKGEFLG " 
+ "WHERE (q.wasteId IS NULL OR UPPER(A.VWASTEID) LIKE '%'||UPPER(q.wasteId)||'%') " 
+ "  AND (q.wasteType IS NULL OR A.VWASTETYPE LIKE '%'||q.wasteType||'%' ) " 
+ "  AND (q.status IS NULL OR A.vstatus = q.status) " 
+ "  AND (q.wasteDesc IS NULL OR upper(A.vwastedesc) LIKE '%' || UPPER(q.wasteDesc) || '%' ) " 
+ "  AND (q.entryNote IS NULL OR A.ventrynt LIKE '%' || q.entryNote || '%' ) " 
+ "  AND (q.entryWeight IS NULL OR A.ventrywght LIKE '%' || q.entryWeight || '%') " ;
    public static final String Qparam = "wasteId wasteType status wasteDesc entryNote entryWeight"; 
}
