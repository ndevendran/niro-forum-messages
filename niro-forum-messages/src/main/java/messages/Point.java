package messages;

import java.util.Date;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Point {
	private String id;
	private String username;
	private String userId;
	private Date createdAt;
	private String message;
	
	public Point(String id, String username, String userId, Date createdAt, String message) {
		this.id = id;	
		this.username = username;
		this.userId = userId;
		this.createdAt = createdAt;
		this.message = message;
	}
	
}
