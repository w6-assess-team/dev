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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WeaponsParser {
   Set weapons;
   String globalPath = "/weapons.txt";
   TokenizerFactory<CoreLabel> tokenizerFactory;
   LexicalizedParser parser;
   
   public WeaponsParser(LexicalizedParser globalParser) throws IOException
   {
       tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
                        "invertible=true");
       parser = globalParser;
       try {
           setWeaponsDictionary();
       } catch (IOException e) {
           throw e;
       }
   }
   
   private void setWeaponsDictionary() throws IOException
   {
       
        weapons = new HashSet<String>();
        
        try{
            InputStream in =
                this.getClass().getResourceAsStream(globalPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            while ((str = br.readLine()) != null) {
                str = str.toLowerCase();
                str.replaceAll("\\s+","");
                weapons.add(str);
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
   
   public List<String> getAllWeapons(String text)
   {    
        List<String> result = new ArrayList<String>();
        
        List<CoreLabel> tokens = tokenize(text, tokenizerFactory);
        Tree tree = parser.apply(tokens);
        List<Tree> leaves = tree.getLeaves();
        
        for(Tree leave : leaves){
            Tree parent = leave.parent(tree);
            if(parent != null){
                WordTag tag = Morphology.stemStatic(leave.label().value()
                    ,parent.label().value());
                if(weapons.contains(tag.value())){
                    result.add(leave.label().value());
                }
            }
        }
    
        return result;      
   }
    
}
