package com.w6.nlp;

import com.w6.data.Node;
import com.w6.data.ObjectsAndSubjects;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
        List<Pair<String, Integer>> listOfSubjects = new ArrayList<>();
        List<Pair<String, Integer>> listOfObjects = new ArrayList<>();
        
        for (TypedDependency obj:list)
        {
            if( violentList.contains(obj.gov().value()))
            {
                String tag = obj.reln().toString();
                
                if(tag.equals("nsubj") || tag.equals("nmod:agent"))
                {
                    listOfSubjects.add(new Pair<>(obj.dep().value(),obj.dep().index()));
                }

                if(tag.equals("dobj") || tag.equals("nsubjpass"))
                {
                    listOfObjects.add(new Pair<>(obj.dep().value(),obj.dep().index()));
                }
            }
        }
        
        HashMap<Pair<String, Integer>, Node> mapOfNodes = new HashMap<>();
        
        for (TypedDependency obj:list)
        {
            String wordGov, wordDep, tag;
            Integer posGov, posDep;
            
            wordGov = obj.gov().value();
            posGov = obj.gov().index();
            
            wordDep = obj.dep().value();
            posDep = obj.dep().index();
            
            Pair<String, Integer> govStruct = new Pair(wordGov,posGov);
            Pair<String, Integer> depStruct = new Pair(wordDep,posDep);
            
            tag = obj.reln().toString();
            
            
            if(!mapOfNodes.containsKey(govStruct))
            {
                mapOfNodes.put(govStruct, new Node(govStruct, new ArrayList<>()));
            }
            
            if(!mapOfNodes.containsKey(depStruct))
            {
                mapOfNodes.put(depStruct, new Node(depStruct, new ArrayList<>()));
            }
            
            Node firstWord = mapOfNodes.get(govStruct);
            firstWord.addEdge(tag, mapOfNodes.get(depStruct));
        }
        
        ArrayList<String> newObjectList = new ArrayList<>();
        ArrayList<String> newSubjectList = new ArrayList<>();
        
        for (Pair<String, Integer> word : listOfSubjects)
        {
            List<Pair<String, Integer>> childs = getAllChilds(word,mapOfNodes);
            Collections.sort(childs, new ComparatorOfWords());
            String newSubject = getNewWord(childs);
            newSubjectList.add(newSubject);
        }
        
        for (Pair<String, Integer> word : listOfObjects)
        {
            List<Pair<String, Integer>> childs = getAllChilds(word,mapOfNodes);
            Collections.sort(childs, new ComparatorOfWords());
            String newObject = getNewWord(childs);
            newObjectList.add(newObject);
        }
        
        result.objects = newObjectList;
        result.subjects = newSubjectList;
        
    }
    
    private static String getNewWord(List<Pair<String, Integer>> words)
    {
        StringBuilder result = new StringBuilder();
        
        for (Pair<String, Integer> word : words)
        {
            result.append(word.first + " ");
        }
        
        return result.toString();
    }
    
    private static List<Pair<String, Integer>> getAllChilds(Pair<String, Integer> word, HashMap<Pair<String, Integer>,Node> mapOfNodes)
    {
        ArrayList<Pair<String, Integer>> result = new ArrayList<>();
        Node nodeOfWord = mapOfNodes.get(word);
        result.add(word);
        
        for(Pair<String, Node> node : nodeOfWord.getAllEdges())
        {
            List<Pair<String, Integer>> listOfChilds = getAllChilds(node.second.getWord(), mapOfNodes);
            result.addAll(listOfChilds);
        }
        
        return result;
    }
    
    private static class ComparatorOfWords implements Comparator<Pair<String, Integer>>
    {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o1.second.compareTo(o2.second);
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
