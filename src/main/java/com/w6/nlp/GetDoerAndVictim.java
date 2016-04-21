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
    //tlp - не понятно, gsf - не понятно
    static final TreebankLanguagePack treeLanguagePack = new PennTreebankLanguagePack();
    static final GrammaticalStructureFactory factoryForGramaticalStructure = treeLanguagePack.grammaticalStructureFactory();


    //void - тоже не понятно, result тоже не понятно, list, violentList, getResult
    
    private static ObjectsAndSubjects getResultWithViolentVerbs(
            Collection<TypedDependency> list, 
            List<String> violentList
    ){
        ObjectsAndSubjects objectsAndSubjects = new ObjectsAndSubjects();
        List<Pair<String, Integer>> listOfDoers = new ArrayList<>();
        List<Pair<String, Integer>> listOfVictims = new ArrayList<>();
        
        HashMap<Pair<String, Integer>, Node> mapOfNodes = new HashMap<>();
        
        for (TypedDependency dependency:list)
        {
            String tag = dependency.reln().toString();
            
            //main, dep Struct - не понятно, как связано с dep
            Pair<String, Integer> mainStruct = new Pair(dependency.gov().value(), dependency.gov().index());
            Pair<String, Integer> depStruct = new Pair(dependency.dep().value(), dependency.dep().index());
            
            
            addWordToMap(mainStruct, mapOfNodes);
            addWordToMap(depStruct, mapOfNodes);
            
            Node firstWord = mapOfNodes.get(mainStruct);
            firstWord.addEdge(tag, mapOfNodes.get(depStruct));
            
            if( violentList.contains(mainStruct.first))
            {   
                if(tag.equals("nsubj") || tag.equals("nmod:agent"))
                {
                    listOfDoers.add(depStruct);
                }

                if(tag.equals("dobj") || tag.equals("nsubjpass"))
                {
                    listOfVictims.add(depStruct);
                }
            }
        }
        
        //listOfObjects, Objects, функция для одного объекта
        objectsAndSubjects.objects = getComplexEntity(listOfDoers, mapOfNodes);
        objectsAndSubjects.subjects = getComplexEntity(listOfVictims, mapOfNodes);
        
        return objectsAndSubjects;
        
    }
    
    private static void addWordToMap(Pair<String, Integer> word, HashMap<Pair<String, Integer>, Node> mapOfNodes)
    {
        if(!mapOfNodes.containsKey(word))
        {
            mapOfNodes.put(word, new Node(word, new ArrayList<>()));
        }
    }
    
    private static ArrayList<String> getComplexEntity(List<Pair<String, Integer>> listOfObjects, HashMap<Pair<String, Integer>, Node> mapOfNodes)
    {
        ArrayList<String> result = new ArrayList();
         
        for (Pair<String, Integer> word : listOfObjects)
        {
            List<Pair<String, Integer>> childs = getAllChilds(word,mapOfNodes);
            Collections.sort(childs, new ComparatorOfWords());
            String newObject = fromListToOneWord(childs);
            result.add(newObject);
        }
        
        return result;
    }
    
    //serialise
    private static String fromListToOneWord(List<Pair<String, Integer>> words)
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
    
    
    //lhs,rhs
    private static class ComparatorOfWords implements Comparator<Pair<String, Integer>>
    {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o1.second.compareTo(o2.second);
        }    
    }

    //gs, td
    public static ObjectsAndSubjects getSubjectAndObjectOfViolence(
            Tree tree, 
            List<String> violentVerbs
    ) {
        //mock gramatical structure, передавать in constructor
        GrammaticalStructure sentence = factoryForGramaticalStructure.newGrammaticalStructure(tree);
        Collection<TypedDependency> listOfDependencies = sentence.typedDependenciesCollapsed();
            
        
        return getResultWithViolentVerbs(listOfDependencies, violentVerbs);
    }
}
