package com.cj.core.service.Impl;

import com.cj.core.domain.Datapram;
import com.cj.core.service.DBService;
import com.cj.core.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private Datapram datapram;
    @Override
    public void backup() {
        String string = datapram.getTooldir()+" -h "+datapram.getDbip()+" -u"+datapram.getUsername()+" -p"+datapram.getPassword()+" "+datapram.getDbname();

        SqlUtil.backup(string,datapram.getDir());
    }
}
