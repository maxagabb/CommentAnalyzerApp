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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.util.ArrayMap;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.services.youtube.model.VideoLocalization;
import com.google.common.collect.Lists;

import business.Video1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;


public class VideoRetriever implements Retriever{

    /**
     * Define a global instance of a YouTube object, which will be used to make
     * YouTube Data API requests.
     */
    private static YouTube youtube;


    /**
     * Set and retrieve localized metadata for a video.
     *
     * @param args command line args (not used).
     * @return 
     */
    public SearchListResponse getJson(String searchTerm){

        // This OAuth 2.0 access scope allows for full read/write access to the
        // authenticated user's account.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        try {
            // Authorize the request.

        Credential credential = Auth.authorize(scopes, "localizations");
        YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
                .setApplicationName("youtube-cmdline-localizations-sample").build();

            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("part", "snippet");
            parameters.put("maxResults", "10");
            parameters.put("q", searchTerm);
            parameters.put("type", "video");

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
        catch (Exception e){
        	e.printStackTrace();
        }
		return null;
    }



    /*
     * Prompt the user to enter a resource ID. Then return the ID.
     */
    private static String getId(String resource) throws IOException {

        String id = "";

        System.out.print("Please enter a " + resource + " id: ");
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        id = bReader.readLine();

        System.out.println("You chose " + id + " for localizations.");
        return id;
    }



	@Override
	public ArrayList<Video1> retrieve(String fieldInput) throws JsonParseException, IOException {
		SearchListResponse response = getJson(fieldInput);
		ArrayList<Video1> videos = new ArrayList<Video1>();
	    for (SearchResult result : response.getItems()) {
	        Video1 video = new Video1(result);
	        videos.add(video);
	    }
		//System.out.print(videos);
		return videos;
	}

}