package ru.eldorado.web.utils;

import java.io.*;

/**
 * Created by OsipovI on 15.07.2015.
 */
public class NumberWriter {
    public static void writeOut(String fileName, String text) throws FileNotFoundException {
        File file = new File(fileName);

        try {
            if(!file.exists()){ file.createNewFile();}

            FileWriter fileWriter = new FileWriter(file, true);
            try {
                fileWriter.write(text);
            } finally {
                fileWriter.close();
            }

        } catch (IOException e) { throw new RuntimeException(e);}
    }

}
