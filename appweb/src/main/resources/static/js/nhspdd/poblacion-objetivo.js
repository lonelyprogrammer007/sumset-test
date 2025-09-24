// $(document).ready(function () {
//   let accion = false;
//   $("#tab09-action").click(function (e) {
//     if (!accion) {
//       console.log("accion123");
//       accion = true;
//       let idProyectoInversion = 5;
//       let idPoblacionSeleccionado;

//       let $tablaGruposEtarios = $("#tabla_grupos_etarios");
//       let $tablaSectores = $("#tabla_sectores_condiciones");

//       agregarCamposDeFiltro($tablaGruposEtarios);
//       agregarCamposDeFiltro($tablaSectores);

//       let $dataTableGruposEtarios = crearDataTable($tablaGruposEtarios);
//       let $dataTableSectores = crearDataTable($tablaSectores);

//       $(".filter").on("click", function (e) {
//         e.stopPropagation();
//       });

//       /**
//        * @description elementos dom para la interaccion de la tabla GRUPOS ETARIOS
//        * @constant $btnAgregarGrupoEtario -->> boton de agregar "grupo etario"
//        * @constant $spinnerTablaGruposEtarios -->> spinner usado para la interaccion de las peticiones
//        * @constant $alertaInformacionGruposEtarios -->> contenedor de mansajes de interaccion de las peticiones
//        * @constant $btnRecargarPeticionGruposEtarios -->> botón para rehacer la peticion de cargar la tabla grupos etarios
//        */
//       let $btnAgregarGrupoEtario = $("#btn_agregar_grupo_etario");
//       let $spinnerTablaGruposEtarios = $("#spinner_tabla_grupos_etarios");
//       let $mensajesInformacionGruposEtarios = $(
//         "#mensajes_tabla_grupos_etarios"
//       );
//       let $btnRecargarPeticionGruposEtarios = $("#btn_recargar_grupos_etarios");

//       /**
//        * @description elementos dom para la interaccion de la tabla GRUPOS ETARIOS
//        * @constant $btnAgregarGrupoEtario -->> boton de agregar "grupo etario"
//        * @constant $spinnerTablaGruposEtarios -->> spinner usado para la interaccion de las peticiones
//        */
//       let $btnAgregarSector = $("#btn_agregar_sector_condicion");
//       let $spinnerTablaSectores = $("#spinner_tabla_sectores");
//       let $mensajesInformacionSectores = $("#mensajes_tabla_sectores");
//       let $btnRecargarPeticionSectores = $("#btn_recargar_sectores");
//       cambiarEstadoTablaSectores("inicial");
//       cambiarEstadoTablaGruposEtarios("inicial");

//       function cambiarEstadoTablaGruposEtarios(accion) {
//         switch (accion) {
//           case "inicial":
//             $btnAgregarGrupoEtario.addClass("d-none");
//             $spinnerTablaGruposEtarios.addClass("d-none");
//             $mensajesInformacionGruposEtarios.addClass("d-none");
//             $btnRecargarPeticionGruposEtarios.addClass("d-none");
//             $tablaGruposEtarios.removeClass("d-none");
//             break;
//           case "cargando":
//             $btnAgregarGrupoEtario.addClass("d-none");
//             $spinnerTablaGruposEtarios.removeClass("d-none");
//             $mensajesInformacionGruposEtarios.addClass("d-none");
//             $btnRecargarPeticionGruposEtarios.addClass("d-none");
//             $tablaGruposEtarios.removeClass("d-none");
//             break;
//           case "bien_y_con_datos":
//             $btnAgregarGrupoEtario.removeClass("d-none");
//             $spinnerTablaGruposEtarios.addClass("d-none");
//             $mensajesInformacionGruposEtarios.addClass("d-none");
//             $btnRecargarPeticionGruposEtarios.addClass("d-none");
//             $tablaGruposEtarios.removeClass("d-none");
//             break;
//           case "bien_y_sin_datos":
//             $btnAgregarGrupoEtario.removeClass("d-none");
//             $spinnerTablaGruposEtarios.addClass("d-none");
//             //mensaje
//             $mensajesInformacionGruposEtarios.removeClass();
//             $mensajesInformacionGruposEtarios.addClass("alert alert-primary");
//             $mensajesInformacionGruposEtarios.html(
//               "No hay grupos etarios para mostrar"
//             );
//             //mensaje
//             $btnRecargarPeticionGruposEtarios.addClass("d-none");
//             $tablaGruposEtarios.removeClass("d-none");
//             break;
//           case "fallida":
//             $btnAgregarGrupoEtario.addClass("d-none");
//             $spinnerTablaGruposEtarios.addClass("d-none");
//             //mensaje
//             $mensajesInformacionGruposEtarios.removeClass();
//             $mensajesInformacionGruposEtarios.addClass("alert alert-danger");
//             $mensajesInformacionGruposEtarios.html(
//               "Error al cargar grupos etarios"
//             );
//             //mensaje
//             $btnRecargarPeticionGruposEtarios.removeClass("d-none");
//             $tablaGruposEtarios.removeClass("d-none");
//             break;

