package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.Constants;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.awt.*;

public class BaseControllerTest {

    MultiValueMap<String, String> headers = new LinkedMultiValueMap();

    private String tempToken = "882ca748-2950-450b-b8f8-a8c5b8a4dd7e";

    private String userName = "sofosofi";

    BaseControllerTest(){
        headers.set(Constants.HEADER_USER_NAME, userName);
        headers.set(Constants.HEADER_USER_TOKEN, tempToken);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

}
