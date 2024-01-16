package com.example.demo.LighterConfig;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class LighterService {
    
    @Autowired
    private LighterRepository lighterRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Lighter> getALL(){
        return lighterRepository.findAll();
    }

    public Lighter getLighterbyId(UUID id){
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Lighter.class);
    }

    public Optional<Lighter> getLighterbyObjectId(ObjectId id){
        return lighterRepository.findById(id);
    }


    public void insertLighter(Lighter lighter){
        lighterRepository.insert(lighter);
    }

    public void updateLighter(UUID id, Lighter lighter){
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("name", lighter.getName());
        update.set("price", lighter.getPrice());
        mongoTemplate.findAndModify(query, update, Lighter.class);
    }

    public void removeLighter(UUID id){
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Lighter.class);
    }
}
