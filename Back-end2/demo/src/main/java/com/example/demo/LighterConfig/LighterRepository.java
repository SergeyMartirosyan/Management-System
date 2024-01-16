package com.example.demo.LighterConfig;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LighterRepository extends MongoRepository<Lighter, ObjectId> {
    // default Optional<Lighter> getLighterbyName(String name){
    //     return findAll().stream()
    //             .filter(product -> product.getName().equals(name))
    //             .findFirst();
    // }
}
