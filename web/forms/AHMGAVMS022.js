//[Begin] Variable Declaration
var ahmgavms022_url_root = 'req/ahmgavms022/jx05/ahmgavms000-psh/rest/ga/vms022';

var ahmgavms022_roles = "";
var ahmgavms022_token = "";
var ahmgavms022_userid = "";
var ahmgavms022_username = "";
var ahmgavms022_email = "";

var ahmgavms022_list_datatable_total_data;

var ahmgavms022_list_datatable = null;

var ahmgavms022_order_global = null;
var ahmgavms022_sort_global = null;

var ahmgavms022_list_datatable_check = null;

var ahmgavms022_firstScan = 0;

var ahmgavms022_list_datatable_check_array = [];
var ahmgavms022_list_datatable_check_outstatus_array = [];
var ahmgavms022_list_datatable_check_note_array = [];
var ahmgavms022_list_datatable_check_outid_array = [];
var ahmgavms022_list_datatable_check_outname_array = [];

var ahmgavms022_list_datatable_check_submit_array = [];
var ahmgavms022_list_datatable_check_submit_outid_array = [];
var ahmgavms022_list_datatable_check_submit_outname_array = [];
var ahmgavms022_list_datatable_check_submit_outstatus_array = [];
var ahmgavms022_list_datatable_check_submit_note_array = [];
var ahmgavms022_list_datatable_current_page = null;

var ahmgavms022_detail_plants_datatable = [];
var ahmgavms022_detail_plants_datatable_data = {};
var ahmgavms022_detail_gates_datatable = [];
var ahmgavms022_detail_gates_datatable_data = {};
var ahmgavms022_detail_pic_datatable = [];
var ahmgavms022_detail_pic_datatable_data = {};
var ahmgavms022_detail_status_submit = "";

var ahmgavms022_detail_list_id = [];
var ahmgavms022_detail_list_code = "";

var ahmgavms022_Ktp_Temp = null;
var ahmgavms022_Photo_Temp = null;
var ahmgavms022_Vac_array_Temp = null;
var ahmgavms022_Attach_array_Temp = null;

var ahmgavms022_form_auth_detail = false;

var ahmgavms022_form_auth_add = false;
var ahmgavms022_form_auth_edit = false;
var ahmgavms022_form_auth_delete = false;

var ahmgavms022_default_requirement = '';
var ahmgavms022_detail_rownum_getter = 0;
var ahmgavms022_detail_rownum_getter_from_pages = 0;
var ahmgavms022_detail_param_getter = new Object();
var selectedCheckboxId = [];

var detail_id_for_confirm = '';
var detail_outid_for_confirm = '';
var detail_outname_for_confirm = '';
var detail_outstatus_for_confirm = '';
var detail_note_for_confirm = '';
var detail_company_for_confirm = '';
var detail_department_for_confirm = '';

var approve_reject_confirmation = '';
var formObject = $('#ahmgavms022_form');

var ahmgavms022_ = new Object();
var data = [];
var ahmgavms022_detail_old_expiry_date;
var ahmgavms022_excel_order_col;
var ahmgavms022_excel_order;


var htmlCheckboxPicDisabled = `<div class="input-control checkbox" style="margin-left: 0px!important">
    <label>
        <input type="checkbox" name="chkItem"
            onclick="_fw_table_check(this); ahmgavms022_list_check_action(this)" 
            id="{id}" 
            data-id="{id}" 
            outstat="{outStatus}"
            outId="{OutId}"
            rownum="{rowNum}"
            outName="{OutName}"
            note="{Note}"
            disabled
            >
        <span class="check"></span>
        </input>
    </label>
</div>`;

var htmlCheckboxPic = `<div class="input-control checkbox" style="margin-left: 0px!important">
    <label>
        <input type="checkbox" name="chkItem"
            onclick="_fw_table_check(this); ahmgavms022_list_check_action(this)" 
            id="{id}" 
            data-id="{id}" 
            outstat="{outStatus}"
            outId="{OutId}"
            rownum="{rowNum}"
            outName="{OutName}"
            note="{Note}"
            >
        <span class="check"></span>
        </input>
    </label>
</div>`;

var htmlTablePic = `<thead>
    <tr>
        <th class="tblHeader" data-field-id="checkbox"></th>
        <th>ID Hide</th>
        <th>Outsource ID</th>
        <th>Outsource Name</th>
        <th>NIK</th>
        <th>Outsource Type</th>
        <th>Outsource Type Hide</th>
        <th>Outsource Company Hide</th>
        <th>Outsource Company</th>
        <th>Outsource Status</th>
        <th>Plant Hide</th>
        <th>Plant</th>
        <th>Access Reader Hide</th>
        <th>Canteen Hide</th>
        <th>Security Gate Hide</th>
        <th>Covid19 Vaccine Status</th>
        <th>Covid19 Vaccine Last Type Hide</th>
        <th>Covid19 Vaccine Last Date Hide</th>
        <th>Begin Work Effective Date</th>
        <th>End Work Effective Date</th>
        <th>Pass Card Number</th>
        <th>Pass Card Expiry Date</th>
        <th>Phone Number Hide</th>
        <th>Vaccine Summary Hide</th>
        <th>Note Hide</th>
        <th>Note Vaccine Hide</th>
        <th>Modified By</th>
        <th>Modified Date</th>
        <th>Pas Foto Hide</th>
        <th>Action</th>
        <th class="tblHeader" data-field-id="no">No</th>
    </tr>
</thead>
<tbody></tbody>`;

var htmlTableSec = `<thead>
    <tr>
        <th>ID Hide</th>
        <th>Outsource ID</th>
        <th>Outsource Name</th>
        <th>NIK</th>
        <th>Outsource Type</th>
        <th>Outsource Type Hide</th>
        <th>Outsource Company Hide</th>
        <th>Outsource Company</th>
        <th>Outsource Status</th>
        <th>Plant Hide</th>
        <th>Plant</th>
        <th>Access Reader Hide</th>
        <th>Canteen Hide</th>
        <th>Security Gate Hide</th>
        <th>Covid19 Vaccine Status</th>
        <th>Covid19 Vaccine Last Type Hide</th>
        <th>Covid19 Vaccine Last Date Hide</th>
        <th>Begin Work Effective Date</th>
        <th>End Work Effective Date</th>
        <th>Pass Card Number</th>
        <th>Pass Card Expiry Date</th>
        <th>Phone Number Hide</th>
        <th>Vaccine Summary Hide</th>
        <th>Note Hide</th>
        <th>Note Vaccine Hide</th>
        <th>Modified By</th>
        <th>Modified Date</th>
        <th>Pas Foto Hide</th>
        <th>Action</th>
        <th class="tblHeader" data-field-id="no">No</th>
    </tr>
</thead>
<tbody></tbody>`;

var htmlCheckboxSec = `<div class="input-control checkbox" style="margin-left: 0px!important">
</div>`;

ahmgavms022_.data = {};
//[End] Variable Declaration

//=========== document.ready begin ===========
ahmgavms022_getUserDetail();

var thisform = document.getElementById("ahmgavms022form");
ahmgavms022_list_datatable_check = null;
_fw_filterpanel_toggle($('#ahmgavms022_filter_collapse_button'));
// var objectObj = new Object();

const tudey = new Date();
const yyyy = tudey.getFullYear();
var mm = tudey.getMonth() + 1;
var dd = tudey.getDate();

if (dd < 10) dd = '0' + dd;
if (mm < 10) mm = '0' + mm;

const dateForStatus = dd + '-' + mm + '-' + yyyy;

// [Begin] for set date in filter session
Date.prototype.toShortFormat = function () {
    let monthNames = ["Jan", "Feb", "Mar", "Apr",
        "May", "Jun", "Jul", "Aug",
        "Sep", "Oct", "Nov", "Dec"];

    let day = this.getDate();

    let monthIndex = this.getMonth();
    let monthName = monthNames[monthIndex];

    let year = this.getFullYear();

    return `${day}-${monthName}-${year}`;

}
var today = new Date();
var date_temp = new Date(today.getFullYear(), today.getMonth() + 1, 0);
var date_temp_string = date_temp.toString();
var get_last_date = date_temp_string.substring(8, 10);

var ahmgavms022_date_periode_default = new Date();
var ahmgavms022_date_to_default = new Date();

ahmgavms022_date_periode_default.setDate(1);
ahmgavms022_date_to_default.setDate(get_last_date);

$('#ahmgavms022_filter_PeriodeFrom').val(ahmgavms022_date_periode_default.toShortFormat());
$('#ahmgavms022_filter_PeriodeTo').val(ahmgavms022_date_to_default.toShortFormat());
// [End] for set date in filter session

ahmgavms022_setPlantDropDown();

//=========== document.ready end ===========

function ahmgavms022_setPlantDropDown() {

    let tempVal = `<select name="ahmgavms022_filter_Plant" id="ahmgavms022_filter_Plant">
                        <option value="" selected="">-</option>`;

    vo = {};

    _fw_postJson($('#ahmgavms022_form'), vo, ahmgavms022_url_root + '/show-plant', function (ret) {
        if (ret.status === '1') {

            for (let x in ret.data) {
                tempVal += `<option value="` + ret.data[x].name + `">` + ret.data[x].name + `</option>`
            }
            tempVal += `</select>`;
        } else {
            `</select>`
        }
        $('#ahmgavms022_filter_Plant_div').html(tempVal);
    });
}

function ahmgavms022_getUserDetail() {

    catcher = {
    };

    _fw_postJson($('#ahmgavms022_form'), catcher, ahmgavms022_url_root + '/get-user-detail', function (ret) {
        if (ret.status === '1') {
            ahmgavms022_userid = ret.data.userid;
            ahmgavms022_username = ret.data.username;
            ahmgavms022_email = ret.data.email;
            $('#ahmgavms022_filter_nrpCatcher').val(ret.data.userid);
            ahmgavms022getToken();
        }
    });
}

function ahmgavms022getToken() {

    catcher = {
        username: ahmgavms022_username,
        email: ahmgavms022_email,
        userid: ahmgavms022_userid,
        reqdate: "18102016 104800"
    };

            ahmgavms022_token = "Q1Jv4n8WuT4rQYDevwbfRd9JMBEt327cNW9HZxU74hOyLibBybpujWXIxmJhFSNMCY9uQgNObYqYd/MO7droSBZpDU6b8N+Bn+UHWHKJ5sey7KaJq3O1e8u8j84cFb+EJl+gQ5ti6thsGqLVDeu9Qw==";
            ahmgavms022_get_form_authorization();

}

function ahmgavms022_get_form_authorization() {
    var formObject = $('#ahmgavms022_form');
    var data = JSON.stringify({});

    _fw_postJson(formObject, data, ahmgavms022_url_root + '/getformauth', function (ret) {

        if (ret.status === '1') {
            ahmgavms022_roles = ret.data[0].roleName;
        }

        if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
            document.getElementById("ahmgavms022_filter_OutStatus").selectedIndex = 1;

            $('#ahmgavms022_list_datatable').html(htmlTablePic); //for table
            $('#ahmgavms022_list_check').html(htmlCheckboxPic); //for checkbox
            $('#ahmgavms022_div_filter_OutType_Ofc').addClass('hide-this'); //hide ofc lov
            $('#ahmgavms022_div_picAhm').addClass('hide-this');

            ahmgavms022_list_datatable = ahmgavms022_list_datatable_init();

        } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
            $("#ahmgavms022_detail_ExpDate_button").removeAttr("disabled");
            $("#ahmgavms022_detail_ExpDate").removeAttr("disabled");
            $('#ahmgavms022_filter_approve_button').remove();
            $('#ahmgavms022_filter_reject_button').remove();
            document.getElementById("ahmgavms022_filter_OutStatus").selectedIndex = 2;

            $('#ahmgavms022_list_datatable').html(htmlTableSec); //for table
            $('#ahmgavms022_list_check').html(htmlCheckboxSec); //for checkbox
            $('#ahmgavms022_div_filter_OutType_Pic').addClass('hide-this'); //hide pic lov

            ahmgavms022_list_datatable = ahmgavms022_list_datatable_init_no_checkbox();

        } else if (ahmgavms022_roles == "RO_GAVMS_SCHSEC") {
            $('#ahmgavms022_filter_approve_button').remove();
            $('#ahmgavms022_filter_reject_button').remove();
            document.getElementById("ahmgavms022_filter_OutStatus").selectedIndex = 2;

            $('#ahmgavms022_list_datatable').html(htmlTableSec); //for table
            $('#ahmgavms022_list_check').html(htmlCheckboxSec); //for checkbox
            $('#ahmgavms022_div_filter_OutType_Pic').addClass('hide-this'); //hide pic lov

            ahmgavms022_list_datatable = ahmgavms022_list_datatable_init_no_checkbox();

        } else {
            _fw_setMessage(formObject, 0, '<ul class="errorList">Invalid Roles!</ul>');
            $('#ahmgavms022_list_datatable').remove();
            $('#ahmgavms022_filter_approve_button').remove();
            $('#ahmgavms022_filter_reject_button').remove();
            $('#ahmgavms022form').remove();
            return false;
        }

        $('ahmgavms022_filter_search_button').click();
    });
}

