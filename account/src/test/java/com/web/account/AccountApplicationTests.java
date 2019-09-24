package com.web.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountApplicationTests {

    @Test
    public void contextLoads() throws ParseException {
        Date dt=new Date();

        String year=String.format("%tY", dt);

        String mon=String .format("%tm", dt);

//        String day=String .format("%td", dt);


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now = df.parse("2004-03-26 13:31:40");
        java.util.Date date=df.parse("2004-01-02 11:30:24");
        long l=now.getTime()-date.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);

        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");

        System.out.println(year);

        System.out.println(mon);

        System.out.println(day);

    }

}
