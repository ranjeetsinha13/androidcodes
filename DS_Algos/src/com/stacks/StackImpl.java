
package com.stacks;

public class StackImpl<E> {

    protected E[] stacklist;

    private int position;

    public StackImpl() {
        stacklist = (E[])new Object[20];
    }

    public void push(E e) {

        stacklist[++position] = e;
        System.out.println("pos, push" + position);
    }

    public E pop() {

        System.out.println("pos, pop" + position);
        return stacklist[position--];

    }

    public E peek() {
        return stacklist[position];
    }

    public boolean isEmpty() {
        System.out.println("position is " + position);
        return position == 0;
    }

}
