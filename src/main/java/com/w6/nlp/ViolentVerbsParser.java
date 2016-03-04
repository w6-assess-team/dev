package com.w6.nlp;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.WordTag;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.Morphology;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.*;

public class ViolentVerbsParser {
    
   static Set violentWords;
   static String globalPpath = "./src/main/resources/violentVerbsDictionary.txt";
   static final String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
   
   private static void setViolentDictionary() throws IOException{
        violentWords = new HashSet<String>();
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(globalPpath));
            String str;
            while ((str = br.readLine()) != null) {
                violentWords.add(str);
            }
        } catch (IOException e){}
   }
   
   private  static List<CoreLabel> tokenize(String str, 
           TokenizerFactory<CoreLabel> tokenizerFactory) {
        Tokenizer<CoreLabel> tokenizer =
                tokenizerFactory.getTokenizer(
                        new StringReader(str));
        return tokenizer.tokenize();
    }
   
   public static List<String> getAllViolentVerbs(String text){
        try {
            if(violentWords == null)
               setViolentDictionary();
        } catch (Exception e) {}
        
        List<String> result = new ArrayList<String>();

        TokenizerFactory<CoreLabel> tokenizerFactory = 
                PTBTokenizer.factory(new CoreLabelTokenFactory(),
                        "invertible=true");
        LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);
        List<CoreLabel> tokens = tokenize(text, tokenizerFactory);
        Tree tree = parser.apply(tokens);
        List<Tree> leaves = tree.getLeaves();
        
        for(Tree leave : leaves){
            Tree parent = leave.parent(tree);
            WordTag tag = Morphology.stemStatic(leave.label().value()
                    ,parent.label().value());
            if(parent.label().value().contains("VB")){
                if(violentWords.contains(tag.value())){
                    result.add(leave.label().value());
                }
            }
        }
    
        return result;
       
   }
           
}
