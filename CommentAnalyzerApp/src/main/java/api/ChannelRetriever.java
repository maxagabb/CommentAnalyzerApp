/*
 * Copyright (c) 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package api;

import com.fasterxml.jackson.core.JsonParseException;
import com.google.api.client.auth.oauth2.Credential;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.common.collect.Lists;

import byChannelFrontEnd.Channel1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChannelRetriever extends Retriever<Channel1>{




    /**
     * Set and retrieve localized metadata for a video.
     *
     * @param args command line args (not used).
     * @return 
     * @throws IOException 
     */
    public SearchListResponse getJson(String searchTerm) throws IOException{

        // This OAuth 2.0 access scope allows for full read/write access to the
        // authenticated user's account.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

      
            // Authorize the request.

        Credential credential = Auth.authorize(scopes, "localizations");
        YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
                .setApplicationName("youtube-cmdline-localizations-sample").build();

            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("part", "snippet");
            parameters.put("maxResults", "10");
            parameters.put("q", searchTerm);
            parameters.put("type", "channel");

            YouTube.Search.List searchListByKeywordRequest = youtube.search().list(parameters.get("part").toString());
            if (parameters.containsKey("maxResults")) {
                searchListByKeywordRequest.setMaxResults(Long.parseLong(parameters.get("maxResults").toString()));
            }

            if (parameters.containsKey("q") && parameters.get("q") != "") {
                searchListByKeywordRequest.setQ(parameters.get("q").toString());
            }

            if (parameters.containsKey("type") && parameters.get("type") != "") {
                searchListByKeywordRequest.setType(parameters.get("type").toString());
            }

            SearchListResponse response = searchListByKeywordRequest.execute();
            return response;
        
    }




	@Override
	public ArrayList<Channel1> retrieve(String fieldInput) throws JsonParseException, IOException {
		ArrayList<Channel1> channels = new ArrayList<Channel1>();
		try {
		SearchListResponse response = getJson(fieldInput);
	    for (SearchResult result : response.getItems()) {
	        Channel1 channel = new Channel1(result);
	        channels.add(channel);
	    }
		return channels;
		}
		catch(IOException e){
			channels.add(new Channel1(e.toString()));
			return channels;
		}
	}

}