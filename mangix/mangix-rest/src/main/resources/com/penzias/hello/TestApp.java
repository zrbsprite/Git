package com.penzias.hello;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestApp {

	private HttpServer server;
	
	private WebTarget target;
	
	@BeforeTest
	public void Before() {
		
		server = App.startServer();
		Client client = ClientBuilder.newClient();
		target = client.target(App.BASE_URI);
	}

	@AfterTest
	private void after() {
		
		server.shutdownNow();
	}
	
	@Test
	public void test(){
		String returnMsg = target.path("myresource").request().get(String.class);
		Assert.assertEquals("Got it!", returnMsg);
	}
}
