package com.w6.nlp;

import com.w6.data.Table;
import com.w6.data.Response;
import com.w6.data.Word;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.Pair;
import java.io.IOException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {    
    static LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
    static ViolentVerbsParser violentVerbsParser;
    static WeaponsParser weaponsParser;
    
    public Parser() 
    {
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
        
       
        TokenizerFactory<CoreLabel> tokenizerFactory =
                PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
        Tokenizer<CoreLabel> tok =
                tokenizerFactory.getTokenizer(new StringReader(input));
        List<CoreLabel> rawWords2 = tok.tokenize();
        Tree parse = lp.apply(rawWords2);

        where = LocationParser.parseLocationFromString(input);

        when = DateTimeParser.parseDateAndTimeFromString(input);

        what = violentVerbsParser.getAllViolentVerbs(input);
        
        weapon = weaponsParser.getAllWeapons(input);

        Pair<ArrayList<String>, ArrayList<String>> objAndSubj = GetDoerAndVictim.getSubjectAndObjectOfViolence(input,what);

        who.addAll(objAndSubj.first);
        whom.addAll(objAndSubj.second);
        
        when = DateTimeParser.parseDateAndTimeFromString(input);
        where = LocationParser.parseLocationFromString(input);

        for (Tree leaf : parse.getLeaves()) {
            Tree parent = leaf.parent(parse);
            String label = "";
            String word = leaf.label().value();
            if (who.contains(word))
            {
                label = "who";
            } else 
            if (what.contains(word))
            {
                label = "what";
            } else 
            if (where.contains(word))
            {
                label = "where";
            } else 
            if (whom.contains(word))
            {
                label = "whom";
            } else 
            if (when.contains(word))
            {
                label = "when";
            } else
            if (weapon.contains(word))
            {
                label = "weapon";
            }
            text.add(new Word(word, label));
        }
        
        
        return new Response(text, new Table(who, weapon, what, whom, where, when));
    }
}