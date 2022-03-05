package messages;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
	private String id;
	private Date createdAt;
	private String username;
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
		{
			return false;
		}
		
		if(obj.getClass() != this.getClass()) {
			return false;
		}
		
		User user = (User) obj;
		return user.id.equalsIgnoreCase(this.id);
	}
}
