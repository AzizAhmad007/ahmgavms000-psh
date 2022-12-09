var ahmgavms022_url_root = 'req/ahmgavms022/jx05/ahmgavms000-psh/rest/ga/vms022';

var ahmgavms022_roles = "";

var coba;

var ahmgavms022_list_datatable_total_data;

var ahmgavms022_list_datatable = null;

var ahmgavms022_order_global = null;
var ahmgavms022_sort_global = null;

var ahmgavms022_list_datatable_check = null;
var ahmgavms022_list_datatable_check_array = [];
var ahmgavms022_list_datatable_check_submit_array = [];
var ahmgavms022_list_datatable_current_page = null;

var ahmgavms022_detail_plants_datatable = [];
var ahmgavms022_detail_plants_datatable_data = {};
var ahmgavms022_detail_gates_datatable = [];
var ahmgavms022_detail_gates_datatable_data = {};
var ahmgavms022_detail_pic_datatable = [];
var ahmgavms022_detail_pic_datatable_data = {};
var ahmgavms022_detail_status_submit = "";

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
var approve_reject_confirmation = '';
var formObject = $('#ahmgavms022_form');

var ahmgavms022_ = new Object();
var data = [];

ahmgavms022_.data = {};

//=========== document.ready begin ===========


var thisform = document.getElementById("ahmgavms022form");

ahmgavms022_list_datatable_check = null;

_fw_filterpanel_toggle($('#ahmgavms022_filter_collapse_button'));

var objectObj = new Object();

ahmgavms022_get_form_authorization();

// # begin for set date in filter session
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
var date_temp = new Date(today.getFullYear(), today.getMonth()+1, 0);
var date_temp_string = date_temp.toString();
var get_last_date = date_temp_string.substring(8, 10);

var ahmgavms022_date_periode_default = new Date();
var ahmgavms022_date_to_default = new Date();

ahmgavms022_date_periode_default.setDate(1);
ahmgavms022_date_to_default.setDate(get_last_date);

$('#ahmgavms022_filter_PeriodeFrom').val(ahmgavms022_date_periode_default.toShortFormat());
$('#ahmgavms022_filter_PeriodeTo').val(ahmgavms022_date_to_default.toShortFormat());

// $('#ahmgavms022_filter_PeriodeFrom').val("1-Jan-1900");
// $('#ahmgavms022_filter_PeriodeTo').val("1-Jan-1900");
// # end for set date in filter session

// $('#ahmgavms022_filter_OutName').val('reza');

//=========== document.ready end ===========

function ahmgavms022_get_form_authorization() {
    var formObject = $('#ahmgavms022_form');
    var data = JSON.stringify({});

    _fw_postJson(formObject, data, ahmgavms022_url_root + '/getformauth', function (ret) {
        if (ret.status === '1') {
            ahmgavms022_roles = ret.data[0].roleName;
            // ahmgaers002_form_auth_add = ret.data.isAdd;
            // ahmgaers002_form_auth_edit = ret.data.isEdit;
            // ahmgaers002_form_auth_delete = ret.data.isDelete;
        }

        if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
            document.getElementById("ahmgavms022_filter_OutStatus").selectedIndex = 2;

        } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
            $("#ahmgavms022_detail_ExpDate_button").removeAttr("disabled");
            $("#ahmgavms022_detail_ExpDate").removeAttr("disabled");
            document.getElementById("ahmgavms022_filter_OutStatus").selectedIndex = 3;
        } else {
            _fw_setMessage(formObject, 0, '<ul class="errorList">Invalid Roles!</ul>');
            $('#ahmgavms022_list_datatable').remove();
            $('#ahmgavms022_filter_approve_button').remove();
            $('#ahmgavms022_filter_reject_button').remove();
            $('#ahmgavms022form').remove();
            return false;
        }

        ahmgavms022_list_datatable = ahmgavms022_list_datatable_init();

        $('ahmgavms022_filter_search_button').click();
    });
}

