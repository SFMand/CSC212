package structures;
public interface BSTree<K, T> {

    public boolean full();

    public boolean empty();

    public T retrieve();

    public boolean findKey(K key);

    public boolean insert(K key, T e);

    public boolean update(K key, T e);

    public boolean removeKey(K key);  

    public void traverse(TraverseOrder t);

    public void deleteSub();
}
