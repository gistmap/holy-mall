package com.gistmap.ddbeat.user.web;

import com.gistmap.common.json.JsonResponseEntity;
import com.gistmap.ddbeat.user.service.AddressService;
import com.gistmap.ddbeat.user.service.dto.AddressDTO;
import com.gistmap.ddbeat.user.service.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangran
 * @date 2018/8/3
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public JsonResponseEntity save(AddressDTO dto){
        addressService.save(dto);
        return new JsonResponseEntity();
    }

    @DeleteMapping
    public JsonResponseEntity remove(String ids) {
        addressService.delete(ids);
        return new JsonResponseEntity();
    }

    @PutMapping
    public JsonResponseEntity defaultAddress(String id) {
        addressService.setDefault(id);
        return new JsonResponseEntity();
    }

    @GetMapping
    public JsonResponseEntity<AddressVO> get(String id) {
        JsonResponseEntity<AddressVO> response = new JsonResponseEntity<>();
        response.data = new AddressVO(addressService.get(id));
        return response;
    }
}
