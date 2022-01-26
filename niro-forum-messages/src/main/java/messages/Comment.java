package messages;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Comment {
	private String id;
	private String parentId;
	private String parentType;
	private String username;
	private String userid;
	private Date createdAt;
	private String message;
}
