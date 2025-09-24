$(document).ready(
		function() {
			var name = "";
			var ws = null;
			connect();
			function setConnected(connected) {
				var status = "disconnected";
				if (connected) {
					status = "connected";
					connect();
				}
			}

			/* Connect to topic using web sockets */
			function connect() {
				name = document.getElementById("usuarioSesion").value;
				var aux = document.getElementById("socket").value;
				var socket = new WebSocket(aux);
				ws = Stomp.over(socket);
				ws.connect({}, function(frame) {
					console.log('Connected: ' + frame);
					ws.subscribe('/topic/notificacion/' + name, function(
							notificacion) {
						
						showNotificacion(JSON.parse(notificacion.body));
					});
					sendPlace();
					console.log("prueba")
				});
			}

			/* Disconnect */
			function disconnect() {
				if (ws != null) {
					ws.disconnect();
				}
			}

			/* Send place */
			function sendPlace() {
				ws.send("/app/mensaje/" + name, {}, JSON.stringify({
					'usuario' : name
				}));

			}

			/* Show weather info */
			function showNotificacion(wInfo) {

				console.log(wInfo);

				$("#notificaciones").replaceWith(
						'<div id="notificaciones" class="color-azul"><tr><td>'
								+ wInfo.notificaciones + "</td></tr></div>");
				console.log(wInfo.notificaciones);
				disconnect();
			}
		});