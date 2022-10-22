package graph;

import graph.node.Node;
import utilities.ErrorHandlingUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for making graph out of input file
 */
public class Graph {
    private String rootNode;
    private final Map<Node, List<Node>> adjVertices = new HashMap<>();
    private final Map<String, Node> nodeMap = new HashMap<>();

    public Map<Node, List<Node>> getAdjVertices() {
        return adjVertices;
    }

    public Map<String, Node> getNodeMap() {
        return nodeMap;
    }

    public String getRootNode() {
        return rootNode;
    }

    public void setRootNode(String rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * Add non leaf node and neighbour define its children hence, edge between nodes
     * @param label
     * @param neighbours
     */
    public void addNonLeafVertex(String label, String[] neighbours) {
        List<Node> neighbour = new ArrayList<>();
        Node node = nodeMap.containsKey(label) ? nodeMap.get(label) : new Node(label);

        addNodeMapMapping(node, neighbours);
        for(String k : neighbours) {
            neighbour.add(nodeMap.get(k));
        }
        adjVertices.putIfAbsent(node, neighbour);
    }

    public void addLeafVertex(String label, Float value) {
        if (!nodeMap.containsKey(label)) {
            ErrorHandlingUtil.errorOccurred("No root node defined for " + label + " node");
        }

        Node node = nodeMap.get(label);
        node.setValue(value);
        adjVertices.putIfAbsent(node, new ArrayList<>());
    }

    /**
     * To keep count of every node
     * @param parentNode
     * @param groupOfNode
     */
    private void addNodeMapMapping(Node parentNode, String[] groupOfNode) {
        nodeMap.put(parentNode.getName(), parentNode);
        for(String n : groupOfNode) {
            if (!nodeMap.containsKey(n)) {
                nodeMap.put(n, new Node(n));
            }
        }
    }
}