//           default:
//             break;
//         }
//       }

//       function cambiarEstadoTablaSectores(accion) {
//         switch (accion) {
//           case "inicial":
//             $btnAgregarSector.addClass("d-none");
//             $spinnerTablaSectores.addClass("d-none");
//             $mensajesInformacionSectores.addClass("d-none");
//             $btnRecargarPeticionSectores.addClass("d-none");
//             $tablaSectores.removeClass("d-none");
//             break;
//           case "cargando":
//             $btnAgregarSector.addClass("d-none");
//             $spinnerTablaSectores.removeClass("d-none");
//             $mensajesInformacionSectores.addClass("d-none");
//             $btnRecargarPeticionSectores.addClass("d-none");
//             $tablaSectores.removeClass("d-none");
//             break;
//           case "bien_y_con_datos":
//             $btnAgregarSector.removeClass("d-none");
//             $spinnerTablaSectores.addClass("d-none");
//             $mensajesInformacionSectores.addClass("d-none");
//             $btnRecargarPeticionSectores.addClass("d-none");
//             $tablaSectores.removeClass("d-none");
//             break;
//           case "bien_y_sin_datos":
//             $btnAgregarSector.removeClass("d-none");
//             $spinnerTablaSectores.addClass("d-none");
//             //mensaje
//             $mensajesInformacionSectores.removeClass();
//             $mensajesInformacionSectores.addClass("alert alert-primary");
//             $mensajesInformacionSectores.html(
//               "No hay información de Grupos / Situaciones / Condiciones / Sectores para mostrar"
//             );
//             //mensaje
//             $btnRecargarPeticionSectores.addClass("d-none");
//             $tablaSectores.removeClass("d-none");
//             break;
//           case "fallida":
//             $btnAgregarSector.addClass("d-none");
//             $spinnerTablaSectores.addClass("d-none");
//             //mensaje
//             $mensajesInformacionSectores.removeClass();
//             $mensajesInformacionSectores.addClass("alert alert-danger");
//             $mensajesInformacionSectores.html(
//               "Error al cargar Grupos / Situaciones / Condiciones / Sectores"
//             );
//             //mensaje
//             $btnRecargarPeticionSectores.removeClass("d-none");
//             $tablaSectores.removeClass("d-none");
//             break;

//           default:
//             break;
//         }
//       }

//       function agregarCamposDeFiltro($table) {
//         $table.find("table thead th").each(function () {
//           let title = $(this).text();
//           if (title !== "Acción") {
//             $(this).html(
//               '<div class="form-group" id="centrado2">' +
//                 '<label for="th" class="blanco">' +
//                 title +
//                 "</label>" +
//                 '<div class="centrado">' +
//                 '<input class="form-control filter" id="nombre1" type="text"  placeholder="&#xf0b0; Filtrar ' +
//                 title +
//                 '" />' +
//                 "</div>" +
//                 "</div>"
//             );
//           }
//         });
//       }

//       function agregarFiltrosDataTable($dataTable) {
//         $dataTable.columns().every(function () {
//           let that = this;

//           $("input", this.header()).on("keyup change clear", function () {
//             if (that.search() !== this.value) {
//               that.search(this.value).draw();
//             }
//           });
//         });
//       }

//       function accionFila(idPoblacion) {
//         // alert(idPoblacion);
//         $(".fila-hover-etario").addClass("disabled");
//         idPoblacionSeleccionado = idPoblacion;
//         ajaxSectores(idPoblacion);
//       }

//       function crearDataTable($element) {
//         let $dataTableCreado = $element.find("table").DataTable({
//           language: {
//             info: '<div class="formato">_END_ de _TOTAL_ registros</div>',
//             infoEmpty: '<div class="formato">0 de 0.</div>',
//             infoFiltered: "(filtrados de un total de _MAX_ registros)",
//             zeroRecords: "No hay información para mostrar",
//             responsive: true,
//             paginate: {
//               first:
//                 '<img src="css/img/icono-flecha-doble-izquierda.png" class="flecha-paginador">',
//               last:
//                 '<img src="css/img/icono-flecha-doble-derecha.png" class="flecha-paginador">',
//               next:
//                 '<img src="css/img/icono-flecha-derecha.png" class="flecha-paginador">',
//               previous:
//                 '<img src="css/img/icono-flecha-izquierda.png" class="flecha-paginador">',
//             },
//             lengthMenu: "_MENU_",
//             columnDefs: [
//               {
//                 targets: -1,
//                 data: null,
//                 defaultContent: '<button class"btn btn-primary">editar<button>',
//               },
//             ],
//           },
//           initComplete: function (settings, json) {},
//           lengthMenu: [5, 10, 20, 50, 100],
//           pageLength: 5,
//           pagingType: "full_numbers",
//           dom:
//             "<'row'<'col-sm-5 col-md-7'>>" +
//             "<'row'<'col-sm-8 col-md-7'tr>>" +
//             "<'row'<'col-sm-8 col-md-7'><'col-sm-2 col-md-1'l><'col-sm-1 col-md-1'i><'col-sm-1 col-md-3'p>>",
//         });

