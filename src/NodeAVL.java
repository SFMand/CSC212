
public class NodeAVL<T> {
    public int key, height, balanceFactor;
    public T data;
    public NodeAVL<T> right, left, parent;

    public NodeAVL(int key, T data) {
        this.key = key;
        this.data = data;
        height = 0;
        balanceFactor = 0;
        right = null;
        left = null;
        parent = null;
    }
}
