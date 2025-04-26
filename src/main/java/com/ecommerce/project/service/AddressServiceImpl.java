package com.ecommerce.project.service;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        Address address = modelMapper.map(addressDTO, Address.class);

        // setting address in user
        List<Address> addressList = user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);

        address.setUser(user);

        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addressList = addressRepository.findAll();

        List<AddressDTO> addressDTOList =  addressList.stream()
                .map(a -> modelMapper.map(a, AddressDTO.class))
                .toList();

        return addressDTOList;
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
       Address address = addressRepository.findById(addressId)
               .orElseThrow(() -> new ResourceNotFoundException("Address", "AddressId", addressId));

        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddresses(User user) {
        List<Address> addressList = user.getAddresses();
        List<AddressDTO> addressDTOList =  addressList.stream()
                .map(a -> modelMapper.map(a, AddressDTO.class))
                .toList();

        return addressDTOList;
    }

    @Override
    public AddressDTO updateAddressById(Long addressId, AddressDTO addressDTO) {
        Address addressFromDb = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "AddressId", addressId));

        addressFromDb.setCity(addressDTO.getCity());
        addressFromDb.setPinCode(addressDTO.getPinCode());
        addressFromDb.setState(addressDTO.getState());
        addressFromDb.setCountry(addressDTO.getCountry());
        addressFromDb.setStreet(addressDTO.getStreet());
        addressFromDb.setBuildingName(addressDTO.getBuildingName());

        Address updatedAddress = addressRepository.save(addressFromDb);

        User user = addressFromDb.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);

        userRepository.save(user);

        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public String deleteByAddressId(Long addressId) {
        Address addressFromDb = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "AddressId", addressId));

        User user = addressFromDb.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);

        addressRepository.delete(addressFromDb);


        return "Address Deleted Successfully with addressId : " + addressId ;
    }
}
