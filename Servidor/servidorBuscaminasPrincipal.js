// JavaScript source code
var io = require("socket.io")(7000);
var listaDatos = new Array();
var listaSockets = new Array();

function SocketJugador(idJugador, socket){
	this.idJugador = idJugador;
	this.socket = socket;
}
function Jugador(idJugador, nombreJugador) {
    this.idJugador = idJugador;
    this.nombreJugador = nombreJugador;
}

function coordenadas(coordenadaX, coordenadaY){
    this.coordenadaY = coordenadaY;
    this.coordenadaX = coordenadaX;
}
function GenerarNumeros(inicio,fin){
    return aleatorio = Math.floor(Math.random()*(fin - inicio) + parseInt(inicio));
}
io.on("connection", function (socket) { 
    console.log("nuevo usuario conectado");
    socket.on("jugadorConectado", function (jugador) {
    	jugadorSocket = new SocketJugador(jugador.idJugador,socket);
        jugadorDatos = new Jugador(jugador.idJugador, jugador.nombreJugador);
        listaDatos.push(jugadorDatos);
        listaSockets.push(jugadorSocket);
        console.log("se conecto: " + jugador.nombreJugador);
        socket.broadcast.emit("nuevoJugador",jugador);
    });
    socket.on("getJugadores", function(){
        socket.emit("jugadorLista", listaDatos);
    });

    socket.on("disconnect", function () {
    	console.log("Jugadores actuales");
        for (var i = 0; i< listaDatos.length; i++) {
        	console.log(listaDatos[i].nombreJugador);
        }
        
    });

    socket.on("jugadorDesconectado",function(idJugador){
    	var auxiliarBusqueda;
		for (var i = 0; i < listaDatos.length; i++) {
            auxiliarBusqueda = listaDatos[i];
            if (auxiliarBusqueda.idJugador == idJugador) {
            	listaDatos.splice(i, 1);
                console.log("jugador "+auxiliarBusqueda.idJugador+" desconectado.");
                break;
            }
        }
        for (var i = 0; i < listaSockets.length; i++) {
            auxiliarBusqueda = listaSockets[i];
            if (auxiliarBusqueda.idJugador == idJugador) {
                listaSockets.splice(i, 1);
                console.log("jugador "+auxiliarBusqueda.idJugador+" desconectado.");
                break;
            }
        }
        socket.broadcast.emit("jugadorDesconectado", idJugador);
    });
	socket.on("solicitudPartida",function(solicitud){
            var nombre;
        for(var i = 0; i < listaDatos.length; i++){
            if(listaDatos[i].idJugador == solicitud.idSolicitante){
                nombre = listaDatos[i].nombreJugador;
                console.log(nombre);
                break;
            }
        }
		for(var i = 0; i< listaSockets.length; i++){
			if(listaSockets[i].idJugador == solicitud.idCompañero){
				listaSockets[i].socket.emit("solicitud",solicitud, nombre);
                break;
			}
		}
	});
    socket.on("respuestaPartida", function(aceptado,solicitud){//solicitud.idCompañero...id cliente .iddestino dificultad
    	var auxiliarBusqueda;
        var socketAuxiliar;
    	for(var i = 0; i< listaSockets.length; i++){
    		auxiliarBusqueda = listaSockets[i];
    		if(auxiliarBusqueda.idJugador == solicitud.idSolicitante){
                console.log("si se encontró");
    			socketAuxiliar = auxiliarBusqueda.socket;
    			break;
    		}
    	}
        if(aceptado == "aceptado"){
            var listaNumeros = new Array();
            for(var i = 0; i < solicitud.numeroMinas; i++){
                var coordenada = new coordenadas( GenerarNumeros(0,solicitud.numeroFilas), GenerarNumeros(0,solicitud.numeroColumnas));
                listaNumeros.push(coordenada);
                console.log(coordenada);
            }
            socketAuxiliar.emit("iniciarPartida",solicitud,listaNumeros);
            var idJugador;
            var idCompañero;
            idCompañero = solicitud.idSolicitante;
            idJugador = solicitud.idCompañero;
            solicitud.idCompañero = idCompañero;
            solicitud.idSolicitante = idJugador;
            socket.emit("iniciarPartida",solicitud,listaNumeros);
        }else{

            socketAuxiliar.emit("rechazado");
        }
    });
    socket.on("tiro",function(coordenadaX,coordenadaY,idDestino){
        console.log("recibido"+idDestino);
        for(var i = 0; i<listaSockets.length; i++){
            if(idDestino == listaSockets[i].idJugador){
                console.log("entro dos veces");
                listaSockets[i].socket.emit("tiroRecibido",coordenadaX,coordenadaY);
                break;
            }
        }
    });
});