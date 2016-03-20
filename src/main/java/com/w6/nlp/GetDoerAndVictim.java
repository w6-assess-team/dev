package com.w6.nlp;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.simple.*;
import edu.stanford.nlp.util.Pair;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class GetDoerAndVictim 
{    

    static final String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";

    private  static List<CoreLabel> tokenize(String str,
                                             TokenizerFactory<CoreLabel> tokenizerFactory) 
    {
        Tokenizer<CoreLabel> tokenizer =
                tokenizerFactory.getTokenizer(
                        new StringReader(str));
        return tokenizer.tokenize();
    }

    private static void getResultWithViolentVerbs(Collection<TypedDependency> list, 
            Pair<ArrayList<String>, ArrayList<String>> result, List<String> violentList){
        for(TypedDependency obj:list)
        {
            if( violentList.contains(obj.gov().value()))
            {
                String tag = obj.reln().toString();
                
                if(tag.equals("nsubj") || tag.equals("nmod:agent"))
                {
                    result.first.add(obj.dep().value());
                }

                if(tag.equals("dobj") || tag.equals("nsubjpass"))
                {
                    result.second.add(obj.dep().value());
                }
            }
        }
    }



    public static Pair<ArrayList<String>, ArrayList<String>> getSubjectAndObjectOfViolence(String text, List<String> violentVerbs) {


        Pair<ArrayList<String>, ArrayList<String>> result = new Pair<>();
        
        result.first = new ArrayList<>();
        result.second = new ArrayList<>();
        
        Document document = new edu.stanford.nlp.simple.Document(text);

        TokenizerFactory<CoreLabel> tokenizerFactory =
                PTBTokenizer.factory(new CoreLabelTokenFactory(),
                        "invertible=true");
        
        LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);

        for(Sentence sentence:document.sentences()) 
        {
            List<CoreLabel> tokens = tokenize(sentence.text(), tokenizerFactory);
            Tree tree = parser.apply(tokens);

            TreebankLanguagePack tlp = new PennTreebankLanguagePack();
            GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
            GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
            Collection<TypedDependency> td = gs.typedDependenciesCollapsed();
            
            getResultWithViolentVerbs(td,result,violentVerbs);

        }


        return result;

    }
}
