package me.buhuan.http;

import me.buhuan.http.core.HttpServer;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start.....");
		new HttpServer().start(8080);
	}
}
