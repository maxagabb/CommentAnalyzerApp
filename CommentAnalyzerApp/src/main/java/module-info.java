/**
 * 
 */
/**
 * @author maxOrAng
 *
 */
module commentAnalyzerApp {
	exports byVideoFrontEnd;
	exports byChannelFrontEnd;
	exports loginRegister;
	exports business;
	exports api;
	exports commentsFrontEnd;

	requires transitive com.fasterxml.jackson.core;
	requires transitive com.fasterxml.jackson.databind;
	requires transitive com.google.api.client;
	requires transitive com.google.common;
	requires transitive google.api.client;
	requires transitive google.api.services.youtube.v3.rev20190128;
	requires transitive google.http.client.jackson2;
	requires transitive google.oauth.client;
	requires transitive google.oauth.client.java6;
	requires transitive google.oauth.client.jetty;
	requires transitive java.desktop;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.graphics;
	requires transitive org.json;
	requires transitive org.jsoup;
}