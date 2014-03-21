package de.jf.flippingblocks.spielelemente;

import java.util.ArrayList;

import de.jf.flippingblocks.graphics.BlockPanel;

public class Spielfeld {

	ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<Block>> blocklist;
	private int borderX, borderY;

	public Spielfeld(int sizex, int sizey) {
		borderX = sizex;
		borderY = sizey;
		blocklist = new ArrayList<ArrayList<Block>>(borderX);
		for (ArrayList<Block> a : blocklist) {
			a = new ArrayList<Block>(borderY);
		}
	}

	// finding Block by the Reference of its (clicked) Button
	public Block getBlockByBtnRef(BlockPanel b) {
		Block erg = null;
		for (ArrayList<Block> y : blocklist) {
			for (Block bl : y) {
				if (bl.getBtn().equals(b)) {
					erg = bl;
					break;
				}
			}
			if (!erg.equals(null)) {
				break;
			}
		}
		return erg;
	}

	// check for the apposing Blocks
	public ArrayList<Block> checkMates(Block b, Block callingBlock) {
		ArrayList<Block> erg = new ArrayList<Block>();
		int x = -1, y = -1;
		boolean posfound = false;
		for (int i = 0; i < blocklist.size(); i++) {
			for (int i2 = 0; i2 < blocklist.get(i).size(); i2++) {
				if (blocklist.get(i).get(i2).equals(b)) {
					x = i;
					y = i2;
					posfound = true;
					break;
				}
			}
			if (posfound) {
				break;
			}
		}
		ArrayList<Block> temp = new ArrayList<Block>();

		if (x > 0) {
			if (BlockAccording(blocklist.get(x - 1).get(y), b, callingBlock)) {
				temp.add(blocklist.get(x - 1).get(y));
			}
		}
		if (x < borderX) {
			if (BlockAccording(blocklist.get(x + 1).get(y), b, callingBlock)) {
				temp.add(blocklist.get(x + 1).get(y));
			}
		}
		if (y > 0) {
			if (BlockAccording(blocklist.get(x).get(y - 1), b, callingBlock)) {
				temp.add(blocklist.get(x).get(y - 1));
			}

		}
		if (y < 0) {
			if (BlockAccording(blocklist.get(x).get(y + 1), b, callingBlock)) {
				temp.add(blocklist.get(x).get(y + 1));
			}
		}

		for (Block blo : temp) {
			ArrayList<Block> temp2 = checkMates(blo, b);
			for (int i = 0; i < temp2.size(); i++) {
				for (int i2 = 0; i2 < temp.size(); i2++) {
					if (temp2.get(i).equals(temp.get(i2))) {
						temp.remove(i2);
					}
				}

			}
		}

		return erg;
	}

	// Check if the Block beside is same Color & not the same which called
	private boolean BlockAccording(Block newBlock, Block actualBlock,
			Block calledBlock) {
		boolean erg = true;

		if (newBlock.getColor() != actualBlock.getColor()) {
			erg = false;
		}
		if (newBlock.equals(calledBlock)) {
			erg = false;
		}

		return erg;
	}

}


