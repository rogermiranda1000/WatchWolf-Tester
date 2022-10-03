package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class RedShulkerBox extends Block implements Orientable {
	/*   --- ORIENTABLE INTERFACE ---   */
	private final Map<Orientable.Orientation,Boolean> orientation = new HashMap<>();
	public boolean isSet(Orientable.Orientation o) throws IllegalArgumentException {
		Boolean result = this.orientation.get(o);
		return result;
	}

	public Orientable set(Orientable.Orientation o, boolean value) throws IllegalArgumentException {
		RedShulkerBox current = new RedShulkerBox(this);
		current.orientation.put(o, value);
		return current;
	}

	public Orientable set(Orientable.Orientation o) throws IllegalArgumentException {
		return this.set(o, true);
	}

	public Orientable unset(Orientable.Orientation o) throws IllegalArgumentException {
		return this.set(o, false);
	}

	public Set<Orientable.Orientation> getValidOrientations() {
		return orientation.keySet();
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)0);
		out.add((byte)(((this.orientation.get(Orientable.Orientation.U) == true) ? 0b100000_00 : 0x00) |((this.orientation.get(Orientable.Orientation.D) == true) ? 0b010000_00 : 0x00) |((this.orientation.get(Orientable.Orientation.N) == true) ? 0b001000_00 : 0x00) |((this.orientation.get(Orientable.Orientation.S) == true) ? 0b000100_00 : 0x00) |((this.orientation.get(Orientable.Orientation.E) == true) ? 0b000010_00 : 0x00) |((this.orientation.get(Orientable.Orientation.W) == true) ? 0b000001_00 : 0x00)));
		out.add((byte)0);
		SocketHelper.fill(out, 51);
	}

	/*   --- CONSTRUCTORS ---   */
	public RedShulkerBox(short id) {
		super(id, "RED_SHULKER_BOX");
		this.orientation.put(Orientable.Orientation.E, false);
		this.orientation.put(Orientable.Orientation.D, false);
		this.orientation.put(Orientable.Orientation.U, false);
		this.orientation.put(Orientable.Orientation.S, false);
		this.orientation.put(Orientable.Orientation.N, false);
		this.orientation.put(Orientable.Orientation.W, false);
	}

	public RedShulkerBox(int id) {
		this((short) id);
	}

	private RedShulkerBox(RedShulkerBox old) {
		this(old.id);
		this.orientation.putAll(old.orientation);
	}

}