package com.way2automation.helpers;

import java.io.File;

public class FileUtils {

    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }
}