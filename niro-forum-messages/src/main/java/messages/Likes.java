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
	
	public Likes(String parentId, List<User> users, int likes){
		this.parentId = parentId;
		this.users = users;
		this.likes = likes;
	}
	
	public Likes toggleLikes(User user) {
		//Check to see if the user already liked message
		int userExists = this.users.indexOf(user);
		if(userExists != -1) {
			this.users.remove(userExists);			
			this.likes = this.users.size();
			return this;
		}
		this.users.add(user);
		this.likes = this.users.size();
		return this;
	}
}
