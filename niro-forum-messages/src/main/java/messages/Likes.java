package messages;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Likes {
	String id;
	String parentId;
	List<User> users;
	int likes;
}
