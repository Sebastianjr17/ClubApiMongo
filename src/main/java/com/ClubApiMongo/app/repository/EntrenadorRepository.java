package com.ClubApiMongo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ClubApiMongo.app.entity.Entrenador;

public interface EntrenadorRepository extends MongoRepository<Entrenador, String>{

}