function ahmgavms022_list_datatable_init() {
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

                var OutsourceIdFilter = $('#ahmgavms022_filter_OutId').val();
                var OutsourceNameFilter = $('#ahmgavms022_filter_OutName').val();
                var NikFilter = $('#ahmgavms022_filter_Nik').val();
                var PeriodeFromFilter = $('#ahmgavms022_filter_PeriodeFrom').val();
                var PeriodeToFilter = $('#ahmgavms022_filter_PeriodeTo').val();
                var PassCardNumberFilter = $('#ahmgavms022_filter_PassCardNum').val();
                var PicAhmFilter = $('#ahmgavms022_filter_IdPicAhm').val();
                var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType').val();
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

                ahmgavms022_list_datatable_current_page = d.start;

                ahmgavms022_detail_rownum_getter = 0;
                ahmgavms022_detail_rownum_getter += d.start;

                ahmgavms022_detail_param_getter = objectSearch;

                ahmgavms022_order_global = d.order[0].dir;
                ahmgavms022_sort_global = d.columns[d.order[0].column].data;

                return JSON.stringify({
                    offset: d.start,
                    limit: d.length,
                    sort: d.columns[d.order[0].column].data,
                    order: d.order[0].dir,
                    search: objectSearch
                });
            }
        },
        initComplete: function (settings, json) {
        },
        columns: [
            // { "searchable": false },
            // { data: null, defaultContent: '', className: 'control', orderable: false },
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
            // { data: 'beginDate', name: 'beginDate', visible: false },
            { data: 'beginDateText', name: 'beginDateText', className: 'dt-head-center dt-body-left', orderable: true },
            // { data: 'endDate', name: 'endDate', visible: false },
            { data: 'endDateText', name: 'endDateText', className: 'dt-head-center dt-body-left', orderable: true },
            { data: 'passNumber', name: 'passNumber', className: 'dt-head-center dt-body-left', orderable: true },
            // { data: 'passExpiryDate', name: 'passExpiryDate', visible: false },
            { data: 'passExpiryDateText', name: 'passExpiryDateText', className: 'dt-head-center dt-body-left', orderable: true },
            { data: 'phoneNo', name: 'phoneNo', visible: false, orderable: false },
            // { data: 'fileNamePhoto', name: 'fileNamePhoto', visible: false },
            // { data: 'filePhoto', name: 'filePhoto', visible: false },
            // { data: 'supplier', name: 'supplier', visible: false },
            // { data: 'job', name: 'job', visible: false },
            // { data: 'accessReader', name: 'accessReader', visible: false },
            // { data: 'canteen', name: 'canteen', visible: false },
            // { data: 'securityGate', name: 'securityGate', visible: false },
            { data: 'note', name: 'note', visible: false, orderable: false },
            // { data: 'fileNameKtp', name: 'fileNameKtp', visible: false },
            // { data: 'fileKtp', name: 'fileKtp', visible: false },
            // { data: 'vacType', name: 'vacType', visible: false },
            // { data: 'vacDate', name: 'vacDate', visible: false },
            { data: 'vacSummary', name: 'vacSummary', visible: false, orderable: false },
            { data: 'vacNote', name: 'vacNote', visible: false, orderable: false },
            // { data: 'diclaimer', name: 'diclaimer', visible: false },
            // { data: 'gateName', name: 'gateName', visible: false },
            // { data: 'pic', name: 'pic', visible: false },
            // { data: 'access', name: 'access', visible: false },
            // { data: 'vmodi', name: 'vmodi', visible: false },
            // { data: 'dmodi', name: 'dmodi', visible: false },
            // { data: 'plants', name: 'plants', visible: false },
            // { data: 'gates', name: 'gates', visible: false },
            // { data: 'nrps', name: 'nrps', visible: false },
            // { data: 'fileVaccines', name: 'fileVaccines', visible: false },
            // { data: 'fileSk', name: 'fileSk', visible: false },
            { data: 'modifyBy', name: 'modifyBy', className: 'dt-head-center dt-body-left', orderable: true },
            // { data: 'modifyDate', name: 'modifyDate', visible: false },
            { data: 'modifyDateText', name: 'modifyDateText', className: 'dt-head-center dt-body-left', orderable: true },
            { data: 'filePhoto', name: 'filePhoto', visible: false, orderable: false },
            { name: 'action', className: 'dt-head-center dt-body-center', orderable: false },
            { name: 'rowNum', data: null, orderable: false, className: 'dt-head-center dt-body-right', visible: false, orderable: false },
        ],
        // order: [[1, 'desc']],
        createdRow: function (row, data, dataIndex) {
            // $(row).find('td:eq(0)').attr('data-field-id', 'checkbox');
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
                    return $("#ahmgavms022_list_check").clone().html().replaceAll("{id}", data.id).replaceAll("{outStatus}", data.outStatus);
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

    new $.fn.dataTable.FixedColumns(datatable, {
        leftColumns: 5,
        rightColumns: 3
    });

    coba = datatable;

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
                // ahmgavms022_detail_rownum_getter_from_pages = 0;
                $('#' + $(this).closest('.tablePaging').attr('data-table-id')).dataTable().api().page('first').draw('page');
                return false;
            }),
            btnPrev = $('<a href="#" class="icon icon-arrow-left-4 button medium default"></a>&nbsp;').click(function (e) {
                e.stopPropagation();
                // ahmgavms022_detail_rownum_getter_from_pages -= 10;
                $('#' + $(this).closest('.tablePaging').attr('data-table-id')).dataTable().api().page('previous').draw('page');
                return false;
            }),
            btnNext = $('<a href="#" class="icon icon-arrow-right-4 button medium default"></a>&nbsp;').click(function (e) {
                e.stopPropagation();
                // ahmgavms022_detail_rownum_getter_from_pages += 10;
                $('#' + $(this).closest('.tablePaging').attr('data-table-id')).dataTable().api().page('next').draw('page');
                return false;
            }),
            btnLast = $('<a href="#" class="icon icon-last-2 button medium default"></a>').click(function (e) {
                e.stopPropagation();

                // var ahmgavms022_throw = 0;

                // if ((ahmgavms022_list_datatable_total_data > 10) && (ahmgavms022_list_datatable_total_data % 10 == 0)) {
                //     ahmgavms022_throw = ahmgavms022_list_datatable_total_data - 10;
                // } else {
                //     ahmgavms022_throw = ahmgavms022_list_datatable_total_data;
                // }

                // var count_temp = String(ahmgavms022_throw);
                // var nim = count_temp.substring(count_temp.length - 1);
                // var num = count_temp.substring(0, count_temp.length - 1) + "0";

                // ahmgavms022_detail_rownum_getter_from_pages = Number(num);

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
        // if (tpg > 0) {
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

        console.log('isi ahmgavms022_list_datatable_total_data = ', ahmgavms022_list_datatable_total_data);
        console.log('isi ahmgavms022_detail_rownum_getter_from_pages = ', ahmgavms022_detail_rownum_getter_from_pages);
    }
}

