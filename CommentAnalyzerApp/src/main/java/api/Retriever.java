package api;

import java.util.ArrayList;

public interface Retriever {

	<T> ArrayList<T> retrieve(String fieldInput);

}
