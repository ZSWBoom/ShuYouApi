package com.module.user.service.impl;

import com.module.user.dao.DogDetailMapper;
import com.module.user.service.DogDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dogDetailService")
public class DogDetailServiceImpl implements DogDetailService {
    @Autowired
    private DogDetailMapper dogDetailMapper;

    public String getDogDetail(String name) {
        return this.dogDetailMapper.selectDogDetail(name);
    }
}