function ahmgavms022_list_datatable_init() {

    if (ahmgavms022_firstScan == 0) {
        //1
        var datatable = $('#ahmgavms022_list_datatable').DataTable({
            destroy: true,
            filter: false,
            ordering: true,
            scrollX: true,
            scrollCollapse: false,
            processing: true,
            serverSide: true,
            drawCallback: ahmgavms022_list_datatable_draw_callback,
            oLanguage: {
                'sZeroRecords': 'No data available in table',
                'sEmptyTable': 'No data available in table'
            },
            ajax: {
                type: 'POST',
                url: ahmgavms022_url_root + '/monitoring',
                contentType: 'application/json',
                complete: function () {

                    if ($('#ahmgavms022_list_datatable_wrapper').find('div.totalDataText').html() === 'No data found.') {
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyLiner').find('table').removeClass('ahmgavms022-right-border-1px').addClass('ahmgavms022-right-border-0px');
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyWrapper').removeClass('ahmgavms022-right-border-1px').addClass('ahmgavms022-right-border-0px');
                    }
                    else {
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyLiner').find('table').addClass('ahmgavms022-right-border-1px');
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyWrapper').addClass('ahmgavms022-right-border-1px');
                    }
                },
                data: function (d) {
                    var sortby = d.columns[d.order[0].column].data;
                    var order = d.order[0].dir;
                    ahmgavms022_excel_order_col = d.columns[d.order[0].column].data;
                    ahmgavms022_excel_order = d.order[0].dir;

                    var OutsourceIdFilter = $('#ahmgavms022_filter_OutId').val();
                    var OutsourceNameFilter = $('#ahmgavms022_filter_OutName').val();
                    var NikFilter = $('#ahmgavms022_filter_Nik').val();
                    var PeriodeFromFilter = $('#ahmgavms022_filter_PeriodeFrom').val();
                    var PeriodeToFilter = $('#ahmgavms022_filter_PeriodeTo').val();
                    var PassCardNumberFilter = $('#ahmgavms022_filter_PassCardNum').val();
                    var PicAhmFilter = $('#ahmgavms022_filter_IdPicAhm').val();

                    if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
                        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Pic').val();

                    } else {
                        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Ofc').val();

                    }

                    var OutsourceCompanyFilter = $('#ahmgavms022_filter_IdOutCompany').val();
                    var OutsourceStatusFilter = $('#ahmgavms022_filter_OutStatus').val();
                    var PlantFilter = $('#ahmgavms022_filter_Plant').val();
                    var Covid19VaccineStatusFilter = $('#ahmgavms022_filter_CovVacStat').val();

                    var objectSearch = new Object();
                    objectSearch.outId = OutsourceIdFilter;
                    objectSearch.outName = OutsourceNameFilter;
                    objectSearch.nik = NikFilter;
                    objectSearch.beginDate = PeriodeFromFilter;
                    objectSearch.endDate = PeriodeToFilter;
                    objectSearch.passNumber = PassCardNumberFilter;
                    objectSearch.pic = PicAhmFilter;
                    objectSearch.outType = OutsourceTypeFilter;
                    objectSearch.company = OutsourceCompanyFilter;
                    objectSearch.outStatus = OutsourceStatusFilter;
                    objectSearch.plant = PlantFilter;
                    objectSearch.vacStatus = Covid19VaccineStatusFilter;
                    objectSearch.userid = ahmgavms022_userid;
                    objectSearch.role = ahmgavms022_roles;

                    ahmgavms022_list_datatable_current_page = d.start;

                    ahmgavms022_detail_rownum_getter = 0;
                    ahmgavms022_detail_rownum_getter += d.start;

                    ahmgavms022_detail_param_getter = objectSearch;

                    ahmgavms022_order_global = order;
                    ahmgavms022_sort_global = sortby;

                    return JSON.stringify({
                        offset: d.start,
                        limit: d.length,
                        sort: sortby,
                        order: order,
                        search: objectSearch
                    });
                }
            },
            order: [[2, "desc"]],
            initComplete: function (settings, json) {
            },
            columns: [
                { data: null, name: 'cb', orderable: false },
                { data: 'id', name: 'id', visible: false, orderable: false },
                { data: 'outId', name: 'outId', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outName', name: 'outName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'persId', name: 'persId', className: 'dt-head-center dt-body-center', orderable: true },
                { data: 'outTypeName', name: 'outTypeName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outType', name: 'outType', visible: false, orderable: false },
                { data: 'company', name: 'company', visible: false, orderable: false },
                { data: 'companyName', name: 'companyName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outStatus', name: 'outStatus', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'area', name: 'area', visible: false, orderable: false },
                { data: 'areaName', name: 'areaName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'accessReader', name: 'accessReader', visible: false, orderable: false },
                { data: 'canteen', name: 'canteen', visible: false, orderable: false },
                { data: 'securityGate', name: 'securityGate', visible: false, orderable: false },
                { data: 'vacStatus', name: 'vacStatus', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'vacTypeName', name: 'vacTypeName', visible: false, orderable: false },
                { data: 'vacDateText', name: 'vacDateText', visible: false, orderable: false },
                { data: 'beginDateText', name: 'beginDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'endDateText', name: 'endDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'passNumber', name: 'passNumber', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'passExpiryDateText', name: 'passExpiryDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'phoneNo', name: 'phoneNo', visible: false, orderable: false },
                { data: 'note', name: 'note', visible: false, orderable: false },
                { data: 'vacSummary', name: 'vacSummary', visible: false, orderable: false },
                { data: 'vacNote', name: 'vacNote', visible: false, orderable: false },
                { data: 'modifyBy', name: 'modifyBy', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'modifyDateText', name: 'modifyDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'filePhoto', name: 'filePhoto', visible: false, orderable: false },
                { name: 'action', className: 'dt-head-center dt-body-center', orderable: false },
                { name: 'rowNum', data: 'rowNum', orderable: false, className: 'dt-head-center dt-body-right', visible: false, orderable: false },
            ],
            createdRow: function (row, data, dataIndex) {
                $(row).find('td:eq(0)').attr('data-title', 'outId');
                $(row).find('td:eq(1)').attr('data-title', 'outName');
                $(row).find('td:eq(2)').attr('data-title', 'persId');
                $(row).find('td:eq(3)').attr('data-title', 'outType');
                $(row).find('td:eq(4)').attr('data-title', 'company');
                $(row).find('td:eq(5)').attr('data-title', 'outStatus');
                $(row).find('td:eq(6)').attr('data-title', 'areaName');
                $(row).find('td:eq(7)').attr('data-title', 'vacStatus');
                $(row).find('td:eq(8)').attr('data-title', 'beginDateText');
                $(row).find('td:eq(9)').attr('data-title', 'endDateText');
                $(row).find('td:eq(10)').attr('data-title', 'passNumber');
                $(row).find('td:eq(11)').attr('data-title', 'passExpiryDateText');
                $(row).find('td:eq(12)').attr('data-title', 'modifyBy');
                $(row).find('td:eq(13)').attr('data-title', 'modifyDateText');
                $(row).find('td:eq(14)').attr('data-title', 'action');
            },
            columnDefs: [
                {
                    targets: 0,
                    searchable: false,
                    orderable: false,
                    responsivePriority: 1,
                    className: 'tblData checks',
                    render: function (data, type, full, meta) {

                        if ($("#ahmgavms022_list_check").hasClass('hide-this')) {
                            $("#ahmgavms022_list_check").removeClass('hide-this');
                        }


                        if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
                            if (data.outStatus.toUpperCase() == "WAITING FOR APPROVAL PIC") {
                                $('#ahmgavms022_list_check').html(htmlCheckboxPic); //for checkbox
                                return $("#ahmgavms022_list_check").clone().html().replaceAll("{id}", data.id).replaceAll("{outStatus}", data.outStatus).replaceAll("{rowNum}", data.rowNum).replaceAll("{Note}", data.note).replaceAll("{OutId}", data.outId).replaceAll("{OutName}", data.outName);
                            } else {
                                $('#ahmgavms022_list_check').html(htmlCheckboxPicDisabled); //for checkbox
                                return $("#ahmgavms022_list_check").clone().html().replaceAll("{id}", data.id).replaceAll("{outStatus}", data.outStatus).replaceAll("{rowNum}", data.rowNum).replaceAll("{Note}", data.note).replaceAll("{OutId}", data.outId).replaceAll("{OutName}", data.outName);
                            }
                        } else {
                            $('#ahmgavms022_list_check').html(htmlCheckboxPicDisabled); //for checkbox
                            return $("#ahmgavms022_list_check").clone().html().replaceAll("{id}", data.id).replaceAll("{outStatus}", data.outStatus).replaceAll("{rowNum}", data.rowNum).replaceAll("{Note}", data.note).replaceAll("{OutId}", data.outId).replaceAll("{OutName}", data.outName);
                        }
                    }
                },
                {
                    targets: 29,
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        var dataTableButtonHtml = '';

                        dataTableButtonHtml = dataTableButtonHtml + '<a class="linkedit button bg-transparent fg-darkCyan" href="#" onclick="ahmgavms022_detail_button_action(this)"></span> <span class="actionlink-title">Respond</span></a>';

                        return dataTableButtonHtml;
                    }
                },
                {
                    targets: 30,
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        return (meta.row + 1 + ahmgavms022_list_datatable_current_page).toLocaleString('en');
                    }
                }
            ],
            language: {
                processing: 'Please wait ...',
                select: {
                    rows: ' %d row selected'
                }
            }
        });

    } else {
        //2
        var datatable = $('#ahmgavms022_list_datatable').DataTable({
            destroy: true,
            filter: false,
            ordering: true,
            scrollX: true,
            scrollCollapse: false,
            processing: true,
            serverSide: true,
            drawCallback: ahmgavms022_list_datatable_draw_callback,
            oLanguage: {
                'sZeroRecords': 'No data available in table',
                'sEmptyTable': 'No data available in table'
            },
            ajax: {
                type: 'POST',
                url: ahmgavms022_url_root + '/monitoring',
                contentType: 'application/json',
                complete: function () {

                    if ($('#ahmgavms022_list_datatable_wrapper').find('div.totalDataText').html() === 'No data found.') {
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyLiner').find('table').removeClass('ahmgavms022-right-border-1px').addClass('ahmgavms022-right-border-0px');
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyWrapper').removeClass('ahmgavms022-right-border-1px').addClass('ahmgavms022-right-border-0px');
                    }
                    else {
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyLiner').find('table').addClass('ahmgavms022-right-border-1px');
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyWrapper').addClass('ahmgavms022-right-border-1px');
                    }
                },
                data: function (d) {
                    var sortby = d.columns[d.order[0].column].data;
                    var order = d.order[0].dir;
                    ahmgavms022_excel_order_col = d.columns[d.order[0].column].data;
                    ahmgavms022_excel_order = d.order[0].dir;

                    var OutsourceIdFilter = $('#ahmgavms022_filter_OutId').val();
                    var OutsourceNameFilter = $('#ahmgavms022_filter_OutName').val();
                    var NikFilter = $('#ahmgavms022_filter_Nik').val();
                    var PeriodeFromFilter = $('#ahmgavms022_filter_PeriodeFrom').val();
                    var PeriodeToFilter = $('#ahmgavms022_filter_PeriodeTo').val();
                    var PassCardNumberFilter = $('#ahmgavms022_filter_PassCardNum').val();
                    var PicAhmFilter = $('#ahmgavms022_filter_IdPicAhm').val();

                    if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
                        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Pic').val();

                    } else {
                        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Ofc').val();

                    }

                    var OutsourceCompanyFilter = $('#ahmgavms022_filter_IdOutCompany').val();
                    var OutsourceStatusFilter = $('#ahmgavms022_filter_OutStatus').val();
                    var PlantFilter = $('#ahmgavms022_filter_Plant').val();
                    var Covid19VaccineStatusFilter = $('#ahmgavms022_filter_CovVacStat').val();

                    var objectSearch = new Object();
                    objectSearch.outId = OutsourceIdFilter;
                    objectSearch.outName = OutsourceNameFilter;
                    objectSearch.nik = NikFilter;
                    objectSearch.beginDate = PeriodeFromFilter;
                    objectSearch.endDate = PeriodeToFilter;
                    objectSearch.passNumber = PassCardNumberFilter;
                    objectSearch.pic = PicAhmFilter;
                    objectSearch.outType = OutsourceTypeFilter;
                    objectSearch.company = OutsourceCompanyFilter;
                    objectSearch.outStatus = OutsourceStatusFilter;
                    objectSearch.plant = PlantFilter;
                    objectSearch.vacStatus = Covid19VaccineStatusFilter;
                    objectSearch.userid = ahmgavms022_userid;
                    objectSearch.role = ahmgavms022_roles;

                    ahmgavms022_list_datatable_current_page = d.start;

                    ahmgavms022_detail_rownum_getter = 0;
                    ahmgavms022_detail_rownum_getter += d.start;

                    ahmgavms022_detail_param_getter = objectSearch;

                    ahmgavms022_order_global = order;
                    ahmgavms022_sort_global = sortby;

                    return JSON.stringify({
                        offset: d.start,
                        limit: d.length,
                        sort: sortby,
                        order: order,
                        search: objectSearch
                    });
                }
            },
            initComplete: function (settings, json) {
            },
            columns: [
                { data: null, name: 'cb', orderable: false },
                { data: 'id', name: 'id', visible: false, orderable: false },
                { data: 'outId', name: 'outId', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outName', name: 'outName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'persId', name: 'persId', className: 'dt-head-center dt-body-center', orderable: true },
                { data: 'outTypeName', name: 'outTypeName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outType', name: 'outType', visible: false, orderable: false },
                { data: 'company', name: 'company', visible: false, orderable: false },
                { data: 'companyName', name: 'companyName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outStatus', name: 'outStatus', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'area', name: 'area', visible: false, orderable: false },
                { data: 'areaName', name: 'areaName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'accessReader', name: 'accessReader', visible: false, orderable: false },
                { data: 'canteen', name: 'canteen', visible: false, orderable: false },
                { data: 'securityGate', name: 'securityGate', visible: false, orderable: false },
                { data: 'vacStatus', name: 'vacStatus', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'vacTypeName', name: 'vacTypeName', visible: false, orderable: false },
                { data: 'vacDateText', name: 'vacDateText', visible: false, orderable: false },
                { data: 'beginDateText', name: 'beginDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'endDateText', name: 'endDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'passNumber', name: 'passNumber', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'passExpiryDateText', name: 'passExpiryDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'phoneNo', name: 'phoneNo', visible: false, orderable: false },
                { data: 'note', name: 'note', visible: false, orderable: false },
                { data: 'vacSummary', name: 'vacSummary', visible: false, orderable: false },
                { data: 'vacNote', name: 'vacNote', visible: false, orderable: false },
                { data: 'modifyBy', name: 'modifyBy', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'modifyDateText', name: 'modifyDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'filePhoto', name: 'filePhoto', visible: false, orderable: false },
                { name: 'action', className: 'dt-head-center dt-body-center', orderable: false },
                { name: 'rowNum', data: 'rowNum', orderable: false, className: 'dt-head-center dt-body-right', visible: false, orderable: false },
            ],
            createdRow: function (row, data, dataIndex) {
                $(row).find('td:eq(0)').attr('data-title', 'outId');
                $(row).find('td:eq(1)').attr('data-title', 'outName');
                $(row).find('td:eq(2)').attr('data-title', 'persId');
                $(row).find('td:eq(3)').attr('data-title', 'outType');
                $(row).find('td:eq(4)').attr('data-title', 'company');
                $(row).find('td:eq(5)').attr('data-title', 'outStatus');
                $(row).find('td:eq(6)').attr('data-title', 'areaName');
                $(row).find('td:eq(7)').attr('data-title', 'vacStatus');
                $(row).find('td:eq(8)').attr('data-title', 'beginDateText');
                $(row).find('td:eq(9)').attr('data-title', 'endDateText');
                $(row).find('td:eq(10)').attr('data-title', 'passNumber');
                $(row).find('td:eq(11)').attr('data-title', 'passExpiryDateText');
                $(row).find('td:eq(12)').attr('data-title', 'modifyBy');
                $(row).find('td:eq(13)').attr('data-title', 'modifyDateText');
                $(row).find('td:eq(14)').attr('data-title', 'action');
            },
            columnDefs: [
                {
                    targets: 0,
                    searchable: false,
                    orderable: false,
                    responsivePriority: 1,
                    className: 'tblData checks',
                    render: function (data, type, full, meta) {
                        if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
                            $('#' + data.id + '').removeAttr("disabled");
                        }
                        return $("#ahmgavms022_list_check").clone().html().replaceAll("{id}", data.id).replaceAll("{outStatus}", data.outStatus).replaceAll("{rowNum}", data.rowNum).replaceAll("{Note}", data.note).replaceAll("{OutId}", data.outId).replaceAll("{OutName}", data.outName);
                    }
                },
                {
                    targets: 29,
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        var dataTableButtonHtml = '';

                        dataTableButtonHtml = dataTableButtonHtml + '<a class="linkedit button bg-transparent fg-darkCyan" href="#" onclick="ahmgavms022_detail_button_action(this)"></span> <span class="actionlink-title">Respond</span></a>';

                        return dataTableButtonHtml;
                    }
                },
                {
                    targets: 30,
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        return (meta.row + 1 + ahmgavms022_list_datatable_current_page).toLocaleString('en');
                    }
                }
            ],
            language: {
                processing: 'Please wait ...',
                select: {
                    rows: ' %d row selected'
                }
            }
        });

    }
    ahmgavms022_firstScan++;

    new $.fn.dataTable.FixedColumns(datatable, {
        leftColumns: 5,
        rightColumns: 3
    });

    return datatable;
}

