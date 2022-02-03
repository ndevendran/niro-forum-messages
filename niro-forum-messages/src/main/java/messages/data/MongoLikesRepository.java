package messages.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import messages.Likes;
import messages.User;

@Repository
public class MongoLikesRepository implements LikesRepository {
	private MongoOperations mongoOps;
	
	@Autowired
	MongoLikesRepository(MongoOperations mongoOps){
		this.mongoOps = mongoOps;
	}
	
	@Override
	public Likes findById(String id) {
		return mongoOps.findById(id, Likes.class);
	}

	@Override
	public Likes findByMessageId(String messageId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("parentId").is(messageId));
		List<Likes> likes = mongoOps.find(query, Likes.class);
		return likes.get(0);
	}

	@Override
	public Likes findByCommentId(String commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Likes save(Likes likes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Likes likes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Likes toggleLike(User user, String likesId) {
		// TODO Auto-generated method stub
		return null;
	}

}
