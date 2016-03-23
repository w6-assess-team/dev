package com.w6.nlp;

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
        
        ArrayList<String> tmpWho = new ArrayList<>(who);
        ArrayList<String> tmpWhat = new ArrayList<>(what);
        ArrayList<String> tmpWhere = new ArrayList<>(where);
        ArrayList<String> tmpWhen = new ArrayList<>(when);
        ArrayList<String> tmpWhom = new ArrayList<>(whom);
        ArrayList<String> tmpWeapon = new ArrayList<>(weapon);
        
        Arrays.sort(tmpWho.toArray());
        Arrays.sort(tmpWhat.toArray());
        Arrays.sort(tmpWhere.toArray());
        Arrays.sort(tmpWhen.toArray());
        Arrays.sort(tmpWhom.toArray());
        Arrays.sort(tmpWeapon.toArray());
        
        who = new ArrayList<>();
        what = new ArrayList<>();
        where = new ArrayList<>();
        when = new ArrayList<>();
        whom = new ArrayList<>();
        weapon = new ArrayList<>();
        
        for( int i = 0; i<tmpWho.size() ;i++){
            if(i > 0)
            {
                if(!tmpWho.get(i).equals(tmpWho.get(i-1)))
                {
                    who.add(tmpWho.get(i));
                }
            } else 
            {
                who.add(tmpWho.get(i));
            }
        }
        
        for( int i = 0; i<tmpWhat.size() ;i++){
            if(i > 0)
            {
                if(!tmpWhat.get(i).equals(tmpWhat.get(i-1)))
                {
                    what.add(tmpWhat.get(i));
                }
            } else 
            {
                what.add(tmpWhat.get(i));
            }
        }
        
        for( int i = 0; i<tmpWhere.size() ;i++){
            if(i > 0)
            {
                if(!tmpWhere.get(i).equals(tmpWhere.get(i-1)))
                {
                    where.add(tmpWhere.get(i));
                }
            } else 
            {
                where.add(tmpWhere.get(i));
            }
        }
        
        for( int i = 0; i<tmpWhen.size() ;i++){
            if(i > 0)
            {
                if(!tmpWhen.get(i).equals(tmpWhen.get(i-1)))
                {
                    when.add(tmpWhen.get(i));
                }
            } else 
            {
                when.add(tmpWhen.get(i));
            }
        }
        
        for( int i = 0; i<tmpWhom.size() ;i++){
            if(i > 0)
            {
                if(!tmpWhom.get(i).equals(tmpWhom.get(i-1)))
                {
                    whom.add(tmpWhom.get(i));
                }
            } else 
            {
                whom.add(tmpWhom.get(i));
            }
        }
        
        for( int i = 0; i<tmpWeapon.size() ;i++){
            if(i > 0)
            {
                if(!tmpWeapon.get(i).equals(tmpWeapon.get(i-1)))
                {
                    weapon.add(tmpWeapon.get(i));
                }
            } else 
            {
                weapon.add(tmpWeapon.get(i));
            }
        }
        



        
        return new Response(text, new Table(who, weapon, what, whom, where, when));
    }
}