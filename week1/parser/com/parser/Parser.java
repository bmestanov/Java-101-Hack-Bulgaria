package com.parser;

import com.listify.Listify;

public class Parser{
	public static void main(String[] args){
		String[] result = Listify.readToArray(args[0]);

		for(String line : result) {
			System.out.println(line);
		}
	}
}