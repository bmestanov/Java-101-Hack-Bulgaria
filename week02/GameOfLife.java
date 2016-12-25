package week02;

public class GameOfLife {
	/**
	 * Implementation of the Conway's Game Of Life.
	 * Full task can be found here: 
	 * https://github.com/HackBulgaria/Programming101-Java-2016/tree/master/week02/GameOfLife
	 */
	
	boolean[][] grid, tempGrid;
	int size;
	
	public static final char ALIVE = '■',
			DEAD = '□';
	
	public GameOfLife(int size) {
		this.size = size;
		grid = new boolean[size][size];
		tempGrid = new boolean[size][size];
		for(int i=0; i<size; i++){
			grid[i] = new boolean[size];
			tempGrid[i] = new boolean[size];
		}
	}
	
	public void add(int x, int y){		 
		 grid[x][y] = true;
	}
	
	public int getNeighbours(int x, int y){
		int counter = 0;
		for(int i= x-1; i<=x+1;i++){
			for(int j= y-1 ; j<=y+1; j++){
				boolean isOutGrid = 
						i < 0 || i>size-1 || j<0 || j > size-1;
				boolean isSourceCell = i == x && j ==y;		
				if(!isOutGrid && !isSourceCell){
					counter += (grid[i][j] == true) ? 1 : 0;
				}
			}
		}
		
		return counter;
	}
	
	public void start() throws InterruptedException{
		while(true){
			for(int i=0; i<size; i++){
				for(int j=0; j<size; j++){
					tempGrid[i][j] = getNewStatus(grid[i][j], i, j);
				}
			}
			initTemp();
			System.out.println(toString());
			Thread.sleep(1000);
		}
	}
	
	private void initTemp(){
		for(int i=0; i<size;i++){
			for(int j=0;j<size;j++){
				grid[i][j] = tempGrid[i][j];
				tempGrid[i][j] = false;
			}
		}
	}
	
	public boolean getNewStatus(boolean current, int x, int y){
		int neighbors = getNeighbours(x, y);
		if(current == true){
			if(neighbors < 2 || neighbors > 3){
				return false;
			}
			
			return true;
		}
		
		if(neighbors == 3){
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		String result = "";
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				result += (grid[i][j] == true) ? ALIVE : DEAD;
				result += " "; 
			}
			result += "\n";
		}
		return result;
	}
}