function ahmgavms022_filter_search_button_action(obj) {

    var OutsourceIdFilter = $('#ahmgavms022_filter_OutId').val();
    var OutsourceNameFilter = $('#ahmgavms022_filter_OutName').val();
    var NikFilter = $('#ahmgavms022_filter_Nik').val();
    var PeriodeFromFilter = $('#ahmgavms022_filter_PeriodeFrom').val();
    var PeriodeToFilter = $('#ahmgavms022_filter_PeriodeTo').val();
    var PassCardNumberFilter = $('#ahmgavms022_filter_PassCardNum').val();
    var PicAhmFilter = $('#ahmgavms022_filter_IdPicAhm').val();
    var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType').val();
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

        ahmgavms022_detail_rownum_getter_from_pages = 0;
        
        ahmgavms022_list_datatable_check_array = [];
        
        ahmgavms022_list_datatable.ajax.reload();

        _fw_filterpanel_toggle($('#ahmgavms022_filter_collapse_button'));

        var countdatabody = JSON.stringify({
            offset: 0,
            limit: 0,
            search: objectSearch
        });

        _fw_postJson(obj, countdatabody, ahmgavms022_url_root + '/monitoring', function (ret) {
            if (ret.status === '1') {
                ahmgavms022_list_datatable_total_data = ret.recordsTotal;
            }
        });

    }
}

