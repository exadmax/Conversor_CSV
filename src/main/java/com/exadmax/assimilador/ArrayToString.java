package com.exadmax.assimilador;

import java.util.ArrayList;
import java.util.List;

public class ArrayToString {
	public static void main(String[] args) {

		List<String> strings = new ArrayList<String>();
		strings.add("String 1");
		strings.add("Strings 2");
		strings.add("Strings 3");

		String token = strings.toString();
		System.out.println(token);

	}
}
