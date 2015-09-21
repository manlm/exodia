/**
 * Created by manlm1 on 9/21/2015.
 */

$(document).ready(function () {
    var table = $('#myTable').DataTable({
        "iDisplayLength": 20,
        "bLengthChange": false,
        "bSort": false,

        initComplete: function () {
            var api = this.api();

            // search role
            var columnRole = api.column(3);
            var selectRole = $('<select class="form-control" name="search-role"><option value="" default selected>Select Role</option></select>')
                .appendTo("#search-role")
                .on('change', function () {
                    var val = $.fn.dataTable.util.escapeRegex(
                        $(this).val()
                    );

                    columnRole
                        .search(val ? '^' + val + '$' : '', true, false)
                        .draw();
                });

            columnRole.data().unique().sort().each(function (d) {
                selectRole.append('<option value="' + d + '">' + d + '</option>')
            });

            // search status
            var columnStatus = api.column(4);
            var selectStatus = $('<select class="form-control" name="search-role"><option value="" default selected>Select Status</option></select>')
                .appendTo("#search-status")
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

    // search username
    $('#search-username').html('<input class="form-control" type="text" placeholder="Search by usernname"/>');
    $('input', '#search-username').on('keyup change', function () {
        table
            .column(1)
            .search(this.value)
            .draw();
    });

    // search email
    $('#search-email').html('<input class="form-control" type="text" placeholder="Search by email"/>');
    $('input', '#search-email').on('keyup change', function () {
        table
            .column(2)
            .search(this.value)
            .draw();
    });
});
