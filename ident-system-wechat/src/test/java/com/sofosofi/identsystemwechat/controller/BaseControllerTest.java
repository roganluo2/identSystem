package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.Constants;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.awt.*;

public class BaseControllerTest {

    MultiValueMap<String, String> headers = new LinkedMultiValueMap();

    private String tempToken = "7e0c9411-5239-42bd-b930-551aefd871c7";

    private String userName = "sofosofi";

    BaseControllerTest(){
        headers.set(Constants.HEADER_USER_NAME, userName);
        headers.set(Constants.HEADER_USER_TOKEN, tempToken);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

}
