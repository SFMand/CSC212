
public class AVLTree<T> implements Tree<T> {
    private NodeAVL<T> root, current;

    public AVLTree() {
        current = root = null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean empty() {
        return root == null;
    }

    @Override
    public T retrieve() {
        return current.data;
    }

    @Override
    public boolean findKey(int key) {
        if (empty())
            return false;

        NodeAVL<T> p = root, q = null;

        while (p != null) {
            q = p;
            if (p.key == key) {
                current = p;
                return true;
            }
            p = p.key > key ? p.left : p.right;
        }

        current = q;
        return false;

    }

    @Override
    public boolean insert(int key, T e) {
        if (empty()) {
            root = new NodeAVL<T>(key, e);
            return true;
        }

        current = root;
        NodeAVL<T> parent;
        while (true) {
            if (current.key == key)
                return false;
            parent = current;
            boolean checkSideLeft = current.key > key;
            current = checkSideLeft ? current.left : current.right;

            if (current == null) {
                if (checkSideLeft) {
                    parent.left = new NodeAVL<T>(key, e);
                    current = parent.left;
                } else {
                    parent.right = new NodeAVL<T>(key, e);
                    current = parent.right;
                }
                break;
            }
        }
        // Rebalance method goes here, not impl yet
        balance(parent);
        return true;

    }

    @Override
    public boolean update(int key, T e) {
        if (findKey(key)) {
            current.data = e;
            return true;
        } else
            return false;
    }

    @Override
    public boolean removeKey(int key) {
        if (empty())
            return false;

        current = root;

        while (current != null) {
            if (current.key == key) {
                removeNode(current);
                return true;
            }
            current = current.key > key ? current.left : current.right;
        }

        return false;
    }

    public void removeNode(NodeAVL<T> n) { // recursive
        // Case 1: Node is leaf
        // Rebalance method goes here, not impl yet

        // Case 2: Node has Children

        // Move current accordingly
    }

    @Override
    public void traverse(TraverseOrder t) {
        switch (t) {
            case PRE_ORDER:
                preOrder(root);
                break;
            case IN_ORDER:
                inOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
        }
    }

    private void preOrder(NodeAVL<T> n) {
        if (n != null) {
            System.out.println(n.data);
            preOrder(n.left);
            preOrder(n.right);
        }
    }

    private void inOrder(NodeAVL<T> n) {
        if (n != null) {
            inOrder(n.left);
            System.out.println(n.data);
            inOrder(n.right);
        }
    }

    private void postOrder(NodeAVL<T> n) {
        if (n != null) {
            postOrder(n.left);
            postOrder(n.right);
            System.out.println(n.data);
        }
    }

    private void balance(NodeAVL<T> node) { // recursive
        calcBalance(node);

        if (node.balanceFactor == 2) {
            if (heightCheck(node.left.right) - heightCheck(node.left.left) < 0) {
                // rotate right
                rotateRight(node);
            } else {
                // double rotate
                doubleRotateRight(node);
            }
        }

        else if (node.balanceFactor == -2) {

            if (heightCheck(node.right.right) - heightCheck(node.right.left) >= 0 ) {
                // rotate left
                rotateLeft(node);
            } else {

                // double rotate
                doubleRotateLeft(node);
            }

        }
        // call method again for node.parent
        if (node.parent != null)
            balance(node.parent);
    }

    private NodeAVL<T> rotateRight(NodeAVL<T> node1) {
        NodeAVL<T> node2 = node1.right;
        node2.parent = node1.parent;
        node1.right = node2.left;
        if (node1.right != null)
            node1.right.parent = node1;

        node2.left = node1;

        node1.parent = node2;

        if (node2.parent != null) {
            if (node2.parent.left == node1)
                node2.parent.left = node2;
            else
                node2.parent.right = node2;

        }

        calcBalance(node1, node2);
        return node2;

    }

    private NodeAVL<T> rotateLeft(NodeAVL<T> node1) {
        NodeAVL<T> node2 = node1.left;
        node2.parent = node1.parent;

        node1.left = node2.right;
        if (node1.left != null)
            node1.left.parent = node1;

        node2.right = node1;
        node1.parent = node2;

        if (node2.parent != null) {
            if (node2.parent.left == node1)
                node2.parent.left = node2;
            else
                node2.parent.right = node2;

        }

        calcBalance(node1, node2);
        return node2;

    }

    private NodeAVL<T> doubleRotateRight(NodeAVL<T> node1) {
        node1.right = rotateLeft(node1.right);
        return rotateRight(node1);

    }

    private NodeAVL<T> doubleRotateLeft(NodeAVL<T> node1) {
        node1.left = rotateRight(node1.left);
        return rotateLeft(node1);

    }

    private void calcBalance(NodeAVL<T>... nodes) {
        for (NodeAVL<T> node : nodes) {
            node.height = 1 + Math.max(heightCheck(node.right), heightCheck(node.left));
            node.balanceFactor = heightCheck(node.right) - heightCheck(node.left);
        }
    }

    private int heightCheck(NodeAVL<T> node) {
        if (node == null)
            return -1;
        return node.height;
    }

    @Override
    public boolean deleteSub() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSub'");
    }

}
