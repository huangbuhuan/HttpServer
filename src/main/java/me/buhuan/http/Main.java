package me.buhuan.http;

import me.buhuan.http.core.HttpServer;

public class Main {

	public static void main(String[] args) {
		new HttpServer().start(8080);
	}
}
