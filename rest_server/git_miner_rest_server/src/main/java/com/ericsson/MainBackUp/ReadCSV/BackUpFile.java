package com.ericsson.MainBackUp.ReadCSV;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BackUpFile {

    final static String outputFilePath
            = "C:\\Users\\ETMGINU\\Downloads\\CSV\\src\\main\\resources\\backup.csv";

    HashMap<String,String> backUpMap = new HashMap<>();

    public static void main(String[] args) {
        BackUpFile backUpFile = new BackUpFile();

        HashMap readInMap= backUpFile.readFromBackUpFileToMap();
        readInMap.forEach((k,v) -> System.out.println("Key: " + k +"  Value: " + v));

    }
    public void writeToBackUp(HashMap<String, String> hashMap)
    {

        //Case1: File is empty -> add
        //Case2: File is not empty -> append
        File file = new File(outputFilePath);

        BufferedWriter bf = null;

        try {

            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));

            // iterate map entries
            for (Map.Entry<String, String> entry :
                    hashMap.entrySet()) {

                // put key and value separated by a colon
                bf.write(entry.getKey() + ","
                        + entry.getValue());

                // new line
                bf.newLine();
            }

            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {

                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
    }

    public HashMap readFromBackUpFileToMap () {
        {
            CSVReader reader = null;
            try
            {
//parsing a CSV file into CSVReader class constructor
                reader = new CSVReader(new FileReader("C:\\Users\\ETMGINU\\Downloads\\CSV\\src\\main\\resources\\backup.csv"));
                String [] nextLine;
//reads one line at a time
                while ((nextLine = reader.readNext()) != null)
                {
//                    System.out.println("Start: " +nextLine[0] + "  REST:" + nextLine[1] +"--"+ nextLine[2]  +"--"+ nextLine[3]);
                    String key = nextLine[0];
                    String tempValue = Arrays.toString(Arrays.copyOfRange(nextLine, 1, nextLine.length)) ;
                    String value = tempValue.replaceAll(", ",",");
//                    System.out.println(value);
                    //add to hashmap
                    backUpMap.put(key,value);

                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return backUpMap;
    }
}
