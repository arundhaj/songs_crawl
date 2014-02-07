package com.arundhaj;

import java.io.File;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class TamilMp3HubCrawl {

	public void movie_page(String year, String movieName, String url, org.w3c.dom.Document w3cDoc, org.w3c.dom.Element w3cYear) {
		try {
			Document doc = Jsoup.connect(String.format("http://www.tamilmp3hub.com%s", url)).get(); 
			Elements songs = doc.select("span.song-title a.noplay");
			
			Iterator<Element> songItr = songs.iterator();
			
			org.w3c.dom.Element w3cMovie = w3cDoc.createElement("Movie");
			w3cMovie.setAttribute("id", movieName);
			
			while(songItr.hasNext()) {
				Element song = songItr.next();
				
				org.w3c.dom.Element w3cSong = w3cDoc.createElement("Song");
				w3cSong.setAttribute("id", song.text());
				
				w3cMovie.appendChild(w3cSong);
//				System.out.println(song.text());
			}
			
			w3cYear.appendChild(w3cMovie);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void year_page(String year, org.w3c.dom.Document w3cDoc, org.w3c.dom.Element w3cRoot) {
		try {
			Document doc = Jsoup.connect(String.format("http://www.tamilmp3hub.com/index.php?p=%s", year)).get(); 
			Elements movies = doc.select("form a");
			
//			System.out.println(movies.size());

			Iterator<Element> moviesItr = movies.iterator();
			
			org.w3c.dom.Element w3cYear = w3cDoc.createElement("Year");
			w3cYear.setAttribute("id", year);

			while(moviesItr.hasNext()) {
				Element movie = moviesItr.next();
				System.out.println("Processing Year: " + year + ", Movie: " + movie.text());
				String url = movie.attr("href");
				movie_page(year, movie.text(), url,  w3cDoc, w3cYear);
//				System.out.println(movie.text());
			}
			
			w3cRoot.appendChild(w3cYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void crawl() {
		try {
			StringWriter writer = new StringWriter();

			DocumentBuilderFactory w3cDocFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder w3cDocBuilder = w3cDocFactory.newDocumentBuilder();
			
			org.w3c.dom.Document w3cDoc = w3cDocBuilder.newDocument();
			
			org.w3c.dom.Element w3cRoot = w3cDoc.createElement("Results");
			
			for(int i = 2010; i <= 2012; i++) {
				System.out.println("Processing Year: " + i);
				year_page("" + i, w3cDoc, w3cRoot);
			}
			w3cDoc.appendChild(w3cRoot);

			File file = new File("songs_list_10.xml");
	        Result result = new StreamResult(file);
	        
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer trans = transFactory.newTransformer(); 
			
			trans.transform(new DOMSource(w3cDoc), result);
			
//			System.out.println(writer.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}

		System.out.println("Completed...");
	}
}
