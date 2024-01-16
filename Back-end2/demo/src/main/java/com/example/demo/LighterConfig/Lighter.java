package com.example.demo.LighterConfig;


import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Lighters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lighter {
    @Id
    private ObjectId objectId;
    private UUID id;
    private String name;
    private String price;

    public Lighter(@JsonProperty("name") String name, @JsonProperty("price") String price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }
}
