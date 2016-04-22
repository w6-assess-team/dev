package com.w6.nlp;

import com.w6.data.Node;

import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class DependencyTree {
    private HashMap<Pair<String, Integer>, Node> mapOfNodes;
    private Collection<TypedDependency> dependencies;
    
    public DependencyTree(Collection<TypedDependency> dependencies)
    {
        this.dependencies = dependencies;
        this.mapOfNodes = new HashMap<>();
        
        for (TypedDependency dependency:dependencies)
        {
            String tag = dependency.reln().toString();
            Pair<String, Integer> mainStruct = new Pair(dependency.gov().value(), dependency.gov().index());
            Pair<String, Integer> depStruct = new Pair(dependency.dep().value(), dependency.dep().index());
            
            addWordToMap(mainStruct, mapOfNodes);
            addWordToMap(depStruct, mapOfNodes);
            
            Node firstWord = mapOfNodes.get(mainStruct);
            firstWord.addEdge(tag, mapOfNodes.get(depStruct));
        }  
        
    }
    
    public List<String> getSubTreeFromWordsByTag(List<String> keyWords, String tag)
    {
        List<Pair<String, Integer>> dependentWordList = new ArrayList<>();
        for(TypedDependency dependency : dependencies)
        {
            String localTag = dependency.reln().toString();
            Pair<String, Integer> mainStruct = new Pair(dependency.gov().value(), dependency.gov().index());
            Pair<String, Integer> depStruct = new Pair(dependency.dep().value(), dependency.dep().index());
            
            if(keyWords.contains(mainStruct.first))
            {
                if(localTag.equals(tag))
                {
                    dependentWordList.add(depStruct);
                }
            }
        }
        
        return getComplexEntity(dependentWordList);
    }
    
    private  ArrayList<String> getComplexEntity(List<Pair<String, Integer>> listOfObjects)
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
    private  String fromListToOneWord(List<Pair<String, Integer>> words)
    {
        StringBuilder result = new StringBuilder();
        
        for (Pair<String, Integer> word : words)
        {
            result.append(word.first + " ");
        }
        
        return result.toString();
    }
    
    private  List<Pair<String, Integer>> getAllChilds(Pair<String, Integer> word, HashMap<Pair<String, Integer>,Node> mapOfNodes)
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
    private  class ComparatorOfWords implements Comparator<Pair<String, Integer>>
    {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o1.second.compareTo(o2.second);
        }    
    }
    
    private void addWordToMap(Pair<String, Integer> word, HashMap<Pair<String, Integer>, Node> mapOfNodes)
    {
        if(!mapOfNodes.containsKey(word))
        {
            mapOfNodes.put(word, new Node(word, new ArrayList<>()));
        }
    }
    
}
