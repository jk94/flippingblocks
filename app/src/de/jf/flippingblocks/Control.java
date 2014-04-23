package de.jf.flippingblocks;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import de.jf.flippingblocks.Enum.EnumTimerStatus;
import de.jf.flippingblocks.gameGui.GameGui;
import de.jf.flippingblocks.graphics.BlockPanel;
import de.jf.flippingblocks.spielelemente.SpielfeldAnders;

public class Control {
	/**
	 * 
	 */

	transient SpielfeldAnders feld;
	transient int col;
	transient int row;
	public static int id = 0;
	public final int id2;
	private GameGui gui;
	private Timer timer = null;
	private int interval = 100;
	private int time = 0;
	private EnumTimerStatus timerstatus = EnumTimerStatus.CANCELED;

	public Control(GameGui gui, int col, int row) {
		this.id2 = id;
		id++;

		this.col = col;
		this.row = row;
		this.gui = gui;
		feld = new SpielfeldAnders(this, col, row);		

	}

	public GameGui getGameGui() {
		return this.gui;
	}

	public void action(BlockPanel panel, Context context) {
		// ArrayList<Block> liste = new ArrayList<Block>();
		// feld.CheckMate(liste, feld.getBlockByBtnRef(panel),
		// feld.getBlockByBtnRef(panel).getColor());
		// feld.destroyBlocks(liste);

		feld.click(panel);

	}

	public void startTimer() {
		if (timer != null) {
			stopTimer();
			timer = new Timer();
			timer.schedule(new TimeTask(), 0, interval);
			timerstatus = EnumTimerStatus.RUNNING;
		}
	}

	public void stopTimer() {
		if (timer != null) {
			timer.cancel();
			timerstatus = EnumTimerStatus.CANCELED;
		}
	}

	public void toogleTimerStatus() {
		switch (timerstatus) {
		case CANCELED:
			timer = new Timer();
			timer.schedule(new TimeTask(), 0, interval);
			timerstatus = EnumTimerStatus.RUNNING;
			break;
		case RUNNING:
			timer.cancel();
			timerstatus = EnumTimerStatus.PAUSED;
			break;
		case PAUSED:
			timer = new Timer();
			timer.schedule(new TimeTask(), 0, interval);
			timerstatus = EnumTimerStatus.RUNNING;
			break;
		default:
			break;
		}
	}

	public void GameOver() {
		stopTimer();

	}

	public void addScore(int size) {
		double multiplier = 1.5;

		int score = (int) Math.pow(size, multiplier);
		gui.setCurrenScore(score);

	}

	class TimeTask extends TimerTask {
		@Override
		public void run() {
			// TODO Hier darf dann die Zeit hochgezählt werden
			time++;
			gui.setTimeBar(time);
		}
	}

}
