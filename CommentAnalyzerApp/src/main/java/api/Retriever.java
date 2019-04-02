package api;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;

public abstract class Retriever {

	public abstract <T> ArrayList<T> retrieve(String fieldInput) throws JsonParseException, IOException;
	public <T> ArrayList<T> retrieveFromChannel(String fieldInput) throws JsonParseException, IOException{
		return null;
	}
}
