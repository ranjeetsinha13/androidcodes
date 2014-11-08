package com.stacks;

public class StockSpan {

	private int arr[] = { 10, 4, 5, 90, 120, 80 };

	public void getSpan() {
		StackImpl s = new StackImpl<Integer>();
		s.push(0);
		int stockSpanRes[] = new int[arr.length];
		stockSpanRes[0] = 1;
		for (int i = 0; i < arr.length; i++) {
			while (!s.isEmpty() && arr[(int) s.peek()] <= arr[i]) {
				s.pop();
			}
			stockSpanRes[i] = s.isEmpty() ? i + 1 : i - (int) s.peek();
			s.push(i);
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.println(stockSpanRes[i]);
		}
	}

	public static void main(String[] args) {
		StockSpan s = new StockSpan();
		s.getSpan();

	}

}
