package com.penzias.hello;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class App {

	public static final String BASE_URI = "http://localhost:8080/myapp/";
	
	public static HttpServer startServer(){
		final ResourceConfig rc = new ResourceConfig().packages("com.penzias.hello.resource");
		
		//Grizzly http server
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}
	
	public static void main(String[] args) {
		final HttpServer server = App.startServer();
		System.out.println(String.format("Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it ...", App.BASE_URI));
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.shutdownNow();
	}
}
