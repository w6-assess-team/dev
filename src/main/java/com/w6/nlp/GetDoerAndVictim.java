package com.w6.nlp;

import edu.stanford.nlp.trees.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class GetDoerAndVictim 
{    
    private Collection<TypedDependency> listOfDependencies;
    private DependencyTree dependencyTree;
    

    public GetDoerAndVictim(Collection<TypedDependency> listOfDependencies, DependencyTree dependencyTree)
    {
        this.listOfDependencies = listOfDependencies;
        this.dependencyTree = dependencyTree;
    }
    
    public List<String> getSubjectsOfViolence(List<String> violenceVerbs)
    {
        List<String> subjectsOfViolence = new ArrayList<>();
        subjectsOfViolence.addAll(dependencyTree.getSubTreeFromWordsByTag(violenceVerbs, "dobj"));
        subjectsOfViolence.addAll(dependencyTree.getSubTreeFromWordsByTag(violenceVerbs, "nsubjpass"));
        return subjectsOfViolence;
    }
    
    public List<String> getObjectsOfViolence(List<String> violenceVerbs)
    {
        List<String> subjectsOfViolence = new ArrayList<>();
        subjectsOfViolence.addAll(dependencyTree.getSubTreeFromWordsByTag(violenceVerbs, "nsubj"));
        subjectsOfViolence.addAll(dependencyTree.getSubTreeFromWordsByTag(violenceVerbs, "nmod:agent"));
        return subjectsOfViolence;
    }
   
}
