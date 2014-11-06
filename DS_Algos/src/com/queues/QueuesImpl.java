
package com.queues;

public class QueuesImpl {

    private int front;

    private int rear;

    private int arr[] = {};

    private static final int MAX_SIZE = 20;

    public QueuesImpl() {
        front = 0;
        rear = 0;
        arr = new int[20];

    }

    public void enqueue(int element) {
        int temp = (rear + 1) % MAX_SIZE;
        if (isFull()) {
            System.out.println("queue full. cannot enqueue");
        } else {
            arr[rear] = element;
            rear = temp;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("queue empty .. nothing to dequeue");
            return -1;

        } else {
            int temp = arr[front];
            front = (front + 1) % MAX_SIZE;
            return temp;
        }
    }

    public boolean isFull() {
        return front == rear;
    }

    public boolean isEmpty() {
        return (front == ((rear + 1) % MAX_SIZE));
    }

    public static void main(String[] args) {

        QueuesImpl q = new QueuesImpl();
        q.enqueue(5);

    }

}
