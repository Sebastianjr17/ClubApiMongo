package com.ClubApiMongo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ClubApiMongo.app.entity.Asociacion;

public interface AsociacionRepository extends MongoRepository<Asociacion, String>{

}
