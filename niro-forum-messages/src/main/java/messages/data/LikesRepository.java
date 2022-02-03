package messages.data;

import messages.Likes;
import messages.User;

public interface LikesRepository {
	Likes findById(String id);
	Likes findByMessageId(String messageId);
	Likes findByCommentId(String commentId);
	Likes save(Likes likes);
	boolean delete(Likes likes);
	Likes toggleLike(User user, String likesId);
}
