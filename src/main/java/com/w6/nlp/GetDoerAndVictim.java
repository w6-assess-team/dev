package com.w6.nlp;

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



public class GetDoerAndVictim {
    
    static class ObjectsAndSubjects{
        ArrayList<String> subjects;
        ArrayList<String> objects;

        ObjectsAndSubjects(){
            subjects = new ArrayList<String>();
            objects = new ArrayList<String>();
        }
    }

    static final String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";

    private  static List<CoreLabel> tokenize(String str,
                                             TokenizerFactory<CoreLabel> tokenizerFactory) {
        Tokenizer<CoreLabel> tokenizer =
                tokenizerFactory.getTokenizer(
                        new StringReader(str));
        return tokenizer.tokenize();
    }

    private static void getActiveSubjectAndObjectOfViolence(ArrayList<TypedDependency> list, 
            ObjectsAndSubjects result, List<String> violentList){
        for(TypedDependency obj:list){
            if(obj.reln().getShortName().equals("nsubj") && violentList.contains(obj.gov().value()))
                result.subjects.add(obj.dep().value().toLowerCase());

            if(obj.reln().getShortName().equals("dobj") && violentList.contains(obj.gov().value()))
                result.objects.add(obj.dep().value().toLowerCase());
        }
    }

    private static void getPassiveSubjectAndObjectOfViolence(ArrayList<TypedDependency> list,
            ObjectsAndSubjects result, List<String> violentList){
        for(TypedDependency obj:list){
            if(obj.reln().getShortName().equals("nsubjpass") && violentList.contains(obj.gov().value()))
                result.objects.add(obj.dep().value().toLowerCase());

            if(obj.reln().getShortName().equals("nmod") && violentList.contains(obj.gov().value()))
                result.subjects.add(obj.dep().value().toLowerCase());
        }
    }



    public static ObjectsAndSubjects getSubjectAndObjectOfViolence(String text, List<String> violentVerbs) {


        ObjectsAndSubjects result = new ObjectsAndSubjects();
        Document document = new edu.stanford.nlp.simple.Document(text);

        TokenizerFactory<CoreLabel> tokenizerFactory =
                PTBTokenizer.factory(new CoreLabelTokenFactory(),
                        "invertible=true");
        
        LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);

        for(Sentence sentence:document.sentences()) {
            List<CoreLabel> tokens = tokenize(sentence.text(), tokenizerFactory);
            Tree tree = parser.apply(tokens);

            TreebankLanguagePack tlp = new PennTreebankLanguagePack();
            GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
            GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
            Collection<TypedDependency> td = gs.typedDependenciesCollapsed();


            Object[] listObjects = td.toArray();
            List<String> listOfTags = new ArrayList<String>();
            ArrayList<TypedDependency> listOfTypedDependency = new ArrayList<TypedDependency>();
            TypedDependency typedDependency;
            for (Object object : listObjects) {
                typedDependency = (TypedDependency) object;
                
                listOfTags.add(typedDependency.reln().getShortName());
                listOfTypedDependency.add(typedDependency);
            }

            if(listOfTags.contains("nsubj"))
                getActiveSubjectAndObjectOfViolence(listOfTypedDependency,result, violentVerbs);

            if(listOfTags.contains("nsubjpass"))
                getPassiveSubjectAndObjectOfViolence(listOfTypedDependency,result,violentVerbs);

        }


        return result;

    }
}
