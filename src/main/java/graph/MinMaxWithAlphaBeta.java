package graph;

import graph.node.Node;
import validation.ArgValidation;

import java.util.List;

/**
 * This class applies MinMax with/without Alpha Beta pruning as per our arg
 */
public class MinMaxWithAlphaBeta {
    private static final String MIN = "min";
    private static final String MAX = "max";

    public MinMaxWithAlphaBeta(Graph graph) {
        String state = ArgValidation.getRootPlayer();
        Boolean isABPrunEnable = ArgValidation.getIsABPrunEnable();
        Boolean isVerboseEnable = ArgValidation.getIsVerboseEnable();
        Node parentNode =  graph.getNodeMap().get(graph.getRootNode());
        applyAlgorithm(graph, parentNode, state, isABPrunEnable, isVerboseEnable);

        if (!isVerboseEnable) {
            System.out.println(getPrintMessage(parentNode, state, parentNode.getInputParent(), isABPrunEnable));
        }
    }


    private void applyAlgorithm(Graph graph, Node parentNode, String state, Boolean isPruningEnabled, Boolean isVerboseEnabled) {
        List<Node> neighbor = graph.getAdjVertices().get(parentNode);

        for (Node node: neighbor) {
            node.setIsLeafNode(graph.getAdjVertices().get(node).isEmpty());
            node.setAlphaValue(parentNode.getAlphaValue());
            node.setBetaValue(parentNode.getBetaValue());

            applyAlgorithm(graph, node, toggleState(state), isPruningEnabled, isVerboseEnabled);

            if (isPruningEnabled) {
                if (state.equals(MIN)) {
                    setNodeAPMin(parentNode, node);
                }
                if (state.equals(MAX)) {
                    setNodeAPMax(parentNode, node);
                }

                if (parentNode.getAlphaValue() >= parentNode.getBetaValue()) {
                    return;
                }
            } else {
                setValueInMinMax(parentNode, node, state);
            }
        }

        if (!parentNode.getIsLeafNode() && isVerboseEnabled) {
            System.out.println(getPrintMessage(parentNode, state, parentNode.getInputParent(), isPruningEnabled));
        }
    }

    /**
     * Set values of alpha when player is max
     * @param parentNode
     * @param node
     */
    private static void setNodeAPMax(Node parentNode, Node node) {
        float nodeValue;
        if (node.getIsLeafNode()) {
            nodeValue = Math.max(parentNode.getAlphaValue(), node.getValue());
            if (nodeValue == node.getValue() && nodeValue != parentNode.getAlphaValue()) {
                parentNode.setInputParent(node.getName());
            }
        } else {
            nodeValue = Math.max(parentNode.getAlphaValue(), Math.max(node.getAlphaValue(), node.getBetaValue()));
            if ((nodeValue == node.getAlphaValue() || nodeValue == node.getBetaValue()) && nodeValue != parentNode.getAlphaValue()) {
                parentNode.setInputParent(node.getName());
            }
        }
        parentNode.setAlphaValue(nodeValue);
    }

    /**
     * Set values of beta when player is min
     * @param parentNode
     * @param node
     */
    private static void setNodeAPMin(Node parentNode, Node node) {
        float nodeValue;
        if (node.getIsLeafNode()) {
            nodeValue = Math.min(parentNode.getBetaValue(), node.getValue());
            if (nodeValue == node.getValue() && parentNode.getBetaValue() != nodeValue) {
                parentNode.setInputParent(node.getName());
            }
        } else {
            nodeValue = Math.min(parentNode.getBetaValue(), Math.min(node.getAlphaValue(), node.getBetaValue()));
            if ((nodeValue == node.getAlphaValue() || nodeValue == node.getBetaValue()) && nodeValue != parentNode.getBetaValue()) {
                parentNode.setInputParent(node.getName());
            }
        }

        parentNode.setBetaValue(nodeValue);
    }

    /**
     * Set value in case of minmax algorithm
     *
     * @param parentNode
     * @param node
     * @param state
     */
    private static void setValueInMinMax(Node parentNode, Node node, String state) {
        if (parentNode.getValue() == null) {
            parentNode.setValue(node.getValue());
            parentNode.setInputParent(node.getName());
        } else {
            float value;
            if (state.equals(MAX)) {
                value = Math.max(parentNode.getValue(), node.getValue());
            } else {
                value = Math.min(parentNode.getValue(), node.getValue());
            }
            if ((value == node.getValue() && value != parentNode.getValue())) {
                parentNode.setInputParent(node.getName());
            }
            parentNode.setValue(value);
        }
    }

    private String toggleState(String k) {
        return (k.equals("min")) ? "max" : "min";
    }

    private String getPrintMessage(Node node, String state, String childNode, Boolean isPruningEnabled) {
        if (isPruningEnabled) {
            return state + "(" + node.getName() + ")" + " chooses " + childNode + " for " + (state.equals("max") ? node.getAlphaValue() : node.getBetaValue());
        }

        return state + "(" + node.getName() + ")" + " chooses " + childNode + " for " + node.getValue();
    }
}
