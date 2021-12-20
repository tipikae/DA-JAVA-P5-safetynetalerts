package com.tipikae.safetynetalerts.unit.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.JsonIOException;
import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@ExtendWith(MockitoExtension.class)
class JsonStorageTest {
	
	@Mock
	private Properties prop;
	
	private static JsonStorage jsonStorage;
	
	@BeforeAll
	private static void setUp() {
		jsonStorage = new JsonStorage();
	}
	
	@Test
	void testReadStorage_whenError() throws FileNotFoundException, IOException {
		doThrow(IOException.class).when(prop).load(any(InputStream.class));
		jsonStorage.setProp(prop);
		assertNull(jsonStorage.readStorage());
	}

	@Test
	void testWriteStorage_whenError() throws JsonIOException, IOException {
		Storage storage = jsonStorage.readStorage();
		doThrow(IOException.class).when(prop).load(any(InputStream.class));
		jsonStorage.setProp(prop);
		assertFalse(jsonStorage.writeStorage(storage));
	}
}
