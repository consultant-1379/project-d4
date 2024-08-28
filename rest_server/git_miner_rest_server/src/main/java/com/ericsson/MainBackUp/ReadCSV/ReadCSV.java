package com.ericsson.MainBackUp.ReadCSV;// Java code to illustrate
// Reading CSV File with different separator


import com.ericsson.MainBackUp.Controllers.MapController;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.*;

public class ReadCSV {

    HashMap<String ,String> mainMap = new HashMap<>();

    public  void ReadMainCSVFirstTime(String file) {
        try {
            // Create an object of file reader class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvParser object with
            // custom separator semi-colon
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            // create csvReader object with parameter
            // filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();

            // Read all data at once
            List<String[]> allData = csvReader.readAll();
                int x =0;
//            // Print Data.
            MapController mapController = new MapController();
            BackUpFile backUpFile = new BackUpFile();

            for (String[] row : allData) {

                String[] rowInfo = row[0].split(",");
                int rando = 1 + (int)(Math.random() * ((1_000_000 - 1) + 1));
                String id = rowInfo[0];//+rando;
                String value = rowInfo[1] + ","+ rowInfo[2] + ","+ rowInfo[3];
                System.out.println("Key: " + rowInfo[0] + " Value: " +value );

//                System.out.println("rando: " +rando );
                mapController.createMap(id, value);
            }

//            System.out.println("Map output");
//            mapController.printMap();
            System.out.println("Total Rows: " + allData.size());

            HashMap<String, String> mainMap = mapController.getMap();
            setMainMap(mainMap);



//            System.out.println(mainMap.keySet());
            backUpFile.writeToBackUp(mainMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void ReadMainCSV(String file) {
        try {
            // Create an object of file reader class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvParser object with
            // custom separator semi-colon
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            // create csvReader object with parameter
            // filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();

            // Read all data at once
            List<String[]> allData = csvReader.readAll();
            int x =0;
//            // Print Data.
            MapController mapController = new MapController();
            BackUpFile backUpFile = new BackUpFile();

            for (String[] row : allData) {

                String[] rowInfo = row[0].split(",");
                int rando = 1 + (int)(Math.random() * ((1_000_000 - 1) + 1));
                String id = rowInfo[0];//+rando;
                String value = Arrays.toString(Arrays.copyOfRange(rowInfo, 1, rowInfo.length)) ;

                //= rowInfo[1] + ","+ rowInfo[2] + ","+ rowInfo[3];
//                System.out.println("Key: " + rowInfo[0] + " Value: " +value );

//                System.out.println("rando: " +rando );
                mapController.createMap(id, value);
            }

//            System.out.println("Map output");
//            mapController.printMap();
            System.out.println("Total Rows: " + allData.size());

            HashMap<String, String> mainMap = mapController.getMap();
            setMainMap(mainMap);
//            System.out.println(mainMap.keySet());
//            backUpFile.writeToBackUp(mainMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, String> getMainMap() {
        return mainMap;
    }

    public void setMainMap(HashMap<String, String> mainMap) {
        this.mainMap = mainMap;
    }
}