function ahmgavms022_list_datatable_init_no_checkbox() {

    if (ahmgavms022_firstScan == 0) {
        var datatable = $('#ahmgavms022_list_datatable').DataTable({
            destroy: true,
            filter: false,
            ordering: true,
            scrollX: true,
            scrollCollapse: false,
            processing: true,
            serverSide: true,
            drawCallback: ahmgavms022_list_datatable_draw_callback,
            oLanguage: {
                'sZeroRecords': 'No data available in table',
                'sEmptyTable': 'No data available in table'
            },
            ajax: {
                type: 'POST',
                url: ahmgavms022_url_root + '/monitoring',
                contentType: 'application/json',
                complete: function () {

                    if ($('#ahmgavms022_list_datatable_wrapper').find('div.totalDataText').html() === 'No data found.') {
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyLiner').find('table').removeClass('ahmgavms022-right-border-1px').addClass('ahmgavms022-right-border-0px');
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyWrapper').removeClass('ahmgavms022-right-border-1px').addClass('ahmgavms022-right-border-0px');
                    }
                    else {
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyLiner').find('table').addClass('ahmgavms022-right-border-1px');
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyWrapper').addClass('ahmgavms022-right-border-1px');
                    }
                },
                data: function (d) {
                   
                    var sortby = d.columns[d.order[0].column].data;
                    var order = d.order[0].dir;
                    ahmgavms022_excel_order_col = d.columns[d.order[0].column].data;
                    ahmgavms022_excel_order = d.order[0].dir;

                    var OutsourceIdFilter = $('#ahmgavms022_filter_OutId').val();
                    var OutsourceNameFilter = $('#ahmgavms022_filter_OutName').val();
                    var NikFilter = $('#ahmgavms022_filter_Nik').val();
                    var PeriodeFromFilter = $('#ahmgavms022_filter_PeriodeFrom').val();
                    var PeriodeToFilter = $('#ahmgavms022_filter_PeriodeTo').val();
                    var PassCardNumberFilter = $('#ahmgavms022_filter_PassCardNum').val();
                    var PicAhmFilter = $('#ahmgavms022_filter_IdPicAhm').val();

                    if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
                        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Pic').val();

                    } else {
                        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Ofc').val();

                    }

                    var OutsourceCompanyFilter = $('#ahmgavms022_filter_IdOutCompany').val();
                    var OutsourceStatusFilter = $('#ahmgavms022_filter_OutStatus').val();
                    var PlantFilter = $('#ahmgavms022_filter_Plant').val();
                    var Covid19VaccineStatusFilter = $('#ahmgavms022_filter_CovVacStat').val();

                    var objectSearch = new Object();
                    objectSearch.outId = OutsourceIdFilter;
                    objectSearch.outName = OutsourceNameFilter;
                    objectSearch.nik = NikFilter;
                    objectSearch.beginDate = PeriodeFromFilter;
                    objectSearch.endDate = PeriodeToFilter;
                    objectSearch.passNumber = PassCardNumberFilter;
                    objectSearch.pic = PicAhmFilter;
                    objectSearch.outType = OutsourceTypeFilter;
                    objectSearch.company = OutsourceCompanyFilter;
                    objectSearch.outStatus = OutsourceStatusFilter;
                    objectSearch.plant = PlantFilter;
                    objectSearch.vacStatus = Covid19VaccineStatusFilter;
                    objectSearch.userid = ahmgavms022_userid;
                    objectSearch.role = ahmgavms022_roles;

                    ahmgavms022_list_datatable_current_page = d.start;

                    ahmgavms022_detail_rownum_getter = 0;
                    ahmgavms022_detail_rownum_getter += d.start;

                    ahmgavms022_detail_param_getter = objectSearch;

                    ahmgavms022_order_global = order;
                    ahmgavms022_sort_global = sortby;

                    return JSON.stringify({
                        offset: d.start,
                        limit: d.length,
                        sort: sortby,
                        order: order,
                        search: objectSearch
                    });
                }
            },
            order: [[2, "desc"]],
            initComplete: function (settings, json) {
            },
            columns: [
                { data: 'id', name: 'id', visible: false, orderable: false },
                { data: 'outId', name: 'outId', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outName', name: 'outName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'persId', name: 'persId', className: 'dt-head-center dt-body-center', orderable: true },
                { data: 'outTypeName', name: 'outTypeName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outType', name: 'outType', visible: false, orderable: false },
                { data: 'company', name: 'company', visible: false, orderable: false },
                { data: 'companyName', name: 'companyName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outStatus', name: 'outStatus', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'area', name: 'area', visible: false, orderable: false },
                { data: 'areaName', name: 'areaName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'accessReader', name: 'accessReader', visible: false, orderable: false },
                { data: 'canteen', name: 'canteen', visible: false, orderable: false },
                { data: 'securityGate', name: 'securityGate', visible: false, orderable: false },
                { data: 'vacStatus', name: 'vacStatus', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'vacTypeName', name: 'vacTypeName', visible: false, orderable: false },
                { data: 'vacDateText', name: 'vacDateText', visible: false, orderable: false },
                { data: 'beginDateText', name: 'beginDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'endDateText', name: 'endDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'passNumber', name: 'passNumber', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'passExpiryDateText', name: 'passExpiryDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'phoneNo', name: 'phoneNo', visible: false, orderable: false },
                { data: 'note', name: 'note', visible: false, orderable: false },
                { data: 'vacSummary', name: 'vacSummary', visible: false, orderable: false },
                { data: 'vacNote', name: 'vacNote', visible: false, orderable: false },
                { data: 'modifyBy', name: 'modifyBy', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'modifyDateText', name: 'modifyDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'filePhoto', name: 'filePhoto', visible: false, orderable: false },
                { name: 'action', className: 'dt-head-center dt-body-center', orderable: false },
                { name: 'rowNum', data: 'rowNum', orderable: false, className: 'dt-head-center dt-body-right', visible: false, orderable: false },
            ],
            createdRow: function (row, data, dataIndex) {
                $(row).find('td:eq(0)').attr('data-title', 'outId');
                $(row).find('td:eq(1)').attr('data-title', 'outName');
                $(row).find('td:eq(2)').attr('data-title', 'persId');
                $(row).find('td:eq(3)').attr('data-title', 'outType');
                $(row).find('td:eq(4)').attr('data-title', 'company');
                $(row).find('td:eq(5)').attr('data-title', 'outStatus');
                $(row).find('td:eq(6)').attr('data-title', 'areaName');
                $(row).find('td:eq(7)').attr('data-title', 'vacStatus');
                $(row).find('td:eq(8)').attr('data-title', 'beginDateText');
                $(row).find('td:eq(9)').attr('data-title', 'endDateText');
                $(row).find('td:eq(10)').attr('data-title', 'passNumber');
                $(row).find('td:eq(11)').attr('data-title', 'passExpiryDateText');
                $(row).find('td:eq(12)').attr('data-title', 'modifyBy');
                $(row).find('td:eq(13)').attr('data-title', 'modifyDateText');
                $(row).find('td:eq(14)').attr('data-title', 'action');
            },
            columnDefs: [
                {
                    targets: 28,
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        var dataTableButtonHtml = '';

                        dataTableButtonHtml = dataTableButtonHtml + '<a class="linkedit button bg-transparent fg-darkCyan" href="#" onclick="ahmgavms022_detail_button_action(this)"></span> <span class="actionlink-title">Respond</span></a>';

                        return dataTableButtonHtml;
                    }
                },
                {
                    targets: 29,
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        return (meta.row + 1 + ahmgavms022_list_datatable_current_page).toLocaleString('en');
                    }
                }
            ],
            language: {
                processing: 'Please wait ...',
                select: {
                    rows: ' %d row selected'
                }
            }
        });

    } else {
        var datatable = $('#ahmgavms022_list_datatable').DataTable({
            destroy: true,
            filter: false,
            ordering: true,
            scrollX: true,
            scrollCollapse: false,
            processing: true,
            serverSide: true,
            drawCallback: ahmgavms022_list_datatable_draw_callback,
            oLanguage: {
                'sZeroRecords': 'No data available in table',
                'sEmptyTable': 'No data available in table'
            },
            ajax: {
                type: 'POST',
                url: ahmgavms022_url_root + '/monitoring',
                contentType: 'application/json',
                complete: function () {

                    if ($('#ahmgavms022_list_datatable_wrapper').find('div.totalDataText').html() === 'No data found.') {
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyLiner').find('table').removeClass('ahmgavms022-right-border-1px').addClass('ahmgavms022-right-border-0px');
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyWrapper').removeClass('ahmgavms022-right-border-1px').addClass('ahmgavms022-right-border-0px');
                    }
                    else {
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyLiner').find('table').addClass('ahmgavms022-right-border-1px');
                        $('#ahmgavms022_list_datatable_wrapper div.DTFC_LeftBodyWrapper').addClass('ahmgavms022-right-border-1px');
                    }
                },
                data: function (d) {

                    var sortby = d.columns[d.order[0].column].data;
                    var order = d.order[0].dir;
                    ahmgavms022_excel_order_col = d.columns[d.order[0].column].data;
                    ahmgavms022_excel_order = d.order[0].dir;

                    var OutsourceIdFilter = $('#ahmgavms022_filter_OutId').val();
                    var OutsourceNameFilter = $('#ahmgavms022_filter_OutName').val();
                    var NikFilter = $('#ahmgavms022_filter_Nik').val();
                    var PeriodeFromFilter = $('#ahmgavms022_filter_PeriodeFrom').val();
                    var PeriodeToFilter = $('#ahmgavms022_filter_PeriodeTo').val();
                    var PassCardNumberFilter = $('#ahmgavms022_filter_PassCardNum').val();
                    var PicAhmFilter = $('#ahmgavms022_filter_IdPicAhm').val();

                    if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
                        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Pic').val();

                    } else {
                        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Ofc').val();

                    }

                    var OutsourceCompanyFilter = $('#ahmgavms022_filter_IdOutCompany').val();
                    var OutsourceStatusFilter = $('#ahmgavms022_filter_OutStatus').val();
                    var PlantFilter = $('#ahmgavms022_filter_Plant').val();
                    var Covid19VaccineStatusFilter = $('#ahmgavms022_filter_CovVacStat').val();

                    var objectSearch = new Object();
                    objectSearch.outId = OutsourceIdFilter;
                    objectSearch.outName = OutsourceNameFilter;
                    objectSearch.nik = NikFilter;
                    objectSearch.beginDate = PeriodeFromFilter;
                    objectSearch.endDate = PeriodeToFilter;
                    objectSearch.passNumber = PassCardNumberFilter;
                    objectSearch.pic = PicAhmFilter;
                    objectSearch.outType = OutsourceTypeFilter;
                    objectSearch.company = OutsourceCompanyFilter;
                    objectSearch.outStatus = OutsourceStatusFilter;
                    objectSearch.plant = PlantFilter;
                    objectSearch.vacStatus = Covid19VaccineStatusFilter;
                    objectSearch.userid = ahmgavms022_userid;
                    objectSearch.role = ahmgavms022_roles;

                    ahmgavms022_list_datatable_current_page = d.start;

                    ahmgavms022_detail_rownum_getter = 0;
                    ahmgavms022_detail_rownum_getter += d.start;

                    ahmgavms022_detail_param_getter = objectSearch;

                    ahmgavms022_order_global = order;
                    ahmgavms022_sort_global = sortby;

                    return JSON.stringify({
                        offset: d.start,
                        limit: d.length,
                        sort: sortby,
                        order: order,
                        search: objectSearch
                    });
                }
            },
            initComplete: function (settings, json) {
            },
            columns: [
                { data: 'id', name: 'id', visible: false, orderable: false },
                { data: 'outId', name: 'outId', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outName', name: 'outName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'persId', name: 'persId', className: 'dt-head-center dt-body-center', orderable: true },
                { data: 'outTypeName', name: 'outTypeName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outType', name: 'outType', visible: false, orderable: false },
                { data: 'company', name: 'company', visible: false, orderable: false },
                { data: 'companyName', name: 'companyName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'outStatus', name: 'outStatus', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'area', name: 'area', visible: false, orderable: false },
                { data: 'areaName', name: 'areaName', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'accessReader', name: 'accessReader', visible: false, orderable: false },
                { data: 'canteen', name: 'canteen', visible: false, orderable: false },
                { data: 'securityGate', name: 'securityGate', visible: false, orderable: false },
                { data: 'vacStatus', name: 'vacStatus', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'vacTypeName', name: 'vacTypeName', visible: false, orderable: false },
                { data: 'vacDateText', name: 'vacDateText', visible: false, orderable: false },
                { data: 'beginDateText', name: 'beginDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'endDateText', name: 'endDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'passNumber', name: 'passNumber', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'passExpiryDateText', name: 'passExpiryDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'phoneNo', name: 'phoneNo', visible: false, orderable: false },
                { data: 'note', name: 'note', visible: false, orderable: false },
                { data: 'vacSummary', name: 'vacSummary', visible: false, orderable: false },
                { data: 'vacNote', name: 'vacNote', visible: false, orderable: false },
                { data: 'modifyBy', name: 'modifyBy', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'modifyDateText', name: 'modifyDateText', className: 'dt-head-center dt-body-left', orderable: true },
                { data: 'filePhoto', name: 'filePhoto', visible: false, orderable: false },
                { name: 'action', className: 'dt-head-center dt-body-center', orderable: false },
                { name: 'rowNum', data: 'rowNum', orderable: false, className: 'dt-head-center dt-body-right', visible: false, orderable: false },
            ],
            createdRow: function (row, data, dataIndex) {
                $(row).find('td:eq(0)').attr('data-title', 'outId');
                $(row).find('td:eq(1)').attr('data-title', 'outName');
                $(row).find('td:eq(2)').attr('data-title', 'persId');
                $(row).find('td:eq(3)').attr('data-title', 'outType');
                $(row).find('td:eq(4)').attr('data-title', 'company');
                $(row).find('td:eq(5)').attr('data-title', 'outStatus');
                $(row).find('td:eq(6)').attr('data-title', 'areaName');
                $(row).find('td:eq(7)').attr('data-title', 'vacStatus');
                $(row).find('td:eq(8)').attr('data-title', 'beginDateText');
                $(row).find('td:eq(9)').attr('data-title', 'endDateText');
                $(row).find('td:eq(10)').attr('data-title', 'passNumber');
                $(row).find('td:eq(11)').attr('data-title', 'passExpiryDateText');
                $(row).find('td:eq(12)').attr('data-title', 'modifyBy');
                $(row).find('td:eq(13)').attr('data-title', 'modifyDateText');
                $(row).find('td:eq(14)').attr('data-title', 'action');
            },
            columnDefs: [
                {
                    targets: 28,
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        var dataTableButtonHtml = '';

                        dataTableButtonHtml = dataTableButtonHtml + '<a class="linkedit button bg-transparent fg-darkCyan" href="#" onclick="ahmgavms022_detail_button_action(this)"></span> <span class="actionlink-title">Respond</span></a>';

                        return dataTableButtonHtml;
                    }
                },
                {
                    targets: 29,
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        return (meta.row + 1 + ahmgavms022_list_datatable_current_page).toLocaleString('en');
                    }
                }
            ],
            language: {
                processing: 'Please wait ...',
                select: {
                    rows: ' %d row selected'
                }
            }
        });

    }
    ahmgavms022_firstScan++;

    new $.fn.dataTable.FixedColumns(datatable, {
        leftColumns: 4,
        rightColumns: 3
    });

    return datatable;
}

