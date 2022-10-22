package graph.node;

/**
 * This class defines structure of a leaf & non leaf node;
 */
public class Node {
    Float alphaValue = -Float.MAX_VALUE;
    Float betaValue = Float.MAX_VALUE;
    Float value;
    Boolean isLeafNode = false;
    String name;
    String inputParent = "";

    public Node(String nodeName) {
        value = null;
        name = nodeName;
    }

    public Float getAlphaValue() {
        return alphaValue;
    }

    public Float getBetaValue() {
        return betaValue;
    }

    public Float getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public void setAlphaValue(Float alphaValue) {
        this.alphaValue = alphaValue;
    }

    public void setBetaValue(Float betaValue) {
        this.betaValue = betaValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(Boolean leafNode) {
        isLeafNode = leafNode;
    }

    public String getInputParent() {
        return inputParent;
    }

    public void setInputParent(String inputParent) {
        this.inputParent = inputParent;
    }
}
