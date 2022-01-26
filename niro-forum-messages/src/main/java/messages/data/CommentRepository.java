package messages.data;

import java.util.List;

import messages.Comment;

public interface CommentRepository {
	Comment findById(String id);
	List<Comment> findByParentId(String id);
	Comment save(Comment comment);
	boolean delete(String commentId);
}
