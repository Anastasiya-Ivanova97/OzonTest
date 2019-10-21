package ru.aplana.autotest.util;

import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureListener extends AllureJunit4 {
    @Attachment(value = "Вложение")
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/resources", resourceName));
    }
}
