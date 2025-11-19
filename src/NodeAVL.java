
public class NodeAVL<K, T> {
    public int height, balanceFactor;
    public K key;
    public T data;
    public NodeAVL<K, T> right, left, parent;

    public NodeAVL(K key, T data) {
        this.key = key;
        this.data = data;
    }


}
