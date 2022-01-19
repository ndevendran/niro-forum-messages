package messages;

import org.springframework.context.annotation.Bean;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class AppConfig {
	public @Bean MongoClient mongoClient() {
		return MongoClients.create("mongodb://127.0.0.1:27017/");
	}
}
