package com.w6.data;

import edu.stanford.nlp.util.Pair;
import java.util.List;

public class Node {
    String name;
    List<Pair<String,String>> edges;
    
    public Node(String nameString, List<Pair<String,String>> ed)
    {
        this.edges = ed;
        this.name = nameString;
    }
    
    public void addEdge( String tag, String toWhatWord)
    {
        edges.add(new Pair<>(tag, toWhatWord));
    }
    
    public List<Pair<String,String>> getAllEdges()
    {
        return edges;
    }
}