function ahmgavms022_detail_back_button_action(obj) {
    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='list']", objFrame);

    _fw_navigateSubPage(obj, 'list');
}

function ahmgavms022_filter_approve_reject_button(obj, val) {
    if (ahmgavms022_list_datatable_check_array.length == 0) {
        var errorMessage = '<li>At least must check 1 field</li>';
        _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
    } else {
        ahmgavms022_list_datatable_check_submit_array = ahmgavms022_list_datatable_check_array;

        for (let x in ahmgavms022_list_datatable_check_submit_array) {
            $('#' + ahmgavms022_list_datatable_check_submit_array[x] + '').prop('checked', false);
        }

        var objFrame = $(obj).closest('.tab-frame');
        var newFrame = $(".sub-page[data-section='list-confirmation']", objFrame);
        var htmlVal = '';

        htmlVal = htmlVal + '<h2>The following data will be ' + val + '. Are you sure?</h2>';
        htmlVal = htmlVal + '<p>' + ahmgavms022_list_datatable_check_submit_array.length / 2 + ' Items selected</p>';
        if (val == "Reject") {
            htmlVal += '<div class="input-control textarea" data-role="input-control">' +
                '<textarea name="ahmgavms022_reject_note" id="ahmgavms022_reject_note" type="text" placeholder="Insert The Reject Note" ></textarea>'
            '</div>';
        } else {
        }


        approve_reject_confirmation = val;

        $('#ahmgavms022_h2_confirmation_list').html(htmlVal);

        _fw_formResetFields(newFrame);

        _fw_navigateSubPage(obj, 'list-confirmation');
    }
}

function ahmgavms022_detail_button_previous_action(obj) {
    ahmgavms022_detail_rownum_getter -= 1;
    console.log('rownum = ', ahmgavms022_detail_rownum_getter);
    console.log(ahmgavms022_list_datatable_total_data);

    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='detail']", objFrame);
    _fw_formResetFields(newFrame);

    var ahmgavms022_detail_outType = "";
    var ahmgavms022_detail_area = "";

    if (ahmgavms022_detail_rownum_getter < 2) {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = true;
        document.getElementById("ahmgavms022_detail_button_next").disabled = false;
    } else {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = false;
        document.getElementById("ahmgavms022_detail_button_next").disabled = false;
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
                $('#ahmgavms022_detail_JobDetail').val(item.outStatus);
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
                        html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'PrevBtn" onclick="ahmgavms022PreviewMultipleHandler(this, \'vaccine\', \'ahmgavms022p02ExistImage' + i + 'Vac\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
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
                        html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'PrevBtn" onclick="ahmgavms022PreviewMultipleHandler(this, \'attachment\', \'ahmgavms022p04ExistImage' + i + 'Attach\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
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

                _fw_postJson(obj, dataParamPlantGate, ahmgavms022_url_root + '/get-plants', function (ret) {
                    if (ret.status === '1') {
                        $.each(ret.data, function (index, item) {
                            var data = {
                                seq: item.seq,
                                id: item.id,
                                name: item.name
                            }
                            ahmgavms022_detail_plants_datatable_data.push(data);
                        });
                        ahmgavms022_detail_plants_datatable = ahmgavms022_detail_plants_datatable_init();
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

                ahmgavms022_detail_pic_datatable_data = [];
                var dataParamPic = JSON.stringify({
                    "code": ahmgavms022_detail_outType,
                    "area": ahmgavms022_detail_area
                });

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

            });
        }
    });

}

