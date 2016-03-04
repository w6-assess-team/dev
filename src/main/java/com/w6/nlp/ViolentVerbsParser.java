package com.w6.nlp;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
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
   static final String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
   
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
   
   private  static List<CoreLabel> tokenize(String str) {
        Tokenizer<CoreLabel> tokenizer =
                tokenizerFactory.getTokenizer(
                        new StringReader(str));
        return tokenizer.tokenize();
    }
   
   public static List<String> getAllViolentVerbs(String text){
        try {
          setViolentDictionary();
        } catch (Exception e) {}
        
        List<String> result = new ArrayList<>();
    

        TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "invertible=true");
        LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);
        List<CoreLabel> tokens = tokenize(str);
    
        return result;
       
   }
           
}