function ahmgavms022_list_datatable_draw_callback(setting) {
    debugger;
    if (typeof setting != 'undefined') {
        var tbid = setting.sTableId,
            cpg = setting._iDisplayStart >= 0 ? (setting._iDisplayStart / setting._iDisplayLength) : 0,
            tpg = setting._iRecordsTotal,
            dpg = setting._iDisplayLength,
            totpg = Math.ceil(tpg / dpg),
            btnFirst = $('<a href="#" class="icon icon-first-2 button medium default"></a>&nbsp;').click(function (e) {
                e.stopPropagation();
                $('#' + $(this).closest('.tablePaging').attr('data-table-id')).dataTable().api().page('first').draw('page');
                return false;
            }),
            btnPrev = $('<a href="#" class="icon icon-arrow-left-4 button medium default"></a>&nbsp;').click(function (e) {
                e.stopPropagation();
                $('#' + $(this).closest('.tablePaging').attr('data-table-id')).dataTable().api().page('previous').draw('page');
                return false;
            }),
            btnNext = $('<a href="#" class="icon icon-arrow-right-4 button medium default"></a>&nbsp;').click(function (e) {
                e.stopPropagation();
                $('#' + $(this).closest('.tablePaging').attr('data-table-id')).dataTable().api().page('next').draw('page');
                return false;
            }),
            btnLast = $('<a href="#" class="icon icon-last-2 button medium default"></a>').click(function (e) {
                e.stopPropagation();

                $('#' + $(this).closest('.tablePaging').attr('data-table-id')).dataTable().api().page('last').draw('page');
                return false;
            }),
            opts = $('<div class="input-control select" data-role="input-control"><select name="selectPage"></select></div>&nbsp;').on('change', function () {
                $('#' + $(this).closest('.tablePaging').attr('data-table-id')).dataTable().api().page(parseInt($('option:selected', this).val(), 10)).draw('page');
                return false;
            }),
            npaging = $('<div class="tablePaging dataTables_customPaging" data-table-id="' + tbid + '"></div>'),
            ctx = $('#' + tbid).closest('.dataTables_wrapper'),
            ln = $('.tableButtons[data-table-id="' + tbid + '"]').parent();
        for (var i = 0; i < totpg; i++) {
            $('<option value="' + i + '" ' + (cpg == i ? 'selected="selected"' : '') + ' >Page ' + (i + 1) + '</option>').appendTo($('select', opts));
        }
        if (cpg > 0) {
            npaging
                .append(btnFirst)
                .append(btnPrev);
        }
        npaging.append(opts);
        if (cpg < totpg - 1) {
            npaging
                .append(btnNext)
                .append(btnLast);
        }
        $('.tablePaging,.dataTables_info,.dataTables_paginate,.dataTables_length', ctx).remove();
        if (tpg == NaN)
            tpg = 0;
        ctx.append(npaging);
        if (ln.length <= 0)
            ln = ctx;
        $('.tableButtons', ln).remove();

        var dataTableButtonHtml = '<div class="tableButtons" data-table-id="ahmgavms022_list_datatable">';
        dataTableButtonHtml = dataTableButtonHtml + '<div class="totalDataText">' + tpg + ' data found.</div>';

        dataTableButtonHtml = dataTableButtonHtml + '</div>';

        ln.prepend(dataTableButtonHtml);

        ahmgavms022_list_datatable_total_data = tpg;
        ahmgavms022_detail_rownum_getter_from_pages = setting._iDisplayStart;
    }
}

function ahmgavms022_filter_search_button_action(obj) {

    //[Begin] set detail properties
    document.getElementById("ahmgavms022_detail_approve_button").disabled = false;
    document.getElementById("ahmgavms022_detail_reject_button").disabled = false;

    if (!$('#ahmgavms022_detail_reject_button').hasClass('danger')) {
        $('#ahmgavms022_detail_reject_button').addClass('danger');
    }
    //[End] set detail properties

    //get data for search function
    var OutsourceIdFilter = $('#ahmgavms022_filter_OutId').val();
    var OutsourceNameFilter = $('#ahmgavms022_filter_OutName').val();
    var NikFilter = $('#ahmgavms022_filter_Nik').val();
    var PeriodeFromFilter = $('#ahmgavms022_filter_PeriodeFrom').val();
    var PeriodeToFilter = $('#ahmgavms022_filter_PeriodeTo').val();
    var PassCardNumberFilter = $('#ahmgavms022_filter_PassCardNum').val();
    var PicAhmFilter = $('#ahmgavms022_filter_IdPicAhm').val();
    if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Pic').val();

    } else {
        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Ofc').val();

    }
    var OutsourceCompanyFilter = $('#ahmgavms022_filter_IdOutCompany').val();
    var OutsourceStatusFilter = $('#ahmgavms022_filter_OutStatus').val();
    var PlantFilter = $('#ahmgavms022_filter_Plant').val();
    var Covid19VaccineStatusFilter = $('#ahmgavms022_filter_CovVacStat').val();

    var objectSearch = new Object();
    objectSearch.outId = OutsourceIdFilter;
    objectSearch.outName = OutsourceNameFilter;
    objectSearch.nik = NikFilter;
    objectSearch.beginDate = PeriodeFromFilter;
    objectSearch.endDate = PeriodeToFilter;
    objectSearch.passNumber = PassCardNumberFilter;
    objectSearch.pic = PicAhmFilter;
    objectSearch.outType = OutsourceTypeFilter;
    objectSearch.company = OutsourceCompanyFilter;
    objectSearch.outStatus = OutsourceStatusFilter;
    objectSearch.plant = PlantFilter;
    objectSearch.vacStatus = Covid19VaccineStatusFilter;
    objectSearch.userid = ahmgavms022_userid;
    objectSearch.role = ahmgavms022_roles;


    if (OutsourceIdFilter == "" && OutsourceNameFilter == ""
        && NikFilter == "" && PeriodeFromFilter == "" && PeriodeToFilter == ""
        && PassCardNumberFilter == "" && PicAhmFilter == ""
        && OutsourceTypeFilter == "" && OutsourceCompanyFilter == ""
        && OutsourceStatusFilter == "" && PlantFilter == ""
        && Covid19VaccineStatusFilter == "") {
        var errorMessage = '<li>At Least 1 Field Search Filled.</li>';
        _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
    } else {
        _fw_validation_clear(obj);

        var periodeFrom = $("#ahmgavms022_filter_PeriodeFrom").val();
        var periodevalidTo = $("#ahmgavms022_filter_PeriodeTo").val();

        if (periodeFrom != '' && periodevalidTo != '') {
            var from = moment(periodeFrom, "DD-MMM-YYYY").toDate();           
            var to = moment(periodevalidTo, "DD-MMM-YYYY").toDate();

            if (from.getTime() > to.getTime()) {               
                var errorMessage = '[Periode Date From] must be less or equal to [Periode Date To]';
                _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
                return false;
            }
        }

        //reset data called variable
        ahmgavms022_detail_rownum_getter_from_pages = 0;
        ahmgavms022_list_datatable_check_array = [];

        //reload table
        ahmgavms022_list_datatable.ajax.reload();

        _fw_filterpanel_toggle($('#ahmgavms022_filter_collapse_button'));
    }

    //reset data called variable
    ahmgavms022_list_datatable_check_array = [];
    ahmgavms022_list_datatable_check_outstatus_array = [];
    ahmgavms022_list_datatable_check_note_array = [];
    ahmgavms022_list_datatable_check_outid_array = [];
    ahmgavms022_list_datatable_check_outname_array = [];
}

//[Begin] Checkbox Table function
function ahmgavms022_list_check_action(obj) {

    if ($(obj).prop('checked')) {
        selectedCheckboxId = $(obj).attr('data-id');
        selectedCheckboxOutStat = $(obj).attr('rownum') + $(obj).attr('outstat');
        selectedCheckboxNote = $(obj).attr('rownum') + $(obj).attr('note');
        selectedCheckboxOutId = $(obj).attr('rownum') + $(obj).attr('outid');
        selectedCheckboxOutName = $(obj).attr('rownum') + $(obj).attr('outname');

        if(ahmgavms022_list_datatable_check_array.includes(selectedCheckboxId)) {

        } else {
            //set data to variable
            ahmgavms022_list_datatable_check_array.push(selectedCheckboxId);
            ahmgavms022_list_datatable_check_note_array.push(selectedCheckboxNote);
            ahmgavms022_list_datatable_check_outid_array.push(selectedCheckboxOutId);
            ahmgavms022_list_datatable_check_outname_array.push(selectedCheckboxOutName);
            ahmgavms022_list_datatable_check_outstatus_array.push(selectedCheckboxOutStat.toUpperCase());
        }


    } else {
        selectedCheckboxId = $(obj).attr('data-id');
        selectedCheckboxOutStat = $(obj).attr('rownum') + $(obj).attr('outstat');
        selectedCheckboxNote = $(obj).attr('rownum') + $(obj).attr('note');
        selectedCheckboxOutId = $(obj).attr('rownum') + $(obj).attr('outid');
        selectedCheckboxOutName = $(obj).attr('rownum') + $(obj).attr('outname');

        //filtering data
        var ahmgavms022_list_datatable_uncheck_temp = ahmgavms022_list_datatable_check_array.filter(ahmgavms022_uncheck_delete_list_value);
        var ahmgavms022_list_datatable_uncheck_note_temp = ahmgavms022_list_datatable_check_note_array.filter(ahmgavms022_uncheck_delete_list_note_value);
        var ahmgavms022_list_datatable_uncheck_outid_temp = ahmgavms022_list_datatable_check_outid_array.filter(ahmgavms022_uncheck_delete_list_outid_value);
        var ahmgavms022_list_datatable_uncheck_outname_temp = ahmgavms022_list_datatable_check_outname_array.filter(ahmgavms022_uncheck_delete_list_outname_value);
        var ahmgavms022_list_datatable_uncheck_outstatus_temp = ahmgavms022_list_datatable_check_outstatus_array.filter(ahmgavms022_uncheck_delete_list_outstatus_value);

        //set data to variable
        ahmgavms022_list_datatable_check_array = ahmgavms022_list_datatable_uncheck_temp;
        ahmgavms022_list_datatable_check_note_array = ahmgavms022_list_datatable_uncheck_note_temp;
        ahmgavms022_list_datatable_check_outid_array = ahmgavms022_list_datatable_uncheck_outid_temp;
        ahmgavms022_list_datatable_check_outname_array = ahmgavms022_list_datatable_uncheck_outname_temp;
        ahmgavms022_list_datatable_check_outstatus_array = ahmgavms022_list_datatable_uncheck_outstatus_temp;
    }

}

function ahmgavms022_uncheck_delete_list_value(id) {
    return (id != selectedCheckboxId);
}

function ahmgavms022_uncheck_delete_list_note_value(id) {
    return (id != selectedCheckboxNote);
}

function ahmgavms022_uncheck_delete_list_outid_value(id) {
    return (id != selectedCheckboxOutId);
}

function ahmgavms022_uncheck_delete_list_outname_value(id) {
    return (id != selectedCheckboxOutName);
}

function ahmgavms022_uncheck_delete_list_outstatus_value(id) {
    return (id != selectedCheckboxOutStat);
}
//[End] Checkbox Table function

//Approve & Reject from Main Page
function ahmgavms022_filter_approve_reject_button(obj, val) {
    //check any checkbox selected

    if (ahmgavms022_list_datatable_check_array.length == 0) {
        var errorMessage = '<li>At least must check 1 field</li>';
        _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
        return false;
    }

    if (!ahmgavms022_roles == "RO_GAVMS_PICAHM") {
        _fw_setMessage(formObject, 0, 'This Role cannot do this action!');
        return false;
    } else {

        ahmgavms022_list_datatable_check_submit_array = ahmgavms022_list_datatable_check_array;
        ahmgavms022_list_datatable_check_submit_note_array = ahmgavms022_list_datatable_check_note_array;
        ahmgavms022_list_datatable_check_submit_outid_array = ahmgavms022_list_datatable_check_outid_array;
        ahmgavms022_list_datatable_check_submit_outname_array = ahmgavms022_list_datatable_check_outname_array;
        ahmgavms022_list_datatable_check_submit_outstatus_array = ahmgavms022_list_datatable_check_outstatus_array;

        //check if selected checkbox has required validation or not
        for (let x in ahmgavms022_list_datatable_check_submit_array) {
            var getid = ahmgavms022_list_datatable_check_submit_outstatus_array[x].slice(1);

            if (getid.toUpperCase() != 'WAITING FOR APPROVAL PIC') {
                _fw_setMessage(formObject, 0, `Cannot Process Outsource Status data other than 'WAITING FOR APPROVAL PIC'`);
                return false;
            }
        }

        for (let x in ahmgavms022_list_datatable_check_submit_array) {
            $('#' + ahmgavms022_list_datatable_check_submit_array[x] + '').prop('checked', false);
        }

        var objFrame = $(obj).closest('.tab-frame');
        var newFrame = $(".sub-page[data-section='list-confirmation']", objFrame);
        var htmlVal = '';

        htmlVal = htmlVal + '<h2>The following data will be ' + val + '. Are you sure?</h2>';
        htmlVal = htmlVal + '<p>' + ahmgavms022_list_datatable_check_submit_array.length + ' Items selected</p>';

        //set the reject note textfield
        if (val == "Reject") {
            htmlVal += '<div class="input-control textarea" data-role="input-control">' +
                '<textarea name="ahmgavms022_reject_note" id="ahmgavms022_reject_note" type="text" placeholder="Insert The Reject Note" maxlength="100"></textarea>'
            '</div>';
        }

        approve_reject_confirmation = val;
        $('#ahmgavms022_h2_confirmation_list').html(htmlVal);
        _fw_formResetFields(newFrame);
        _fw_navigateSubPage(obj, 'list-confirmation');
    }
}

