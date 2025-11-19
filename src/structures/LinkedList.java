package structures;

public class LinkedList<T> implements List<T> {

    Node<T> head;
    Node<T> current;

    public LinkedList() {
        head = null;
        current = null;
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

    @Override
    public void findFirst() {
        current = head;
    }

    @Override
    public void findNext() {
        if (!empty() && !last()) {
            current = current.next;
        }
    }

    @Override
    public T retrieve() {
        return current.data;
    }

    @Override
    public void insert(T e) {
        Node<T> temp;
        if (empty()) {
            head = current = new Node<>(e);
        } else {
            temp = current.next;
            current.next = new Node<>(e);
            current = current.next;
            current.next = temp;

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
        if (!empty()) {
            if (head == current) {
                head = head.next;

            } else {
                Node<T> temp = head;
                while (temp.next != current) {
                    temp = temp.next;
                }

                temp.next = current.next;

            }

            if (current.next == null) {
                current = head;
            } else {
                current = current.next;
            }

        }

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
