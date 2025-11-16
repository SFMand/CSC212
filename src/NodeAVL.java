
public class NodeAVL<T> {
    public int key, height;
    public T data;
    public NodeAVL<T> parent, right, left;

    public NodeAVL(int key, T data) {
        this.key = key;
        this.data = data;
        parent = null;
        right = null;
        left = null;
    }

        
}
