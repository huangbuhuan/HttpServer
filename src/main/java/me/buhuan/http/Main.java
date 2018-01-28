package me.buhuan.http;

import me.buhuan.http.core.HttpServer;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start.....");
		new HttpServer().start(8081);
	}
}
