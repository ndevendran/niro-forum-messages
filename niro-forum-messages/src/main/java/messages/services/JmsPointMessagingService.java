package messages.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import messages.Point;


@Service
public class JmsPointMessagingService implements PointMessagingService {
	private JmsTemplate jms;
	
	@Autowired
	public JmsPointMessagingService(JmsTemplate jms) {
		this.jms = jms;	
	}
	
	@Override
	public void sendPoint(Point point) {
		jms.convertAndSend("forum.messages.notifications", point);
	}
}
