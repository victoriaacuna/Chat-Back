package com.vics.ChatWebSockets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vics.ChatWebSockets.dao.IChatDao;
import com.vics.ChatWebSockets.document.Mensaje;

@Service
public class ChatService implements IChatService{

	@Autowired
	private IChatDao chatDao;
	
	@Override
	public List<Mensaje> obtenerUltimos10Mensajes() {
		
		return this.chatDao.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		
		return this.chatDao.save(mensaje);
	}

}
