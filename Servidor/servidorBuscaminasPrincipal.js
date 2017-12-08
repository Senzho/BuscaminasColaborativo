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
function obtenerSocket(id){
	var socket;
	for (var i = 0; i < listaSockets.length; i ++){
		var socketJugador = listaSockets[i];
		if (socketJugador.idJugador == id){
			socket = socketJugador.socket;
			break;
		}
	}
	return socket;
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
        var socketAuxiliar;
        for(var i = 0; i< listaSockets.length; i++){
        	socketAuxiliar = listaSockets[i];
        	if(socketAuxiliar.socket == socket){
        		listaSockets.splice(i,1);
        		console.log("jugador borrado de la lista");
        		for(var j = 0; j < listaDatos.length; j++){
        			if(listaDatos[j].idJugador == socketAuxiliar.idJugador){
        				listaDatos.splice(j,1);
        				break;
        			}
        		}
        		break;
        	}
        }
        console.log("jugadores actuales");
        for(var i = 0; i < listaDatos.length; i++){
        	console.log(listaDatos[i].nombreJugador);
        }
		console.log("lista de sockets actuales");
		for(var i = 0; i < listaSockets.length; i++){
			console.log(listaSockets[i].idJugador);
		}        
		console.log("borrar esto");
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
        obtenerSocket(solicitud.idCompañero).emit("solicitud",solicitud, nombre);
	});
    socket.on("respuestaPartida", function(aceptado,solicitud){//solicitud.idCompañero...id cliente .iddestino dificultad
    	var auxiliarBusqueda;
    	var socketAuxiliar = obtenerSocket(solicitud.idSolicitante);
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
        obtenerSocket(idDestino).emit("tiroRecibido",coordenadaX,coordenadaY);
    });
    socket.on("terminarPartida", function(idCompañero){
    	var socketAux = obtenerSocket(idCompañero);
    	if(socketAux!=null){
    		socketAux.emit("partidaTerminada");
    	}
    });
	
    socket.on("marca", function(coordenadaX, coordenadaY, idDestino){
    	var socketAux = obtenerSocket(idDestino);
    	if(socketAux!=null){
    		socketAux.emit("marcaRecibida", coordenadaX, coordenadaY);
    	}
    });
	socket.on("validarSesion",function(idJugador){
		var valido = false;
		for(var i = 0; i < listaSockets.length; i++){
			if(listaSockets[i].idJugador == idJugador){
				valido = true;
				break;
			}
		}
		socket.emit("jugadorConectado", valido);
	});
});