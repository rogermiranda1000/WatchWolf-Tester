package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class StonePressurePlate extends Block implements Powerable {
	/*   --- POWERABLE INTERFACE ---   */
	@RelevantBlockData
	private boolean powered;
	@Override
	public boolean isPowered() {
		return this.powered;
	}
	@Override

	public Powerable setPowered(boolean val) {
		StonePressurePlate current = new StonePressurePlate(this);
		current.powered = val;
		return current;
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)(0)); // age
		out.add((byte)(0)); // directionable & orientable
		out.add((byte)0); // reserved
		out.add((byte)(0)); // group_count & delay & eye & hinge & open
		out.add((byte)(0)); // stage
		out.add((byte)(0)); // part & rotation
		out.add((byte)(0)); // note
		out.add((byte)(0)); // mode & leaves & lit & locked
		out.add((byte)0); // reserved
		out.add((byte)((byte)(this.powered ? 0b00000_1_0_0 : 0))); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public StonePressurePlate(short id) {
		super(id, "STONE_PRESSURE_PLATE", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.powered = false;
	}

	public StonePressurePlate(int id) {
		this((short) id);
	}

	private StonePressurePlate(StonePressurePlate old) {
		this(old.id);
		this.powered = old.powered;
	}

}