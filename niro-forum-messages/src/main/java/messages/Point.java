package messages;

import java.util.Date;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Point {
	private String id;
	private String parentId;
	private String username;
	private String userId;
	private Date createdAt;
	private String message;
	private Likes likes;
	
	public Point(String id, String parentId, String username, String userId, Date createdAt, String message) {
		this.id = id;
		this.parentId = parentId;
		this.username = username;
		this.userId = userId;
		this.createdAt = createdAt;
		this.message = message;
	}
	
}
