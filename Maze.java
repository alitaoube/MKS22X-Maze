public class Maze{


  public static read(String filename){
		File text = new File(filename);	

		Scanner inf = new Scanner(text);

		while (inf.hastNext()){
			System.out.println(inf.next());
		}
	}
}
