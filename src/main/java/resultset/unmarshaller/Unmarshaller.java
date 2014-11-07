package resultset.unmarshaller;
import java.util.List;

import org.json.JSONArray;



/**
 *
 * @author masterOpti
 * @param <T>
 */
public interface Unmarshaller{
    Object populateModel(Class beanClass);
    List<Object> populateListModel(Class beanClass);
    
}
