package com.ericsson.MainBackUp.Controllers;

import java.util.Arrays;
import java.util.HashMap;

public class MapController {

    HashMap<String, String> map = new HashMap<>();
    HashMap<String, String> scheduledForDBAdditionMap = new HashMap<>();
   public void createMap (String id, String value) {

       //Case1: duplicate keys -> error
       //Case2: unique keys
       map.put(id,value);

       //Case3:

   }
   public void printMap () {
       map.forEach((key,value) -> System.out.println("Key: " +key + " Value: " + value));
   }

   public HashMap<String, String> getMap() {
        return map;
    }



    public void compareMaps(HashMap<String,String> mainMap, HashMap<String,String> backUpMap) {

       int count =0;

       if (mainMap.size() != backUpMap.size()) {
            for (String key: mainMap.keySet()) {
                //if key main ==key backup, check for value equality
//                System.out.println("MATCH-> Key: "+ key);
                if (backUpMap.containsKey(key)) {
//                    System.out.println("KEY: " + "Backup: " + backUpMap.containsKey(key) + " main: " + mainMap.containsKey(key));

                    String backUpMapValue = backUpMap.get(key).substring(1,backUpMap.get(key).length()-1);

//                    System.out.println(mainMap.get(key) + " --  " + backUpMapValue);
                    if (mainMap.get(key).equals(backUpMapValue)) {
//                        System.out.println("Keys do not match for key: " + key + "  Mainvalue: " + mainMap.get(key) +  "  Backupvalue: "+  backUpMap.get(key));
//                        System.out.println("VALUES MATCH");
                    }

                }
                //if key main != key backup, schedule for addition to database
                else if (!backUpMap.containsKey(key)) {
                    if (count>1) {break;}
                    System.out.println("ADDING Key -" + key +  "  value: "+ mainMap.get(key));
                     int repo_id =1;
                     String[] args = mainMap.get(key).split(",");
                    System.out.println("REPO NAME: " + args[0] + " FILENAME: " + args[1] + " Lines added: " + args[2] + " Lines removed: " + args[3]  + " Change Set Max: " + args[4]
                            + " Change Set Avg: " + args[5]  + " Code Churn Avg: " + args[6]  + " Code Churn Max: " + args[7]  + " Count All Contributors: " + args[8]
                            + " Count Minor Contributors: " + args[9]  + " Hunks Count: " + args[10]);

                     String repo_name= args[0].substring(1,args[0].length());

                     String hash =key;
                     String filename= args[1];
                     int lines_added= Integer.parseInt(args[2]);
                     int lines_removed = Integer.parseInt( args[3]);
                     int change_set_max = Integer.parseInt(args[4]);
                     int change_set_avg = Integer.parseInt(args[5]);
                     int code_churn_avg = Integer.parseInt(args[6]);
                     int code_churn_max = Integer.parseInt(args[7]);
                     int contributor_count = Integer.parseInt(args[8]);
                     int minor_contributor_count = Integer.parseInt(args[9]);
                     int hunks_count = Integer.parseInt(args[10]);
                }
            }
       }
    }
}
