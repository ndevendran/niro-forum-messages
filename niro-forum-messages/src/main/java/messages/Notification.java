package messages;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Notification {
	private String id;
	User to;
	List<User> from;
	Point message;
	Date createdAt;
}