function ahmgavms022_detail_button_previous_action(obj) {

    ahmgavms022_detail_rownum_getter -= 1;

    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='detail']", objFrame);
    _fw_formResetFields(newFrame);

    if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
        $('#ahmgavms022_detail_ExpDate_button').show();
        document.getElementById("ahmgavms022_detail_ExpDate").disabled = false;
    }

    var ahmgavms022_detail_outType = "";
    var ahmgavms022_detail_area = "";

    document.getElementById("ahmgavms022_detail_approve_button").disabled = false;
    document.getElementById("ahmgavms022_detail_reject_button").disabled = false;

    if (!$('#ahmgavms022_detail_reject_button').hasClass('danger')) {
        $('#ahmgavms022_detail_reject_button').addClass('danger');
    }

    if (ahmgavms022_detail_rownum_getter < 1
        || ahmgavms022_detail_rownum_getter == 1) {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = true;
        document.getElementById("ahmgavms022_detail_button_next").disabled = false;
    } else {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = false;
        document.getElementById("ahmgavms022_detail_button_next").disabled = false;
    }

    if (ahmgavms022_detail_rownum_getter > ahmgavms022_list_datatable_total_data
        || ahmgavms022_detail_rownum_getter == ahmgavms022_list_datatable_total_data) {
        document.getElementById("ahmgavms022_detail_button_next").disabled = true;
    } else {
        document.getElementById("ahmgavms022_detail_button_next").disabled = false;
    }

    if (ahmgavms022_roles != "RO_GAVMS_OFCSECT") {
        $('#ahmgavms022_detail_ExpDate_button').remove();
        document.getElementById("ahmgavms022_detail_ExpDate").disabled = true;
    }

    $('#ahmgavms022p02ImageVacList').empty();
    $('#ahmgavms022p02ImageAttachList').empty();

    var jsonbody = JSON.stringify({
        sort: ahmgavms022_sort_global,
        order: ahmgavms022_order_global,
        offset: ahmgavms022_detail_rownum_getter - 1,
        limit: 1,
        search: ahmgavms022_detail_param_getter
    });

    //get data detail
    _fw_postJson(formObject, jsonbody, ahmgavms022_url_root + '/monitoring', function (ret) {
        if (ret.status === '1') {
            $.each(ret.data, function (index, item) {

                var supplier = item.supplier;

                if (supplier == "S") {
                    supplier = "Supplier";
                } else if (supplier == "N") {
                    supplier = "Non-Supplier"
                }

                $('#ahmgavms022_detail_OutId').val(item.outId);
                $('#ahmgavms022_detail_OutName').val(item.outName);
                $('#ahmgavms022_detail_PhoneOut').val(item.phoneNo);
                $('#ahmgavms022_detail_Nik').val(item.persId);
                $('#ahmgavms022_detail_OutType').val(item.outType);
                $('#ahmgavms022_detail_OutCompany').val(item.company);
                $('#ahmgavms022_detail_JobDetail').val(item.job);
                $('#ahmgavms022_detail_plant').val(item.areaName);
                $('#ahmgavms022_detail_Supplier').val(supplier);
                $('#ahmgavms022_detail_CovVacStatus').val(item.vacStatus);
                $('#ahmgavms022_detail_BeginEffDate').val(item.beginDateText);
                $('#ahmgavms022_detail_EndEffDate').val(item.endDateText);
                $('#ahmgavms022_detail_PassCardNum').val(item.passNumber);
                $('#ahmgavms022_detail_ExpDate').val(item.passExpiryDateText);
                $('#ahmgavms022_detail_CovSumVacStatus').val(item.vacSummary);
                $('#ahmgavms022_detail_note').val(item.note);
                $('#ahmgavms022_detail_NoteCovVacStatus').val(item.vacNote);
                $('#ahmgavms022_detail_CovLastVacDate').val(item.vacDateText);
                $('#ahmgavms022_detail_CovLastVacType').val(item.vacTypeName);


                if ($('#ahmgavms022_detail_CovVacStatus').val() == "ALREADY VACCINATED") {
                    $('#ahmgavms022_already_group').removeClass('hide-this');
                    $('#ahmgavms022_notYetVaccine_group').addClass('hide-this');
                } else if($('#ahmgavms022_detail_CovVacStatus').val() == "NOT YET VACCINATED") {
                    $('#ahmgavms022_notYetVaccine_group').removeClass('hide-this');
                    $('#ahmgavms022_already_group').addClass('hide-this');
                }else{
                    $('#ahmgavms022_notYetVaccine_group').addClass('hide-this');
                    $('#ahmgavms022_already_group').addClass('hide-this');
                }

                detail_id_for_confirm = item.id;
                detail_outid_for_confirm = item.outId;
                detail_outname_for_confirm = item.outName;
                detail_note_for_confirm = item.note;
                detail_department_for_confirm = item.outTypeName
                detail_company_for_confirm = item.companyName
                ahmgavms022_detail_old_expiry_date = item.passExpiryDateText;


                ahmgavms022_detail_status_submit = item.outStatus;

                $("#ahmgavms022_detail_AttachKtp_Preview").attr('src', "data:image/jpg;base64, " + item.fileKtp);
                $("#ahmgavms022p02PhotoPreview").attr('src', "data:image/jpg;base64, " + item.filePhoto);
                ahmgavms022_Photo_Temp = item.filePhoto;
                ahmgavms022_Ktp_Temp = item.fileKtp;

                if (item.fileVaccines != null) {
                    $('#ahmgavms022_image_vaccine_div').show();

                    var ahmgavms022_detail_filevaccine_array = item.fileVaccines;
                    ahmgavms022_Vac_array_Temp = item.fileVaccines;

                    $.each(ahmgavms022_detail_filevaccine_array, function (i, v) {

                        var html = "";

                        html += '<li class="containerimgvac">';
                        html += '    <img id="ahmgavms022p02ExistImage' + i + 'VacPreview" class="imgprevvac" src="data:image/jpg;base64,' + ahmgavms022_detail_filevaccine_array[i].name + '" alt="" />';
                        html += ' <div class="overlayvac"></div>';
                        html += ' <div class="col-sm-9 col-sm-offset-3 upload-file" data-allowedext="jpg, png, bmp, jpeg" style="display:none">';
                        html += '    <input id="ahmgavms022p02ExistImage' + i + 'Vac" type="file" accept=".jpg,.png,.bmp, .jpeg" class="form-control input-upload" onchange="return false;">';
                        html += ' </div>';
                        html += ' <div class="form-group-button" style="margin-top: 0px;">';
                        html += '    <div class="col-sm-12">';
                        html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'UploadBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-folder-open fg-white"></i></a>';
                        html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'PrevBtn" onclick="ahmgavms022_PreviewMultipleHandler(this, \'vaccine\', \'ahmgavms022p02ExistImage' + i + 'Vac\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
                        html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'DeleteBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-trash fg-white"></i></a>';
                        html += '    </div>';
                        html += ' </div>';
                        html += ' </li>';

                        $('#ahmgavms022p02ImageVacList').append(html);
                    });
                } else {
                    $('#ahmgavms022_image_vaccine_div').hide();

                }


                var ahmgavms022_detail_fileSk_array = item.fileSk;
                ahmgavms022_Attach_array_Temp = item.fileSk;

                if (item.fileSk != null) {
                    $('#ahmgavms022_attachment_div').show();

                    $.each(ahmgavms022_detail_fileSk_array, function (i, v) {

                        var html = "";

                        html += '<li class="containerimgvac">';
                        html += '    <img id="ahmgavms022p04ExistImage' + i + 'AttachPreview" class="imgprevvac" src="data:image/jpg;base64,' + ahmgavms022_detail_fileSk_array[i].name + '" alt="" />';
                        html += ' <div class="overlayvac"></div>';
                        html += ' <div class="col-sm-9 col-sm-offset-3 upload-file" data-allowedext="jpg, png, bmp, jpeg" style="display:none">';
                        html += '    <input id="ahmgavms022p04ExistImage' + i + 'Attach" type="file" accept=".jpg,.png,.bmp, .jpeg" class="form-control input-upload" onchange="return false;">';
                        html += ' </div>';
                        html += ' <div class="form-group-button" style="margin-top: 0px;">';
                        html += '    <div class="col-sm-12">';
                        html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'UploadBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-folder-open fg-white"></i></a>';
                        html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'PrevBtn" onclick="ahmgavms022_PreviewMultipleHandler(this, \'attachment\', \'ahmgavms022p04ExistImage' + i + 'Attach\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
                        html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'DeleteBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-trash fg-white"></i></a>';
                        html += '    </div>';
                        html += ' </div>';
                        html += ' </li>';

                        $('#ahmgavms022p02ImageAttachList').append(html);
                    });

                } else {
                    $('#ahmgavms022_attachment_div').hide();
                }

                ahmgavms022_detail_outType = item.outType;
                ahmgavms022_detail_area = item.area;
                var absenceReader = item.accessReader;
                var canteen = item.canteen;
                var securityGate = item.securityGate;
                var disclaimer = item.diclaimer;

                if (absenceReader === "Y") {
                    $('#ahmgavms022_detail_AccessAbsReader').prop('checked', true);
                } else {
                    $('#ahmgavms022_detail_AccessAbsReader').prop('checked', false);
                }

                if (canteen === "Y") {
                    $('#ahmgavms022_detail_AccessCanteen').prop('checked', true);
                } else {
                    $('#ahmgavms022_detail_AccessCanteen').prop('checked', false);
                }

                if (securityGate === "Y") {
                    $('#ahmgavms022_detail_AccessSecurityGate').prop('checked', true);
                } else {
                    $('#ahmgavms022_detail_AccessSecurityGate').prop('checked', false);
                }

                if (disclaimer == "Y") {
                    $('#ahmgavms022_detail_disclaimer').prop('checked', true);
                } else {
                    $('#ahmgavms022_detail_disclaimer').prop('checked', false);
                }


                ahmgavms022_detail_plants_datatable_data = [];
                var dataParamPlantGate = JSON.stringify({
                    "id": $('#ahmgavms022_detail_OutId').val(),
                    "code": $('#ahmgavms022_detail_Nik').val()
                });

                //get detail plant table data
                _fw_postJson(obj, dataParamPlantGate, ahmgavms022_url_root + '/get-plants', function (ret) {
                    if (ret.status === '1') {
                        $.each(ret.data, function (index, item) {
                            var data = {
                                seq: item.seq,
                                id: item.id,
                                name: item.name
                            }
                            ahmgavms022_detail_plants_datatable_data.push(data);

                            var contain = item.id;
                            ahmgavms022_detail_list_code += "'" + contain + "',";
                        });

                        ahmgavms022_detail_list_code = ahmgavms022_detail_list_code.substring(0, ahmgavms022_detail_list_code.length - 1);

                        //set detail plant table data
                        ahmgavms022_detail_plants_datatable = ahmgavms022_detail_plants_datatable_init();

                        ahmgavms022_detail_pic_datatable_data = [];
                        var dataParamPic = JSON.stringify({
                            "code": ahmgavms022_detail_outType,
                            "area": ahmgavms022_detail_list_code
                        });

                        ahmgavms022_detail_list_code = "";

                        //get detail PIC AHM table data
                        _fw_postJson(obj, dataParamPic, ahmgavms022_url_root + '/get-pic-ahm', function (ret) {
                            if (ret.status === '1') {
                                $.each(ret.data, function (index, item) {
                                    var data = {
                                        nrp: item.nrp,
                                        name: item.name,
                                        area: item.area,
                                        phoneNum: item.phoneNum
                                    }
                                    ahmgavms022_detail_pic_datatable_data.push(data);
                                });
                                //set detail PIC AHM table data
                                ahmgavms022_detail_pic_datatable = ahmgavms022_detail_pic_datatable_init();
                            }
                        });
                    }
                });

                ahmgavms022_detail_gates_datatable_data = [];

                //get detail gates table data
                _fw_postJson(obj, dataParamPlantGate, ahmgavms022_url_root + '/get-gates', function (ret) {
                    if (ret.status === '1') {
                        $.each(ret.data, function (index, item) {
                            var data = {
                                seq: item.seq,
                                id: item.id,
                                code: item.code,
                                name: item.name
                            }
                            ahmgavms022_detail_gates_datatable_data.push(data);
                        });
                        //set detail gates table data
                        ahmgavms022_detail_gates_datatable = ahmgavms022_detail_gates_datatable_init();
                    }
                });

                if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
                    if (item.outStatus.toUpperCase() != "WAITING FOR APPROVAL PIC") {
                        document.getElementById("ahmgavms022_detail_approve_button").disabled = true;
                        document.getElementById("ahmgavms022_detail_reject_button").disabled = true;

                        if ($('#ahmgavms022_detail_reject_button').hasClass('danger')) {
                            $('#ahmgavms022_detail_reject_button').removeClass('danger');
                        }
                    }
                } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
                    if (item.outStatus.toUpperCase() != "WAITING FOR APPROVAL SECURITY") {
                        document.getElementById("ahmgavms022_detail_approve_button").disabled = true;
                        document.getElementById("ahmgavms022_detail_reject_button").disabled = true;

                        if ($('#ahmgavms022_detail_reject_button').hasClass('danger')) {
                            $('#ahmgavms022_detail_reject_button').removeClass('danger');
                        }
                    }
                } else if (ahmgavms022_roles == "RO_GAVMS_SCHSEC") {
                    $('#ahmgavms022_detail_approve_button').hide();
                    $('#ahmgavms022_detail_reject_button').hide();
                }

            });
        }
    });

}

