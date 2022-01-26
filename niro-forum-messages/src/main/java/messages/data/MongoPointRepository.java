package messages.data;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

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

}
