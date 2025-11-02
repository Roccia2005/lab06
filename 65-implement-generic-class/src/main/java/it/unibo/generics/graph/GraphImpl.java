package it.unibo.generics.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> nodeGraph = new HashMap<>();

    @Override
    public void addNode(N node) {
        if (nodeGraph.get(node) == null && node != null){ 
            final Set<N> linkedNode = new HashSet<>();
            nodeGraph.put(node, linkedNode);
        }
    }

    @Override
    public void addEdge(N source, N target) {
        if (nodeGraph.containsKey(source) && nodeGraph.containsKey(target)
        && source != null && target != null){
            nodeGraph.get(source).add(target);
        }
    }

    @Override
    public Set<N> nodeSet() {
        return nodeGraph.keySet();
    }

    @Override
    public Set<N> linkedNodes(N node) {
        if (!this.nodeGraph.containsKey(node)){
            return Collections.emptySet();
        }
        return new HashSet<>(this.nodeGraph.get(node));
    }

    @Override
    public List<N> getPath(N source, N target) {
        if (this.nodeGraph.containsKey(source) 
                && this.nodeGraph.containsKey(target)){
            if (source.equals(target)){
                return Collections.emptyList();
            }
            final Queue<N> nodeToVisit = new LinkedList<>();
            nodeToVisit.add(source);
            final Map<N, N> solutionPath = new HashMap<>();
            solutionPath.put(source, null);
            final Set<N> visitedNode = new HashSet<>();
            visitedNode.add(source);
            while (!nodeToVisit.isEmpty()){
                final N currentNode = nodeToVisit.remove();
                if (currentNode.equals(target)){
                    final List<N> path = new LinkedList<>();
                    N currentStep = target;
                    while (currentStep != null) {
                        path.add(0, currentStep); 
                        currentStep = solutionPath.get(currentStep); 
                    }
                    return path;
                }
                for (N n : this.nodeGraph.get(currentNode)) {
                    if (!visitedNode.contains(n)){
                        nodeToVisit.add(n);
                        visitedNode.add(n);
                        solutionPath.put(n, currentNode);
                    }
                }
            }
        }
        return Collections.emptyList();
    }
    
}
