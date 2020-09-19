package com.vics.ChatWebSockets.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vics.ChatWebSockets.document.Mensaje;

public interface IChatDao extends MongoRepository<Mensaje, String>{

	public List<Mensaje> findFirst10ByOrderByFechaDesc();
	
}
