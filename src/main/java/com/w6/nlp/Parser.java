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
            sentenseWhen = DateTimeParser.parseDateAndTimeFromString(
                sentence, 
                dateTimeTags
            );
            sentenseWhere = DateTimeParser.parseDateAndTimeFromString(
                sentence, 
                locationTags
            );
            sentenseWhat = violentVerbsParser.getAllViolentVerbs(parse);
        
            sentenseWeapon = weaponsParser.getAllWeapons(parse);
            ObjectsAndSubjects objAndSubj = GetDoerAndVictim.getSubjectAndObjectOfViolence(parse,sentenseWhat);

            sentenseWho.addAll(objAndSubj.subjects);
            sentenseWhom.addAll(objAndSubj.objects);
            
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



        
        return new Response(text, new Table(who, weapon, what, whom, where, when));
    }
}