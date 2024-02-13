package org.example.MongoDBConnection;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class mongoDBConnection {

    public static MongoDatabase getConnection() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017/flowershop");
        return client.getDatabase("Flowershop");
    }
}
