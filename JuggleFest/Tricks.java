package JuggleFest;

public class Tricks{
	private int number; //name of the circuits/ jugglers
	private int h; // requiring hand to eye coordination (H)
	private int e; // endurance (E)
	private int p; // pizzazz (P)
	
	// constructor
	public Tricks(int number, int h, int e, int p){
		this.number = number;
		this.h = h;
		this.e = e;
		this.p = p;
	}
	
	// getters
	public int getNumber(){
		return number;
	}
	public int getH(){
		return h;
	}
	public int getE(){
		return e;
	}
	public int getP(){
		return p;
	}
}
