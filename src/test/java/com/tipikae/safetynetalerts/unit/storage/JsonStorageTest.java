package com.tipikae.safetynetalerts.unit.storage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import java.io.File;
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
	private JsonStorage jsonStorage;
	
	@Mock
	private Properties prop;
	@Mock
	private File file;
	
	@Test
	void testReadStorage_whenPropLoadError() throws IOException {
		doThrow(IOException.class).when(prop).load(any(InputStream.class));
		assertThrows(StorageException.class, () -> jsonStorage.readStorage());
	}

	@Test
	void testWriteStorage_whenPropLoadError() throws StorageException, IOException {
		doThrow(IOException.class).when(prop).load(any(InputStream.class));
		assertThrows(StorageException.class, () -> jsonStorage.writeStorage(null));
	}
}
