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
import java.io.IOException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    
    static LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
    static ViolentVerbsParser violentVerbsParser;
    
    public Parser() throws IOException{
        violentVerbsParser = new ViolentVerbsParser(lp);
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
        for (Tree leaf : parse.getLeaves()) {
            Tree parent = leaf.parent(parse);
            String label = "";
            if (parent.label().value().startsWith("N"))
            {
                who.add(leaf.label().value());
                label = "who";
            }
            text.add(new Word(leaf.label().value(), label));
        }
        
        where = LocationParser.parseLocationFromString(input);
        
        for(String str:where){
            text.add(new Word(str, "where"));
        }
        
        when = DateTimeParser.parseDateAndTimeFromString(input);
        
        for(String str:when){
            text.add(new Word(str, "when"));
        }
        
        what = violentVerbsParser.getAllViolentVerbs(input);
        
        for(String str:what){
            text.add(new Word(str, "what"));
        }
        
        GetDoerAndVictim.ObjectsAndSubjects objAndSubj = GetDoerAndVictim.getSubjectAndObjectOfViolence(input,what);
        
        for(String str:objAndSubj.subjects){
            text.add(new Word(str, "who"));
        }
        
        for(String str:objAndSubj.objects){
            text.add(new Word(str, "whom"));
        }
        
       

        return new Response(text, new Table(who, weapon, what, whom, where, when));
    }
}