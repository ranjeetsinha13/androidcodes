package com.lists;

public class LinkedListImpl<E> {

	private class Node<E> {
		private E data;
		private Node<E> next;

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;

		}

		public Node(E data) {
			this.data = data;
			this.next = null;
		}

		/**
		 * @return the data
		 */
		public E getData() {
			return data;
		}

		/**
		 * @param data
		 *            the data to set
		 */
		public void setData(E data) {
			this.data = data;
		}

		/**
		 * @return the next
		 */
		public Node<E> getNext() {
			return next;
		}

		/**
		 * @param next
		 *            the next to set
		 */
		public void setNext(Node<E> next) {
			this.next = next;
		}

	}

	private Node<E> head;
	private int listCount;

	public LinkedListImpl() {
		head = new Node<E>(null);
		listCount = 0;

	}

	public void add(E data) {
		Node<E> nodeTemp = new Node<E>(data);
		Node<E> current = head;
		while (current.next != null) {
			current = current.getNext();
		}
		current.setNext(nodeTemp);
		nodeTemp.setNext(null);
		listCount++;

	}

	public void addAtPos(E data, int pos) {
		Node<E> current = head;
		Node<E> nodeTemp = new Node<E>(data);
		for (int i = 0; i < pos; i++) {
			current = current.getNext();
		}

		nodeTemp.setNext(current.getNext());
		current.setNext(nodeTemp);
		listCount++;

	}

	public boolean remove(int pos) {
		Node<E> current = head;
		if (current == null || current.getNext() == null || listCount < pos) {
			System.out.println("error");
			return false;
		}
		for (int i = 0; i < pos; i++) {
			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		listCount--;
		return true;

	}

	public E get(int pos) {
		Node<E> current = head;
		if (listCount < pos || pos < 0) {
			System.out.println("error");
			return null;
		}
		for (int i = 0; i < pos; i++) {
			current = current.getNext();
		}
		return (E) current;

	}

	public int getSize() {
		return listCount;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
