/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jf.flippingblocks.spielelemente;

import de.jf.flippingblocks.Enum.EnumColor;
import java.util.ArrayList;

/**
 * 
 * @author Jan
 */
public class Spalte {

	private final int length;
	private final int visible_length;
	private final ArrayList<Block> list;

	public Spalte(int leng) {
		this.visible_length = leng;
		this.length = leng;
		this.list = new ArrayList<Block>();
	}

	public int getVisible_length() {
		return visible_length;
	}

	public int getLength() {
		return length;
	}

	public ArrayList<Block> getSpalte() {
		return list;
	}

	public boolean addBlock(Block b) {
		if (list.size() < length) {
			list.add(b);
			return true;
		}
		return false;
	}

	public boolean removeBlockWithoutMoveing(Block b) {
		if (list.contains(b)) {
			try {
				list.remove(b);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public boolean removeBlockWithoutMoveing(int i) {
		if (list.size() < i && i > 0) {
			try {
				list.remove(i);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public boolean removeBlock(Block b) {
		if (list.contains(b)) {
//			if (schiebeRefBisBlock(b)) {
//				list.remove(b);
//				this.fillWithBlocks();
//				return true;
//			}
			this.newSchiebenUndLoeschen(b);
		}
		return false;
	}

	public boolean removeBlock(int i) {
		if (i >= 0 && i < list.size()) {
			if (schiebeRefBisBlock(list.get(i))) {
				list.remove(i);
				this.fillWithBlocks();
				return true;
			}
		}
		return false;
	}

	public boolean schiebeRefBisBlock(Block b) {
		if (list.contains(b)) {
			int index = list.indexOf(b);
			for (int i = list.size() - 1; i > index; i--) {
				list.get(i).setBtn(list.get(i - 1).getBtn());
			}
			return true;
		}
		return false;
	}

	public boolean newSchiebenUndLoeschen(Block BlockZumLoeschen) {
		try {
			if (list.contains(BlockZumLoeschen)) {
				int index = list.indexOf(BlockZumLoeschen);
				int color = (int) ((Math.random() * EnumColor.values().length - 1));
				Block neuerBlock = new Block(EnumColor.values()[color], list.get(list.size() - 1).getBtn());
				schiebeRefBisBlock(BlockZumLoeschen);
				list.remove(index);
				this.addBlock(neuerBlock);
				return true;
			}
		} catch (Exception e) {

		}

		return false;
	}

	// F�llt das Spielfeld wieder mit Bl�cken auf
	// TODO Referenzen f�r Buttons auf GUI
	public void fillWithBlocks() {
		while (list.size() < length) {
			int color = (int) ((Math.random() * EnumColor.values().length - 1));
			Block b = new Block(EnumColor.values()[color], null);
			list.add(b);
		}

	}
}
