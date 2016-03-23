package com.w6.nlp;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.w6.data.ObjectsAndSubjects;
import com.w6.data.Table;
import com.w6.data.Response;
import com.w6.data.Word;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.Pair;
import java.io.IOException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {    
    static LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
    static ViolentVerbsParser violentVerbsParser;
    static WeaponsParser weaponsParser;
    private TokenizerFactory<CoreLabel> tokenizerFactory;
    
    public Parser() throws IOException{
        tokenizerFactory =
                PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
        violentVerbsParser = new ViolentVerbsParser(lp);
        weaponsParser = new WeaponsParser(lp);
    }
    
    public Response generateResponse(final String input) {

        List<String> who = new ArrayList<String>();
        List<String> weapon = new ArrayList<String>();
        List<String> whom = new ArrayList<String>();
        List<String> where = new ArrayList<String>();
        List<String> when = new ArrayList<String>();
        List<String> what = new ArrayList<String>();
        List<Word> text = new ArrayList<Word>();
        
        List<Pair<String,Integer>> ratedWhen = new ArrayList<>();
        List<Pair<String,Integer>> ratedWhere = new ArrayList<>();
        
        ArrayList<String> dateTimeTags = new ArrayList<>();
        dateTimeTags.add("DATE");
        dateTimeTags.add("TIME");
        ArrayList<String> locationTags = new ArrayList<>();
        locationTags.add("LOCATION");
       
        
        Document document = new Document(input);
        for (Sentence sentence : document.sentences())
        {
            List<String> sentenseWho = new ArrayList<String>();
            List<String> sentenseWeapon = new ArrayList<String>();
            List<String> sentenseWhom = new ArrayList<String>();
            List<String> sentenseWhere = new ArrayList<String>();
            List<String> sentenseWhen = new ArrayList<String>();
            List<String> sentenseWhat = new ArrayList<String>();
            
            
            
            
            Tree parse = lp.apply(
                    tokenizerFactory.getTokenizer(new StringReader(sentence.text()))
                        .tokenize()
            );
            
            sentenseWhat = violentVerbsParser.getAllViolentVerbs(parse);
            
            sentenseWhen = DateTimeParser.parseDateAndTimeFromString(
                sentence, 
                dateTimeTags
            );
            
            sentenseWhere = DateTimeParser.parseDateAndTimeFromString(
                sentence, 
                locationTags
            );
            
            sentenseWeapon = weaponsParser.getAllWeapons(parse);
            
            if (!sentenseWhat.isEmpty())
            {
                ObjectsAndSubjects objAndSubj = GetDoerAndVictim.getSubjectAndObjectOfViolence(parse,sentenseWhat);
                sentenseWho.addAll(objAndSubj.subjects);
                sentenseWhom.addAll(objAndSubj.objects);
            }
            
            for (Tree leaf : parse.getLeaves()) {
                Tree parent = leaf.parent(parse);
                String label = "";
                String word = leaf.label().value();
                if (sentenseWho.contains(word))
                {
                    label = "who";
                } else 
                if (sentenseWhat.contains(word))
                {
                    label = "what";
                } else 
                if (sentenseWhere.contains(word))
                {
                    label = "where";
                } else 
                if (sentenseWhom.contains(word))
                {
                    label = "whom";
                } else 
                if (sentenseWhen.contains(word))
                {
                    label = "when";
                } else
                if (sentenseWeapon.contains(word))
                {
                    label = "weapon";
                }
                text.add(new Word(word, label));
            }
            
            
            
            who.addAll(sentenseWho);
            what.addAll(sentenseWhat);
            where.addAll(sentenseWhere);
            whom.addAll(sentenseWhom);
            when.addAll(sentenseWhen);
            weapon.addAll(sentenseWeapon);
        }
        
        

        removeEquals(who);
        removeEquals(what);
        removeEquals(whom);
        removeEquals(weapon);
        
        
        removeAndCountRatedEquals(ratedWhen);
        removeAndCountRatedEquals(ratedWhere);


        
        return new Response(text, new Table(who, weapon, what, whom, where, when));
    }
    
    private void removeEquals(
            List<String> list
    ) {
        
        ArrayList<String> tmp = new ArrayList<>(list);
        
        Arrays.sort(tmp.toArray());
        
        list = new ArrayList<>();
        
        
        for( int i = 0; i<tmp.size() ;i++){
            String nowString = tmp.get(i);
            
            if(i > 0)
            {
                String prevString = tmp.get(i-1);
                if(!nowString.equals(prevString))
                {
                    list.add(nowString);
                }
            } else 
            {
                list.add(nowString);
            }
        }
        
    }
    
    private void removeAndCountRatedEquals(List<Pair<String,Integer>> list)
    {
        
    }
}