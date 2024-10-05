package com.ClubApiMongo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ClubApiMongo.app.entity.Jugador;

public interface JugadorRepository extends MongoRepository<Jugador, String>{

}