function ahmgavms022_detail_button_next_action(obj) {
    ahmgavms022_detail_rownum_getter += 1;

    if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
        $('#ahmgavms022_detail_ExpDate_button').show();
        document.getElementById("ahmgavms022_detail_ExpDate").disabled = false;
    }

    $('#ahmgavms022p02ImageVacList').empty();
    $('#ahmgavms022p02ImageAttachList').empty();

    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='detail']", objFrame);
    _fw_formResetFields(newFrame);

    var ahmgavms022_detail_outType = "";
    var ahmgavms022_detail_area = "";
    var ahmgavms022_detail_OutId = "";
    var ahmgavms022_detail_Nik = "";

    document.getElementById("ahmgavms022_detail_approve_button").disabled = false;
    document.getElementById("ahmgavms022_detail_reject_button").disabled = false;

    if (!$('#ahmgavms022_detail_reject_button').hasClass('danger')) {
        $('#ahmgavms022_detail_reject_button').addClass('danger');
    }

    if (ahmgavms022_detail_rownum_getter > (ahmgavms022_list_datatable_total_data - 1)) {
        document.getElementById("ahmgavms022_detail_button_next").disabled = true;
        document.getElementById("ahmgavms022_detail_button_previous").disabled = false;
    } else {
        document.getElementById("ahmgavms022_detail_button_next").disabled = false;
        document.getElementById("ahmgavms022_detail_button_previous").disabled = false;
    }

    if (ahmgavms022_detail_rownum_getter < 1
        || ahmgavms022_detail_rownum_getter == 1) {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = true;
    } else {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = false;
    }

    if (ahmgavms022_roles != "RO_GAVMS_OFCSECT") {
        $('#ahmgavms022_detail_ExpDate_button').remove();
        document.getElementById("ahmgavms022_detail_ExpDate").disabled = true;
    }

    var jsonbody = JSON.stringify({
        sort: ahmgavms022_sort_global,
        order: ahmgavms022_order_global,
        offset: ahmgavms022_detail_rownum_getter - 1,
        limit: 1,
        search: ahmgavms022_detail_param_getter
    });

    _fw_postJson(formObject, jsonbody, ahmgavms022_url_root + '/monitoring', function (ret) {
        if (ret.status === '1') {
            $.each(ret.data, function (index, item) {

                var supplier = item.supplier;

                if (supplier == "S") {
                    supplier = "Supplier";
                } else if (supplier == "N") {
                    supplier = "Non-Supplier"
                }

                $('#ahmgavms022_detail_OutId').val(item.outId);
                $('#ahmgavms022_detail_OutName').val(item.outName);
                $('#ahmgavms022_detail_PhoneOut').val(item.phoneNo);
                $('#ahmgavms022_detail_Nik').val(item.persId);
                $('#ahmgavms022_detail_OutType').val(item.outTypeName);
                $('#ahmgavms022_detail_OutCompany').val(item.companyName);
                $('#ahmgavms022_detail_JobDetail').val(item.job);
                $('#ahmgavms022_detail_plant').val(item.areaName);
                $('#ahmgavms022_detail_Supplier').val(supplier);
                $('#ahmgavms022_detail_CovVacStatus').val(item.vacStatus);
                $('#ahmgavms022_detail_BeginEffDate').val(item.beginDateText);
                $('#ahmgavms022_detail_EndEffDate').val(item.endDateText);
                $('#ahmgavms022_detail_PassCardNum').val(item.passNumber);
                $('#ahmgavms022_detail_ExpDate').val(item.passExpiryDateText);
                $('#ahmgavms022_detail_CovSumVacStatus').val(item.vacSummary);
                $('#ahmgavms022_detail_note').val(item.note);
                $('#ahmgavms022_detail_NoteCovVacStatus').val(item.vacNote);
                $('#ahmgavms022_detail_CovLastVacDate').val(item.vacDateText);
                $('#ahmgavms022_detail_CovLastVacType').val(item.vacTypeName);

                if ($('#ahmgavms022_detail_CovVacStatus').val() == "ALREADY VACCINATED") {
                    $('#ahmgavms022_already_group').removeClass('hide-this');
                    $('#ahmgavms022_notYetVaccine_group').addClass('hide-this');
                } else if($('#ahmgavms022_detail_CovVacStatus').val() == "NOT YET VACCINATED") {
                    $('#ahmgavms022_notYetVaccine_group').removeClass('hide-this');
                    $('#ahmgavms022_already_group').addClass('hide-this');
                }else{
                    $('#ahmgavms022_notYetVaccine_group').addClass('hide-this');
                    $('#ahmgavms022_already_group').addClass('hide-this');
                }

                detail_id_for_confirm = item.id;
                detail_outid_for_confirm = item.outId;
                detail_outname_for_confirm = item.outName;
                detail_note_for_confirm = item.note;
                detail_department_for_confirm = item.outTypeName
                detail_company_for_confirm = item.companyName
                ahmgavms022_detail_old_expiry_date = item.passExpiryDateText;

                ahmgavms022_detail_status_submit = item.outStatus;

                $("#ahmgavms022_detail_AttachKtp_Preview").attr('src', "data:image/jpg;base64, " + item.fileKtp);
                $("#ahmgavms022p02PhotoPreview").attr('src', "data:image/jpg;base64, " + item.filePhoto);
                ahmgavms022_Photo_Temp = item.filePhoto;
                ahmgavms022_Ktp_Temp = item.fileKtp;

                if (item.fileVaccines != null) {
                    $('#ahmgavms022_image_vaccine_div').show();

                    var ahmgavms022_detail_filevaccine_array = item.fileVaccines;
                    ahmgavms022_Vac_array_Temp = item.fileVaccines;

                    $.each(ahmgavms022_detail_filevaccine_array, function (i, v) {

                        var html = "";

                        html += '<li class="containerimgvac">';
                        html += '    <img id="ahmgavms022p02ExistImage' + i + 'VacPreview" class="imgprevvac" src="data:image/jpg;base64,' + ahmgavms022_detail_filevaccine_array[i].name + '" alt="" />';
                        html += ' <div class="overlayvac"></div>';
                        html += ' <div class="col-sm-9 col-sm-offset-3 upload-file" data-allowedext="jpg, png, bmp, jpeg" style="display:none">';
                        html += '    <input id="ahmgavms022p02ExistImage' + i + 'Vac" type="file" accept=".jpg,.png,.bmp, .jpeg" class="form-control input-upload" onchange="return false;">';
                        html += ' </div>';
                        html += ' <div class="form-group-button" style="margin-top: 0px;">';
                        html += '    <div class="col-sm-12">';
                        html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'UploadBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-folder-open fg-white"></i></a>';
                        html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'PrevBtn" onclick="ahmgavms022_PreviewMultipleHandler(this, \'vaccine\', \'ahmgavms022p02ExistImage' + i + 'Vac\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
                        html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'DeleteBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-trash fg-white"></i></a>';
                        html += '    </div>';
                        html += ' </div>';
                        html += ' </li>';

                        $('#ahmgavms022p02ImageVacList').append(html);
                    });
                } else {
                    $('#ahmgavms022_image_vaccine_div').hide();
                }


                var ahmgavms022_detail_fileSk_array = item.fileSk;
                ahmgavms022_Attach_array_Temp = item.fileSk;

                if (item.fileSk != null) {
                    $('#ahmgavms022_attachment_div').show();

                    $.each(ahmgavms022_detail_fileSk_array, function (i, v) {

                        var html = "";

                        html += '<li class="containerimgvac">';
                        html += '    <img id="ahmgavms022p04ExistImage' + i + 'AttachPreview" class="imgprevvac" src="data:image/jpg;base64,' + ahmgavms022_detail_fileSk_array[i].name + '" alt="" />';
                        html += ' <div class="overlayvac"></div>';
                        html += ' <div class="col-sm-9 col-sm-offset-3 upload-file" data-allowedext="jpg, png, bmp, jpeg" style="display:none">';
                        html += '    <input id="ahmgavms022p04ExistImage' + i + 'Attach" type="file" accept=".jpg,.png,.bmp, .jpeg" class="form-control input-upload" onchange="return false;">';
                        html += ' </div>';
                        html += ' <div class="form-group-button" style="margin-top: 0px;">';
                        html += '    <div class="col-sm-12">';
                        html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'UploadBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-folder-open fg-white"></i></a>';
                        html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'PrevBtn" onclick="ahmgavms022_PreviewMultipleHandler(this, \'attachment\', \'ahmgavms022p04ExistImage' + i + 'Attach\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
                        html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'DeleteBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-trash fg-white"></i></a>';
                        html += '    </div>';
                        html += ' </div>';
                        html += ' </li>';

                        $('#ahmgavms022p02ImageAttachList').append(html);
                    });

                } else {
                    $('#ahmgavms022_attachment_div').hide();
                }

                ahmgavms022_detail_outType = item.outType;
                ahmgavms022_detail_area = item.area;
                ahmgavms022_detail_OutId = item.outId;
                ahmgavms022_detail_Nik = item.persId;
                var absenceReader = item.accessReader;
                var canteen = item.canteen;
                var securityGate = item.securityGate;
                var disclaimer = item.diclaimer;

                if (absenceReader === "Y") {
                    $('#ahmgavms022_detail_AccessAbsReader').prop('checked', true);
                } else {
                    $('#ahmgavms022_detail_AccessAbsReader').prop('checked', false);
                }

                if (canteen === "Y") {
                    $('#ahmgavms022_detail_AccessCanteen').prop('checked', true);
                } else {
                    $('#ahmgavms022_detail_AccessCanteen').prop('checked', false);
                }

                if (securityGate === "Y") {
                    $('#ahmgavms022_detail_AccessSecurityGate').prop('checked', true);
                } else {
                    $('#ahmgavms022_detail_AccessSecurityGate').prop('checked', false);
                }

                if (disclaimer == "Y") {
                    $('#ahmgavms022_detail_disclaimer').prop('checked', true);
                } else {
                    $('#ahmgavms022_detail_disclaimer').prop('checked', false);
                }

                ahmgavms022_detail_plants_datatable_data = [];
                var dataParamPlantGate = JSON.stringify({
                    "id": ahmgavms022_detail_OutId,
                    "code": ahmgavms022_detail_Nik
                });

                _fw_postJson(obj, dataParamPlantGate, ahmgavms022_url_root + '/get-plants', function (ret) {
                    if (ret.status === '1') {
                        $.each(ret.data, function (index, item) {
                            var data = {
                                seq: item.seq,
                                id: item.id,
                                name: item.name
                            }
                            ahmgavms022_detail_plants_datatable_data.push(data);

                            var contain = item.id;
                            ahmgavms022_detail_list_code += "'" + contain + "',";
                        });

                        ahmgavms022_detail_list_code = ahmgavms022_detail_list_code.substring(0, ahmgavms022_detail_list_code.length - 1);

                        ahmgavms022_detail_plants_datatable = ahmgavms022_detail_plants_datatable_init();

                        ahmgavms022_detail_pic_datatable_data = [];

                        var dataParamPic = JSON.stringify({
                            "code": ahmgavms022_detail_outType,
                            "area": ahmgavms022_detail_list_code
                        });

                        ahmgavms022_detail_list_code = "";

                        _fw_postJson(obj, dataParamPic, ahmgavms022_url_root + '/get-pic-ahm', function (ret) {
                            if (ret.status === '1') {
                                $.each(ret.data, function (index, item) {
                                    var data = {
                                        nrp: item.nrp,
                                        name: item.name,
                                        area: item.area,
                                        phoneNum: item.phoneNum
                                    }
                                    ahmgavms022_detail_pic_datatable_data.push(data);
                                });
                                ahmgavms022_detail_pic_datatable = ahmgavms022_detail_pic_datatable_init();
                            }
                        });

                    }
                });

                ahmgavms022_detail_gates_datatable_data = [];

                _fw_postJson(obj, dataParamPlantGate, ahmgavms022_url_root + '/get-gates', function (ret) {
                    if (ret.status === '1') {
                        $.each(ret.data, function (index, item) {
                            var data = {
                                seq: item.seq,
                                id: item.id,
                                code: item.code,
                                name: item.name
                            }
                            ahmgavms022_detail_gates_datatable_data.push(data);
                        });
                        ahmgavms022_detail_gates_datatable = ahmgavms022_detail_gates_datatable_init();
                    }
                });

                if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
                    if (item.outStatus.toUpperCase() != "WAITING FOR APPROVAL PIC") {
                        document.getElementById("ahmgavms022_detail_approve_button").disabled = true;
                        document.getElementById("ahmgavms022_detail_reject_button").disabled = true;

                        if ($('#ahmgavms022_detail_reject_button').hasClass('danger')) {
                            $('#ahmgavms022_detail_reject_button').removeClass('danger');
                        }
                    }
                } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
                    if (item.outStatus.toUpperCase() != "WAITING FOR APPROVAL SECURITY") {
                        document.getElementById("ahmgavms022_detail_approve_button").disabled = true;
                        document.getElementById("ahmgavms022_detail_reject_button").disabled = true;

                        if ($('#ahmgavms022_detail_reject_button').hasClass('danger')) {
                            $('#ahmgavms022_detail_reject_button').removeClass('danger');
                        }
                    }
                } else if (ahmgavms022_roles == "RO_GAVMS_SCHSEC") {
                    $('#ahmgavms022_detail_approve_button').hide();
                    $('#ahmgavms022_detail_reject_button').hide();
                }

            });
        }
    });
}

