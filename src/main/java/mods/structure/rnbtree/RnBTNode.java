package mods.structure.rnbtree;

public class RnBTNode<T extends Comparable<T>> {
    //  Key
    T key;

    //  Neighbour Nodes
    RnBTNode<T> leftChild;
    RnBTNode<T> rightChild;
    RnBTNode<T> parent;

    //  Color
    boolean isBlack;

    public RnBTNode(T key, boolean color, RnBTNode<T> parent, RnBTNode<T> left, RnBTNode<T> right) {
        this.key = key;
        this.isBlack = color;
        this.parent = parent;
        this.leftChild = left;
        this.rightChild = right;
    }
}
