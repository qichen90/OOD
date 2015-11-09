package JuggleFest;

class Juggler extends Tricks{
	private int[] preferences;//preference of each juggle
	private int[] matchScores;// the values for each preference
	
	public int matchedscore;// matched score for the juggler to a specific circuit
	public boolean isAssigned; // the flag of whether the juggler is assigned to any circuit
	
	// "super" constructor
	public Juggler(int number, int h, int e, int p, int[] preferences, int[] matchScores){
		super(number, h, e, p);
		this.preferences = preferences;
		this.matchScores = matchScores;
		
		isAssigned = false;
//		matchedCircuit = -1;
		matchedscore = -1;
	}
	
	// getters
	public int[] getPreferences(){
		return preferences;
	}
	public int[] getmatchScores(){
		return matchScores;
	}
	
	// juggler to string
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("J" + super.getNumber() + " ");
		for(int i = 0; i < preferences.length; i++){
			sb.append("C" + preferences[i] + ":" + matchScores[i] + " ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
}