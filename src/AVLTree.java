
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
            // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeKey'");
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

    @Override
    public boolean deleteSub() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSub'");
    }

}
