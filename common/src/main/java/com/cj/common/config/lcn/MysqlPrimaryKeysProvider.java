package com.cj.common.config.lcn;

import com.codingapi.txlcn.common.util.Maps;
import com.codingapi.txlcn.tc.core.transaction.txc.analy.def.PrimaryKeysProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class MysqlPrimaryKeysProvider implements PrimaryKeysProvider {

    @ApiOperation(value = "provide", notes = "LCN-TXC模式定义表的实际主键")
    @Override
    public Map<String, List<String>> provide() {
        return Maps.of("t_demo", Collections.singletonList("kid"));
    }
}
