package com.seconds.service.impl;

import com.seconds.dao.DisallowWordDao;
import com.seconds.entity.DisallowWord;
import com.seconds.service.DisallowWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisallowWordServiceImpl implements DisallowWordService {
    @Autowired
    private DisallowWordDao disallowWordDao;

    @Override
    public List<DisallowWord> selectAll() {
        return disallowWordDao.selectAll();
    }
}
