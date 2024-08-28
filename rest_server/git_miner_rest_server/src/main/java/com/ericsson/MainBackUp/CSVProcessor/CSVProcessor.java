package com.ericsson.MainBackUp.CSVProcessor;

import com.ericsson.MainBackUp.Model.FileCommit;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVProcessor
{

    private HashMap<String,String> mainCsv;
    private HashMap<String,String> backupCsv;

    private HashMap<String,String> scheduledCsv;


    private final String MAINCSVPATH = "C:\\Users\\ETMGINU\\Downloads\\CSV\\src\\main\\resources\\main.csv";
    private final String BACKUPCSVPATH = "C:\\Users\\ETMGINU\\Downloads\\CSV\\src\\main\\resources\\backup.csv";

    public CSVProcessor()
    {
        mainCsv = new HashMap<String,String>();
        backupCsv = new HashMap<String,String>();
        scheduledCsv = new HashMap<String, String>();
    }

    public void initCsvProcessing() throws CsvValidationException, IOException {
        System.out.println("Read main csv file");
        readMainCsv();
        System.out.println("Read backup csv file");
        readBackupCsv();
        System.out.println("Compare both csv file");
        compareCsv();
        System.out.println("Overwrite backup csv file");
        overwriteBackupCsv();
    }

    public void compareCsv()
    {
        scheduledCsv.clear();

        if (backupCsv.isEmpty())
        {
            backupCsv = mainCsv;
        }

        for (Map.Entry<String, String> mainSet: mainCsv.entrySet())
        {
            boolean found = false;
            for (Map.Entry<String, String> backupSet: backupCsv.entrySet())
            {
                if (mainSet.getKey().equals(backupSet.getKey()))
                {
                    found = true;
                    if (!mainSet.getValue().equals(backupSet.getValue()))
                    {
                        scheduledCsv.put(mainSet.getKey(),mainSet.getValue());
                    }
                    else
                    {
                        continue;
                    }
                }
            }
            if (!found)
            {
                scheduledCsv.put(mainSet.getKey(),mainSet.getValue());
            }
        }

        System.out.println("Total difference = "+scheduledCsv.size());
    }

    public void readMainCsv() throws IOException, CsvValidationException {
        mainCsv.clear();
        //Instantiating the CSVReader class
        CSVReader reader = null;
        reader = new CSVReader(new FileReader(MAINCSVPATH));

        //Reading the contents of the csv file
        StringBuffer buffer = new StringBuffer();
        String line[];
        boolean first = true;
        while ((line = reader.readNext()) != null)
        {
            //skip the header
            if (first)
            {
                first = false;
                continue;
            }

            for(int i = 0; i<line.length; i++) {
                System.out.print(line[i]+",");
            }

            System.out.println("");

            String wholeString = "";
            for (String s : line)
            {
                wholeString += s + ",";
            }

            //removes an extra comma at the end of this string
            wholeString = wholeString.substring(0, wholeString.length() -1);

            mainCsv.put(line[0],wholeString);

        }
    }

    public void readBackupCsv() throws IOException, CsvValidationException {
        backupCsv.clear();

        //Instantiating the CSVReader class
        CSVReader reader = null;
        reader = new CSVReader(new FileReader(BACKUPCSVPATH));

        //Reading the contents of the csv file
        StringBuffer buffer = new StringBuffer();
        String line[];

        boolean first = true;
        while ((line = reader.readNext()) != null)
        {
            //skip the header
            if (first)
            {
                first = false;
                continue;
            }

            for(int i = 0; i<line.length; i++) {
                System.out.print(line[i]+",");
            }

            System.out.println("");

            String wholeString = "";
            for (String s : line)
            {
                wholeString += s + ",";
            }

            //removes an extra comma at the end of this string
            wholeString = wholeString.substring(0, wholeString.length() -1);

            backupCsv.put(line[0],wholeString);

        }
    }

    public void overwriteBackupCsv() throws IOException {
        //clear the backup csv
        new FileWriter(BACKUPCSVPATH, false).close();

        FileWriter csvWriter = new FileWriter(BACKUPCSVPATH);

        csvWriter.append("hash,repo_name,filename,lines_added,lines_removed,change_set_max,change_set_avg,code_churn_avg,code_churn_max,hunks_count\n");
        for (Map.Entry<String,String> mainSet: mainCsv.entrySet())
        {
            csvWriter.append(mainSet.getValue()+"\n");
        }
        csvWriter.flush();
        csvWriter.close();

    }

    public HashMap<String, String> getScheduledCsv()
    {
        return scheduledCsv;
    }

}
