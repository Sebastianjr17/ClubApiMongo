package com.ClubApiMongo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ClubApiMongo.app.entity.Competicion;

public interface CompeticionRepository extends MongoRepository<Competicion, String>{

}
