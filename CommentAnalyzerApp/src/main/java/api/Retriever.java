package api;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;

public interface Retriever {

	<T> ArrayList<T> retrieve(String fieldInput) throws JsonParseException, IOException;

}
