
public class ArrayList<T> implements List<T> {

    int current;
    int size;
    int maxSize;
    T nodes[];

    public ArrayList(int maxSize) {
        current = -1;
        size = 0;
        this.maxSize = maxSize;
        nodes = (T[]) new Object[maxSize];

    }

    @Override
    public boolean full() {
        return size == maxSize;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public boolean last() {
        return !empty() && current == size - 1;
    }

    @Override
    public void findFirst() {
        if (!empty()) {
            current = 0;
        }
    }

    @Override
    public void findNext() {
        if (!last()) {
            current++;
        }
    }

    @Override
    public T retrieve() {
        return nodes[current];
    }

    @Override
    public void insert(T e) {
        if (!full()) {
            for (int i = size - 1; i > current; i--) {
                nodes[i + 1] = nodes[i];
            }
            nodes[++current] = e;
            size++;
        }
    }

    @Override
    public void update(T e) {
        if (!empty()) {
            nodes[current] = e;
        }
    }

    @Override
    public void remove() {
        for (int i = current + 1; i < size - 1; i++) {

            nodes[i - 1] = nodes[i];
        }
        size--;

        if (size == 0) {
            current = -1;
        }

        if (current == size) {
            current = 0;
        }
    }

}
