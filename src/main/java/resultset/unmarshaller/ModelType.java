package resultset.unmarshaller;


import org.json.JSONArray;

public class ModelType {
	
	private static JSONArray jsonArray;
	public ModelType(JSONArray jsonArray){
		this.jsonArray=jsonArray;
	}
	
	public ListModel  getListModel(){
		return new ListModel(this.jsonArray);
	}
	
	public Model getModel(){
		return new Model(this.jsonArray);
	}
	
	
}
