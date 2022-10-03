package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PetrifiedOakSlab extends Block implements Orientable {
	/*   --- ORIENTABLE INTERFACE ---   */
	private final Map<Orientable.Orientation,Boolean> orientation = new HashMap<>();
	public boolean isSet(Orientable.Orientation o) throws IllegalArgumentException {
		Boolean result = this.orientation.get(o);
		if (result == null) throw new IllegalArgumentException("PetrifiedOakSlab block doesn't contain orientation " + o.name());
		return result;
	}

	public Orientable set(Orientable.Orientation o, boolean value) throws IllegalArgumentException {
		if (!this.orientation.containsKey(o)) throw new IllegalArgumentException("PetrifiedOakSlab block doesn't contain orientation " + o.name());
		PetrifiedOakSlab current = new PetrifiedOakSlab(this);
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
	public PetrifiedOakSlab(short id) {
		super(id, "PETRIFIED_OAK_SLAB");
		this.orientation.put(Orientable.Orientation.D, false);
		this.orientation.put(Orientable.Orientation.U, false);
	}

	public PetrifiedOakSlab(int id) {
		this((short) id);
	}

	private PetrifiedOakSlab(PetrifiedOakSlab old) {
		this(old.id);
		this.orientation.putAll(old.orientation);
	}

}