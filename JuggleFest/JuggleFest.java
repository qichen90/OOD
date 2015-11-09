package JuggleFest;
/*
 * Juggle Fest was implemented in Java, October 19th, Qi Chen
 */
import java.util.*;
import java.io.*;

public class JuggleFest {

	public static void main(String[] args) throws IOException{
		File data = new File("input.txt");
		Scanner input = new Scanner(data);
		
		List<Circuit> circuits = new ArrayList<Circuit>();
		List<Juggler> jugglers = new ArrayList<Juggler>();
		// get input
		System.out.println("Read data from the input file...");
		try{
			while(input.hasNextLine()){
				String[] strs = input.nextLine().split(" ");
				
				if(strs[0].equals("C")){//get all circuits
					int number = Integer.valueOf(strs[1].substring(1));
					int h = Integer.valueOf(strs[2].substring(2));
					int e = Integer.valueOf(strs[3].substring(2));
					int p = Integer.valueOf(strs[4].substring(2));
					Circuit c = new Circuit(number, h, e, p);
					circuits.add(c);
				}else if(strs[0].equals("J")){//get all jugglers
					int number = Integer.valueOf(strs[1].substring(1));
					int h = Integer.valueOf(strs[2].substring(2));
					int e = Integer.valueOf(strs[3].substring(2));
					int p = Integer.valueOf(strs[4].substring(2));
					
					String[] prefers = strs[5].split(",");
					int[] preferences = new int[prefers.length];
					int[] matchedscores = new int[prefers.length];
					for(int i = 0; i < prefers.length; i++){
						preferences[i] = Integer.valueOf(prefers[i].substring(1));
						Circuit c = circuits.get(preferences[i]);
						matchedscores[i] = c.getH() * h + c.getP() * p + c.getE() * e;
					}
					Juggler j = new Juggler(number, h, e, p, preferences, matchedscores);
					jugglers.add(j);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			input.close();
		}
		
		// assign the circuits with jugglers
		Assignment action = new Assignment(circuits, jugglers);
		System.out.println("Assign the circuits with jugglers...");
		action.assign();
		// output the result to the text file
		System.out.println( "Writing the result to the output text file..." );
		Writer output = new OutputStreamWriter(new FileOutputStream("output.txt"));
		try {
			output.write(action.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			output.close();
		}
//		Circuit c1970 = circuits.get(1970);
//		System.out.println(c1970.sum());
	}	
}
