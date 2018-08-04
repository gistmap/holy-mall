package com.gistmap.ddbeat.user.service;

import com.gistmap.common.exception.BaseException;
import com.gistmap.ddbeat.user.persistence.domain.Address;
import com.gistmap.ddbeat.user.persistence.repository.AddressRepository;
import com.gistmap.ddbeat.user.service.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangran
 * @date 2018/8/2
 */
@Component
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void save(AddressDTO dto) {
        Address address;
        if (dto.id == null) {
            address = new Address();
            address.setInfo(dto.info);
            address.setName(dto.name);
            address.setMobile(dto.mobile);
            List<Address> all = addressRepository.findAll();
            if (all.size() == 0) {
                address.setAcquiescence(true);
            }
            address.setUser(userService.get(dto.uid));
            addressRepository.save(address);
        } else {
            address = get(dto.id);
            address.setInfo(dto.info);
            address.setName(dto.name);
            address.setMobile(dto.mobile);
            addressRepository.save(address);
        }
    }
    
    public void setDefault(String id) {
        Address address = get(id);
        address.setAcquiescence(true);
        addressRepository.save(address);
    }

    @Transactional
    public void delete(String addressIds){
        String[] ids = addressIds.split(",");
        for (String id : ids) {
            Address address = get(id);
            address.setDelFlag(true);
            addressRepository.save(address);
        }

    }


    public Address get(String id) {
        return addressRepository.findById(id).orElseThrow(() -> new BaseException("不存在的地址"));
    }
}
