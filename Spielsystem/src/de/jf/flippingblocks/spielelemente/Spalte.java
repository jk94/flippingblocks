/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jf.flippingblocks.spielelemente;

import java.util.ArrayList;

/**
 *
 * @author Jan
 */
public class Spalte {

    private final int length;
    private final ArrayList<Block> list;

    public Spalte(int leng) {
        this.length = leng + 1;
        this.list = new ArrayList<>();
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
        return false;
    }

    public boolean schiebeRefBisBlock(Block b) {
        if (list.contains(b)) {
            int index = list.indexOf(b);
            for (int i = list.size() - 1; i > index; i--) {
                list.get(i).setBtn(list.get(i - 1).getBtn());
            }
        }
        return false;
    }
}
