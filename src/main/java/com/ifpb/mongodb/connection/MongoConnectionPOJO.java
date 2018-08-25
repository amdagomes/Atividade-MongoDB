package com.ifpb.mongodb.connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoConnectionPOJO {
    
    private MongoClient client;
    private MongoDatabase database;
    private CodecRegistry codecRegistry;
    
    public MongoConnectionPOJO(){
        codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        client = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(codecRegistry).build());
        database = client.getDatabase("aula").withCodecRegistry(codecRegistry);
    }
    
    public MongoCollection getCollection(String nome, Class classe){
        return database.getCollection(nome, classe);
    }
    
}
