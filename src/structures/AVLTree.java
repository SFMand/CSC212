package structures;

public class AVLTree<K extends Comparable<K>, T> implements BinaryTree<K, T> {
    private NodeAVL<K, T> root, current;

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

    public K getKey() {
        return current.key;
    }

    public void findRoot() {
        current = root;
    }

    public boolean findRight() {
        if (!empty() && current.right != null) {
            current = current.right;
            return true;
        }
        return false;
    }

    public boolean findLeft() {
        if (!empty() && current.left != null) {
            current = current.left;
            return true;
        }
        return false;
    }

    public boolean findParent() {
        if (!empty() && current.parent != null) {
            current = current.parent;
            return true;
        }
        return false;
    }

    @Override
    public boolean findKey(K key) {
        if (empty())
            return false;

        NodeAVL<K, T> p = root, q = null;

        while (p != null) {
            q = p;
            if (key.equals(p.key)) {
                current = p;
                return true;
            }
            p = key.compareTo(p.key) < 0 ? p.left : p.right;

        }

        current = q;
        return false;

    }

    @Override
    public boolean insert(K key, T e) {
        if (empty()) {
            root = new NodeAVL<K, T>(key, e);
            current = root;
            return true;
        }

        if (!findKey(key)) {
            if (key.compareTo(current.key) < 0) {
                current.left = new NodeAVL<K, T>(key, e);
                current.left.parent = current;
                current = current.left;
            } else {
                current.right = new NodeAVL<K, T>(key, e);
                current.right.parent = current;
                current = current.right;
            }
            balance(current);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(K key, T e) {
        return removeKey(current.key) && insert(key, e);
    }

    @Override
    public boolean removeKey(K key) {
        if (findKey(key)) {
            removeNode(current);
            current = root;
            return true;
        }
        return false;
    }

    public void removeNode(NodeAVL<K, T> node) { // recursive
        // Case 1: Node is leaf
        if (node.left == null && node.right == null) {
            NodeAVL<K, T> parent = node.parent;
            if (parent != null) {
                if (parent.left == node)
                    parent.left = null;
                else
                    parent.right = null;
                balance(parent);
                return;
            } else {
                root = null;
            }

        }

        // Case 2: Node has Children
        NodeAVL<K, T> child;
        if (node.right != null)
            child = minInTree(node.right);
        else {

            child = maxInTree(node.left);
        }

        node.key = child.key;
        node.data = child.data;
        removeNode(child);
    }

    private NodeAVL<K, T> minInTree(NodeAVL<K, T> node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    private NodeAVL<K, T> maxInTree(NodeAVL<K, T> node) {
        while (node.right != null)
            node = node.right;
        return node;
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

    private void preOrder(NodeAVL<K, T> n) {
        if (n != null) {
            System.out.println(n.data);
            System.out.println("-------------------------");
            preOrder(n.left);
            preOrder(n.right);
        }
    }

    private void inOrder(NodeAVL<K, T> n) {
        if (n != null) {
            inOrder(n.left);
            System.out.println(n.data);
            System.out.println("-------------------------");

            inOrder(n.right);
        }
    }

    private void postOrder(NodeAVL<K, T> n) {
        if (n != null) {
            postOrder(n.left);
            postOrder(n.right);
            System.out.println(n.data);
            System.out.println("-------------------------");

        }
    }

    private void balance(NodeAVL<K, T> node) { // recursive
        calcBalance(node);

        if (node.balanceFactor == 2) {
            if (node.right.balanceFactor >= 0) {
                // rotate right
                rightRotation(node);
            } else {
                // double rotate
                doubleRotateRight(node);
            }
        }

        else if (node.balanceFactor == -2) {

            if (node.left.balanceFactor < 0) {
                // rotate left
                leftRotation(node);
            } else {

                // double rotate
                doubleRotateLeft(node);
            }

        }
        // call method again for node.parent
        if (node.parent != null)
            balance(node.parent);
        else
            root = node;
    }

    private NodeAVL<K, T> rightRotation(NodeAVL<K, T> node1) {
        NodeAVL<K, T> node2 = node1.right;
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

    private NodeAVL<K, T> leftRotation(NodeAVL<K, T> node1) {
        NodeAVL<K, T> node2 = node1.left;
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

    private void doubleRotateRight(NodeAVL<K, T> node1) {
        node1.right = leftRotation(node1.right);
        rightRotation(node1);

    }

    private void doubleRotateLeft(NodeAVL<K, T> node1) {
        node1.left = rightRotation(node1.left);
        leftRotation(node1);

    }

    private void calcBalance(NodeAVL<K, T>... nodes) {
        for (NodeAVL<K, T> node : nodes) {
            node.height = 1 + Math.max(heightCheck(node.right), heightCheck(node.left));
            node.balanceFactor = heightCheck(node.right) - heightCheck(node.left);
        }
    }

    private int heightCheck(NodeAVL<K, T> node) {
        if (node == null)
            return -1;
        return node.height;
    }

    @Override
    public void deleteSub() {
        if (current == root) {
            current = root = null;
            return;
        }
        NodeAVL<K, T> parent = current.parent;
        if (parent.left == current)
            parent.left = null;
        else
            parent.right = null;
        balance(parent);
        current = root;
    }

}
