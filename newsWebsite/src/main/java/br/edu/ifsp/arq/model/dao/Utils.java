package br.edu.ifsp.arq.model.dao;

import java.io.File;
import java.io.IOException;

public class Utils {

    public static void createDirectoryIfNotExists(String basePath, String filePath) {

        File directory = new File(basePath);
        if (!directory.exists())
            directory.mkdir();

        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
