package com.w6.nlp;

import edu.stanford.nlp.trees.TypedDependency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class LocationParser {
    
    private Collection<TypedDependency> listOfDependencies;
    private DependencyTree dependencyTree;
    
    public LocationParser(Collection<TypedDependency> listOfDependencies, DependencyTree dependencyTree)
    {
        this.listOfDependencies = listOfDependencies;
        this.dependencyTree = dependencyTree;
    }
    public  List<String> getLoationOfViolence(List<String> violenceVerbs) {
        List<String> listOfLocations = new ArrayList<>();
        listOfLocations.addAll(dependencyTree.getSubTreeFromWordsByTag(violenceVerbs, "nmod:in"));
        return listOfLocations;
    }
}