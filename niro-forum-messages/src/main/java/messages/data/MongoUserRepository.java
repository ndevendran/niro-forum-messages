package messages.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

import messages.User;

@Repository
public class MongoUserRepository implements UserRepository {
	private MongoOperations mongoOps;
	
	@Autowired
	public MongoUserRepository(MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}
	
	@Override
	public User findById(String userID) {
		// TODO Auto-generated method stub
		User user = mongoOps.findById(userID, User.class);
		return user;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		User savedUser = mongoOps.save(user);
		return savedUser;
	}

	@Override
	public boolean delete(String userID) {
		User deletedUser = mongoOps.findById(userID, User.class);
		DeleteResult result = mongoOps.remove(deletedUser);
		if(result.getDeletedCount() > 0) {
			return true;
		}
		return false;
	}

}
