package com.summer.tech.spring.di;

public class CircleA {
	private CircleB circleB;

	public CircleA() {
	}

	public CircleA(CircleB circleB) {
		this.circleB = circleB;
	}

	public void setCircleB(CircleB circleB) {
		this.circleB = circleB;
	}

	public void a() {
		System.out.println("a");
		circleB.b();
	}
}