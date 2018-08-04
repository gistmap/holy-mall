package com.gistmap.ddbeat.user.service.vo;

import com.gistmap.ddbeat.user.persistence.domain.Address;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangran
 * @date 2018/8/2
 */
public class AddressVO {

    public String id;

    public String name;

    public String info;

    public String mobile;

    public boolean acquiescence;

    public AddressVO(Address address) {
        if (address != null) {
            BeanUtils.copyProperties(address, this);
        }
    }

}
