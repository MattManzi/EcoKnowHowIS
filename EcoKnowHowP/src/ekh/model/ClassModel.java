package ekh.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClassModel<T> {
	public T doRetrieveByKey(String key) throws SQLException;

	public ArrayList<T> doRetrieveAll(String order) throws SQLException;

	public void doSave(T obj) throws SQLException;

	public void doUpdate(String dato, String str, String key) throws SQLException;

	public void doDelete(String key) throws SQLException;
}
