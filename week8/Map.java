package week8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
	private static final int MAP_SIZE = 4;
	private Element[][] map;
	
	public Map() throws FileNotFoundException {
		map = new Element[MAP_SIZE][];
		for(int i=0; i<MAP_SIZE;i++){
			map[i] = new Element[MAP_SIZE];
		}
		
		File file = new File("map.txt");
		Scanner sc = new Scanner(file);
		int i=0, j=0;
		while(sc.hasNext()){
			System.out.println(sc.next());
		}
	}
	
	@Override
	public String toString() {
		String out = new String();
		for(int i=0; i<map.length;i++){
			for(int j=0; j<map[i].length; j++){
				out += map[i][j] + " ";
			}
			out += "\n";
		}
		
		return out;
	}
}
