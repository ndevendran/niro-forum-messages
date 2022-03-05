package messages.data;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import messages.Point;

@Repository
public class MongoPointRepository implements PointRepository {
	private MongoOperations mongoOps;
	
	@Autowired
	public MongoPointRepository(MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}
	
	@Override
	public List<Point> findAll() {
		List<Point> writtenMessages = mongoOps.findAll(Point.class);		
		return writtenMessages;
	}

	@Override
	public Point findById(String id) {
		Point message = mongoOps.findById(id, Point.class);
		return message;
	}

	@Override
	public Point save(Point message) {
		message.setCreatedAt(new Date());
		mongoOps.insert(message);
		return message;
	}

	@Override
	public boolean delete(String messageId) {
		Point deletedMessage = mongoOps.findById(messageId,  Point.class);
		DeleteResult result = mongoOps.remove(deletedMessage);
		if(result.getDeletedCount() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Point> findByParentId(String parentId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("parentId").is(parentId));
		List<Point> comments = mongoOps.find(query, Point.class);
		return comments;
	}

	@Override
	public Point update(Query query, Update update) {
		mongoOps.upsert(query, update, Point.class);
		Point updatedMessage = mongoOps.find(query, Point.class).get(0);
		return updatedMessage;
	}

}
