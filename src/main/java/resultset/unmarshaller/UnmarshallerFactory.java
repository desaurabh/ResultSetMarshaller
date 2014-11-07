package resultset.unmarshaller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author masterOpti
 */
public class UnmarshallerFactory {
	public static String RESULT_SET = "RESULT_SET";

	public ModelType getModelType(ResultSet resultSet) {
		if (resultSet == null) {
			return null;
		} else {
			ModelType mt = null;
			try {
				mt = new ModelType(buildJson(resultSet));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mt;
		}
	}

	private JSONArray buildJson(ResultSet resultSet) throws SQLException,
			JSONException {
		ResultSetMetaData resultSetMd = resultSet.getMetaData();
		JSONArray jsonArr = new JSONArray();
		while (resultSet.next()) {
			JSONObject obj = new JSONObject();
			int numColumns = resultSetMd.getColumnCount();
			for (int i = 1; i < numColumns + 1; i++) {
				String columnName = resultSetMd.getColumnName(i);
				if (resultSetMd.getColumnType(i) == java.sql.Types.ARRAY) {
					obj.put(columnName, resultSet.getArray(i));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.BIGINT) {
					obj.put(columnName, resultSet.getInt(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.BOOLEAN) {
					obj.put(columnName, resultSet.getBoolean(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.BLOB) {
					obj.put(columnName, resultSet.getBlob(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.DOUBLE) {
					obj.put(columnName, resultSet.getDouble(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.FLOAT) {
					obj.put(columnName, resultSet.getFloat(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.INTEGER) {
					obj.put(columnName, resultSet.getInt(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.NVARCHAR) {
					obj.put(columnName, resultSet.getNString(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.VARCHAR) {
					obj.put(columnName, resultSet.getString(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.TINYINT) {
					obj.put(columnName, resultSet.getInt(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.SMALLINT) {
					obj.put(columnName, resultSet.getInt(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.DATE) {
					obj.put(columnName, resultSet.getDate(columnName));
				} else if (resultSetMd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
					obj.put(columnName, resultSet.getTimestamp(columnName));
				} else {
					obj.put(columnName, resultSet.getObject(columnName));
				}
			}

			jsonArr.put(obj);
		}
		return jsonArr;
	}
}
