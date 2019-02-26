import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze{
	public static void main(String[] args){
		readArr(args[0]);	
	}

  public static void read(String filename){
	try{
		File text = new File(filename);	

		Scanner inf = new Scanner(text);

		while (inf.hasNext()){
			System.out.println(inf.next());
		}
	}
	catch(FileNotFoundException e){
		System.out.println("Gotta catch 'em all");
	}
	}

	public static void readArr(String filename){
	try{
		File text = new File(filename);	

		Scanner inf = new Scanner(text);

		while (inf.hasNextLine()){
			System.out.println(inf.nextLine());
		}
	}
	catch(FileNotFoundException e){
		System.out.println("Gotta catch 'em all");
	}
	}

}
