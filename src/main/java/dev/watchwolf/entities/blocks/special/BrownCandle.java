package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class BrownCandle extends Block implements Groupable, Ignitable {
	/*   --- GROUPABLE INTERFACE ---   */
	private int groupAmountMinusOne;
	private final int maxGroupAmount;
	@Override

	public Groupable setGroupAmount(int amount) throws IllegalArgumentException {
		if (amount < 1 || amount > this.getMaxGroupAmount()) throw new IllegalArgumentException("BrownCandle block only allows grouping from 1 to " + this.getMaxGroupAmount());
		BrownCandle current = new BrownCandle(this);
		current.groupAmountMinusOne = amount - 1;
		return current;
	}
	@RelevantBlockData
	@Override

	public int getGroupAmount() {
		return this.groupAmountMinusOne + 1;
	}
	@Override

	public int getMaxGroupAmount() {
		return this.maxGroupAmount;
	}
	/*   --- IGNITABLE INTERFACE ---   */
	@RelevantBlockData
	private boolean ignited;
	@Override
	public boolean isIgnited() {
		return this.ignited;
	}
	@Override

	public Ignitable setIgnited(boolean value) {
		BrownCandle current = new BrownCandle(this);
		current.ignited = value;
		return current;
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)(0)); // age
		out.add((byte)(0)); // directionable & orientable
		out.add((byte)0); // reserved
		out.add((byte)((byte)(this.groupAmountMinusOne << 5))); // group_count & delay & eye & hinge & open
		out.add((byte)(0)); // stage
		out.add((byte)(0)); // part & rotation
		out.add((byte)(0)); // note
		out.add((byte)((byte)(this.ignited ? 0b0000_00_1_0 : 0))); // mode & leaves & lit & locked
		out.add((byte)0); // reserved
		out.add((byte)(0)); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public BrownCandle(short id) {
		super(id, "BROWN_CANDLE", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.groupAmountMinusOne = 0; // 0 is 1
		this.maxGroupAmount = 4;
		this.ignited = true;
	}

	public BrownCandle(int id) {
		this((short) id);
	}

	private BrownCandle(BrownCandle old) {
		this(old.id);
		this.groupAmountMinusOne = old.groupAmountMinusOne;
		this.ignited = old.ignited;
	}

}