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
}
