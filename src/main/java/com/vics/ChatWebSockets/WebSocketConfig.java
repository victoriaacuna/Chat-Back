package com.vics.ChatWebSockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// Esta ruta se usará en el front luego.
		registry.addEndpoint("/chat-websocket")
		// Para configurar los cors.
		.setAllowedOrigins("http://localhost:4200")
		// Permite conectarnos al broker.
		.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//  Evento al que estarán subscritos.
		// Habilita el prefijo del nombre del evento.
		registry.enableSimpleBroker("/chat/");
		// Habilita el prefijo del destino donde se va a publicar.
		registry.setApplicationDestinationPrefixes("/app");
		
	}

	
	
}