function ahmgavms022_detail_button_next_action(obj) {
    ahmgavms022_detail_rownum_getter += 1;
    console.log('rownum = ', ahmgavms022_detail_rownum_getter);
    console.log(ahmgavms022_list_datatable_total_data);

    $('#ahmgavms022p02ImageVacList').empty();
    $('#ahmgavms022p02ImageAttachList').empty();

    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='detail']", objFrame);
    _fw_formResetFields(newFrame);

    var ahmgavms022_detail_outType = "";
    var ahmgavms022_detail_area = "";
    var ahmgavms022_detail_OutId = "";
    var ahmgavms022_detail_Nik = "";

    if (ahmgavms022_detail_rownum_getter > (ahmgavms022_list_datatable_total_data - 1)) {
        document.getElementById("ahmgavms022_detail_button_next").disabled = true;
        document.getElementById("ahmgavms022_detail_button_previous").disabled = false;
    } else {
        document.getElementById("ahmgavms022_detail_button_next").disabled = false;
        document.getElementById("ahmgavms022_detail_button_previous").disabled = false;
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
                $('#ahmgavms022_detail_JobDetail').val(item.outStatus);
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
                        html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'PrevBtn" onclick="ahmgavms022PreviewMultipleHandler(this, \'vaccine\', \'ahmgavms022p02ExistImage' + i + 'Vac\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
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
                        html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'PrevBtn" onclick="ahmgavms022PreviewMultipleHandler(this, \'attachment\', \'ahmgavms022p04ExistImage' + i + 'Attach\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
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
                        });
                        ahmgavms022_detail_plants_datatable = ahmgavms022_detail_plants_datatable_init();
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

                ahmgavms022_detail_pic_datatable_data = [];
                var dataParamPic = JSON.stringify({
                    "code": ahmgavms022_detail_outType,
                    "area": ahmgavms022_detail_area
                });

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
                        // ahmgavms022_detail_pic_datatable.ajax.reload();
                    }
                });

            });
        }
    });
}

