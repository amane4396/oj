package org.oj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {

    public Class<?> loadClassFromFile(String filePath) throws IOException {
        byte[] classData = loadClassData(filePath);
        return defineClass(null, classData, 0, classData.length);
    }

    private byte[] loadClassData(String filePath) throws IOException {
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            return buffer;
        }
    }

}