//         agregarFiltrosDataTable($dataTableCreado);
//         return $dataTableCreado;
//       }

//       function ajaxGruposEtarios() {
//         cambiarEstadoTablaGruposEtarios("cargando");
//         $.ajax({
//           type: "GET",
//           url: "/Segplan/" + "obtenerPoblacion/" + idProyectoInversion,
//           success: function (data) {
//             let idFilaGrupoEtario = 1;
//             if (data.lstObjectsDTO.length === 0) {
//               cambiarEstadoTablaGruposEtarios("bien_y_sin_datos");
//             } else {
//               cambiarEstadoTablaGruposEtarios("bien_y_con_datos");
//               data.lstObjectsDTO.forEach(function (item) {
//                 let selectorFila = `fila-tabla-grupos-etarios-id-${idFilaGrupoEtario++}`;
//                 let btnEditar = `
//             <button id="editar-grupo-etario-${item.idPoblacion}" class="btnAction">
//                 <img src="css/img/icono-1-azul.png">
//             </button>
//             `;
//                 let btnEliminar = `
//             <button id="eliminar-grupo-etario-${item.idPoblacion}" class="btnAction">
//                 <img src="css/img/icono-eliminar.png">
//               </button>
//             `;
//                 let acciones = `
//             <div class="centrarItems">
//                 ${btnEditar}
//                 ${btnEliminar}
//             </div>
//             `;
//                 let row = `
//             <tr class='fila-hover-etario ${selectorFila}'>
//               <td>${item.stringLsEtario}</td>
//               <td>${item.numero}</td>
//               <td>${item.descripcion}</td>
//               <td>${acciones}</td>
//             </tr>
//             `;
//                 $dataTableGruposEtarios.rows.add($(row)).draw();
//                 $(`#editar-grupo-etario-${item.idPoblacion}`).click((e) => {
//                   e.stopPropagation();
//                   alert(item.idPoblacion);
//                 });
//                 $(`#eliminar-grupo-etario-${item.idPoblacion}`).click((e) => {
//                   e.stopPropagation();
//                   alert(item.idPoblacion);
//                 });
//                 $(`.${selectorFila}`).click(() => accionFila(item.idPoblacion));
//               });
//             }
//             // $("#editar-grupo-etario-4").on("click", function (e) {
//             //   e.stopPropagation();
//             // });
//           },
//           error: function () {
//             cambiarEstadoTablaGruposEtarios("fallida");
//           },
//           complete: function (data) {},
//         });
//       }

//       function limpiarTablaSectores() {
//         $dataTableSectores.clear();
//         $dataTableSectores.draw();
//       }

//       function ajaxSectores(idGrupoEtario) {
//         cambiarEstadoTablaSectores("cargando");
//         $.ajax({
//           type: "GET",
//           url: "/Segplan/obtenerSectoress/" + idGrupoEtario,
//           success: function (response) {
//             limpiarTablaSectores();

//             if (response.lstObjectsDTO.length === 0) {
//               cambiarEstadoTablaSectores("bien_y_sin_datos");
//             } else {
//               cambiarEstadoTablaSectores("bien_y_con_datos");

//               response.lstObjectsDTO.forEach(function (item) {
//                 let btnEditar = `
//             <button id="editar-sector-condicion-${item.idEtnico}" class="btnAction">
//                 <img src="css/img/icono-1-azul.png">
//             </button>
//             `;
//                 let btnEliminar = `
//             <button id="eliminar-sector-condicion-${item.idEtnico}" class="btnAction">
//                 <img src="css/img/icono-eliminar.png">
//               </button>
//             `;
//                 let acciones = `
//             <div class="centrarItems">
//                 ${btnEditar}
//                 ${btnEliminar}
//             </div>
//             `;
//                 let row = `
//             <tr class='fila-hover'>
//               <td>${item.stringLsEtnico}</td>
//               <td>${item.numero}</td>
//               <td>${item.descripcion}</td>
//               <td>${acciones}</td>
//             </tr>
//             `;

//                 $dataTableSectores.rows.add($(row)).draw();
//                 $(`#editar-sector-condicion-${item.idEtnico}`).click(() =>
//                   alert(item.idEtnico)
//                 );
//                 $(`#eliminar-sector-condicion-${item.idEtnico}`).click(() =>
//                   alert(item.idEtnico)
//                 );
//               });
//             }
//           },
//           error: function () {
//             cambiarEstadoTablaSectores("fallida");
//             limpiarTablaSectores();
//           },
//           complete: function () {
//             $(".fila-hover-etario").removeClass("disabled");
//           },
//         });
//       }

//       $("#btn_recargar_grupos_etarios").click(function (e) {
//         e.preventDefault();
//         cambiarEstadoTablaGruposEtarios("cargando");
//         ajaxGruposEtarios();
//       });
//       $("#btn_recargar_sectores").click(function (e) {
//         e.preventDefault();
//         cambiarEstadoTablaSectores("cargando");
//         ajaxSectores(idPoblacionSeleccionado);
//       });

//       function main() {
//         ajaxGruposEtarios();
//       }

//       main();
//     }
//   });
// });
