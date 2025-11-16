
public class NodeAVL<T> {
    public int key, height;
    public T data;
    public NodeAVL<T> right, left;

    public NodeAVL(int key, T data) {
        this.key = key;
        this.data = data;
        height = 0;
        right = null;
        left = null;
    }

}
