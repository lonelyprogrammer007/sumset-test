$(document).ready(function () {
	
	
	$('#dtablaEntidades  thead th').each( function () {
        var title = $(this).text();
        if (title != "Acción") {
        $(this).html( '<div class="form-group" id="centrado2">' + '<label for="th" class="blanco">' + title + '</label>' + '<div class="centrado">' + '<input class="form-control filter" id="nombre1" type="text"  placeholder="&#xf0b0; Filtrar ' + title + '" />' + '</div>' + '</div>' );
        }
    } );
	


    $('#dtablaUsuarios thead th').each(function () {
        var title = $(this).text();
        if(title == "Fecha de creación"){
        	$(this).html('<div class="form-group" id="centrado2">' + '<label for="th" class="blanco">' + title + '</label>' + '<div class="centrado">' + '<input class="form-control filter" id="nombre1" type="date"  placeholder="&#xf0b0; Filtrar ' + title + '" />' + '</div>' + '</div>');
        }
        if (title != "Acción" && title != "Fecha de creación") {
            $(this).html('<div class="form-group" id="centrado2">' + '<label for="th" class="blanco">' + title + '</label>' + '<div class="centrado">' + '<input class="form-control filter" id="nombre1" type="text"  placeholder="&#xf0b0; Filtrar ' + title + '" />' + '</div>' + '</div>');
        }

    });
    $('#dtablaAdministrativo thead th').each(function () {
        var title = $(this).text();

        if (title != "Acción") {
            $(this).html('<div class="form-group" id="centrado2">' + '<label for="th" class="blanco">' + title + '</label>' + '<div class="centrado">' + '<input class="form-control filter" id="nombre1" type="text"  placeholder="&#xf0b0; Filtrar ' + title + '" />' + '</div>' + '</div>');
        }

    });
    $('#dtabla2 thead th').each(function () {
        var title_1 = $(this).text();

        if (title_1 != "Acción") {
            $(this).html('<div class="form-group" id="centrado2">' + '<label for="th" class="blanco">' + title_1 + '</label>' + '<div class="centrado">' + '<input class="form-control filter" id="nombre1" type="text"   placeholder="&#xf0b0; Filtrar ' + title_1 + '" />' + '</div>' + '</div>');
        }

    });
    
    $('#dtablaSectorial thead th').each(function () {
        var title_2 = $(this).text();

        if (title_2 != "Acción") { 
            $(this).html('<div class="form-group" id="centrado2">' + '<label for="th" class="blanco">' + title_2 + '</label>' + '<div class="centrado">' + '<input class="form-control filter" id="nombre1" type="text"   placeholder="&#xf0b0; Filtrar ' + title_2 + '" />' + '</div>' + '</div>');
        }

    });
    $('#dtablaPlantilla thead th').each(function () {
        var title_2 = $(this).text();

        if (title_2 != "Acción") {
            $(this).html('<div class="form-group" id="centrado2">' + '<label for="th" class="blanco">' + title_2 + '</label>' + '<div class="centrado">' + '<input class="form-control filter" id="nombre1" type="text"   placeholder="&#xf0b0; Filtrar ' + title_2 + '" />' + '</div>' + '</div>');
        }

    });
    

    $('#dtablaAsignarEntidad thead th').each(function () {
        var title_3 = $(this).text();
        if (title_3 == "Código entidad" || title_3 == "Entidad") {
            $(this).html('<div class="form-group" id="centrado2">' + '<label for="th" class="blanco">' + title_3 + '</label>' + '<div class="centrado">' + '<input class="form-control filter" id="nombre1" type="text"   placeholder="&#xf0b0; Filtrar ' + title_3 + '" />' + '</div>' + '</div>');
        }

    });
    $.fn.DataTable.ext.pager.numbers_length = 5;

    

    var tableEntidades = $('#dtablaEntidades').DataTable({
    	"orderCellsTop": true,
    	"order": [ 0, 'desc' ],
    	"columnDefs": [
    	    { "orderable": false, "targets":5}
    	  ],
    	"processing" : true,
    	"ordering": true,
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
    $('div.dataTables_length select').addClass("formato-2");
    $.fn.DataTable.ext.pager.numbers_length = 5;
    var tableUsuarios = $('#dtablaUsuarios').DataTable({
    	"order": [[ 0, "desc" ]],
    	"columnDefs": [
    	    { "orderable": false, "targets":8}
    	  ],
    	"processing" : true,
        "language": {
            "info": '<div class="formato">_END_ de _TOTAL_ registros</div>',
            "infoEmpty": '<div class="formato">0 de 0.</div>',
            "infoFiltered": "(filtrados de un total de _MAX_ registros)",
            "zeroRecords": "No se han encontrado coincidencias.",
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
    $('div.dataTables_length select').addClass("formato-2");
    $.fn.DataTable.ext.pager.numbers_length = 5;
    var tableAdministrativos = $('#dtablaAdministrativo').DataTable({
    	
    	"order": [[ 0, "desc" ]],
    	"columnDefs": [
    	    { "orderable": false, "targets":6}
    	  ],
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
    $('div.dataTables_length select').addClass("formato-2");
    
    
    $.fn.DataTable.ext.pager.numbers_length = 5;
    var table_2 = $('#dtabla2').DataTable({

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
    $('div.dataTables_length select').addClass("formato-2");
    
    $.fn.DataTable.ext.pager.numbers_length = 5;
    var tableSectorial = $('#dtablaSectorial').DataTable({
    	"order": [[ 0, "desc" ]],
    	"columnDefs": [
    	    { "orderable": false, "targets":6}
    	  ],
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
    $('div.dataTables_length select').addClass("formato-2");
    

    $.fn.DataTable.ext.pager.numbers_length = 5;
    var tableAsignarEntidad = $('#dtablaAsignarEntidad').DataTable({
    	"order": [[ 0, "desc" ]],
    	"columnDefs": [
    	    { "orderable": false, "targets": [2, 3] }
    	  ],
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
    $('div.dataTables_length select').addClass("formato-2");
    $.fn.DataTable.ext.pager.numbers_length = 5;

    var tablePlantilla = $('#dtablaPlantilla').DataTable({
    	"processing" : true, 
    	"columnDefs": [
    	    { "orderable": false, "targets":6}
    	  ],
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
    $('div.dataTables_length select').addClass("formato-2");
   // Apply the search
    tableEntidades.columns().every(function () {
        var that = this;

        $('input', this.header()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                        .search(this.value)
                        .draw();
            }
        });
    });
    
    tableUsuarios.columns().every(function () {
        var that = this;

        $('input', this.header()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                        .search(this.value)
                        .draw();
            }
        });
    });
    
    tableAdministrativos.columns().every(function () {
        var that = this;

        $('input', this.header()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                        .search(this.value)
                        .draw();
            }
        });
    });
    
    table_2.columns().every(function () {
        var that = this;

        $('input', this.header()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                        .search(this.value)
                        .draw();
            }
        });
    });
    
    tableSectorial.columns().every(function () {
        var that = this;

        $('input', this.header()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                        .search(this.value)
                        .draw();
            }
        });
    });
    
    tablePlantilla.columns().every(function () {
        var that = this;

        $('input', this.header()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                        .search(this.value)
                        .draw();
            }
        });
    });
    
//    var filterIndexes = [0, 1]; 
//    $('td', '#filter').each(function (i) { 
//        if ($.inArray(i, filterIndexes) != -1) { 
//         this.innerHTML = fnCreateSelect(oTable.fnGetColumnData(i)); 
//         $('select', this).change(function() { 
//          oTable.fnFilter($(this).val(), i); 
//         }); 
//        } 
//    });
    
    
   

});

$(document).ready(function () {

    var buttonpressed;
    $('.myFormButton').click(function () {
        buttonpressed = $(this).attr('name')
    })
    $(document).on('submit', '#form', function (e) {

        e.preventDefault();
        var gestion = "";
        var alerta = "";

        var data = $(this).serialize();
        var table = $('#dtabla').DataTable();
        var pagina_actual = table.page();
        var pag_len = table.page.len();

        if (buttonpressed == "save") {
            gestion = document.getElementById("agregar").value;
            alerta = "agregar";
        } else {
            gestion = document.getElementById("edit").value;
            alerta = "edit";
        }
        // AJAX.
        $.ajax({
            type: 'POST',
            url: '/appweb/' + gestion,
            data: data,
            beforeSend: function () {
                Swal.fire({
                    title: 'Cargando...',
                    position: 'top',
                    allowOutsideClick: () => !Swal.isLoading(),
                    onBeforeOpen: () => {
                        Swal.showLoading();
                    }
                });
            },

            success: function (data) {
                Swal.close();

                if (gestion == "crear_equipamientos") {
                    $('#crearEquipamientoModal').modal('hide');

                } else if (gestion == "crear_componente_gasto") {
                        $('#crearCGModal').modal('hide');

                    } else if (gestion == "crear_pot") {
                            $('#modalPotCrear').modal('hide');

                        } else if (gestion == "crear_linea_inversion") {
                                $('#agregarLineaInversionModal').modal('hide');

                            } else if (gestion == "crear_estructura_meta") {
                                    $('#crearEstructuraMetaModal').modal('hide');

                                } else if (gestion == "gestion_terr") {
                                        $('#agregarTerritorializacionModal').modal('hide');

                                    } else if (gestion == "crearArgumento") {
                                            $('#crearArgumentoModal').modal('hide');

                                        } else if (gestion == "editar_equipamientos") {
                                                $('#editarEquipamientoModal').modal('hide');

                                            } else if (gestion == "editar_componente_gasto") {
                                                    $('#editarComponenteGastoModal').modal('hide');

                                                } else if (gestion == "editar_ProyectosPOT") {
                                                        $('#modalPotEditar').modal('hide');

                                                    } else if (gestion == "editar_linea_inversion") {
                                                            $('#editarLineaInversionModal').modal('hide');

                                                        } else if (gestion == "editar_estructura_meta") {
                                                                $('#editarEstructuraMetaModal').modal('hide');

                                                            } else if (gestion == "editarParametro") {
                                                                    $('#editarParametroModal').modal('hide');

                                                                } else if (gestion == "editarConsecutivo") {
                                                                        $('#editarConsecutivoModal').modal('hide');

                                                                    } else if (gestion == "editar-argumento") {
                                                                            $('#editarArgumentoModal').modal('hide');

                                                                        }
                                                   
                                
                            

                        
                    
                

                table.destroy();
                filtro(table, data, pag_len, pagina_actual);

            },
            error:
                    function (e) {
                        Swal.close();

                    }
            ,
            complete:
                    function (e) {
                        Swal.close();
                        if (alerta == "agregar") {
                            exitoLoad();
                        } else {
                            editarLoad();
                        }

                    }
        });
        return false;
    });
});//
function exitoLoad() {
    Swal.fire({
        title: 'Operación exitosa!',
        text: "Registro creado",
        icon: 'success',
        allowOutsideClick: () => false
    })
}
function editarLoad() {
    Swal.fire({
        title: 'Operación exitosa!',
        text: "Registro modificado",
        icon: 'success',
        allowOutsideClick: () => false
    })
}
function crearEntidad(codigo,entidad,sigla,dominio){
	
	  $.ajax({
			
          type: 'GET',
          url: '/appweb/' + 'crear-ent/' + entidad + '/' + sigla+'/'+dominio+'/'+codigo,
          beforeSend: function () {
              Swal.fire({
                  title: 'Cargando...',
                  position: 'top',
                  allowOutsideClick: () => !Swal.isLoading(),
                  onBeforeOpen: () => {
                      Swal.showLoading();
                  }
              });
          },

          success: function (data) {
              Swal.close();
              console.log("success");
              console.log(data);
              href.location.replace(data);

          },
          error:
                  function (e) {
                      Swal.close();

                  }
          ,
          complete:
                  function (e) {
                      Swal.close();
                  console.log("complete");

                  }
      });
	
	
	
	
}

