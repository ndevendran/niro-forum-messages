package messages.data;

import java.util.List;

import messages.Point;

public interface PointRepository {
	List<Point> findAll();
	Point findById(String id);
	List<Point> findByParentId(String parentId);
	Point save(Point message);
	boolean delete(String messageId);
}
