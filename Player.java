
public class Player {
	private String name;
	int dx, dy;
	private int add;
	private int sub;
	private int mult;
	private int div;
	public Player(String name, int add, int sub, int mult, int div) {
		super();
		this.name = name;
		this.add = add;
		this.sub = sub;
		this.mult = mult;
		this.div = div;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAdd() {
		return add;
	}
	public void setAdd(int add) {
		this.add = add;
	}
	public int getSub() {
		return sub;
	}
	public void setSub(int sub) {
		this.sub = sub;
	}
	public int getMult() {
		return mult;
	}
	public void setMult(int mult) {
		this.mult = mult;
	}
	public int getDiv() {
		return div;
	}
	public void setDiv(int div) {
		this.div = div;
	}
	public void moveRight(){
		
	}
	
}
