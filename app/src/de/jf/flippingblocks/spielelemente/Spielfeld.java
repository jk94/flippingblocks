package de.jf.flippingblocks.spielelemente;

import java.util.ArrayList;

import de.jf.flippingblocks.Control;
import de.jf.flippingblocks.Enum.EnumColor;
import de.jf.flippingblocks.graphics.BlockPanel;

public class Spielfeld {

	private ArrayList<Spalte> collist;
	private final int borderX, borderY;
	private Control cnt;

	public Spielfeld(int sizex, int sizey, Control control) {
		borderX = sizex;
		borderY = sizey;
		this.cnt = control;
		collist = new ArrayList<Spalte>(borderX);
		for (int i = 0; i < borderX; i++) {
			collist.add(new Spalte(borderY));
			for (int t = 0; t < borderY; t++) {
				int color = (int) ((Math.random() * EnumColor.values().length - 1));
				// Block b = new Block(EnumColor.values()[color], null);
				Block b = new Block(EnumColor.values()[color], null);
				collist.get(i).addBlock(b);
			}
		}
		for (int i = 0; i < collist.size(); i++) {
			ArrayList<Block> sp = collist.get(i).getSpalte();
			for (int t = sp.size() - 1; t >= 0; t--) {

				sp.get(t).setBtn(cnt.getGameGui().addBlockPanel(sp.get(t).getColor()));
			}
		}
	}

	// Gibt den Block zur�ck, zu dem der (geklickte) Button (Referenz)
	// geh�rt.
	public Block getBlockByBtnRef(BlockPanel b) {
		Block erg = null;
		boolean found = false;
		for (Spalte y : collist) {
			for (Block bl : y.getSpalte()) {
				if (bl.getBtn().equals(b)) {
					erg = bl;
					found = true;
					break;
				}
			}
			if (found) {
				break;
			}
		}
		return erg;
	}

	// �berpr�ft, ob Block die Richtige Farbe hat (zum abbauen) und f�gt
	// ihn in Liste hinzu
	// �berpr�ft die anliegenden Bl�cke auf passende Farbe -> Rekursiv
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

	// Pr�ft ob Block in einer Liste schon vorhanden -> Verwendung, ob Block
	// zum zerst�ren schon in der Liste.
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
	// Ist die angegebene Position au�erhalb des Bereichs gibt es >null<
	// zur�ck.
	public Block getBlockNext(Block ausgangsBlock, int xRichtung, int yRichtung) {
		Block erg = null;
		// Find Block
		int x = 0, y = 0;
		boolean found = false;
		try {
			for (int i = 0; i < collist.size(); i++) {
				Spalte sp = collist.get(i);
				for (int n = 0; n < sp.getLength() - 1; n++) {
					if (sp.getSpalte().get(n).equals(ausgangsBlock)) {
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
			if (x < collist.size() && x >= 0 && y >= 0
					&& y < collist.get(0).getLength()) {
				erg = collist.get(x).getSpalte().get(y);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return erg;
	}

	// Zerst�rt die (gefundenen) Bl�cke/Bei Spielende k�nnten alle
	// Bl�cke
	// zerst�rt werden.
	// TODO (-> Animation?)
	public void destroyBlocks(ArrayList<Block> list) {
		for (Block b : list) {
			boolean found = false;
			for (Spalte spalte : this.collist) {
				for (Block b2 : spalte.getSpalte()) {
					if (b2.equals(b)) {
						spalte.removeBlock(b);
						break;
					}
				}
				if (found) {
					break;
				}
			}
		}
	}

	public ArrayList<Spalte> getBlocks() {
		return collist;
	}
}
