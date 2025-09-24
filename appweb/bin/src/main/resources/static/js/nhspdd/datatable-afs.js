$(document).ready(function () {
    $('#dtabla thead th').each(function () {
        var title = $(this).text();

        if (title != "Acciones") {
            $(this).html('<div class="form-group" id="centrado2">' + '<label for="th" class="blanco">' + title + '</label>' + '<div class="centrado">' + '<input class="form-control" id="nombre1" type="text"  placeholder="&#xf0b0; Filtrar ' + title + '" />' + '</div>' + '</div>');
        }

    });
    
    var table = $('#dtabla').DataTable({
    	"processing" : true,
        "language": {
            "info": '<div class="formato">_END_ de _TOTAL_ registros</div>',
            "infoEmpty": '<div class="formato">0 de 0.</div>',
            "infoFiltered": "(filtrados de un total de _MAX_ registros)",
            "zeroRecords": "No se han encontrado coincidencias.",
            "responsive": true,
            "paginate": {
                "first": '<img src="img/icono-flecha-doble-izquierda.png" class="flecha-paginador">',
                "last":  '<img src="img/icono-flecha-doble-derecha.png" class="flecha-paginador">',
                "next": '<img src="img/icono-flecha-derecha.png" class="flecha-paginador">',
                "previous":'<img src="img/icono-flecha-izquierda.png" class="flecha-paginador">'
            },
            "lengthMenu": "_MENU_",
        },

        "lengthMenu": [[5, 10, 20, 50, -1], [5, 10, 20, 50, "Todos"]],
        "pageLength": 5,
        "pagingType": "full_numbers",
        "dom": "<'row'<'col-sm-5 col-md-7'>>" +
                "<'row'<'col-sm-8 col-md-7'tr>>" +
                "<'row'<'col-sm-8 col-md-7'><'col-sm-2 col-md-1'l><'col-sm-1 col-md-1'i><'col-sm-1 col-md-3'p>>",

    });
    table
    .order( [ 0, 'desc' ] )
    .draw();
    $('div.dataTables_length select').addClass("formato-2");

    
   // Apply the search
    table.columns().every(function () {
        var that = this;

        $('input', this.header()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                        .search(this.value)
                        .draw();
            }
        });
    });
    
});