function ahmgavms022_detail_button_action(obj) {

    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='detail']", objFrame);
    var getRow = ahmgavms022_list_datatable.row($(obj).parents('tr')).data();

    document.getElementById("ahmgavms022_detail_approve_button").disabled = false;
    document.getElementById("ahmgavms022_detail_reject_button").disabled = false;

    if (!$('#ahmgavms022_detail_reject_button').hasClass('danger')) {
        $('#ahmgavms022_detail_reject_button').addClass('danger');
    }

    if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
        $('#ahmgavms022_detail_ExpDate_button').show();
        document.getElementById("ahmgavms022_detail_ExpDate").disabled = false;
    }

    $('#ahmgavms022p02ImageVacList').empty();
    $('#ahmgavms022p02ImageAttachList').empty();

    ahmgavms022_detail_rownum_getter = ahmgavms022_detail_rownum_getter_from_pages + getRow.rowNum + 1;

    //validate previous detail button
    if (ahmgavms022_detail_rownum_getter < 1
        || ahmgavms022_detail_rownum_getter == 1) {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = true;
    } else {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = false;
    }

    //validate next detail button
    if (ahmgavms022_detail_rownum_getter > ahmgavms022_list_datatable_total_data
        || ahmgavms022_detail_rownum_getter == ahmgavms022_list_datatable_total_data) {
        document.getElementById("ahmgavms022_detail_button_next").disabled = true;
    } else {
        document.getElementById("ahmgavms022_detail_button_next").disabled = false;
    }

    if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
        if (getRow.outStatus.toUpperCase() != "WAITING FOR APPROVAL PIC") {
            document.getElementById("ahmgavms022_detail_approve_button").disabled = true;
            document.getElementById("ahmgavms022_detail_reject_button").disabled = true;

            if ($('#ahmgavms022_detail_reject_button').hasClass('danger')) {
                $('#ahmgavms022_detail_reject_button').removeClass('danger');
            }
        }
    } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
        if (getRow.outStatus.toUpperCase() != "WAITING FOR APPROVAL SECURITY") {
            document.getElementById("ahmgavms022_detail_approve_button").disabled = true;
            document.getElementById("ahmgavms022_detail_reject_button").disabled = true;

            if ($('#ahmgavms022_detail_reject_button').hasClass('danger')) {
                $('#ahmgavms022_detail_reject_button').removeClass('danger');
            }
        }
    } else if (ahmgavms022_roles == "RO_GAVMS_SCHSEC") {
        $('#ahmgavms022_detail_approve_button').hide();
        $('#ahmgavms022_detail_reject_button').hide();
    }

    if (ahmgavms022_roles != "RO_GAVMS_OFCSECT") {
        $('#ahmgavms022_detail_ExpDate_button').hide();
        document.getElementById("ahmgavms022_detail_ExpDate").disabled = true;
    }

    _fw_formResetFields(newFrame);
    detail_id_for_confirm = getRow.id;
    detail_outid_for_confirm = getRow.outId;
    detail_outname_for_confirm = getRow.outName;
    detail_outstatus_for_confirm = getRow.outStatus;
    detail_note_for_confirm = getRow.note;
    detail_department_for_confirm = getRow.outTypeName
    detail_company_for_confirm = getRow.companyName
    var ahmgavms022_detail_area = getRow.area;
    var ahmgavms022_detail_outType = getRow.outType;
    ahmgavms022_detail_old_expiry_date = getRow.passExpiryDateText;
   


    ahmgavms022_Ktp_Temp = getRow.fileKtp;

    var supplier = getRow.supplier;

    if (supplier == "S") {
        supplier = "Supplier";
    } else if (supplier == "N") {
        supplier = "Non-Supplier"
    }

    $('#ahmgavms022_detail_OutId').val(getRow.outId);
    $('#ahmgavms022_detail_OutName').val(getRow.outName);
    $('#ahmgavms022_detail_PhoneOut').val(getRow.phoneNo);
    $('#ahmgavms022_detail_Nik').val(getRow.persId);
    $('#ahmgavms022_detail_OutType').val(getRow.outTypeName);
    $('#ahmgavms022_detail_OutCompany').val(getRow.companyName);
    $('#ahmgavms022_detail_JobDetail').val(getRow.job);
    $('#ahmgavms022_detail_plant').val(getRow.areaName);
    $('#ahmgavms022_detail_Supplier').val(supplier);
    $('#ahmgavms022_detail_CovVacStatus').val(getRow.vacStatus);
    $('#ahmgavms022_detail_BeginEffDate').val(getRow.beginDateText);
    $('#ahmgavms022_detail_EndEffDate').val(getRow.endDateText);
    $('#ahmgavms022_detail_PassCardNum').val(getRow.passNumber);
    $('#ahmgavms022_detail_ExpDate').val(getRow.passExpiryDateText);
    $('#ahmgavms022_detail_CovSumVacStatus').val(getRow.vacSummary);
    $('#ahmgavms022_detail_note').val(getRow.note);
    $('#ahmgavms022_detail_NoteCovVacStatus').val(getRow.vacNote);
    $('#ahmgavms022_detail_CovLastVacDate').val(getRow.vacDateText);
    $('#ahmgavms022_detail_CovLastVacType').val(getRow.vacTypeName);

    if ($('#ahmgavms022_detail_CovVacStatus').val() == "ALREADY VACCINATED") {
        $('#ahmgavms022_already_group').removeClass('hide-this');
        $('#ahmgavms022_notYetVaccine_group').addClass('hide-this');
    } else if($('#ahmgavms022_detail_CovVacStatus').val() == "NOT YET VACCINATED") {
        $('#ahmgavms022_notYetVaccine_group').removeClass('hide-this');
        $('#ahmgavms022_already_group').addClass('hide-this');
    }else{
        $('#ahmgavms022_notYetVaccine_group').addClass('hide-this');
        $('#ahmgavms022_already_group').addClass('hide-this');
    }

    ahmgavms022_detail_status_submit = getRow.outStatus;

    $("#ahmgavms022_detail_AttachKtp_Preview").attr('src', "data:image/jpg;base64, " + getRow.fileKtp);
    $("#ahmgavms022p02PhotoPreview").attr('src', "data:image/jpg;base64, " + getRow.filePhoto);
    ahmgavms022_Photo_Temp = getRow.filePhoto;
    ahmgavms022_Ktp_Temp = getRow.fileKtp;

    if (getRow.outStatus.toUpperCase() != "WAITING FOR APPROVAL SECURITY") {
        $('#ahmgavms022_detail_ExpDate_button').hide();
        document.getElementById("ahmgavms022_detail_ExpDate").disabled = true;
    }

    if (getRow.fileVaccines == null) {
        $('#ahmgavms022_image_vaccine_div').hide();
    } else {
        $('#ahmgavms022_image_vaccine_div').show();
        var ahmgavms022_detail_filevaccine_array = getRow.fileVaccines;

        ahmgavms022_Vac_array_Temp = getRow.fileVaccines;

        $.each(ahmgavms022_detail_filevaccine_array, function (i, v) {

            var html = "";

            html += '<li class="containerimgvac">';
            html += '    <img id="ahmgavms022p02ExistImage' + i + 'VacPreview" class="imgprevvac" src="data:image/jpg;base64,' + ahmgavms022_detail_filevaccine_array[i].name + '" alt="" />';
            html += ' <div class="overlayvac"></div>';
            html += ' <div class="col-sm-9 col-sm-offset-3 upload-file" data-allowedext="jpg, png, bmp, jpeg" style="display:none">';
            html += '    <input id="ahmgavms022p02ExistImage' + i + 'Vac" type="file" accept=".jpg,.png,.bmp, .jpeg" class="form-control input-upload" onchange="return false;">';
            html += ' </div>';
            html += ' <div class="form-group-button" style="margin-top: 0px;">';
            html += '    <div class="col-sm-12">';
            html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'UploadBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-folder-open fg-white"></i></a>';
            html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'PrevBtn" onclick="ahmgavms022_PreviewMultipleHandler(this, \'vaccine\', \'ahmgavms022p02ExistImage' + i + 'Vac\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
            html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'DeleteBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-trash fg-white"></i></a>';
            html += '    </div>';
            html += ' </div>';
            html += ' </li>';

            $('#ahmgavms022p02ImageVacList').append(html);
        });

    }


    var ahmgavms022_detail_fileSk_array = getRow.fileSk;
    ahmgavms022_Attach_array_Temp = getRow.fileSk;

    if (getRow.fileSk != null) {
        $('#ahmgavms022_attachment_div').show();

        $.each(ahmgavms022_detail_fileSk_array, function (i, v) {

            var html = "";

            html += '<li class="containerimgvac">';
            html += '    <img id="ahmgavms022p04ExistImage' + i + 'AttachPreview" class="imgprevvac" src="data:image/jpg;base64,' + ahmgavms022_detail_fileSk_array[i].name + '" alt="" />';
            html += ' <div class="overlayvac"></div>';
            html += ' <div class="col-sm-9 col-sm-offset-3 upload-file" data-allowedext="jpg, png, bmp, jpeg" style="display:none">';
            html += '    <input id="ahmgavms022p04ExistImage' + i + 'Attach" type="file" accept=".jpg,.png,.bmp, .jpeg" class="form-control input-upload" onchange="return false;">';
            html += ' </div>';
            html += ' <div class="form-group-button" style="margin-top: 0px;">';
            html += '    <div class="col-sm-12">';
            html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'UploadBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-folder-open fg-white"></i></a>';
            html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'PrevBtn" onclick="ahmgavms022_PreviewMultipleHandler(this, \'attachment\', \'ahmgavms022p04ExistImage' + i + 'Attach\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
            html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'DeleteBtn" onclick="return false;" style="cursor: pointer; display: none;"><i class="glyphicon  glyphicon-trash fg-white"></i></a>';
            html += '    </div>';
            html += ' </div>';
            html += ' </li>';

            $('#ahmgavms022p02ImageAttachList').append(html);
        });

    } else {
        $('#ahmgavms022_attachment_div').hide();

    }




    var absenceReader = getRow.accessReader;
    var canteen = getRow.canteen;
    var securityGate = getRow.securityGate;
    var disclaimer = getRow.diclaimer;

    if (absenceReader == "Y") {
        $('#ahmgavms022_detail_AccessAbsReader').prop('checked', true);
    } else {
        $('#ahmgavms022_detail_AccessAbsReader').prop('checked', false);
    }

    if (canteen == "Y") {
        $('#ahmgavms022_detail_AccessCanteen').prop('checked', true);
    } else {
        $('#ahmgavms022_detail_AccessCanteen').prop('checked', false);
    }

    if (securityGate == "Y") {
        $('#ahmgavms022_detail_AccessSecurityGate').prop('checked', true);
    } else {
        $('#ahmgavms022_detail_AccessSecurityGate').prop('checked', false);
    }

    if (disclaimer == "Y") {
        $('#ahmgavms022_detail_disclaimer').prop('checked', true);
    } else {
        $('#ahmgavms022_detail_disclaimer').prop('checked', false);
    }

    ahmgavms022_detail_plants_datatable_data = [];
    var dataParamPlantGate = JSON.stringify({
        "id": getRow.outId,
        "code": getRow.persId
    });

    _fw_postJson(obj, dataParamPlantGate, ahmgavms022_url_root + '/get-plants', function (ret) {
        if (ret.status === '1') {
            $.each(ret.data, function (index, item) {
                var data = {
                    seq: item.seq,
                    id: item.id,
                    name: item.name
                }
                ahmgavms022_detail_plants_datatable_data.push(data);

                var contain = item.id;
                ahmgavms022_detail_list_code += "'" + contain + "',";
            });

            ahmgavms022_detail_list_code = ahmgavms022_detail_list_code.substring(0, ahmgavms022_detail_list_code.length - 1);

            ahmgavms022_detail_plants_datatable = ahmgavms022_detail_plants_datatable_init();

            ahmgavms022_detail_pic_datatable_data = [];

            var dataParamPic = JSON.stringify({
                "code": ahmgavms022_detail_outType,
                "area": ahmgavms022_detail_list_code

            });

            ahmgavms022_detail_list_code = "";

            _fw_postJson(obj, dataParamPic, ahmgavms022_url_root + '/get-pic-ahm', function (ret) {
                if (ret.status === '1') {
                    $.each(ret.data, function (index, item) {
                        var data = {
                            nrp: item.nrp,
                            name: item.name,
                            area: item.area,
                            phoneNum: item.phoneNum
                        }
                        ahmgavms022_detail_pic_datatable_data.push(data);
                    });
                    ahmgavms022_detail_pic_datatable = ahmgavms022_detail_pic_datatable_init();
                }
            });
        }
    });

    ahmgavms022_detail_gates_datatable_data = [];

    _fw_postJson(obj, dataParamPlantGate, ahmgavms022_url_root + '/get-gates', function (ret) {
        if (ret.status === '1') {
            $.each(ret.data, function (index, item) {
                var data = {
                    seq: item.seq,
                    id: item.id,
                    code: item.code,
                    name: item.name
                }
                ahmgavms022_detail_gates_datatable_data.push(data);
            });
            ahmgavms022_detail_gates_datatable = ahmgavms022_detail_gates_datatable_init();
        }
    });

    _fw_navigateSubPage(obj, 'detail');
}

function ahmgavms022_detail_plants_datatable_init() {
    var datatableTemplateObject = $('#ahmgavms022_detail_plants_datatable_template').clone();
    datatableTemplateObject.attr('id', 'ahmgavms022_detail_plants_datatable');
    datatableTemplateObject.removeClass('ahmgavms022-datatables-template');

    $('#ahmgavms022_detail_plants').html(datatableTemplateObject);

    var ahmgavms022_detail_plants_datatable_data_temp = [];

    $.each(ahmgavms022_detail_plants_datatable_data, function (index, item) {
        var data = {
            seq: item.seq,
            id: item.id,
            name: item.name,
        }
        ahmgavms022_detail_plants_datatable_data_temp.push(data);
    });

    var datatable = $('#ahmgavms022_detail_plants_datatable').DataTable({
        destroy: true,
        filter: false,
        ordering: false,
        scrollX: false,
        scrollCollapse: false,
        data: ahmgavms022_detail_plants_datatable_data_temp,
        columns: [{
            data: 'seq',
            name: 'seq',
            className: 'dt-head-center dt-body-center'
        }, {
            data: 'name',
            name: 'name',
            className: 'dt-head-center dt-body-left'
        }],
        createdRow: function (row, data, dataIndex) {
            $(row).find('td:eq(0)').attr('data-title', 'seq');
            $(row).find('td:eq(1)').attr('data-title', 'name');
        }, columnDefs: [
            {
                "targets": 0,
                "width": "5%"
            }
        ]

    });
    $('.totalDataText', $('#ahmgavms022_detail_plants_datatable_wrapper')).remove();
    $('.dataTables_customPaging', $('#ahmgavms022_detail_plants_datatable_wrapper')).remove();

    return datatable;
}

function ahmgavms022_detail_gates_datatable_init() {

    var DataTable_Template_Object = $('#ahmgavms022_detail_gates_datatable_template').clone();
    DataTable_Template_Object.attr('id', 'ahmgavms022_detail_gates_datatable');
    DataTable_Template_Object.removeClass('ahmgavms022-datatables-template');

    $('#ahmgavms022_detail_gates').html(DataTable_Template_Object);

    var ahmgavms022_detail_gates_datatable_data_temp = [];

    $.each(ahmgavms022_detail_gates_datatable_data, function (index, item) {
        var data = {
            seq: item.seq,
            name: item.name,
            id: item.id,
            code: item.code
        }
        ahmgavms022_detail_gates_datatable_data_temp.push(data);
    });

    var datatable = $('#ahmgavms022_detail_gates_datatable').DataTable({
        destroy: true,
        filter: false,
        ordering: true,
        scrollX: false,
        scrollCollapse: false,
        paging: false,
        data: ahmgavms022_detail_gates_datatable_data_temp,
        columns: [{
            data: 'seq',
            name: 'seq',
            className: 'dt-head-center dt-body-center'
        }, {
            data: 'name',
            name: 'name',
            className: 'dt-head-center dt-body-left'
        }],
        order: [[0, 'asc']],
        createdRow: function (row, data, dataIndex) {
            $(row).find('td:eq(0)').attr('data-title', 'seq');
            $(row).find('td:eq(1)').attr('data-title', 'name');
        }, columnDefs: [
            {
                "targets": 0,
                "width": "5%"
            }
        ]
    });

    $('.totalDataText', $('#ahmgavms022_detail_gates_datatable_wrapper')).remove();
    $('.dataTables_customPaging', $('#ahmgavms022_detail_gates_datatable_wrapper')).remove();

    return datatable;
}

function ahmgavms022_detail_pic_datatable_init() {
    var datatableTemplateObject = $('#ahmgavms022_detail_listPicAhm_datatable_template').clone();
    datatableTemplateObject.attr('id', 'ahmgavms022_detail_listPicAhm_datatable');
    datatableTemplateObject.removeClass('ahmgavms022-datatables-template');

    $('#ahmgavms022_detail_listPicAhm').html(datatableTemplateObject);

    var ahmgavms022_detail_pic_datatable_data_temp = [];

    $.each(ahmgavms022_detail_pic_datatable_data, function (index, item) {
        var data = {
            nrp: item.nrp,
            name: item.name,
            area: item.area,
            phoneNum: item.phoneNum,
        }
        ahmgavms022_detail_pic_datatable_data_temp.push(data);
    });

    var datatable = $('#ahmgavms022_detail_listPicAhm_datatable').DataTable({
        destroy: true,
        filter: false,
        ordering: true,
        scrollX: false,
        scrollCollapse: false,
        data: ahmgavms022_detail_pic_datatable_data_temp,
        columns: [{
            data: 'nrp',
            name: 'nrp',
            className: 'dt-head-center dt-body-left'
        }, {
            data: 'name',
            name: 'name',
            className: 'dt-head-center dt-body-left'
        }, {
            data: 'area',
            name: 'area',
            className: 'dt-head-center dt-body-left'
        }, {
            data: 'phoneNum',
            name: 'phoneNum',
            className: 'dt-head-center dt-body-left'
        }],
        createdRow: function (row, data, dataIndex) {
            $(row).find('td:eq(0)').attr('data-title', 'nrp');
            $(row).find('td:eq(1)').attr('data-title', 'name');
            $(row).find('td:eq(2)').attr('data-title', 'area');
            $(row).find('td:eq(3)').attr('data-title', 'phoneNum');
        }

    });

    $('.totalDataText', $('#ahmgavms022_detail_listPicAhm_datatable_wrapper')).remove();
    $('.dataTables_customPaging', $('#ahmgavms022_detail_listPicAhm_datatable_wrapper')).remove();

    return datatable;

}

function ahmgavms022_detail_back_button_action(obj) {
    _fw_navigateSubPage(obj, 'list');
}

function getDateWithoutTime(date) {
    const currentDate = new Date(date);
    currentDate.setHours(0, 0, 0, 0);
    return currentDate;
  }

function ahmgavms022_detail_approve_button_action(obj) {
    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='confirmation']", objFrame);
    var htmlVal = '';
    var stringdend = $('#ahmgavms022_detail_EndEffDate').val();
    var stringdexp = $('#ahmgavms022_detail_ExpDate').val();
    var getting = [];
    const months = [
        'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
      ];

    let maxExpDate;
    let tempExp;

    let DateOldExp = moment(ahmgavms022_detail_old_expiry_date,"DD-MMM-YYYY").toDate();
    var dateExp = moment(stringdexp, 'DD-MMM-YYYY').toDate();
    var dateEffDate = moment(stringdend, 'DD-MMM-YYYY').toDate();

    //validasi mandatory expiry date
    if($('#ahmgavms022_detail_ExpDate').val() == ""){
        var  errorMessage = `<li>Pass Card Expiry Date cannot be empty.</li>`;      
        _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
        return false;
    }

    //validasi expiry date di page response jika dia security  
    if(ahmgavms022_roles == "RO_GAVMS_OFCSECT"){
        const validateDateExp = getDateWithoutTime(new Date(dateExp));
        const validateDateExpOld = getDateWithoutTime(new Date(DateOldExp));
        const validateDateNow = getDateWithoutTime(new Date());
        const validateDateEnd = getDateWithoutTime(new Date(dateEffDate));


        if (validateDateExp > validateDateEnd) {

              var  errorMessage = `<li>Pass Card Expiry Date Can Not be Less Than System Date and Can Not be Greater Than ${DateOldExp.getDate()} ${months[DateOldExp.getMonth()]} ${DateOldExp.getFullYear()} </li>`;      
            _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
            return false;
        }


        if (validateDateExp > validateDateExpOld) {
            var errorMessage = `<li>Pass Card Expiry Date Can Not be Less Than System Date and Can Not be Greater Than ${DateOldExp.getDate()} ${months[DateOldExp.getMonth()]} ${DateOldExp.getFullYear()} </li>`;           
            _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
            return false;
        }

        if (validateDateExp < validateDateNow) {
            var errorMessage = `<li>Pass Card Expiry Date Can Not be Less Than System Date and Can Not be Greater Than ${DateOldExp.getDate()} ${months[DateOldExp.getMonth()]} ${DateOldExp.getFullYear()} </li>`;
            _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
            return false;
        }
    
    }

    var catcher = {
        "endDateText": stringdend,
        "passExpiryDateText": stringdexp
    };
    getting.push(catcher);

    var forJSON = JSON.stringify(getting);

    _fw_postJson(obj, forJSON, ahmgavms022_url_root + '/check-date', function (ret) {
        if (ret.status === '1') {

            if ($('#ahmgavms022_detail_disclaimer').prop("checked") != true) {
                var errorMessage = '<li>Disclaimer required.</li>';
                _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');

            } else if ($('#ahmgavms022_detail_ExpDate').val() == "") {
                var errorMessage = '<li>Expiry Date Required.</li>';
                _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');

            } else {
                approve_reject_confirmation = 'APPROVE';
                htmlVal = htmlVal + '<h2>The following data will be approve. Are you sure?</h2>';

                $('#ahmgavms022_h2_confirmation').html(htmlVal);
                $('#ahmgavms022_decision_confirmation').val('approve');

                _fw_formResetFields(newFrame);
                _fw_navigateSubPage(obj, 'confirmation');
            }
        } else {
            var errorMessage = '<li>' + ret.error_fields[0].m + '</li>';
            _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
            return false;
        }
    });


    var stringdend = $('#ahmgavms022_detail_EndEffDate').val();
    var stringdexp = $('#ahmgavms022_detail_ExpDate').val();
    var dend = moment(stringdend).unix();
    var dexp = moment(stringdexp).unix();
    dend = dend * -1;
    dexp = dexp * -1;

    var check = $('#ahmgavms022_detail_disclaimer');
    var checkObject = $('input[type="checkbox"][name="ahmgavms022_detail_disclaimer"]');
    var getvalue = $('input[type="radio"][name="ahmgaers009_addasset_permitcategory"]:checked');

}

