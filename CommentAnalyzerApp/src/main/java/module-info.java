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

	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires com.google.api.client;
	requires com.google.common;
	requires google.api.client;
	requires google.api.services.youtube.v3.rev20190128;
	requires google.http.client.jackson2;
	requires google.oauth.client;
	requires google.oauth.client.java6;
	requires google.oauth.client.jetty;
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires org.json;
	requires org.jsoup;
}