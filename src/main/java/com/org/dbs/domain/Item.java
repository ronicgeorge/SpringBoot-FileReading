package com.org.dbs.domain;

import java.util.Comparator;

public class Item implements Comparable<Item> {

	private int satisfaction;
	private int time;

	public Item(int satisfaction, int time) {
		super();
		this.satisfaction = satisfaction;
		this.time = time;
	}

	public int getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int compareTo(Item compareItem) {
		int compareSatisfaction = ((Item) compareItem).getSatisfaction();
		return this.time - compareSatisfaction;
	}

	public static Comparator<Item> ItemTimeComparator = new Comparator<Item>() {

		public int compare(Item item1, Item item2) {
			int time1 = item1.getTime();
			int time2 = item2.getTime();
			return time1 - time2;
		}

	};
}