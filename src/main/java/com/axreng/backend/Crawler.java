package com.axreng.backend;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler {

	public static void main(String[] args) {
		 
		String url = "http://hiring.axreng.com/";	//endereço que sera varrido
		crawl(1, url, new ArrayList<String>());
	}
	
	private static void crawl(int level, String url, ArrayList<String> visited) {
		if(level <= 5) {
			Document doc = request(url, visited);
			if(doc != null) {
				for(Element link : doc.select("a[href]")) {     // Element: variavel vinda de jsoup
				String next_link = link.absUrl("href");
				if(visited.contains(next_link) == false) {
					crawl(level++,next_link, visited);			//não repetir o link ja visitado
				}
				}
					
			}
		}
	}
	
	private static Document request(String url, ArrayList<String> v) { // v: apelido de visited
		try {
			Connection con = Jsoup.connect(url);			//jsoup captura conteudo html
			Document doc = con.get();						//doc document, con connection
			
			if(con.response().statusCode() == 200) {
				System.out.println("Link: " + url);
				System.out.println(doc.title());
				v.add(url);
				return doc;
			}
			return null;
			
		}
		catch(IOException e) {
			return null;
		}
	}

}
