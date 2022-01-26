package messages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.extern.slf4j.Slf4j;
import messages.data.CommentRepository;

@Slf4j
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/comment")
public class CommentController {
	private final CommentRepository commentRepo;
	
	@Autowired
	public CommentController(CommentRepository commentRepo) {
		this.commentRepo = commentRepo;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comment> getComment(@PathVariable("id") String commentId) {
		Comment comment = commentRepo.findById(commentId);
		if(comment != null) {
			return new ResponseEntity<>(comment, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/parent/{id}")
	public List<Comment> getCommentsByParentId(@PathVariable("id") String parentId){
		List<Comment> comments = commentRepo.findByParentId(parentId);
		return comments;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Comment createComment(@RequestBody Comment comment) {
		Comment writtenComment = commentRepo.save(comment);
		if(writtenComment != null) {
			return writtenComment;
		}
		
		else return null;
	}
	
	@DeleteMapping("/{commentId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteComment(@PathVariable("commentId") String commentId) {
		Boolean result = commentRepo.delete(commentId);
	}
}
