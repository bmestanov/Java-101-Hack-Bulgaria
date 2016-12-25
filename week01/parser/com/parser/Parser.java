package week01.parser.com.parser;

import week01.listify.Listify;

public class Parser{
	public static void main(String[] args){
		String[] result = Listify.readToArray(args[0]);

		for(String line : result) {
			System.out.println(line);
		}
	}
}