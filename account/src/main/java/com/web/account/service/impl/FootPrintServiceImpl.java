package com.web.account.service.impl;

import com.web.account.entity.FootPrint;
import com.web.account.mapper.FootPrintMapper;
import com.web.account.service.IFootPrintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author luotao
 */
@Service
@Slf4j
public class FootPrintServiceImpl implements IFootPrintService {

    @Resource
    FootPrintMapper footprintMapper;

    @Override
    public List<FootPrint> footPrintList(int uid) {
        List<FootPrint> footPrints = footprintMapper.findByUid(uid);
        return footPrints;
    }

    @Override
    public int updateFootPrint(int uid, int shop_id, Calendar time) {
        FootPrint newest = footprintMapper.findNewest(uid, shop_id);
        long newTime = time.getTime().getTime();
        int days = time.get(Calendar.DATE);
        int mouth = time.get(Calendar.MONTH) + 1;
        if (newest != null){
            Date browseTime = newest.getBrowseTime();
            long oldTime = browseTime.getTime();
            long day = (newTime - oldTime)/(24*60*60*1000);
            if(day >=1 ){
                int count = footprintMapper.insertPoint(uid, shop_id, days, mouth);
                return count;
            }else{
                int count = footprintMapper.updateViews(uid, shop_id);
                return count;
            }
        }else{
            int count = footprintMapper.insertPoint(uid, shop_id, days, mouth);
            return count;
        }
    }
}
