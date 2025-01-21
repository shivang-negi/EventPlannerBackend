package voyago.backend.utils;

import java.io.IOException;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LazyLoadingSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    	try {
    		if (Hibernate.isInitialized(value)) {
            	gen.writeObject(value);
            } 
            else {
            	String id = (String) value.getClass().getMethod("getId").invoke(value);
            	gen.writeString(id);
            }
    	}
        catch(IOException e) {
        	throw new IOException(e.getMessage(), e);
        }
    	catch(Exception e) {
    		throw new RuntimeException(e.getMessage(),e);
    	}
    }
}
