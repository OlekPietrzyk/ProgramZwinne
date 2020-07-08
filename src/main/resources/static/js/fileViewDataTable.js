$(document).ready(function () {

    var oTable = $('#myTable');

    oTable.dataTable( {

        "oLanguage": {
            "sLengthMenu": "Wyświetl po _MENU_ plików",
            "sSearch": "Wyszukaj:",
            "sInfo":"Wyświetlono od _START_ do _END_ z _TOTAL_ plików",
            "sInfoEmpty":"Nie znaleziono",
            "sInfoFiltered": "( Wyszukiwano w _MAX_ rekordach )",
            "sZeroRecords": "Nie znaleziono dopasowań",
            "sEmptyTable": "Brak plików",
            "oPaginate": {
                "sPrevious": "Poprzednia strona",
                "sNext": "Następna strona"
            }
        }

    }).$('tr').css('backgroundColor', 'transparent ' );

    ;
});