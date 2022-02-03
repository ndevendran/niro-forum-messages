package messages.data;

import messages.User;

public interface UserRepository {
	User findById(String userID);
	User save(User user);
	boolean delete(String userID);
}
