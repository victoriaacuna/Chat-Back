package com.vics.ChatWebSockets.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.vics.ChatWebSockets.document.Mensaje;
import com.vics.ChatWebSockets.service.ChatService;

@Controller
public class ChatController {
	
	private String[] colores = {"red", "green", "blue", "purple", "orange", "pink"};
	
	@Autowired
	private ChatService chatService;
	
	// Para poder pasar el clientId para el historial del chat.
	@Autowired
	private SimpMessagingTemplate webSocket;

	// Para recibir, recuerda que se configuró el prefijo (en WebSocketConfig) "/app"
	// Para publicar se ha de colocar "/app/mensaje"
	@MessageMapping("/mensaje")
	// Para enviar sí se tiene que volver a especificar el prefijo.
	//Este es el nombre del evento. La gente se tiene que subscribir a este evento para escuchar.
	// La parte de la ruta de "/mensaje" creo que no tiene que ver con la declarada en el @MessageMapping
	@SendTo("/chat/mensaje")
	public Mensaje recibirMensaje(Mensaje mensaje) {
		
		System.out.println("Mensaje recibido:");
		System.out.println(mensaje);
		
		mensaje.setFecha(new Date().getTime());
		if(mensaje.getType().equals("NUEVO USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("Nuevo usuario conectado.");
		} else {
			// Guarda los mensajes en la DB cuando no sea un mensaje de que se conectó alguien.
			this.chatService.guardar(mensaje);
		}
		
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) {
		return (username + " está escribiendo...");
	}
	
	// Es necesario trabajar con el id de los clientes para que, cuando alguien se conecte,
	// solo le lleguen los últimos 10 mensajes a él y no a todo el mundo.\
	@MessageMapping("/historial")
	public void historial(String clientId){
		
		webSocket.convertAndSend("/chat/historial/"+clientId, this.chatService.obtenerUltimos10Mensajes());
		
	}
	
}
