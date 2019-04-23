package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;

import business.Content;

public abstract class Retriever<T extends Content> {

	public abstract ArrayList<T> retrieve(String fieldInput) throws JsonParseException, IOException;
	public HashMap<String, Object> retrieveFromChannel(String fieldInput) throws JsonParseException, IOException{
		return null;
	}
}
