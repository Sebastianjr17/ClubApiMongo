package com.ClubApiMongo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ClubApiMongo.app.entity.Club;

public interface ClubRepository extends MongoRepository<Club, String>{

}
