package com.tgw.account.receiptsTypeSys.service.impl;


import com.tgw.account.receiptsTypeSys.dao.ReceiptsTypeSysMapper;
import com.tgw.account.receiptsTypeSys.service.ReceiptsTypeSysService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("receiptsTypeSysService")
public class ReceiptsTypeSysServiceImpl extends BaseServiceImpl implements ReceiptsTypeSysService {
    @Resource
    private ReceiptsTypeSysMapper receiptsTypeSysMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=receiptsTypeSysMapper ){
            super.setBaseModelMapper( this.getReceiptsTypeSysMapper() );
        }
    }

    public List<Map<String, Object>> queryReceiptsTypeSysTreeMap() throws PlatformException {
        return this.getReceiptsTypeSysMapper().queryReceiptsTypeSysTreeMap();
    }

    public void checkBeforeDelete(List<String> idList) {
        if( getReceiptsTypeSysMapper().queryChildRecord( idList )>0 ){
            throw new PlatformException( "要删除的类型中有子收入类型，请先删除子收入类型！" );
        }
    }

    public ReceiptsTypeSysMapper getReceiptsTypeSysMapper() {
        return receiptsTypeSysMapper;
    }

    public void setReceiptsTypeSysMapper(ReceiptsTypeSysMapper receiptsTypeSysMapper) {
        this.receiptsTypeSysMapper = receiptsTypeSysMapper;
    }
}
