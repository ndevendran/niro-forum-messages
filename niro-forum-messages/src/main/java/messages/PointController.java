package messages;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import com.mongodb.client.MongoClients;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/point")
public class PointController {
	@GetMapping(produces="application/json")
	public List<Point> showMessageCreationForm() {
		MongoOperations mongOps = new MongoTemplate(MongoClients.create(), "database");
		List<Point> writtenMessages = mongOps.findAll(Point.class);		
		return writtenMessages;
	}
	
	@PostMapping
	public ResponseEntity<String> processMessage(@RequestBody Point message) {
		message.setCreatedAt(new Date());
		log.info("Processing message: " + message);
		MongoOperations mongOps = new MongoTemplate(MongoClients.create(), "database");
		mongOps.insert(message);
		log.info("Wrote message to database...");
		HttpHeaders responseHeaders = new HttpHeaders();
		
		
		return new ResponseEntity<String>("", responseHeaders, HttpStatus.CREATED);
	}
}
