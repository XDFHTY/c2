package com.cj.sadmin.service.impl;

import com.cj.common.exception.UserException;
import com.cj.sadmin.mapper.AdminMapper;
import com.cj.sadmin.service.TestService;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements TestService {


    @Autowired
    private AdminMapper adminMapper;

    @LcnTransaction
    @Override
    public List testTx() {
        int i = adminMapper.testTX();


        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        return list;
    }
}
