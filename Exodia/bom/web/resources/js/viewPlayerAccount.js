/**
 * Created by manlm1 on 10/7/2015.
 */

$(document).ready(function () {
    var table = $('#myTable').DataTable({
        "iDisplayLength": 20,
        "bLengthChange": false,
        "bSort": false,

        initComplete: function () {
            var api = this.api();

            // search status
            var columnStatus = api.column(2);
            var selectStatus = $('<select id="search-status" class="form-control" name="search-status" style="width: 100%">' +
                '<option value="" default selected>Status</option></select>')
                .appendTo("#th-search-status")
                .on('change', function () {
                    var val = $.fn.dataTable.util.escapeRegex(
                        $(this).val()
                    );

                    columnStatus
                        .search(val ? '^' + val + '$' : '', true, false)
                        .draw();
                });

            columnStatus.data().unique().sort().each(function (d) {
                selectStatus.append('<option value="' + d + '">' + d + '</option>')
            });
        }
    });

    // search email
    $('input', '#th-search-email').on('keyup change', function () {
        table
            .column(1)
            .search(this.value)
            .draw();
    });
});

function getSearchValue() {
    $("#txtSearchEmail").val($("#search-email").val());
    $("#txtSearchStatus").val($("#search-status").val());
    $("#export-form").submit();
}

function deleteAccount(param) {
    $("#delete-email").val(param);
    $('#deleteModal').modal('show');
}

function submitDeleteAccount() {
    $("#deleteForm").submit();
}