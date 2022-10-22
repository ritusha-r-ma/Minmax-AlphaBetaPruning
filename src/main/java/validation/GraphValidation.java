package validation;

import graph.Graph;
import graph.node.Node;
import utilities.ErrorHandlingUtil;

import java.util.*;

/**
 * This class covers validation of the formed graph via our text file
 */
public class GraphValidation {

    /**
     * If any of the condition fails, data won't be processed
     * @param graph
     */
    public GraphValidation(Graph graph) {
        hasMissingNode(graph);
        calIncomingDegree(graph);
        hasOnlyOneRootNode(graph);
        isCyclicGraphTree(graph);
    }

    /**
     *
     * @param graph
     */
    private void hasMissingNode(Graph graph) {
        for (Map.Entry<Node, List<Node>> node : graph.getAdjVertices().entrySet()) {
            for (Node n : node.getValue()) {
                if (n.getValue() == null && !graph.getAdjVertices().containsKey(n)) {
                    ErrorHandlingUtil.errorOccurred("child node " + "\"" +n.getName() + "\"" + " of " + "\""+ node.getKey().getName() + "\"" + " not found");
                }
            }
        }
    }

    /**
     * get incoming degree of all nodes, to check cyclic graph and number of rootnodes
     * @param graph
     * @return
     */
    private Map<Node, Integer> calIncomingDegree(Graph graph) {
        Map<Node, Integer> incomingDegree = new HashMap<>();
        for (Map.Entry<String, Node> node : graph.getNodeMap().entrySet()) {
            incomingDegree.putIfAbsent(node.getValue(), 0);
            for (Node n : graph.getAdjVertices().get(node.getValue())) {
                if (incomingDegree.containsKey(n)) {
                    incomingDegree.put(n, incomingDegree.get(n) + 1);
                } else {
                    incomingDegree.put(n, 1);
                }
            }
        }

        return incomingDegree;
    }


    private void hasOnlyOneRootNode(Graph graph) {
        List<String> rootNodes = new ArrayList<>();
        Map<Node, Integer> incomingDegree = calIncomingDegree(graph);

        for(Map.Entry<String, Node> node : graph.getNodeMap().entrySet()) {
            if (incomingDegree.get(node.getValue()) == 0) {
                rootNodes.add(node.getKey());
            }
        }

        if (rootNodes.size() > 1) {
            String errorMessage = "";
            for (int i = 0; i< rootNodes.size(); i++) {
                if (i == rootNodes.size() - 1) {
                    errorMessage = errorMessage + " and " + "\"" + rootNodes.get(i) + "\"";
                } else {
                    if (errorMessage.isEmpty()) {
                        errorMessage = errorMessage + "\"" + rootNodes.get(i) + "\"";
                    } else {
                        errorMessage = errorMessage + " , " + "\"" + rootNodes.get(i) + "\"";
                    }
                }
            }

            ErrorHandlingUtil.errorOccurred("multiple roots: " + errorMessage);
        } else if (rootNodes.isEmpty()) {
            ErrorHandlingUtil.errorOccurred("No root exist");
        } else {
            graph.setRootNode(rootNodes.get(0));
        }
    }

    private void isCyclicGraphTree(Graph graph){
        Map<Node, Integer> inDegree = calIncomingDegree(graph);
        Queue<Node> queue = new LinkedList<>();
        int count = 0;
        for (Map.Entry<Node, Integer> node : inDegree.entrySet()) {
            if (node.getValue() == 0){
                queue.add(node.getKey());
                count++;
            }
        }

        while (!queue.isEmpty()){
            Node parent = queue.poll();
            for (Node child: graph.getAdjVertices().get(parent)){
                inDegree.put(child ,inDegree.get(child)-1);
                if (inDegree.get(child) == 0){
                    queue.add(child);
                    count++;
                }
            }
        }

        if (count != inDegree.size()){
            ErrorHandlingUtil.errorOccurred("Cycle found in the graph");
        }
    }
}

