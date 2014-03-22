package de.jf.flippingblocks.spielelemente;

import java.util.ArrayList;

import de.jf.flippingblocks.Enum.EnumColor;
import de.jf.flippingblocks.graphics.BlockPanel;

public class Spielfeld {

	ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<Block>> blocklist;
	private int borderX, borderY;

	public Spielfeld(int sizex, int sizey) {
		borderX = sizex;
		borderY = sizey;
		blocklist = new ArrayList<ArrayList<Block>>(borderX);
		for (int i = 0; i < borderX; i++) {
			blocklist.add(new ArrayList<Block>(borderY));
			for (int t = 0; t < borderY; t++) {
				int color = (int) (1 + (Math.random() * 4));
				Block b = new Block(EnumColor.values()[color], null);
				blocklist.get(i).add(b);
			}
		}
	}

	//Gibt den Block zurück, zu dem der (geklickte) Button (Referenz) gehört.
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

	//Überprüft, ob Block die Richtige Farbe hat (zum abbauen) und fügt ihn in Liste hinzu
	//Überprüft die anliegenden Blöcke auf passende Farbe -> Rekursiv
	public ArrayList<Block> CheckMate(ArrayList<Block> ergebnisliste,
			Block pruefblock, EnumColor color) {

		if (!schonVorhanden(ergebnisliste, pruefblock)
				&& pruefblock.getColor().equals(color)) {
			ergebnisliste.add(pruefblock);
			if (!schonVorhanden(ergebnisliste, getBlockNext(pruefblock, 1, 0))) {
				CheckMate(ergebnisliste, getBlockNext(pruefblock, 1, 0), color);
			}
			if (!schonVorhanden(ergebnisliste, getBlockNext(pruefblock, -1, 0))) {
				CheckMate(ergebnisliste, getBlockNext(pruefblock, -1, 0), color);
			}
			if (!schonVorhanden(ergebnisliste, getBlockNext(pruefblock, 0, 1))) {
				CheckMate(ergebnisliste, getBlockNext(pruefblock, 0, 1), color);
			}
			if (!schonVorhanden(ergebnisliste, getBlockNext(pruefblock, 0, -1))) {
				CheckMate(ergebnisliste, getBlockNext(pruefblock, 0, -1), color);
			}
		}
		return ergebnisliste;
	}

	// Prüft ob Block in einer Liste schon vorhanden -> Verwendung, ob Block zum zerstören schon in der Liste.
	public boolean schonVorhanden(ArrayList<Block> ergebnisliste,
			Block pruefblock) {
		try {
			pruefblock.getColor();
		} catch (NullPointerException e) {
			return true;
		}
		boolean schonvorhanden = false;
		for (Block b : ergebnisliste) {
			if (b.equals(pruefblock)) {
				schonvorhanden = true;
				break;
			}
		}
		return schonvorhanden;
	}

	// Gibt einen Block in x/y-Richtung vom angegebenen Block an. (Akzeptiert
	// auch Minuswerte)
	// Ist die angegebene Position außerhalb des Bereichs gibt es >null< zurück.
	public Block getBlockNext(Block ausgangsBlock, int xRichtung, int yRichtung) {
		Block erg = null;
		// Find Block
		int x = 0, y = 0;
		boolean found = false;
		for (int i = 0; i < blocklist.size(); i++) {
			for (int n = 0; n < blocklist.get(i).size(); n++) {
				if (blocklist.get(i).get(n).equals(ausgangsBlock)) {
					x = i;
					y = n;
					found = true;
					break;
				}
			}
			if (found) {
				break;
			}
		}
		x = x + xRichtung;
		y = y + yRichtung;
		if (x < 0) {
			x = 0;
		}
		if (x < blocklist.size() && x >= 0 && y >= 0
				&& y < blocklist.get(0).size()) {
			erg = blocklist.get(x).get(y);
		}

		return erg;
	}

	// Zerstört die (gefundenen) Blöcke/Bei Spielende könnten alle Blöcke
	// zerstört werden.
	// TODO (-> Animation?)
	public void destroyBlocks(ArrayList<Block> list) {
		for (Block b : list) {
			boolean found = false;
			for (ArrayList<Block> b1 : this.blocklist) {
				for (Block b2 : b1) {
					if (b2.equals(b)) {
						b1.remove(b);
						break;
					}
				}
				if (found) {
					break;
				}
			}
		}
	}

	// Füllt das Spielfeld wieder mit Blöcken auf
	//TODO Referenzen für Buttons auf GUI
	public void fillWithBlocks() {
		for (ArrayList<Block> list : blocklist) {
			while (list.size() < borderY) {
				int color = (int) (1 + (Math.random() * 4));
				Block b = new Block(EnumColor.values()[color], null);
				list.add(b);
			}
		}
	}
}
