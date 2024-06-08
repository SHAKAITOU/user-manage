package cn.caam.gs.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.caam.gs.common.exception.ShaScreenException;

public class HtmlURLReaderUtility {
	
	public static Document parse(InputStream in, String charsetName) {
		Document doc;
		try {
			doc = Jsoup.parse(in, charsetName, "");
		} catch (IOException e) {
			throw new ShaScreenException(e);
		}
		
		return doc;
	}
	
	public static Document parse(File htmlFile, String charsetName) {
		Document doc;
		try {
			doc = Jsoup.parse(htmlFile, charsetName);
		} catch (IOException e) {
			throw new ShaScreenException(e);
		}
		
		return doc;
	}
	
	public static Document parse(String url) {
		Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
			throw new ShaScreenException(e);
		}
		
		return doc;
	}

	/*
	 public static void main(String[] args) throws Exception {
        
        Document doc;
        try {

            doc = Jsoup.connect("file:///C:/Users/necuser/sha/test/test.html").get();

            // get title of the page
            String title = doc.title();
            System.out.println("Title: " + title);

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {

                // get the value from href attribute
                System.out.println("\nLink : " + link.attr("href"));
                System.out.println("Text : " + link.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}

