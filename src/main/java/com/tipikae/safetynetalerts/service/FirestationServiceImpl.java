package com.tipikae.safetynetalerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IFirestationDAO;
import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.dtoconverter.IFirestationConverter;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;

/**
 * An implementation of IFirestationService.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class FirestationServiceImpl implements IFirestationService {
	
	private static final Logger LOGGER = LogManager.getLogger("FirestationServiceImpl");

	/**
	 * The DAO.
	 */
	@Autowired
	private IFirestationDAO dao;
	
	/**
	 * The DTO converter.
	 */
	@Autowired
	private IFirestationConverter converter;

	/**
	 * {@inheritDoc}
	 * @param firestation {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public FirestationDTO addFirestationMapping(FirestationDTO firestationDTO) throws ServiceException, StorageException {
		Firestation firestation = converter.toEntity(firestationDTO);
		if(dao.findByAddress(firestation.getAddress()) == null) {
			return converter.toDTO(dao.save(firestation));
		} else {
			LOGGER.error("addFirestationMapping: mapping with address: " + firestation.getAddress()
					+ " already exists.");
			throw new ServiceException("Mapping with address: " + firestation.getAddress()
					+ " already exists.");
		}
		
	}

	/**
	 * {@inheritDoc}
	 * @param address {@inheritDoc}
	 * @param firestation {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public FirestationDTO updateFirestationMapping(String address, FirestationDTO firestationDTO) 
			throws ServiceException, StorageException {
		if (address.equals(firestationDTO.getAddress())) {
			Firestation firestation = converter.toEntity(firestationDTO);
			if (dao.findByAddress(address) != null) {
				return converter.toDTO(dao.update(firestation));
			} else {
				LOGGER.error("updateFirestationMapping: Address: " + address + " not found in Firestation.");
				throw new ServiceException("Address: " + address + " not found in Firestation.");
			} 
		} else {
			LOGGER.error("updateFirestationMapping: Address parameter: " + address + 
					" is different from Firestation address: " + firestationDTO.getAddress());
			throw new ServiceException("Address parameter: " + address + 
					" is different from Firestation address: " + firestationDTO.getAddress());
		}
	}

	/**
	 * {@inheritDoc}
	 * @param address {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public void deleteFirestationByAddress(String address) throws StorageException, ServiceException {
		Firestation firestation = dao.findByAddress(address);
		if(firestation != null) {
			dao.delete(firestation);
		} else {
			LOGGER.error("deleteFirestationByAddress: Address: " + address + " not found in Firestation.");
			throw new ServiceException("Address: " + address + " not found in Firestation.");
		}
	}

	/**
	 * {@inheritDoc}
	 * @param station {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public void deleteFirestationsByStation(int station) throws ServiceException, StorageException {
		List<Firestation> firestations = dao.findByStation(station);
		if(!firestations.isEmpty()) {
			dao.deleteFirestations(firestations);
		} else {
			LOGGER.error("deleteFirestationsByStation: Station: " + station + " not found in Firestation.");
			throw new ServiceException("Station: " + station + " not found in Firestation.");
		}
	}
}
