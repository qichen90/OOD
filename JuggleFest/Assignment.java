package JuggleFest;

import java.util.List;
import java.util.Random;

class Assignment{
	private List<Circuit> circuits;
	private List<Juggler> jugglers;
	private int numPerCircuit;
	private int numCircuits;
	private int numJugglers;
	
	// constructor
	public Assignment(List<Circuit> circuits, List<Juggler> jugglers){
		this.circuits = circuits;
		this.jugglers = jugglers;
		numCircuits = circuits.size();
		numJugglers = jugglers.size();
		numPerCircuit = numJugglers / numCircuits;
	}
	
	public void assign(){
		boolean terminate = false;// finish assignment flag
		while(!terminate){
			terminate = true;
			// assign every juggler in the jugglers
			for(Juggler j: jugglers){
				if(!j.isAssigned){
					int[] prefer = j.getPreferences();
					int[] matchScores = j.getmatchScores();
					
					// calculate scores of each preference for the current juggler
					for(int i = 0; i < prefer.length; i++){
						Circuit c = circuits.get(prefer[i]);
						j.matchedscore = matchScores[i];
						
						if(c.jugglers.size() < numPerCircuit){// assign to non-full circuit
							c.add(j);
							j.isAssigned = true;
							break;
						}else if(j.matchedscore > c.minscore){//assign to full circuit with higher score
							Juggler removed = c.jugglers.get(c.jugglers.size() - 1);
							c.jugglers.remove(c.jugglers.size() - 1);
							c.add(j);
							j.isAssigned = true;
							removed.isAssigned = false;
							terminate = false;
							break;
						}
						// cannot assign to current prefered circuit, continue;
					}
				}
				
				// no proper preferred circuit, assign it to a random one
				Random random = new Random(System.currentTimeMillis());
				if(!j.isAssigned){
					while(true){
						int randomCircuit = random.nextInt(numCircuits);// get a random circuit [0, numCircuits)
						Circuit c = circuits.get(randomCircuit);
						//the random circuit may not in the preferences array, recalculate a new score for j and c pair
						j.matchedscore = c.getE() * j.getE() + c.getH() * j.getH() + c.getP() * j.getP();
						
						// assign to random circuit
						if(c.jugglers.size() < numPerCircuit){
							c.add(j);
							j.isAssigned = true;
							break;
						}else if(j.matchedscore > c.minscore){
							Juggler removed = c.jugglers.get(numPerCircuit - 1);
							removed.isAssigned = false;
							c.add(j);
							j.isAssigned = true;
							terminate = false;
							break;
						}
					}
				}
			}
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = circuits.size() - 1; i >= 0; i--){
			Circuit c = circuits.get(i);
			sb.append(c.toString() + "\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}