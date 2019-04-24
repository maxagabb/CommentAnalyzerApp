package business;




import org.jsoup.Jsoup;

public  abstract  class Content {

	public String getID() {return null;}

        /**
         * returns thumbnailURL of API content if it exists in concrete
         * subclasses, returns null otherwise
         * @return thumbnailURL
         * @precondition none
         * @postcondition none
         */
	public String getthumbnailURL() {return null;}

        /**
         * returns text content of Content subclass, parsed into normal
         * text
         * @return text 
         * @precondition none
         * @postcondition returns text if it exists 
         */
	public String getText() {
		return Jsoup.parse(text).text();
	}
	protected String text;
}
