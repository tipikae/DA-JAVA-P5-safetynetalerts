package com.tipikae.safetynetalerts.storage;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.model.Person;

/**
 * A class accessing data stored in a json file.
 * @author tipikae
 * @version 1.0
 *
 */
public class JsonStorage {
	
	private static final String PROPERTIES_FILE = "/application.properties";
	private static final String PROPERTY_KEY_FILE = "storage.file";
	
	private static final Logger LOGGER = LogManager.getLogger("JsonStorage");

	/**
	 * Properties.
	 */
	private Properties prop;

	/**
	 * The constructor
	 */
	public JsonStorage() {
		this.prop = new Properties();
	}

	/**
	 * Set properties.
	 * @param prop a Properties object.
	 */
	public void setProp(Properties prop) {
		this.prop = prop;
	}

	/**
	 * Read the json file.
	 * @return Storage
	 * @throws StorageException
	 */
	public Storage readStorage() throws StorageException {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();
		try (InputStream fis = this.getClass().getResourceAsStream(PROPERTIES_FILE)) {
			
			prop.load(fis);
			
			Reader reader = Files.newBufferedReader(Paths.get(prop.getProperty(PROPERTY_KEY_FILE)));
			Storage storage = gson.fromJson(reader, Storage.class);
			reader.close();
			
			if(storage == null) {
				storage = new Storage(new ArrayList<Person>(), new ArrayList<Firestation>(), 
								new ArrayList<MedicalRecord>());
			}
			
			return storage;
		} catch (Exception e) {
			LOGGER.error("An error occured when reading storage file: " + e.getMessage(), e);
			throw new StorageException("An error occured when reading storage file.", e);
		}
	}

	/**
	 * Write the json file.
	 * @param storage a Storage object.
	 * @throws StorageException
	 */
	public void writeStorage(Storage storage) throws StorageException {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();
		try (InputStream fis = this.getClass().getResourceAsStream(PROPERTIES_FILE)) {
			
			prop.load(fis);
			
			Writer writer = new FileWriter(prop.getProperty(PROPERTY_KEY_FILE), false);
			gson.toJson(storage, writer);
			writer.close();
		} catch (Exception e) {
			LOGGER.error("An error occured when writing storage file: " + e.getMessage(), e);
			throw new StorageException("An error occured when writing storage file.", e);
		}
	}
}

/**
 * An implementation of JsonSerializer<T> and JsonDeserializer<T>
 * @author tipikae
 * @version 1.0
 *
 */
class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
	
	private static final String DATE_FORMAT = "MM/dd/yyyy";

	/**
	 * {@inheritDoc}
	 * @param date {@inheritDoc}
	 * @param typeOfSrc {@inheritDoc}
	 * @param context {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    	return new JsonPrimitive(date.format(formatter));
    }

	/**
	 * {@inheritDoc}
	 * @param json {@inheritDoc}
	 * @param typeOfT {@inheritDoc}
	 * @param context {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws JsonParseException
	 */
	@Override
	public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return LocalDate.parse(json.getAsString(), formatter);
	}
}
