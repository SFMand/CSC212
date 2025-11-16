public interface Tree<T> {

    public boolean full();

    public boolean empty();

    public T retrieve();

    public boolean findKey(int key);

    public boolean insert(int key, T e);

    public boolean update(int key, T e);

    public boolean removeKey(int key);  

    public void traverse(TraverseOrder t);

    public boolean deleteSub();
}
