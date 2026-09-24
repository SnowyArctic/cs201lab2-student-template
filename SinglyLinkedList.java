import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap() {
        if (isEmpty()) {
            return;
        }

        Node<E> current = head;

        List<Node<E>> originalOrder = new ArrayList<>();
        while (current != null) {
            originalOrder.add(current);
            current = current.getNext();
        }

        List<Node<E>> sortedOrder = new ArrayList<>(originalOrder);

        sortedOrder.sort(new Comparator<Node<E>>() {
            @Override
            public int compare(Node<E> node1, Node<E> node2) {
                return node1.getElement().compareTo(node2.getElement());
            }
        });

        int n = originalOrder.size();

        // for (int i = 0; i < n / 2; i++) {
        //     Node<E> forward = sortedOrder.get(i);
        //     Node<E> backward = sortedOrder.get(n - 1 - i);

        //     int indexForward = originalOrder.indexOf(forward);
        //     int indexBackward = originalOrder.indexOf(backward);

        //     Collections.swap(originalOrder, indexForward, indexBackward);
        // }

        // for (int i = 0; i < n - 1; i++) {
        //     Node<E> present = originalOrder.get(i);
        //     Node<E> next = originalOrder.get(i + 1);

        //     present.setNext(next);
        // }

        // head = originalOrder.get(0);
        // tail = originalOrder.get(n-1);

        // tail.setNext(null);

        Map<Node<E>, Integer> indexMap = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            indexMap.put(originalOrder.get(i), i);
        }

        for (int i = 0; i < n / 2; i++) {
            Node<E> forward = sortedOrder.get(i);
            Node<E> backward = sortedOrder.get(n - 1 - i);

            int indexForward = indexMap.get(forward);
            int indexBackward = indexMap.get(backward);

            originalOrder.set(indexForward, backward);
            originalOrder.set(indexBackward, forward);

            indexMap.put(forward, indexBackward);
            indexMap.put(backward, indexForward);
        }

        for (int i = 0; i < n - 1; i++) {
            originalOrder.get(i).setNext(originalOrder.get(i + 1));
        }

        head = originalOrder.get(0);
        tail = originalOrder.get(n-1);

        tail.setNext(null);
    }
}
