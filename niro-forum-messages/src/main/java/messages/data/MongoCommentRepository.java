package messages.data;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

import messages.Comment;

@Repository
public class MongoCommentRepository implements CommentRepository {
	private MongoOperations mongoOps;
	
	@Autowired
	public MongoCommentRepository(MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}

	@Override
	public Comment findById(String id) {
		Comment comment = mongoOps.findById(id, Comment.class);
		return comment;
	}

	@Override
	public List<Comment> findByParentId(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("parentId").is(id));
		List<Comment> comments = mongoOps.find(query, Comment.class);
		return comments;
	}

	@Override
	public Comment save(Comment comment) {
		comment.setCreatedAt(new Date());
		Comment writtenComment = mongoOps.insert(comment);
		return writtenComment;
	}

	@Override
	public boolean delete(String commentId) {
		Comment deletedComment = mongoOps.findById(commentId, Comment.class);
		DeleteResult result = mongoOps.remove(deletedComment);
		if(result.getDeletedCount() > 0) {
			return true;
		}
		return false;
	}

}
