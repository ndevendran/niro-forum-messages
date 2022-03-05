package messages.data;

import java.util.List;

import messages.Notification;

public interface NotificationRepository {
	List<Notification> findByUserId(String userId);
	Notification save(Notification notification);
	Notification findById(String id);
	Notification deleteById(String id);
}
