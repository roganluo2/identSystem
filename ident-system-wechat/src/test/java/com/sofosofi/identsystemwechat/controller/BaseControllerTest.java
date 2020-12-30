package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.Constants;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.awt.*;

public class BaseControllerTest {

    MultiValueMap<String, String> headers = new LinkedMultiValueMap();

    private String tempToken = "9eb2452b-9af3-479e-b011-ef45ac215cc7";

    private String userName = "sofosofi";

    BaseControllerTest(){
        headers.set(Constants.HEADER_USER_NAME, userName);
        headers.set(Constants.HEADER_USER_TOKEN, tempToken);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

}
