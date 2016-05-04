package com.w6.nlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.w6.data.CollectionOfWords;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

public class WeaponsParser {
   private Set weapons;
   private final String globalPath = "/weapons.txt";
   
   
   public WeaponsParser(LexicalizedParser globalParser) throws IOException
   {
        weapons = new HashSet<String>();
        
        InputStream in =
            this.getClass().getResourceAsStream(globalPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str;
        while ((str = br.readLine()) != null) 
        {
            str = str.toLowerCase();
            str.replaceAll("\\s+","");
            weapons.add(str);
        }
   }
   
   public List<String> getAllWeapons(DependencyTree dependencyTree)
   {
       List<String> listOfWeapons = new ArrayList<>();
       
       List<CollectionOfWords> collections = dependencyTree.getCollectionsFromWords(new ArrayList(weapons));
       
       collections.forEach((collection) -> {
           listOfWeapons.add(collection.getCollectionAsString());
       });
       
       return listOfWeapons;
   }
    
}
