package structures;

public class DoubleLinkedList<T> implements List<T> {
    NodeDLL<T> head, current;

    public DoubleLinkedList() {
        current = head = null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean empty() {
        return head == null;
    }

    @Override
    public boolean last() {
        return current.next == null;
    }

    public boolean first() {
        return current.previous == null;
    }

    @Override
    public void findFirst() {
        current = head;
    }

    @Override
    public void findNext() {
        current = current.next;
    }

    @Override
    public T retrieve() {
        return current.data;
    }

    @Override
    public void insert(T e) {
        NodeDLL<T> temp = new NodeDLL<>(e);
        if (empty()) {
            current = head = temp;
        } else {
            temp.next = current.next;
            temp.previous = current;
            if (current.next != null)
                current.next.previous = temp;
            current.next = temp;
            current = temp;
        }

    }

    @Override
    public void update(T e) {
        if (!empty()) {
            current.data = e;
        }

    }

    @Override
    public void remove() {
        if (current == head) {
            head = head.next;
            if (head != null)
                head.previous = null;
        } else {
            current.previous.next = current.next;
            if (current.next != null)
                current.next.previous = current.previous;
        }

        if (current.next == null)
            current = head;
        else
            current = current.next;
    }

    @Override
    public void print() {
        if (!empty()) {
            findFirst();
            while (true) {
                System.out.println(retrieve());
                System.out.println("-------------------------");
                if (last()) {
                    break;
                }
                findNext();
            }

        } else {
            System.err.println("List is empty");
        }
    }

}
