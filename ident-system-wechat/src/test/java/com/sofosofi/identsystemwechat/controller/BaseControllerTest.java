package com.sofosofi.identsystemwechat.controller;

import com.sofosofi.identsystemwechat.common.Constants;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.awt.*;

public class BaseControllerTest {

    MultiValueMap<String, String> headers = new LinkedMultiValueMap();

    private String tempToken = "ef774b2b-db21-4f1e-b8ea-474663904a2d";

    private String userName = "sofosofi";

    BaseControllerTest(){
        headers.set(Constants.HEADER_USER_NAME, userName);
        headers.set(Constants.HEADER_USER_TOKEN, tempToken);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

}
