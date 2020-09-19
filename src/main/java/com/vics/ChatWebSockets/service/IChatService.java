package com.vics.ChatWebSockets.service;

import java.util.List;

import com.vics.ChatWebSockets.document.Mensaje;

public interface IChatService {
	
	public List<Mensaje> obtenerUltimos10Mensajes();
	
	public Mensaje guardar(Mensaje mensaje);

}
