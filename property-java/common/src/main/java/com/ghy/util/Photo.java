package com.ghy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class Photo {

    public static String getBase64Photo(byte[] photoData) {
        return "data:image/png;base64,"+Base64.getEncoder().encodeToString(photoData);
    }
}
