package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;

public abstract class Retriever {

	public abstract <T> ArrayList<T> retrieve(String fieldInput) throws JsonParseException, IOException;
	public HashMap<String, Object> retrieveFromChannel(String fieldInput) throws JsonParseException, IOException{
		return null;
	}
}