function ahmgavms022_detail_approve_button_action(obj) {
    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='confirmation']", objFrame);
    var htmlVal = '';
    var status = $('#ahmgavms022_detail_JobDetail').val();
    var stringdend = $('#ahmgavms022_detail_EndEffDate').val();
    var stringdexp = $('#ahmgavms022_detail_ExpDate').val();
    var getting = [];

    // var a = stringdend.substring(stringdend.length, 7);
    var b = stringdexp.substring(stringdend.length, 7);
    var now = moment(new Date(), 'YYYY').toDate();
    var datenow = moment(now).format('YYYY');

    if (Number(b) > Number(datenow)) {
        var errorMessage = '<li>Expiry Date must be equal with this year!</li>';
        _fw_setMessage(formObject, 0, '<ul class="errorList">' + errorMessage + '</ul>');
        return false;
    }

    var catcher = {
        "endDateText": stringdend,
        "passExpiryDateText": stringdexp
    };
    getting.push(catcher);

    var forJSON = JSON.stringify(getting);

    _fw_postJson(obj, forJSON, ahmgavms022_url_root + '/check-date', function (ret) {
        if (ret.status === '1') {
            ahmgavms022_detail_status_submit = status;

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
    var status = $('#ahmgavms022_detail_JobDetail').val();

    ahmgavms022_detail_status_submit = status;

    if ($('#ahmgavms022_detail_disclaimer').prop("checked") == true) {
        approve_reject_confirmation = 'REJECT';
        htmlVal = htmlVal + '<h2>The following data will be reject. Are you sure?</h2>';
        htmlVal += '<div class="input-control textarea" data-role="input-control">' +
            '<textarea name="ahmgavms022_detail_reject_note" id="ahmgavms022_detail_reject_note" type="text" placeholder="Insert The Reject Note" ></textarea>'
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

function ahmgavms022datatable_add_button_action(obj) {
    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='detail']", objFrame);

    _fw_formResetFields(newFrame);

    _fw_navigateSubPage(obj, 'detail');
}

function ahmgavms022_detail_button_action(obj) {

    var objFrame = $(obj).closest('.tab-frame');
    var newFrame = $(".sub-page[data-section='detail']", objFrame);
    var getRow = ahmgavms022_list_datatable.row($(obj).parents('tr')).data();
    
    
    $('#ahmgavms022p02ImageVacList').empty();
    $('#ahmgavms022p02ImageAttachList').empty();
    
    
    ahmgavms022_detail_rownum_getter = ahmgavms022_detail_rownum_getter_from_pages + getRow.rowNum + 1;
    
    console.log(ahmgavms022_list_datatable_total_data);
    // ahmgavms022_detail_rownum_getter += getRow.rowNum;
    
    if (ahmgavms022_detail_rownum_getter < 2) {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = true;
    } else {
        document.getElementById("ahmgavms022_detail_button_previous").disabled = false;
    }
    
    if (ahmgavms022_detail_rownum_getter > (ahmgavms022_list_datatable_total_data - 1)) {
        document.getElementById("ahmgavms022_detail_button_next").disabled = true;
    } else {
        document.getElementById("ahmgavms022_detail_button_next").disabled = false;
    }

    _fw_formResetFields(newFrame);
    detail_id_for_confirm = getRow.id;
    var ahmgavms022_detail_area = getRow.area;
    var ahmgavms022_detail_outType = getRow.outType;

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
    $('#ahmgavms022_detail_JobDetail').val(getRow.outStatus);
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

    $("#ahmgavms022_detail_AttachKtp_Preview").attr('src', "data:image/jpg;base64, " + getRow.fileKtp);
    $("#ahmgavms022p02PhotoPreview").attr('src', "data:image/jpg;base64, " + getRow.filePhoto);
    ahmgavms022_Photo_Temp = getRow.filePhoto;
    ahmgavms022_Ktp_Temp = getRow.fileKtp;

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
            html += '        <a class="buttonvac" id="ahmgavms022p02Vac' + i + 'PrevBtn" onclick="ahmgavms022PreviewMultipleHandler(this, \'vaccine\', \'ahmgavms022p02ExistImage' + i + 'Vac\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
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
            html += '        <a class="buttonvac" id="ahmgavms022p04Attach' + i + 'PrevBtn" onclick="ahmgavms022PreviewMultipleHandler(this, \'attachment\', \'ahmgavms022p04ExistImage' + i + 'Attach\', \'' + i + '\',\'EDIT\');" style="cursor: pointer; margin-left: 85px;"><i class="glyphicon  glyphicon-search icon-search fg-white"></i></a>';
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
            });
            ahmgavms022_detail_plants_datatable = ahmgavms022_detail_plants_datatable_init();
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

    ahmgavms022_detail_pic_datatable_data = [];
    var dataParamPic = JSON.stringify({
        "code": ahmgavms022_detail_outType,
        "area": ahmgavms022_detail_area
    });

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

    _fw_navigateSubPage(obj, 'detail');
}

function ahmgavms022_detail_btn_sure_action(obj) {

    // approve_reject_confirmation = obj.val();
    _fw_validation_clear(obj);
    var id = detail_id_for_confirm;
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
            "outStatus": send,
            "reasonReject": reason_reject,
            "pic": ahmgavms022_roles,
            "passExpiryDateText": expiryDate
        });

        if (approve_reject_confirmation == 'APPROVE') {
            _fw_postJson(obj, data, ahmgavms022_url_root + '/approve-single', function (ret) {
                if (ret.status === '1') {
                    _fw_setMessage(formObject, 1, ret.message);
                    approve_reject_confirmation = '';
            setTimeout(function () {
                // ahmgavms022_list_datatable.ajax.reload();
                // $('#ahmgavms022_filter_search_button').click();
                _fw_navigateSubPage(obj, 'detail');
                
                // $('#ahmgavms022_filter_search_button').click();
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
                            _fw_navigateSubPage(obj, 'detail');
                            // ahmgavms022_list_datatable.ajax.reload();
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
                canSend = "Waiting For Approval PIC";
                send = "Waiting For Approval Security";
            } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
                canSend = "Waiting For Approval Security";
                send = "Active"
            } else {
                send = "";
            }
        } else if (approve_reject_confirmation == 'Reject') {
            if (ahmgavms022_roles == "RO_GAVMS_PICAHM") {
                canSend = "Waiting For Approval PIC";
                send = "Reject";
            } else if (ahmgavms022_roles == "RO_GAVMS_OFCSECT") {
                canSend = "Waiting For Approval Security";
                send = "Reject"
            } else {
                send = "";
            }
        }

        var reason_reject = $('#ahmgavms022_reject_note').val();
        console.log('ahmgavms022_list_datatable_check_submit_array = ', ahmgavms022_list_datatable_check_submit_array);

        for (let x in ahmgavms022_list_datatable_check_submit_array) {

            if ((x % 2) == 0) {
                var forGetArray = parseInt(x);
                forGetArray += 1;
                var outStatusValue = ahmgavms022_list_datatable_check_submit_array[forGetArray];

                catcher = {
                    "id": ahmgavms022_list_datatable_check_submit_array[x],
                    "outStatus": send,
                    "reasonReject": reason_reject,
                    "pic": ahmgavms022_roles
                };
                geting.push(catcher);
            } else if ((x % 2) != 0) {
                if (ahmgavms022_list_datatable_check_submit_array[x].toUpperCase() == canSend.toUpperCase()) {

                } else {
                    console.log('ahmgavms022_list_datatable_check_submit_array = ', ahmgavms022_list_datatable_check_submit_array[x].toUpperCase());
                    console.log('canSend = ', canSend.toUpperCase());
                    _fw_setMessage(formObject, 0, 'Failed Update Data. You\'ve selecting data that not for your role!');
                    return false;
                }
            }

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

function ahmgavms022_list_check_action(obj) {

    if ($(obj).prop('checked')) {
        selectedCheckboxId = $(obj).attr('data-id');
        selectedCheckboxOutStat = $(obj).attr('outstat');

        ahmgavms022_list_datatable_check_array.push(selectedCheckboxId, selectedCheckboxOutStat);
    } else {
        selectedCheckboxId = $(obj).attr('data-id');
        selectedCheckboxOutStat = $(obj).attr('outstat');
        var ahmgavms022_list_datatable_uncheck_temp = ahmgavms022_list_datatable_check_array.filter(ahmgavms022_uncheck_delete_list_value);
        ahmgavms022_list_datatable_check_array = ahmgavms022_list_datatable_uncheck_temp;
    }
}

function ahmgavms022_uncheck_delete_list_value(id) {
    return (id != selectedCheckboxId) && (id != selectedCheckboxOutStat);
}

//not finish yet
function ahmgavms022PreviewHandler(obj, idx, type) {
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

function test_function(obj) {

    var OutsourceIdFilter = $('#ahmgavms022_filter_OutId').val();
    var OutsourceNameFilter = $('#ahmgavms022_filter_OutName').val();
    var NikFilter = $('#ahmgavms022_filter_Nik').val();
    var PeriodeFromFilter = $('#ahmgavms022_filter_PeriodeFrom').val();
    var PeriodeToFilter = $('#ahmgavms022_filter_PeriodeTo').val();
    var PassCardNumberFilter = $('#ahmgavms022_filter_PassCardNum').val();
    var PicAhmFilter = $('#ahmgavms022_filter_IdPicAhm').val();
    var OutsourceTypeFilter = $('#ahmgavms022_filter_IdOutType').val();
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

    downloadForm.submit();
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
        // drawCallback: ahmgavms022_detail_plants_datatable_draw_callback,
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

    // function ahmgavms022_detail_ListDateAccessLevel_datatable_init() {
    var datatable = $('#ahmgavms022_detail_gates_datatable').DataTable({
        destroy: true,
        filter: false,
        ordering: true,
        scrollX: false,
        scrollCollapse: false,
        paging: false,
        data: ahmgavms022_detail_gates_datatable_data_temp,
        // drawCallback: ahmgavms022_detail_gates_datatable_draw_callback,
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
            // $(row).find('td:eq(0)').attr('data-field-id', 'checkbox');
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

function ahmgavms022PreviewMultipleHandler(obj, fileType, idx, indexFile, type) {

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