function ahmgavms022_detail_reject_button_action(obj) {
    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='confirmation']", objFrame);
    var htmlVal = '';

    if ($('#ahmgavms022_detail_disclaimer').prop("checked") == true) {

        if($('#ahmgavms022_detail_ExpDate').val() == ""){
            var  errorMessage = `<li>Pass Card Expiry Date cannot be empty.</li>`;      
            _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
            return false;
        }

        approve_reject_confirmation = 'REJECT';
        htmlVal = htmlVal + '<h2>The following data will be reject. Are you sure?</h2>';
        htmlVal += '<div class="input-control textarea" data-role="input-control">' +
            '<textarea name="ahmgavms022_detail_reject_note" id="ahmgavms022_detail_reject_note" type="text" placeholder="Insert The Reject Note" maxlength="100"></textarea>'
        '</div>';

        $('#ahmgavms022_h2_confirmation').html(htmlVal);
        $('#ahmgavms022_decision_confirmation').val('reject');

        _fw_formResetFields(newFrame);

        _fw_navigateSubPage(obj, 'confirmation');

    } else {
        var errorMessage = '<li>Disclaimer required.</li>';
        _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
    }
}

function ahmgavms022_detail_btn_sure_action(obj) {
 
    _fw_validation_clear(obj);
    var id = detail_id_for_confirm;
    var outid = detail_outid_for_confirm;
    var outname = detail_outname_for_confirm;
    var note = detail_note_for_confirm;
    var company = detail_company_for_confirm;
    var department = detail_department_for_confirm;
 
    var send = "";
    var bypass = false;

    if (approve_reject_confirmation == "APPROVE") {
        if (ahmgavms022_detail_status_submit.toUpperCase() == "ACTIVE") {
            _fw_setMessage(formObject, 0, 'Failed Update Data. Outsource Status has been Activated!');
            return false;
        }
        if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
            if (ahmgavms022_detail_status_submit.toUpperCase() == "WAITING FOR APPROVAL PIC") {
                send = "Waiting for Approval Security"
                bypass = true;
            } else {
                _fw_setMessage(formObject, 0, 'Failed Update Data. This role unable to update this data!');
                return false;
            }
        } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
            if (ahmgavms022_detail_status_submit.toUpperCase() == "WAITING FOR APPROVAL SECURITY") {
                send = "Active";
                bypass = true;
            } else {
                _fw_setMessage(formObject, 0, 'Failed Update Data. This role unable to update this data!');
                return false;
            }
        } else {
            _fw_setMessage(formObject, 0, 'Failed Update Data. This role unable to update this data!');
            return false;
        }
    } else if (approve_reject_confirmation == "REJECT") {
        if (ahmgavms022_detail_status_submit.toUpperCase() == "REJECT") {
            _fw_setMessage(formObject, 0, 'Failed Update Data. Outsource Status has been Rejected!');
            return false;
        }
        if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
            if (ahmgavms022_detail_status_submit.toUpperCase() == "WAITING FOR APPROVAL PIC") {
                send = "Reject"
                bypass = true;
            } else {
                _fw_setMessage(formObject, 0, 'Failed Update Data. This role just can update \'Waiting For Approval PIC\' data!');
                return false;
            }
        } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
            if (ahmgavms022_detail_status_submit.toUpperCase() == "WAITING FOR APPROVAL SECURITY") {
                send = "Reject";
                bypass = true;
            } else {
                _fw_setMessage(formObject, 0, 'Failed Update Data. This role just can update \'Waiting For Approval Security\' Data!!');
                return false;
            }
        } else {
            _fw_setMessage(formObject, 0, 'Failed Update Data. This role unable to update this data!');
            return false;
        }
    }



    if (ahmgavms022_roles == "RO_GAVMS_PICAHM" || ahmgavms022_roles == "RO_GAVMS_OFCSECT") {

        var expiryDate = $('#ahmgavms022_detail_ExpDate').val();

        var reason_reject = $('#ahmgavms022_detail_reject_note').val();
        var data = JSON.stringify({
            "id": id,
            "outTypeName" : department,
            "companyName" : company,
            "outId": outid,
            "outName": outname,
            "note": note,
            "outStatus": send,
            "reasonReject": reason_reject,
            "pic": ahmgavms022_roles,
            "passExpiryDateText": expiryDate,
            "dateStatus": dateForStatus
        });

        if (approve_reject_confirmation == 'APPROVE') {
            
            _fw_postJson(obj, data, ahmgavms022_url_root + '/approve-single', function (ret) {
                if (ret.status === '1') {
                    _fw_setMessage(formObject, 1, ret.message);
                    approve_reject_confirmation = '';
                    setTimeout(function () {
                        ahmgavms022_list_datatable_total_data -= 1;

                        if (ahmgavms022_list_datatable_total_data == 0) {
                            _fw_navigateSubPage(obj, 'list');
                            $('#ahmgavms022_filter_search_button').click();
                        } else {
                            _fw_navigateSubPage(obj, 'detail');
                        }
                        document.getElementById("ahmgavms022_detail_approve_button").disabled = true;
                        document.getElementById("ahmgavms022_detail_reject_button").disabled = true;

                        if ($('#ahmgavms022_detail_reject_button').hasClass('danger')) {
                            $('#ahmgavms022_detail_reject_button').removeClass('danger');
                        }
                    }, 3000);

                } else {
                    var errorMessage = '<li>' + ret.message + '</li>';
                    _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
                    return false;
                }
            });
        } else if (approve_reject_confirmation == 'REJECT') {
            if (reason_reject == "") {
                _fw_setMessage(formObject, 0, 'Reject Note Must Be Fill!');
                return false;
            } else {
                _fw_postJson(obj, data, ahmgavms022_url_root + '/reject-single', function (ret) {
                    if (ret.status === '1') {
                        _fw_setMessage(formObject, 1, ret.message);
                        approve_reject_confirmation = '';
                        setTimeout(function () {
                            ahmgavms022_list_datatable_total_data -= 1;

                            if (ahmgavms022_list_datatable_total_data == 0) {
                                _fw_navigateSubPage(obj, 'list');
                                $('#ahmgavms022_filter_search_button').click();
                            } else {
                                _fw_navigateSubPage(obj, 'detail');
                            }
                            document.getElementById("ahmgavms022_detail_approve_button").disabled = true;
                            document.getElementById("ahmgavms022_detail_reject_button").disabled = true;

                            if ($('#ahmgavms022_detail_reject_button').hasClass('danger')) {
                                $('#ahmgavms022_detail_reject_button').removeClass('danger');
                            }
                        }, 3000);
                    } else {
                        var errorMessage = '<li>' + ret.message + '</li>';
                        _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
                        return false;
                    }
                });
            }
        }

    } else {
        _fw_setMessage(formObject, 0, 'Role Not Exist!');
    }


}

function ahmgavms022_list_btn_sure_action(obj) {
    _fw_validation_clear(obj);
    var id = ahmgavms022_list_datatable_check_submit_array;

    var catcher;
    var forJSON;
    var geting = [];

    if (ahmgavms022_roles == "RO_GAVMS_PICAHM" || ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
        var send = "";
        var canSend = "";

        if (approve_reject_confirmation == 'Approve') {
            if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {

                for (let x in ahmgavms022_list_datatable_check_submit_array) {
                    var getid = ahmgavms022_list_datatable_check_submit_outstatus_array[x].slice(1);

                    if (getid == 'WAITING FOR APPROVAL SECURITY') {
                        _fw_setMessage(formObject, 0, `Cannot Process Outsource Status with data 'WAITING FOR APPROVAL SECURITY'`);
                        return false;
                    }
                }

                canSend = "Waiting for Approval PIC";
                send = "Waiting for Approval Security";
            } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
                canSend = "Waiting for Approval Security";
                send = "Active"
            } else {
                send = "";
            }
        } else if (approve_reject_confirmation == 'Reject') {
            if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {

                for (let x in ahmgavms022_list_datatable_check_submit_array) {
                    var getid = ahmgavms022_list_datatable_check_submit_outstatus_array[x].slice(1);

                    if (getid == 'WAITING FOR APPROVAL SECURITY') {
                        _fw_setMessage(formObject, 0, `Cannot Process Outsource Status with data 'WAITING FOR APPROVAL SECURITY'`);
                        return false;
                    }
                }

                canSend = "Waiting for Approval PIC";
                send = "Reject";
            } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
                canSend = "Waiting for Approval Security";
                send = "Reject"
            } else {
                send = "";
            }
        }

        var reason_reject = $('#ahmgavms022_reject_note').val();

        for (let x in ahmgavms022_list_datatable_check_submit_array) {

            var forGetArray = parseInt(x);
            forGetArray += 1;
            var getOutid = ahmgavms022_list_datatable_check_submit_outid_array[x].slice(1);
            var getOutName = ahmgavms022_list_datatable_check_submit_outname_array[x].slice(1);
            var getOutStatus = ahmgavms022_list_datatable_check_submit_outstatus_array[x].slice(1);
            var getNote = ahmgavms022_list_datatable_check_submit_note_array[x].slice(1);

            catcher = {
                "id": ahmgavms022_list_datatable_check_submit_array[x],
                "outId": getOutid,
                "outName": getOutName,
                "note": getNote,
                "outStatus": send,
                "reasonReject": reason_reject,
                "pic": ahmgavms022_roles,
                "dateStatus": dateForStatus
            };
            geting.push(catcher);

        }
        forJSON = JSON.stringify(geting);

        if (approve_reject_confirmation == 'Approve') {
            _fw_postJson(obj, forJSON, ahmgavms022_url_root + '/approve', function (ret) {
                if (ret.status === '1') {
                    _fw_setMessage(formObject, 1, ret.message);
                    setTimeout(function () {
                        approve_reject_confirmation = '';
                        ahmgavms022_list_datatable_check_submit_array = [];
                        ahmgavms022_list_datatable.ajax.reload();
                        _fw_navigateSubPage(obj, 'list');
                    }, 3000);
                } else {
                    var errorMessage = '<li>' + ret.message + '</li>';
                    _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
                }
            });
        } else if (approve_reject_confirmation == 'Reject') {

            if (reason_reject == "") {
                _fw_setMessage(formObject, 0, 'Reject Note Must Be Fill!');
                return false;
            } else {
                _fw_postJson(obj, forJSON, ahmgavms022_url_root + '/reject', function (ret) {
                    if (ret.status === '1') {
                        _fw_setMessage(formObject, 1, ret.message);
                        approve_reject_confirmation = '';
                        setTimeout(function () {
                            approve_reject_confirmation = '';
                            ahmgavms022_list_datatable_check_submit_array = [];
                            ahmgavms022_list_datatable.ajax.reload();
                            _fw_navigateSubPage(obj, 'list');
                        }, 3000);
                    } else {
                        var errorMessage = '<li>' + ret.message + '</li>';
                        _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
                    }
                });
            }
        }
    } else {
        _fw_setMessage(formObject, 0, 'Role Not Exist!');
    }
}

//[Begin] Image Preview Modal
function ahmgavms022_PreviewHandler(obj, idx, type) {
    var modal = document.getElementById("myModal");
    var modalImg = document.getElementById("img01");

    if (idx.endsWith('Photo')) {
        modal.style.display = "block";
        modalImg.src = "data:image/jpg;base64, " + ahmgavms022_Photo_Temp;

    } else if (idx.endsWith('Ktp')) {
        modal.style.display = "block";
        modalImg.src = "data:image/jpg;base64, " + ahmgavms022_Ktp_Temp;

    }

}
var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];
span.onclick = function () {
    modal.style.display = "none";
}

function ahmgavms022_PreviewMultipleHandler(obj, fileType, idx, indexFile, type) {

    var modal = document.getElementById("myModal");
    var modalImg = document.getElementById("img01");

    if (fileType == 'vaccine') {
        modal.style.display = "block";
        modalImg.src = "data:image/jpg;base64, " + ahmgavms022_Vac_array_Temp[indexFile].name;
    } else if (fileType == 'attachment') {
        modal.style.display = "block";
        modalImg.src = "data:image/jpg;base64, " + ahmgavms022_Attach_array_Temp[indexFile].name;
    }
}
//[End] Imgage Preview Modal

function Export() {

    var OutsourceIdFilter = $('#ahmgavms022_filter_OutId').val();
    var OutsourceNameFilter = $('#ahmgavms022_filter_OutName').val();
    var NikFilter = $('#ahmgavms022_filter_Nik').val();
    var PeriodeFromFilter = $('#ahmgavms022_filter_PeriodeFrom').val();
    var PeriodeToFilter = $('#ahmgavms022_filter_PeriodeTo').val();
    var PassCardNumberFilter = $('#ahmgavms022_filter_PassCardNum').val();
    var PicAhmFilter = $('#ahmgavms022_filter_IdPicAhm').val();

    if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Pic').val();

    } else {
        var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType_Ofc').val();

    }

    var OutsourceCompanyFilter = $('#ahmgavms022_filter_IdOutCompany').val();
    var OutsourceStatusFilter = $('#ahmgavms022_filter_OutStatus').val();
    var PlantFilter = $('#ahmgavms022_filter_Plant').val();
    var Covid19VaccineStatusFilter = $('#ahmgavms022_filter_CovVacStat').val();

    var downloadForm = document.getElementById('ahmgavms022_download_form');
    downloadForm.action = ahmgavms022_url_root + '/exreg';
    downloadForm.oi.value = OutsourceIdFilter;
    downloadForm.on.value = OutsourceNameFilter;
    downloadForm.n.value = NikFilter;
    downloadForm.pf.value = PeriodeFromFilter;
    downloadForm.pt.value = PeriodeToFilter;
    downloadForm.pcn.value = PassCardNumberFilter;
    downloadForm.pa.value = PicAhmFilter;
    downloadForm.ot.value = OutsourceTypeFilter;
    downloadForm.oc.value = OutsourceCompanyFilter;
    downloadForm.os.value = OutsourceStatusFilter;
    downloadForm.p.value = PlantFilter;
    downloadForm.c19vs.value = Covid19VaccineStatusFilter;
    downloadForm.ui.value = ahmgavms022_userid;
    downloadForm.ro.value = ahmgavms022_roles;
    downloadForm.sortCol.value = ahmgavms022_excel_order_col;
    downloadForm.sort.value = ahmgavms022_excel_order;
    downloadForm.token.value = ahmgavms022_token;


    downloadForm.submit();
}
