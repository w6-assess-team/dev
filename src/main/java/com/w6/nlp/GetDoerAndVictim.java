package com.w6.nlp;

import com.w6.data.ObjectsAndSubjects;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.simple.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class GetDoerAndVictim 
{    
    static final TreebankLanguagePack tlp = new PennTreebankLanguagePack();
    static final GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();


    private static void getResultWithViolentVerbs(
            Collection<TypedDependency> list, 
            ObjectsAndSubjects result, 
            List<String> violentList
    ){
        for(TypedDependency obj:list)
        {
            if( violentList.contains(obj.gov().value()))
            {
                String tag = obj.reln().toString();
                
                if(tag.equals("nsubj") || tag.equals("nmod:agent"))
                {
                    result.subjects.add(obj.dep().value());
                }

                if(tag.equals("dobj") || tag.equals("nsubjpass"))
                {
                    result.objects.add(obj.dep().value());
                }
            }
        }
    }



    public static ObjectsAndSubjects getSubjectAndObjectOfViolence(
            Tree tree, 
            List<String> violentVerbs
    ) {


        ObjectsAndSubjects result = new ObjectsAndSubjects();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
        Collection<TypedDependency> td = gs.typedDependenciesCollapsed();
            
        getResultWithViolentVerbs(td, result, violentVerbs);
        return result;

    }
}
