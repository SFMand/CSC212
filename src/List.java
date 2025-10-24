
public interface List<T> {

    public boolean full();

    public boolean empty();

    public boolean last();

    public void findFirst();

    public void findNext();

    public T retrieve();

    public void insert(T e);

    public void update(T e);

    public void remove();

    public void print();

}
