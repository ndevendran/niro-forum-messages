package messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import com.mongodb.client.MongoClients;
import com.mongodb.client.result.DeleteResult;

import lombok.extern.slf4j.Slf4j;
import messages.data.PointRepository;

@Slf4j
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/point")
public class PointController {
	private final PointRepository pointRepo;
	
	@Autowired
	public PointController(PointRepository pointRepo) {
		this.pointRepo = pointRepo;
	}
	
	@GetMapping(produces="application/json")
	public List<Point> showMessageCreationForm() {
		List<Point> writtenMessages = pointRepo.findAll();	
		return writtenMessages;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Point> messageById(@PathVariable("id") String id){
		Point message = pointRepo.findById(id);
		if (message != null) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Point processMessage(@RequestBody Point message) {
		Point writtenMessage = pointRepo.save(message);
		
		if(writtenMessage != null) {
			return writtenMessage;
		}
		return null;
	}
	
	@DeleteMapping("/{messageId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteMessage(@PathVariable("messageId") String messageId) {
		Boolean result = pointRepo.delete(messageId);
	}
	
	
}
