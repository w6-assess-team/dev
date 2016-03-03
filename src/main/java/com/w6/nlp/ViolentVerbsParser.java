package com.w6.nlp;

import static edu.stanford.nlp.util.logging.RedwoodConfiguration.Handlers.file;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.*;

public class ViolentVerbsParser {
    
   static Set violentWords = null;
   static String globalPpath = "/src/resources/violentVerbsDictionary.txt";
   
   private static void setViolentDictionary() throws IOException{
       if(violentWords == null){
           violentWords = new HashSet<String>();
       }
        try{
            BufferedReader br = new BufferedReader(new FileReader(globalPpath));
            String str;
            while ((str = br.readLine()) != null) {
                violentWords.add(str);
            }
        } catch (IOException e){
            
        }
   }
   
   public static List<String> getAllViolentVerbs(String text){
       try {
          setViolentDictionary();
       } catch (Exception e) {
       }
       List<String> result = new ArrayList<>();
       
       return result;
       
   }
           
}
