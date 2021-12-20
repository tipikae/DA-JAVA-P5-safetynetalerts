package com.tipikae.safetynetalerts.unit.storage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.storage.JsonStorage;

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
	void testReadStorage_whenError() throws IOException {
		doThrow(IOException.class).when(prop).load(any(InputStream.class));
		jsonStorage.setProp(prop);
		assertThrows(StorageException.class, () -> jsonStorage.readStorage());
	}

	@Test
	void testWriteStorage_whenError() throws StorageException, IOException {
		doThrow(IOException.class).when(prop).load(any(InputStream.class));
		jsonStorage.setProp(prop);
		assertThrows(StorageException.class, () -> jsonStorage.writeStorage(null));
	}
}
