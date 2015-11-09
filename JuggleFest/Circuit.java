package JuggleFest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Circuit extends Tricks{
	public List<Juggler> jugglers;
	public int minscore; // minimum score in the circuit
	// "super" constructor
	public Circuit(int number, int h, int e, int p){
		super(number, h, e, p);
		jugglers = new ArrayList<Juggler>();
		minscore = Integer.MAX_VALUE;
	}
	//add new juggler to the circuit
	public void add(Juggler j){
		jugglers.add(j);
		sort();
		minscore = jugglers.get(jugglers.size() - 1).matchedscore;
	}
	// sort the jugglers in the circuit in descending order
	public void sort(){
		Collections.sort(jugglers, new Comparator<Juggler>(){
			public int compare(Juggler j1, Juggler j2){
				return j2.matchedscore - j1.matchedscore;
			}
		});
	}
	// circuit to string
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("C" + super.getNumber() + " ");
		
		for(Juggler j: jugglers){
			sb.append(j.toString() + ", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	// the sum of the names of the jugglers that are assigned to circuit C1970
	public int sum(){
		int res = 0;
		for(Juggler j: jugglers){
			res += j.getNumber();
		}
		return res;
	}
}