import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EndGameState extends State implements Comparable {

	HashMap<String, String> grid;
	ArrayList<Integer> stones;
	ArrayList<Integer> wariors;
	int tX;
	int tY;
	byte health;

	int IPosx;
	int IPosy;
	byte stonesCollected;

	String state_rep;

	public EndGameState(String input) {
		grid = new HashMap<String, String>();
		health = 100;
		stones = new ArrayList<Integer>();
		wariors = new ArrayList<Integer>();

		String[] inputSpilt = input.split(";");
		// [m,n] ;
		// [ix,iy] ;
		// [tx,ty];
		// [s1 ,x,s1 y,s2 x,s2 y,s3 x,s3 y,s4 x,s4 y,s5 x,s5 y,s6 x,s6 y];
		// [w1 x,w1 y,w2 x,w2 y,w3 x,w3 y,w4 x,w4 y,w5 x,w5 y]

		Main.rows = Integer.parseInt((inputSpilt[0].split(",")[0])); // [m,n] element 0 ->m
		Main.cols = Integer.parseInt((inputSpilt[0].split(",")[1])); // [m,n] element 1 -> n

		IPosx = Integer.parseInt((inputSpilt[1].split(",")[0]));// [ix,iy] element 0 ->ix
		IPosy = Integer.parseInt((inputSpilt[1].split(",")[1])); // [ix,iy] element 1 ->iy
		grid.put(getKey(IPosx, IPosy), "i");

		String tx = inputSpilt[2].split(",")[0]; // [tx,ty] elemnt 0-> tx
		String ty = inputSpilt[2].split(",")[1]; // [tx,ty] elemnt 1-> ty
		grid.put(tx + "," + ty, "t");
		tX = Integer.parseInt(tx);
		tY = Integer.parseInt(ty);

		// for stones
		for (int i = 0; i < inputSpilt[3].split(",").length; i += 2) {
			String sx = inputSpilt[3].split(",")[i]; // [sx,sy] elemnt 0-> sx
			String sy = inputSpilt[3].split(",")[i + 1]; // [sx,sy] elemnt 1-> sy
			grid.put(sx + "," + sy, "s");
			stones.add(Integer.parseInt(sx));
			stones.add(Integer.parseInt(sy));
		}

		// for warriors
		for (int i = 0; i < inputSpilt[4].split(",").length; i += 2) {
			String wx = inputSpilt[4].split(",")[i]; // [wx,wy] elemnt 0-> wx
			String wy = inputSpilt[4].split(",")[i + 1]; // [wx,wy] elemnt 1-> wy
			grid.put(wx + "," + wy, "w");
			wariors.add(Integer.parseInt(wx));
			wariors.add(Integer.parseInt(wy));
		}
	}

	private void move(int x, int y) {
		/*
		 * @input: x&y: int from -1 to 1
		 */
		grid.remove(IPosx + "," + IPosy);
		IPosx += x;
		IPosy += y;
		String key = IPosx + "," + IPosy;
		if (IPosx >= Main.rows || IPosx < 0) {
			valid = false;
			return;
		}
		if (IPosy >= Main.cols || IPosy < 0) {
			valid = false;
			return;
		}
		if (grid.containsKey(key)) {
			if (grid.get(key) == "w") {
				valid = false;
				return;
			} else if (grid.get(key) == "t") {
				if (stonesCollected == 6) {
					grid.put(key, "it");
				} else {
					valid = false;
					return;
				}
			} else {
				grid.put(key, "is");
			}
		} else {
			grid.put(key, "i");
		}
	}

	private String getKey(int x, int y) {
		return x + "," + y;
	}

	private void checkSurroundings() {
		if (grid.get(getKey(IPosx - 1, IPosy)) == "t") {
			health -= 5;
		}
		if (grid.get(getKey(IPosx + 1, IPosy)) == "t") {
			health -= 5;
		}
		if (grid.get(getKey(IPosx, IPosy - 1)) == "t") {
			health -= 5;
		}
		if (grid.get(getKey(IPosx, IPosy + 1)) == "t") {
			health -= 5;
		}
		if (grid.get(getKey(IPosx - 1, IPosy)) == "w") {
			health -= 1;
		}
		if (grid.get(getKey(IPosx + 1, IPosy)) == "w") {
			health -= 1;
		}
		if (grid.get(getKey(IPosx, IPosy - 1)) == "w") {
			health -= 1;
		}
		if (grid.get(getKey(IPosx, IPosy + 1)) == "w") {
			health -= 1;
		}
	}

	private void kill() {
		valid = false;
		if (grid.containsKey(getKey(IPosx - 1, IPosy)) && grid.get(getKey(IPosx - 1, IPosy)) == "w") {
			grid.remove(getKey(IPosx - 1, IPosy), "w");
			health -= 2;
			valid = true;
			for (int i = 0; (h1 || h2) && i < wariors.size() / 2; i++) {
				if (wariors.get(i * 2) == IPosx - 1 && wariors.get(i * 2 + 1) == IPosy) {
					wariors.set(i * 2, -1);
					wariors.set(i * 2 + 1, -1);
				}
			}
		}
		if (grid.containsKey(getKey(IPosx + 1, IPosy)) && grid.get(getKey(IPosx + 1, IPosy)) == "w") {
			grid.remove(getKey(IPosx + 1, IPosy), "w");
			health -= 2;
			valid = true;
			for (int i = 0; (h1 || h2) && i < wariors.size() / 2; i++) {
				if (wariors.get(i * 2) == IPosx + 1 && wariors.get(i * 2 + 1) == IPosy) {
					wariors.set(i * 2, -1);
					wariors.set(i * 2 + 1, -1);
				}
			}
		}
		if (grid.containsKey(getKey(IPosx, IPosy - 1)) && grid.get(getKey(IPosx, IPosy - 1)) == "w") {
			grid.remove(getKey(IPosx, IPosy - 1), "w");
			health -= 2;
			valid = true;
			for (int i = 0; (h1 || h2) && i < wariors.size() / 2; i++) {
				if (wariors.get(i * 2) == IPosx && wariors.get(i * 2 + 1) == IPosy - 1) {
					wariors.set(i * 2, -1);
					wariors.set(i * 2 + 1, -1);
				}
			}
		}
		if (grid.containsKey(getKey(IPosx, IPosy + 1)) && grid.get(getKey(IPosx, IPosy + 1)) == "w") {
			grid.remove(getKey(IPosx, IPosy + 1), "w");
			health -= 2;
			valid = true;
			for (int i = 0; (h1 || h2) && i < wariors.size() / 2; i++) {
				if (wariors.get(i * 2) == IPosx && wariors.get(i * 2 + 1) == IPosy + 1) {
					wariors.set(i * 2, -1);
					wariors.set(i * 2 + 1, -1);
				}
			}
		}
	}

	private EndGameState(EndGameState parent, String action) {
		super();
		grid = (HashMap<String, String>) parent.grid.clone();
		IPosx = parent.IPosx;
		IPosy = parent.IPosy;
		stonesCollected = parent.stonesCollected;
		health = parent.health;
		valid = true;
		accepted = false;
		this.stones = (ArrayList<Integer>) parent.stones.clone();
		this.wariors = (ArrayList<Integer>) parent.wariors.clone();

		switch (action) {
		case ("collect"):
			if (grid.get(getKey(IPosx, IPosy)) == "is") {
				stonesCollected++;
				grid.put(getKey(IPosx, IPosy), "i");
				for (int i = 0; (h1 || h2) && i < stones.size() / 2; i++) {
					if (stones.get(i * 2) == IPosx && stones.get(i * 2 + 1) == IPosy) {
						stones.set(i * 2, 1 - 1);
						stones.set(i * 2 + 1, -1);
					}
				}
				health -= 3;
			} else {
				valid = false;
			}
			break;
		case ("kill"):
			kill();
			break;
		case ("snap"):
			if (grid.get(getKey(IPosx, IPosy)) == "it" && stonesCollected == 6) {
				grid.put(getKey(IPosx, IPosy), "i");
				accepted = true;
			} else {
				valid = false;
			}
			break;
		case ("up"):
			move(-1, 0);
			break;
		case ("down"):
			move(1, 0);
			break;
		case ("left"):
			move(0, -1);
			break;
		case ("right"):
			move(0, 1);
			break;
		}
		checkSurroundings();
		if (health <= 0) {
			valid = false;
		}
	}

	int getHeur1() {

		int heuReturn = 0;

		double min = Integer.MAX_VALUE;
		int xmin = tX;
		int ymin = tY;
		for (int i = 0; i < stones.size() / 2; i++) {
			double dist = Math.pow(IPosx - stones.get(i * 2), 2.0) + Math.pow(IPosy - stones.get(i * 2 + 1), 2.0);
			if (dist < min && stones.get(i * 2) > 0) {
				min = dist;
				xmin = stones.get(i * 2);
				ymin = stones.get(i * 2 + 1);
			}
		}
		int lowX = xmin < IPosx ? xmin : IPosx;
		int lowY = ymin < IPosy ? ymin : IPosy;
		int hiX = xmin > IPosx ? xmin : IPosx;
		int hiY = ymin > IPosy ? ymin : IPosy;

		for (int i = 0; i < wariors.size() / 2; i++) {
			int x = wariors.get(i * 2);
			int y = wariors.get(i * 2 + 1);
			if (x >= lowX - 1 && x <= hiX + 1 && y >= lowY - 1 && y <= hiY + 1) {
				heuReturn++;
			}
		}
		return 0;
	}

	int getHeur2() {
		if (stonesCollected < 5) {
			return getHeur1() * (6 - stonesCollected);
		} else {
			return getHeur1();
		}
	}

	String getPos(int x, int y) {
		String pos = x + "," + y;
		if (grid.containsValue(pos)) {
			return grid.get(pos);
		} else {
			return null;
		}
	}

	public String getUniqueString() {
//		int w = 0;
		if (this.state_rep == null) {
			this.state_rep = "";
			Iterator hmIterator = grid.entrySet().iterator();
			while (hmIterator.hasNext()) {
				HashMap.Entry mapElement = (HashMap.Entry) hmIterator.next();
				String val = (String) mapElement.getValue();
				String key = (String) mapElement.getKey();
//				if (val == "w") {
//					/// msh 3aref leh bas el mwdoo3 bytawel lamma ba.ignore el kills
////	            	w++;
//					continue;
//				}
				state_rep = state_rep + val + "," + key + ",";
			}
		}
		return state_rep + stonesCollected;// + health;
//		return stonesCollected + IPosx*10 + IPosy*200;// + health*4000 ;//+ wariors.size()*400000;
//		return stonesCollected + grid.hashCode()*10;
	}

	@Override
	public int compareTo(Object other) {
		EndGameState otherState = (EndGameState) other;

		if (otherState.getCostHeu() < getCostHeu()) {
			return 1;
		} else {
			return -1;
		}

	}

	public int getCostHeu() {
		int retVal = 0;
		if (cost)
			retVal += 100 - health;
		if (h1)
			retVal += getHeur1();
		if (h2)
			retVal += getHeur2();

		return retVal;
	}

	@Override
	public EndGameState getState(String action) {

		return new EndGameState(this, action);
	}
}
