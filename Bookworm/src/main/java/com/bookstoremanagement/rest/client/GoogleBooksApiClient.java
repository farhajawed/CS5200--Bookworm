package com.bookstoremanagement.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
@Transactional
public class GoogleBooksApiClient {
	
	private final String FIND_BOOK_BY_ISBN="https://www.googleapis.com/books/v1/volumes?q=isbn:ISBN";
	
	public GoogleBook findBookByIsbn(String isbn) {
		if(isbn.length()==13) {
		String urlStr = FIND_BOOK_BY_ISBN.replace("ISBN", isbn);
		ObjectMapper mapper = new ObjectMapper();
		String json = getJsonStringForUrl(urlStr);
		
		try {
			return mapper.readValue(json, GoogleBook.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
		else return null;
	}

	private String getJsonStringForUrl(String urlStr) {
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream in = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(isr);
			String out;
			StringBuffer json = new StringBuffer();
			while((out = reader.readLine())!= null) {
				json.append(out);
			}
			return json.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
