package com.ja.plant;

public class Settings {
    private static final String FILENAME = "resources/kvetiny.txt";
    public static String getFilename(){
        return FILENAME;
    }
    private static final String DELIMITER = "\t";
    private static final String FILE_NAME_OUT = "resources/outputKvetiny.txt";


    public static String getDelimiter() {
        return DELIMITER;
    }

    public static String getFileNameOut() {
        return FILE_NAME_OUT;
    }

}
