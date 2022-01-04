package com.tipikae.safetynetalerts.unit.storage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.storage.JsonStorage;

@ExtendWith(MockitoExtension.class)
class JsonStorageTest {

	@InjectMocks
	private JsonStorage storage;
	
	@Mock
	private Properties prop;
	
	@Test
	void testReadStorage_whenError() throws IOException {
		doThrow(IOException.class).when(prop).load(any(InputStream.class));
		assertThrows(StorageException.class, () -> storage.readStorage());
	}

	@Test
	void testWriteStorage_whenError() throws StorageException, IOException {
		doThrow(IOException.class).when(prop).load(any(InputStream.class));
		assertThrows(StorageException.class, () -> storage.writeStorage(null));
	}
}
