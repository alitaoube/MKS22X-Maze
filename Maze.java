
import java.util.*; // Scanner
import java.io.*; // File, FileNotFoundException
public class Maze{
    private char[][]maze;
    private boolean animate;//false by default
		private int[][] moves = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
		private int changes = -1;

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!


      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:

         throw a FileNotFoundException or IllegalStateException

    */

    public Maze(String filename) throws FileNotFoundException{
        //COMPLETE CONSTRUCTOR
				try{
					// animate = true;
					File text = new File(filename);

					Scanner inf = new Scanner(text);
					int x = 0;
					int y = 0;
					while (inf.hasNextLine()) {
						y = inf.nextLine().length();
						x++;

					}
					maze = new char[x][y];

					inf = new Scanner(text);
					int z = 0;
					while (inf.hasNextLine()){
						String line = inf.nextLine();
						for (int i = 0; i < line.length(); i++){
							maze[z][i] = line.charAt(i);
						}
						z++;
					}
				}
				catch(FileNotFoundException e){
					e.printStackTrace();
					System.out.println("Gotta catch 'em all");
				}
    }

		public String toString(){
			String output = "";

			for (char[] x: maze){
				for (char y: x){
					output += y;
				}
				output += "\n";
			}
			return output;
		}


    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){
			for (int x = 0; x < maze.length; x++){
				for (int y = 0; y < maze[x].length; y++){
					if (maze[x][y] == 'S'){
						return solve(x, y, 1);
					}
				}
			}
			return -1;
			}

    //find the location of the S.


    //erase the S


    //and start solving at the location of the s.

    //return solve(???,???);

		/*
			Recursive Solve function:

			A solved maze has a path marked with '@' from S to E.

			Returns the number of @ symbols from S to E when the maze is solved,
			Returns -1 when the maze has no solution.


			Postcondition:

				The S is replaced with '@' but the 'E' is not.

				All visited spots that were not part of the solution are changed to '.'

				All visited spots that are part of the solution are changed to '@'
		*/

		private boolean isValid(int row, int col){ // Checks that all params are met
			return (row >= 0 && col >= 0 && row < maze.length && col < maze[row].length);
		}


		private int solve(int row, int col, int total){
			//automatic animation! You are welcome.
			if(animate){

					clearTerminal();
					System.out.println(this);

					wait(20);
			}

			// System.out.println(maze[row][col] == 'E');

			if (maze[row][col] == 'E') {
				changes = total;
			}

			else{
				maze[row][col] = '@';

				for (int x = 0; x < moves.length; x++){
					int r = row + moves[x][0];
					int c = col + moves[x][1];

					if (isValid(r, c)){
						if (maze[r][c] != '@' && maze[r][c] != '#' && maze[r][c] != '.' && changes == -1){
							solve(r, c, total+1);
						}
					}
				}
				if (changes == -1) maze[row][col] = '.';
			}
			return changes - 1; // to remove counting the E
		}

  }
