package cms.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	
	/**
	 * Creates a new String object with the given length and returns the created object.
	 * @param length
	 * @return
	 */
    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {
            builder.append("a");
        }

        return builder.toString();
    }
    
    /**
     * Converts the object into form url encoded String object and returns the content of that String object as a byte array.
     * @param object
     * @return
     */
    public static byte[] convertObjectToFormUrlEncodedBytes(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Map<String, Object> propertyValues = mapper.convertValue(object, Map.class);

        Set<String> propertyNames = propertyValues.keySet();
        Iterator<String> nameIter = propertyNames.iterator();

        StringBuilder formUrlEncoded = new StringBuilder();

        for (int index=0; index < propertyNames.size(); index++) {
            String currentKey = nameIter.next();
            Object currentValue = propertyValues.get(currentKey);

            formUrlEncoded.append(currentKey);
            formUrlEncoded.append("=");
            formUrlEncoded.append(currentValue);

            if (nameIter.hasNext()) {
                formUrlEncoded.append("&");
            }
        }

        return formUrlEncoded.toString().getBytes();
    }
	
}
