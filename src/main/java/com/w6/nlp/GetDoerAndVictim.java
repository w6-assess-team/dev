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
        
        for (TypedDependency obj:list)
        {
            if( violentList.contains(obj.gov().value()))
            {
                String tag = obj.reln().toString();
                
                if(tag.equals("nsubj") || tag.equals("nmod:agent"))
                {
                    result.subjects.add(obj.dep().value() + '\t'+ obj.dep().index());
                }

                if(tag.equals("dobj") || tag.equals("nsubjpass"))
                {
                    result.objects.add(obj.dep().value() + '\t'+ obj.dep().index());
                }
            }
        }
        
        HashMap<String,Node> mapOfNodes = new HashMap<>();
        
        for (TypedDependency obj:list)
        {
            String word1, word2, tag, wordAndPos1,wordAndPos2;
            Integer pos1,pos2;
            
            word1 = obj.gov().value();
            pos1 = obj.gov().index();
            wordAndPos1 = word1+'\t' + pos1;
            
            word2 = obj.dep().value();
            pos2 = obj.dep().index();
            wordAndPos2 = word2+'\t' + pos2;
            
            tag = obj.reln().toString();
            
            
            if(!mapOfNodes.containsKey(wordAndPos1))
            {
                mapOfNodes.put(wordAndPos1, new Node(wordAndPos1, new ArrayList<>()));
            }
            
            if(!mapOfNodes.containsKey(wordAndPos2))
            {
                mapOfNodes.put(wordAndPos2, new Node(wordAndPos2, new ArrayList<>()));
            }
            
            Node firstWord = mapOfNodes.get(wordAndPos1);
            firstWord.addEdge(tag, wordAndPos2);
        }
        
        ArrayList<String> newObjectList = new ArrayList<>();
        ArrayList<String> newSubjectList = new ArrayList<>();
        
        for (String string : result.subjects)
        {
            List<String> childs = getAllChilds(string,mapOfNodes);
            Collections.sort(childs, new MyStringComparator());
            String newSubject = getNewWord(childs);
            newSubjectList.add(newSubject);
        }
        
        for (String string : result.objects)
        {
            List<String> childs = getAllChilds(string,mapOfNodes);
            Collections.sort(childs, new MyStringComparator());
            String newObject = getNewWord(childs);
            newObjectList.add(newObject);
        }
        
        result.objects = newObjectList;
        result.subjects = newSubjectList;
        
    }
    
    private static String getNewWord(List<String> words)
    {
        StringBuilder result = new StringBuilder();
        
        for (String word : words)
        {
            String[] partOfword =  word.split("\t");
            result.append(partOfword[0] + " ");
        }
        
        return result.toString();
    }
    
    private static List<String> getAllChilds(String word, HashMap<String,Node> mapOfNodes)
    {
        ArrayList<String> result = new ArrayList<>();
        Node nodeOfWord = mapOfNodes.get(word);
        result.add(word);
        
        for(Pair<String,String> node : nodeOfWord.getAllEdges())
        {
            List<String> tmp = getAllChilds(node.second, mapOfNodes);
            result.addAll(tmp);
        }
        
        return result;
    }
    
    static class MyStringComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            Integer pos1 = Integer.parseInt(o1.split("\t")[1]);
            Integer pos2 = Integer.parseInt(o2.split("\t")[1]);
            return pos1.compareTo(pos2);
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
