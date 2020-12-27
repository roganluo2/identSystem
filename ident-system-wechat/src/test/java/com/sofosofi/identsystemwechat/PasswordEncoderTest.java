package com.sofosofi.identsystemwechat;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class PasswordEncoderTest {

    @Test
    public void testMatch() {
        String actualPassword = "$2a$10$9enmi7sZdbz74g1V7XGJp.BKXGzDG2SZPsKECZPNShY8B/YQ2Fdlq";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encode = passwordEncoder.encode(rawPassword);
        log.info(encode);
        log.info("" + passwordEncoder.matches(rawPassword, actualPassword));
    }
}
