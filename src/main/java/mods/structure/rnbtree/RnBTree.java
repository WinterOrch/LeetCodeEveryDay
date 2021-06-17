package mods.structure.rnbtree;

@SuppressWarnings("SuspiciousNameCombination")
public class RnBTree<T extends Comparable<T>> {
    private RnBTNode<T> root;

    /**
     * Perform Left Rotate for The Red and Black Tree Node x
     *
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(rotate2Left)->     / \
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     * created in 20:43 2020/10/25
     */
    private void rotate2Left(RnBTNode<T> x) {
        RnBTNode<T> y = x.rightChild;
        
        if(y.leftChild != null) {
            x.rightChild = y.leftChild;
            y.leftChild.parent = x;
        }
        
        hang(y, x, false);
    }

    /**
     * Perform Right Rotate for The Red and Black Tree Node x
     *
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \   --(rotate2Right)->        /  \                     #
     *        x   ry                           lx   y
     *       / \                                   / \                   #
     *      lx  rx                                rx  ry
     *
     * created in 20:45 2020/10/25
     */
    private void rotate2Right(RnBTNode<T> x) {
        RnBTNode<T> y = x.parent;

        if(x.rightChild != null) {
            y.leftChild = x.rightChild;
            x.rightChild.parent = y;
        }

        hang(x, y, true);
    }

    private void hang(RnBTNode<T> x, RnBTNode<T> y, boolean toRight) {
        x.parent = y.parent;

        //  Check if y Is Root Node
        if(y.parent == null) {
            this.root = x;
        }else {
            if(y == y.parent.leftChild)
                y.parent.leftChild = x;
            else
                y.parent.rightChild = x;
        }

        y.parent = x;
        if(toRight)
            x.rightChild = y;
        else
            x.leftChild = y;
    }

    /**
     * Create A New Node and Insert
     * created in 10:58 2020/10/26
     */
    void insert(T key) {
        RnBTNode<T> node = new RnBTNode<>(key, true, null, null, null);

        insert(node);
    }

    /**
     * Remove Node with same Key
     * created in 16:25 2020/10/26
     */
    void remove(T key) {
        RnBTNode<T> node;

        if((node = this.search(key)) != null)
            remove(node);
    }

    RnBTNode<T> search(T key) {
        RnBTNode<T> x = this.root;
        RnBTNode<T> y;

        //  1.Tree Climbing
        while(x != null) {
            y = x;

            if(key.compareTo(y.key) < 0)
                x = x.leftChild;
            else if(key.compareTo(y.key) == 0)
                return y;
            else
                x = x.rightChild;
        }

        return null;
    }

    /**
     * Delete Node
     * created in 16:03 2020/10/26
     */
    private void remove(RnBTNode<T> node) {
        RnBTNode<T> child, parent;
        boolean color;

        if(node.leftChild != null && node.rightChild != null) {
            RnBTNode<T> replace = node;

            replace = replace.rightChild;
            while(replace.leftChild != null)
                replace = replace.leftChild;

            if(node.parent != null) {
                if(node.parent.leftChild == node)
                    node.parent.leftChild = replace;
                else
                    node.parent.rightChild = replace;
            }else {
                //  Root to Be Removed
                this.root = replace;
            }

            child = replace.rightChild;
            parent = replace.parent;

            color = replace.isBlack;

            if(parent == node) {
                parent = replace;
            }else {
                if(child != null)
                    child.parent = parent;
                parent.leftChild = child;

                replace.rightChild = node.rightChild;
                replace.isBlack = node.isBlack;
                replace.leftChild = node.leftChild;
                node.leftChild.parent = replace;
            }

        }else {
            if(node.leftChild != null)
                child = node.leftChild;
            else
                child = node.rightChild;

            parent = node.parent;

            color = node.isBlack;

            if(child != null)
                child.parent = parent;

            if(parent != null) {
                if(parent.leftChild == node)
                    parent.leftChild = child;
                else
                    parent.rightChild = child;
            }else
                this.root = child;

        }
        if(color)
            removeFixUp(child, parent);
    }

    /**
     * Insert New Node to Red and Black Tree
     * created in 10:50 2020/10/26
     */
    private void insert(RnBTNode<T> node) {
        RnBTNode<T> x = this.root;
        RnBTNode<T> y = null;

        //  1.Tree Climbing
        while(x != null) {
            y = x;

            if(node.key.compareTo(y.key) < 0)
                x = x.leftChild;
            else
                x = x.rightChild;
        }

        if(y == null) {
            this.root = node;
        }else {
            node.parent = y;

            if(node.key.compareTo(y.key) < 0)
                y.leftChild = node;
            else
                y.rightChild = node;
        }

        //  2.Paint Red
        node.isBlack = false;

        //  3.Fix the Tree to A New Red and Black Tree
        insertFixUp(node);
    }

