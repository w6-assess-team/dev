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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.util.*;

public class ViolentVerbsParser {
    
   Set violentWords;
   String globalPpath = "/violentVerbsDictionary.txt";
   TokenizerFactory<CoreLabel> tokenizerFactory;
   LexicalizedParser parser;
   
   
   public ViolentVerbsParser(LexicalizedParser globalParser)
   {
       tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
                        "invertible=true");
       parser = globalParser;
       try {
           setViolentDictionary();
       } catch (IOException e) {
           //Can't load file of dictionary
       }
   }
   
   private void setViolentDictionary() throws IOException
   {
       
        violentWords = new HashSet<String>();
        
        try{
            InputStream in =
                this.getClass().getResourceAsStream(globalPpath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            while ((str = br.readLine()) != null) {
                str = str.toLowerCase();
                str.replaceAll("\\s+","");
                violentWords.add(str);
            }
        } catch (IOException e){
            throw e;
        }
   }
   
   private List<CoreLabel> tokenize(String str, 
            TokenizerFactory<CoreLabel> tokenizerFactory)
    {
       
        Tokenizer<CoreLabel> tokenizer =
                tokenizerFactory.getTokenizer(new StringReader(str));
        
        return tokenizer.tokenize();
    }
   
   public List<String> getAllViolentVerbs(String text)
   {    
        List<String> result = new ArrayList<String>();
        
        List<CoreLabel> tokens = tokenize(text, tokenizerFactory);
        Tree tree = parser.apply(tokens);
        List<Tree> leaves = tree.getLeaves();
        
        for(Tree leave : leaves){
            Tree parent = leave.parent(tree);
            if(parent != null){
                if(parent.label().value().contains("VB")){
                    WordTag tag = Morphology.stemStatic(leave.label().value()
                        ,parent.label().value());
                    if(violentWords.contains(tag.value())){
                        result.add(leave.label().value());
                    }
                }
            }
        }
    
        return result;      
   }
           
}
