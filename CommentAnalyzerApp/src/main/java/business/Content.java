package business;

import java.awt.Component;

import org.jsoup.Jsoup;

public  abstract  class Content {

	public String getID() {return null;}

	public String getthumbnailURL() {return null;}

	public String getText() {
		return Jsoup.parse(text).text();
	}
	protected String text;
}
