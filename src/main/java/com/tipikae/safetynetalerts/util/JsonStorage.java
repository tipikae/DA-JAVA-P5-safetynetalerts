package com.tipikae.safetynetalerts.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tipikae.safetynetalerts.model.Storage;

public class JsonStorage {
	
	private static final String DATE_FORMAT = "MM/dd/yyyy";
	private static final String PROPERTIES_FILE = "/application.properties";
	private static final String PROPERTY_KEY_FILE = "storage.file";

	public Storage readStorage() {
		Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
		try (InputStream fis = this.getClass().getResourceAsStream(PROPERTIES_FILE)) {
			
			Properties prop = new Properties();
			prop.load(fis);
			
			Reader reader = Files.newBufferedReader(Paths.get(prop.getProperty(PROPERTY_KEY_FILE)));
			
			Storage storage = gson.fromJson(reader, Storage.class);
			reader.close();
			
			return storage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean writeStorage(Storage storage) {
		Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
		try (InputStream fis = this.getClass().getResourceAsStream(PROPERTIES_FILE)) {
			
			Properties prop = new Properties();
			prop.load(fis);
			
			Writer writer = new FileWriter(prop.getProperty(PROPERTY_KEY_FILE));
			gson.toJson(storage, writer);
			writer.close();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
