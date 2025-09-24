/* JavaScript for websocket client */
var name = "";
var stompClient = null;
/* Show connection status */
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
	var socket = new SockJS('/BackSegplan/buzon');

	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		sendPlace();
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/notificacion/' + name, function(
				notificacion) {
			showNotificacion(JSON.parse(notificacion.body));
		});
	});
}

/* Disconnect */
function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
		console.log("Disconnected");
	}
}

/* Send place */
function sendPlace() {

	stompClient.send("/app/mensaje/" + name, {}, JSON.stringify({
		'usuario' : name
	}));

}

/* Show weather info */
function showNotificacion(wInfo) {

	$("#notificaciones").replaceWith(
			'<div id="notificaciones" class="color-azul"><tr><td>'
					+ wInfo.notificaciones + "</td></tr></div>");
	disconnect();
	// console.log(wInfo.notificaciones);

}