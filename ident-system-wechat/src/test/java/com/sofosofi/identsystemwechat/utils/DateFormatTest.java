package com.sofosofi.identsystemwechat.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

@Slf4j
public class DateFormatTest {

    @Test
    public void testFormat() {
        Date date = DateUtils.addHours(new Date(), 13);
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        log.info(format);
    }
}
