package com.summer.tech.javase8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FilterApple {

	public static List<Apple> findApple(List<Apple> apples, AppleFilter filter) {
		List<Apple> list = new ArrayList<>();
		for (Apple apple : apples) {
			if (filter.Filter(apple)) {
				list.add(apple);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		List<Apple> list = new ArrayList<>();
		list.add(new Apple("青苹果", "green"));
		list.add(new Apple("红苹果", "red"));

		System.out.println(findApple(list, (Apple apple) -> {
			return apple.getColor().equals("green");
		}).size());

		Function<String, Integer> lambda = s -> s.length();

		Supplier<String> str = String::new;
		System.out.println(str.get().getClass());

		consumer((c) -> System.out.println(c), "Hello word");
		// 函数推导,只合适方法体只有一行代码的？
		consumer(System.out::println, "hello xlet");
	}

	public static <T> void consumer(Consumer<T> consumer, T t) {
		consumer.accept(t);
		consumer.accept(t);
	}
}
