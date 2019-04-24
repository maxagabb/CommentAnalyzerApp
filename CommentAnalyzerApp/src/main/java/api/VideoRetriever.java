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
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.common.collect.Lists;

import business.Video1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class VideoRetriever extends Retriever<Video1>{




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
	public HashMap<String,Object> getJson2(String searchTerm) throws IOException{

		// This OAuth 2.0 access scope allows for full read/write access to the
		// authenticated user's account.
		List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

		// Authorize the request.

		Credential credential = Auth.authorize(scopes, "localizations");
		YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
				.setApplicationName("youtube-cmdline-localizations-sample").build();




		YouTube.Channels.List request = youtube.channels()
				.list("contentDetails, brandingSettings");

		ChannelListResponse response = request.setId(searchTerm)
				.setMaxResults(1L)
				.execute();
		bannerURL = response.getItems().get(0).getBrandingSettings().getImage().getBannerMobileMediumHdImageUrl();

		String uploadsID = response.getItems().get(0).getContentDetails().getRelatedPlaylists().getUploads();
		System.out.print(uploadsID);

		/*credential = Auth.authorize(scopes, "localizations");
		youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
				.setApplicationName("youtube-cmdline-localizations-sample").build();*/

		YouTube.PlaylistItems.List request2 = youtube.playlistItems()
				.list("snippet");

		PlaylistItemListResponse response2 = request2.setPlaylistId(uploadsID)
				.setMaxResults(10L)
				.execute();
		HashMap<String,Object> result = new HashMap<>();;
		result.put("searchResult", response2);
		result.put("bannerURL", bannerURL );

		return result;


	}

	@Override
	public ArrayList<Video1> retrieve(String fieldInput) throws JsonParseException, IOException {
		ArrayList<Video1> videos = new ArrayList<Video1>();
		try {
			SearchListResponse response = getJson(fieldInput);
			for (SearchResult result : response.getItems()) {
				Video1 video = new Video1(result);
				videos.add(video);
			}
			return videos;
		}
		catch(IOException e){
			videos.add(new Video1(e.getMessage()));
			return videos;
		}
	}
	/**
	 * Returns hashmap filled with objects of multiple types: ArrayList<Video1>, String
	 * @param fieldInput
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 * @postcondition bannerURL != null
	 */
	public ArrayList<Video1> retrieveFromChannel(String fieldInput) throws JsonParseException, IOException{
		//HashMap<String,Object> result = new HashMap<>();;
		ArrayList<Video1> videos = new ArrayList<Video1>();
		try {
			HashMap<String,Object> response =  getJson2(fieldInput);
			PlaylistItemListResponse responseItems = (PlaylistItemListResponse) response.get("searchResult");
			//List<PlaylistItem> result = response.getItems();
			for (PlaylistItem item : responseItems.getItems()) {
				Video1 video = new Video1(item);
				videos.add(video);
			}
			//result.put("videos", videos);
			//result.put("bannerURL", response.get("bannerURL"));
			return videos;
		}
		catch(Exception e){
			videos.add(new Video1(e.getMessage()));
			//result.put("videos", videos);
			return videos;
		}
	}
	
	/**
	 * 
	 * @return
	 * @precondition bannerURL != null 
	 */
	public String getBannerURL() {
		return bannerURL;
	}
	private String bannerURL;

}