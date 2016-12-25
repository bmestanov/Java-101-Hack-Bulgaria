package week05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Binary search on the possible outcomes of a problem.
 */
public class PancakeSolver {
	private static ArrayList<Integer> pots;
	private static int pancakes;
	private static int pots_count;
	
	public static int solve(Scanner in){
		pancakes = in.nextInt();
		pots_count = in.nextInt();
		
        pots = new ArrayList<>(); 
		for(int i=0; i<pots_count;i++){
			pots.add(in.nextInt());
		}
		
		int lower_bound = 0;
		int upper_bound = Collections.max(pots)*pancakes;
		
		return solveFor(lower_bound, upper_bound, pancakes);
	}
	
	private static int solveFor(int low, int high, int pancakes) {
		while(low < high){
			int mid = low + (high-low)/2;
			
			if(canBeCooked(mid)){
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		
		return low;
	}
	
	private static boolean canBeCooked(int time){
		int ready = 0;
		for(Integer pot : pots){
			ready += (time/pot);
		}
		
		return ready >= pancakes;
	}
}
