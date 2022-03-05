package messages.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import messages.Likes;
import messages.Point;
import messages.User;
import messages.data.PointRepository;
import messages.data.UserRepository;
import messages.services.PointMessagingService;

@Slf4j
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/point")
public class PointController {
	private final PointRepository pointRepo;
	private final UserRepository userRepo;
	private final PointMessagingService pointMessenger;
	
	@Autowired
	public PointController(PointRepository pointRepo, UserRepository userRepo, PointMessagingService jms) {
		this.pointRepo = pointRepo;
		this.userRepo = userRepo;
		this.pointMessenger = jms;
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
	
	@GetMapping("/comment/{id}")
	public List<Point> commentsByParentId(@PathVariable("id") String id){
		List<Point> comments = pointRepo.findByParentId(id);
		return comments;
	}
	
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Point processMessage(@RequestBody Point message) {
		List<User> users = new ArrayList<User>();
		Likes likes = new Likes(message.getId(), users, 0);
		message.setLikes(likes);
		Point writtenMessage = pointRepo.save(message);
		
		return writtenMessage;
	}
	
	@PostMapping(consumes="application/json",path="/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Point createComment(@RequestBody Point message, @PathVariable("id") String parentId) {
		List<User> users = new ArrayList<User>();
		Likes likes = new Likes(message.getId(), users, 0);
		message.setLikes(likes);
		message.setParentId(parentId);
		Point writtenMessage = pointRepo.save(message);
		return writtenMessage;
	}
	
	
	@DeleteMapping("/{messageId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteMessage(@PathVariable("messageId") String messageId) {
		Boolean result = pointRepo.delete(messageId);
	}
	
	@PostMapping(consumes="application/json")
	@RequestMapping("/like/{id}")
	public Point likeMessage(@RequestBody Point message, @PathVariable("id") String userId) {
		Likes likes = message.getLikes();
		User user = userRepo.findById(userId);
		likes.toggleLikes(user);
		Update update = new Update();
		update.set("likes", likes);
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(message.getId()));
		Point savedMessage = pointRepo.update(query, update);
		return savedMessage;
	}
}
