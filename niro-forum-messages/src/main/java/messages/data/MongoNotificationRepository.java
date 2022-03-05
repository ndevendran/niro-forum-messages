package messages.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import messages.Notification;
import org.springframework.beans.factory.annotation.Autowired;


public class MongoNotificationRepository implements NotificationRepository {
	private MongoOperations mongOps;
	
	@Autowired
	public MongoNotificationRepository(MongoOperations mongOps) {
		this.mongOps = mongOps;
	}

	@Override
	public Notification save(Notification notification) {
		notification.setCreatedAt(new Date());
		mongOps.insert(notification);
		return notification;
	}

	@Override
	public Notification findById(String id) {
		Notification notification = mongOps.findById(id, Notification.class);
		return notification;
	}

	@Override
	public Notification deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> findByUserId(String userId) {
		Query query =  new Query(Criteria.where("to.id").is(userId));
		List<Notification> notifications = mongOps.find(query, Notification.class);
		return notifications;
	}

}
