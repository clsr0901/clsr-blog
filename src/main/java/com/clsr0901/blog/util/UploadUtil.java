package com.clsr0901.blog.util;

import java.io.File;
import java.io.FileOutputStream;

public class UploadUtil {
    public static File uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        File destFile = new File(filePath + "/" + fileName);
        FileOutputStream out = new FileOutputStream(destFile);
        out.write(file);
        out.flush();
        out.close();
        return destFile;
    }
}
