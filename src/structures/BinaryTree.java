package structures;

public interface BinaryTree<K, T> {

    public boolean full();

    public boolean empty();

    public T retrieve();

    public K getKey();

    public void findRoot();

    public boolean findRight();

    public boolean findLeft();

    public boolean findParent();

    public boolean findKey(K key);

    public boolean insert(K key, T e);

    public boolean update(K key, T e);

    public boolean removeKey(K key);

    public void traverse(TraverseOrder t);

    public void deleteSub();
}
