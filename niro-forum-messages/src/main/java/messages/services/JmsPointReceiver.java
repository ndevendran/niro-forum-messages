package messages.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import messages.Notification;
import messages.Point;
import messages.User;
import messages.data.NotificationRepository;
import messages.data.PointRepository;
import messages.data.UserRepository;

public class JmsPointReceiver implements PointReceiver{
	private JmsTemplate jms;
	private PointRepository pointRepo;
	private UserRepository userRepo;
	private NotificationRepository notificationRepo;
	
	@Autowired
	public JmsPointReceiver(JmsTemplate jms, PointRepository pointRepo, UserRepository userRepo) {
		this.jms = jms;
		this.pointRepo = pointRepo;
		this.userRepo = userRepo;
	}
	
	public boolean createNotification(){
		Point message = receivePoint();
		//look up parent
		if(message.getParentId() == null) {
			return false;
		}
		Point parentMessage = pointRepo.findById(message.getParentId());
		User toUser = userRepo.findById(parentMessage.getUserId());
		List<User> fromUsers = new ArrayList<User>();
		Notification notification = new Notification();
		notification.setTo(toUser);
		notification.setFrom(fromUsers);
		notification.setMessage(parentMessage);
		notificationRepo.save(notification);
		return true;
	}

	@Override
	public Point receivePoint() {
		// TODO Auto-generated method stub
		return (Point) jms.receiveAndConvert("forum.messages.notifications");
	}

}
