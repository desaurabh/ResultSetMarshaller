package resultset.unmarshaller;
import org.json.JSONArray;




/**
 * 
 * @author masterOpti
 */
public class Model extends  AbstractUnmarshaller {

	
	public Model(JSONArray jsonArray){
		super.setJsonArray(jsonArray);
		super.setJSONtring(jsonArray.toString());
	}
	
	public Model(){
		
	}
	
	@Override
	public Object populateModel(Class beanClass) {
		return super.populateModel(beanClass);
	}

	

}
