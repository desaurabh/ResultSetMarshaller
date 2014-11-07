package resultset.unmarshaller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

public abstract class AbstractUnmarshaller implements Unmarshaller{

	private static JSONArray jsonArray;
	private static String JSONtring;
	
	@Override
	public Object populateModel(Class beanClass) {
		return buildMap(getJsonArray(), beanClass);
	}

	@Override
	public List<Object> populateListModel(Class beanClass) {
		return buildListMap(getJsonArray(), beanClass);
	}
	

	private Object mapBeanClass(Map<String, Object> map, Class beanClass) {
		Object beanObject = null;
		try {
			beanObject = beanClass.newInstance();
			Field[] fields = beanClass.getDeclaredFields();

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.getName().equalsIgnoreCase(entry.getKey())) {
						field.set(beanObject, entry.getValue());
					}
				}
			}

		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}

		return beanObject;
	}
	
	private Object buildMap(JSONArray jsonArray, Class beanClass) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(jsonArray.toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = (Map<String, Object>) mapper.convertValue(
				node.get(0), Map.class);
		
		return mapBeanClass(map, beanClass);
	}
	
	private List<Object> buildListMap(JSONArray jsonArray, Class beanClass){
		ObjectMapper mapper=new ObjectMapper();
		JsonNode node=null;
		try {
			node=mapper.readTree(jsonArray.toString());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object> list=new ArrayList<Object>();
		for(int i=0; i<node.size(); i++){
			JsonNode entryNode=node.get(i);
			Map<String, Object> map = (Map<String, Object>) mapper.convertValue(
					entryNode, Map.class);
			list.add(mapBeanClass(map, beanClass));
		}
		return list;
	}

	private static JSONArray getJsonArray() {
		return jsonArray;
	}

	public static void setJsonArray(JSONArray jsonArray) {
		AbstractUnmarshaller.jsonArray = jsonArray;
	}

	public static String getJSONtring() {
		return JSONtring;
	}

	public static void setJSONtring(String jSONtring) {
		JSONtring = jSONtring;
	}
	
	
	
	
	
	

}
