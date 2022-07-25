package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.AhmgaersMstdtlwastes;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWasteDetail;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWaste;
import id.co.ahm.ga.vms.app022.vo.Vms022VoRegulation;
import id.co.ahm.jxf.dao.GenericDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.Date;
import java.util.List;

/**
 *
 * @author teguh
 */
public interface Vms022AhmgaersMstdtlwastesDao extends GenericDao<AhmgaersMstdtlwastes, String> {

    public Vms022VoWasteDetail getDataById(String wasteId, Integer seq);

    public List<Vms022VoWasteDetail> search(DtoParamPaging dtoParamPaging);

    public Integer getDetailSequence(String wasteId);

    public List<Vms022VoWasteDetail> viewDetail(DtoParamPaging dtoParamPaging);

    public Integer countViewDetail(DtoParamPaging dtoParamPaging);

    public AhmgaersMstdtlwastes findOne(String wasteId, Date inactiveDate, Integer seq);

    public List<AhmgaersMstdtlwastes> find(String wasteId);

    public Vms022VoRegulation getRegulationsWaste(String wasteId);
}
