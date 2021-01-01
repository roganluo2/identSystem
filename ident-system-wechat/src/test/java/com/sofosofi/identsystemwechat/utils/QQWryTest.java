package com.sofosofi.identsystemwechat.utils;

import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QQWryTest {

    @Test
    public void testQueryLocation() throws IOException {

        QQWry qqwry = new QQWry(); // load qqwry.dat from classpath

//        QQWry qqwry = new QQWry(Paths.get("path/to/qqwry.dat")); // load qqwry.dat from java.nio.file.Path

//        byte[] data = Files.readAllBytes(Paths.get("path/to/qqwry.dat"));
//        QQWry qqwry = new QQWry(data); // create QQWry with provided data

        String dbVer = qqwry.getDatabaseVersion();
//        System.out.printf("qqwry.dat version=%s", dbVer);
//        String myIP = "127.0.0.1";
        String myIP = "120.79.226.150";
        IPZone ipzone = qqwry.findIP(myIP);
        System.out.printf("%s, %s", ipzone.getMainInfo(), ipzone.getSubInfo());
    }


}
