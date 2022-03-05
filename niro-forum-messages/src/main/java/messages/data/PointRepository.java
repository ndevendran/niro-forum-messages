package messages.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import messages.Likes;
import messages.Point;

public interface PointRepository {
	List<Point> findAll();
	Point findById(String id);
	List<Point> findByParentId(String parentId);
	Point save(Point message);
	boolean delete(String messageId);
	Point update(Query query, Update update);
}
