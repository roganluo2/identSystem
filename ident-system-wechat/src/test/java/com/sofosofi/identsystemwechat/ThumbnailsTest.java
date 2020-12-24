package com.sofosofi.identsystemwechat;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;

import java.io.IOException;

/**
 * @Description TODO
 * @Date 2020/12/23
 * @Created by rogan.luo
 */
public class ThumbnailsTest {

    @Test
    public void test1() throws IOException {
        /*
         * size(width,height) 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        Thumbnails.of("C:\\Users\\1\\Desktop\\jobFiles\\鉴真\\pic\\20201223003112.jpg").size(750, 1280).toFile("C:\\Users\\1\\Desktop\\jobFiles\\鉴真\\pic\\thumb\\20201223003112.jpg");
    }
}
