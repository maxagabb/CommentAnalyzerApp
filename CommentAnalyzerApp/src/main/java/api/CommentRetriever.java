package api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonParseException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import business.Comment;

public class CommentRetriever extends Retriever<Comment>{




	/**
	 * Set and retrieve localized metadata for a video.
	 *
	 * @param args command line args (not used).
	 * @return 
	 * @throws IOException 
	 */
	
	public CommentThreadListResponse getJson(String searchTerm) throws IOException, NullPointerException{
			
			YouTube youtube = getYouTubeService();

			HashMap<String, String> parameters = new HashMap<>();
			parameters.put("part", "snippet");//,replies
			parameters.put("videoId", searchTerm);

			YouTube.CommentThreads.List commentThreadsListByVideoIdRequest = youtube.commentThreads().list(parameters.get("part").toString());
			if (parameters.containsKey("videoId") && parameters.get("videoId") != "") {
				commentThreadsListByVideoIdRequest.setVideoId(parameters.get("videoId").toString());
			}

			return(commentThreadsListByVideoIdRequest.execute());
	}




	@Override
	public ArrayList<Comment> retrieve(String fieldInput) throws JsonParseException, IOException {
		
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			CommentThreadListResponse response = getJson(fieldInput);
			for (CommentThread result : response.getItems()) {
				Comment comment = new Comment(result.getSnippet().getTopLevelComment().getSnippet().getTextDisplay());
				comments.add(comment);
			}
			return comments;
		}
		catch(IOException e){
			comments.add(new Comment("Some Error:\t" +e.getMessage()));
			return comments;
		}
		catch(NullPointerException e){
			comments.add(new Comment("Some Error:\t" +e.getMessage()));
			return comments;
		}
		
	}
	
	
	
	
	
	
	
    /** Application name. */
    private static final String APPLICATION_NAME = "Experiment";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
    System.getProperty("user.home"), ".credentials/java-youtube-api-tests");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final Collection<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/youtube.force-ssl", 
    		"https://www.googleapis.com/auth/youtubepartner");
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =  new FileInputStream("client_secret_460298885215-rlitofilod32q6sfcl5ln21p3pqcg93p.apps.googleusercontent.com.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader( in ));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(DATA_STORE_FACTORY)
            .setAccessType("offline")
            .build();
        Credential credential = new AuthorizationCodeInstalledApp(
        flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
            "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * 
     * Build and return an authorized API client service, such as a YouTube
     * Data API client service.
     * @return an authorized API client service
     * @throws IOException
     */
    public static YouTube getYouTubeService() throws IOException {
        Credential credential = authorize();
        return new YouTube.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build();
    }
}
