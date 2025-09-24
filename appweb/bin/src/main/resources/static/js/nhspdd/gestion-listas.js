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
   
       if (buttonpressed == "save") {
           gestion = document.getElementById("agregar").value;
           console.log(gestion);
           alerta = "agregar";
       } else {
           gestion = document.getElementById("edit").value;
           console.log(gestion);
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
                                                               }
               

// filtro(table, data, pag_len, pagina_actual);  

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
$(document).on('submit', '#formEntidad', function (e) {

        e.preventDefault();
        var gestion = "";
        var alerta = "";

        var data = $(this).serialize();
       
       

        if (buttonpressed == "save") {
            gestion = document.getElementById("agregar").value;
            alerta = "agregar";
        } else {
            gestion = document.getElementById("editEntidad").value;
            console.log(gestion);
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
    
                

 
// filtro(table, data, pag_len, pagina_actual);  

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
    
    $(document).on('submit', '#form2', function (e) {

        e.preventDefault();
        var gestion = "";
        var alerta = "";

        var data = $(this).serialize();
      

        if (buttonpressed == "save") {
            gestion = document.getElementById("agregarSect").value;
            alerta = "agregar";
        } else {
            gestion = document.getElementById("editSect").value;
           
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
    
                

 
// filtro(table, data, pag_len, pagina_actual);  

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

    $(document).on('submit', '#form3', function (e) {

        e.preventDefault();
        var gestion = "";
        var alerta = "";

        var data = $(this).serialize();
      
       
        if (buttonpressed == "save") {
            gestion = document.getElementById("agregarHist").value;
            alerta = "agregar";
          
        } else {
            gestion = document.getElementById("editHist").value;
           
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

                


    //filtro(table, data, pag_len, pagina_actual);  

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
        text: "El registro ha sido creado exitosamente",
        icon: 'success',
        allowOutsideClick: () => false
    }).then((result) => {
    	if (result.value) {
    		location.reload(true);
    	}
    })
}
function editarLoad() {
    Swal.fire({
        title: 'Operación exitosa!',
        text: "El registro ha sido actualizado exitosamente",
        icon: 'success',
        allowOutsideClick: () => false
    }).then((result) => {
    	if (result.value) {
    		location.reload(true);
    	}
    })
}
