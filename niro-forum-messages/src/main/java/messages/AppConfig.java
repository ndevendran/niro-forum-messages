package messages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class AppConfig {
	public @Bean MongoClient mongoClient() {
		return MongoClients.create("mongodb://127.0.0.1:27017/");
	}
}