    /**
     * Rebalance the RnB Tree after Inserting A New Node
     * created in 11:02 2020/10/26
     */
    private void insertFixUp(RnBTNode<T> node) {
        RnBTNode<T> parent, grandParent;

        //  Fix Ends When A Black Node is Met
        while((parent = node.parent) != null && !parent.isBlack) {
            grandParent = parent.parent;

            //  Case 1: Parent is Left Child of Grandparent
            if(parent == grandParent.leftChild) {
                RnBTNode<T> uncle = grandParent.rightChild;

                //  Case 1.0: Uncle is Red
                if(uncle != null && !uncle.isBlack) {
                    uncle.isBlack = true;
                    parent.isBlack = true;
                    grandParent.isBlack = false;

                    node = grandParent;
                    continue;
                }

                //  Case 1.1: Uncle is Black, Node On Right Branch of Parent
                if(parent.rightChild == node) {
                    RnBTNode<T> nodeTemp = parent;

                    //  Left Rotate
                    rotate2Left(parent);

                    parent = node;

                    node = nodeTemp;
                }

                //  Case 1.1
                //  with Case 1.2: Uncle is Black, Node on Left Branch of Parent
                parent.isBlack = true;
                grandParent.isBlack = false;

                //  Right Rotate
                rotate2Right(grandParent);
            }
            //  Case 2: Parent is Right Child of Grandparent
            else {
                RnBTNode<T> uncle = grandParent.leftChild;

                //  Case 2.0: Uncle is Red
                if(uncle != null && !uncle.isBlack) {
                    uncle.isBlack = true;
                    parent.isBlack = true;
                    grandParent.isBlack = false;

                    node = grandParent;
                    continue;
                }

                //  Case 2.1: Uncle is Black, Node On Left Branch of Parent
                if(parent.leftChild == node) {
                    RnBTNode<T> nodeTemp = parent;

                    //  Left Rotate
                    rotate2Right(parent);

                    parent = node;

                    node = nodeTemp;
                }


                //  Case 2.1
                //  with Case 2.2: Uncle is Black, Node on Right Branch of Parent
                parent.isBlack = true;
                grandParent.isBlack = false;

                //  Right Rotate
                rotate2Left(grandParent);
            }
        }

        //  Null Check Done in Its Caller
        this.root.isBlack = true;
    }

    /**
     * Rebalance the RnB Tree after Removing A Node
     * created in 20:06 2020/10/26
     */
    private void removeFixUp(RnBTNode<T> node, RnBTNode<T> parent) {
        RnBTNode<T> other;

        while ((node == null || node.isBlack) && (node != this.root)) {
            if (parent.leftChild == node) {
                other = parent.rightChild;

                //  Case 1: x Has A Red Brother
                if (!other.isBlack) {
                    other.isBlack = true;
                    parent.isBlack = false;

                    rotate2Left(parent);
                    other = parent.rightChild;
                }

                //  Case 2: x Has A Black Brother with Two Black Children
                if ((other.leftChild == null || other.leftChild.isBlack) &&
                        (other.rightChild == null || other.rightChild.isBlack)) {
                    other.isBlack = false;
                    node = parent;
                    parent = node.parent;
                } else {
                    //  Case 3: x Has A Black Brother with Red Left Child and Black Right Child
                    if (other.rightChild == null || other.rightChild.isBlack) {
                        other.leftChild.isBlack = true;
                        other.isBlack = false;
                        rotate2Right(other);
                        other = parent.rightChild;
                    }

                    //  Case 4: x Has A Black Brother with Red Right Child
                    other.isBlack = parent.isBlack;
                    parent.isBlack = true;
                    other.rightChild.isBlack = true;
                    rotate2Left(parent);

                    node = this.root;
                    break;
                }
            } else {
                other = parent.leftChild;

                //  Case 1: x Has A Red Brother
                if (!other.isBlack) {
                    other.isBlack = true;
                    parent.isBlack = false;
                    rotate2Right(parent);
                    other = parent.leftChild;
                }

                //  Case 2: x Has A Black Brother with Two Black Children
                if ((other.leftChild == null || other.leftChild.isBlack) &&
                        (other.rightChild == null || other.rightChild.isBlack)) {
                    other.isBlack = false;
                    node = parent;
                    parent = node.parent;
                } else {
                    //  Case 3: x Has A Black Brother with Red Left Child and Black Right Child
                    if (other.leftChild == null || other.leftChild.isBlack) {
                        other.rightChild.isBlack = true;
                        other.isBlack = false;
                        rotate2Left(other);
                        other = parent.leftChild;
                    }

                    //  Case 4: x Has A Black Brother with Red Right Child
                    other.isBlack = parent.isBlack;
                    parent.isBlack = true;
                    other.leftChild.isBlack = true;
                    rotate2Right(parent);

                    node = this.root;
                    break;
                }
            }
        }

        if(node != null)
            node.isBlack = true;
    }
}
