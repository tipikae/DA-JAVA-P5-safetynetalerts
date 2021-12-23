package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.dto.ChildAlertDTO;
import com.tipikae.safetynetalerts.dto.CommunityEmailDTO;
import com.tipikae.safetynetalerts.dto.FireDTO;
import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.dto.FloodDTO;
import com.tipikae.safetynetalerts.dto.PersonInfoDTO;
import com.tipikae.safetynetalerts.dto.PhoneAlertDTO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;

public interface IInformationService {

	FirestationDTO getResidentsByStation(int stationNumber) throws ServiceException, StorageException;
	ChildAlertDTO getChildrenByAddress(String address) throws ServiceException, StorageException;
	PhoneAlertDTO getPhoneNumbersByStation(int station) throws ServiceException, StorageException;
	FireDTO getMembersByAddress(String address) throws ServiceException, StorageException;
	List<FloodDTO> getResidentsByStations(List<Integer> stations) throws ServiceException, StorageException;
	PersonInfoDTO getPersonInfoByLastname(String firstname, String lastname) 
			throws ServiceException, StorageException;
	CommunityEmailDTO getEmailsByCity(String city) throws ServiceException, StorageException;
}
