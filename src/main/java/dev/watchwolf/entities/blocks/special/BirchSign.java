package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class BirchSign extends Block implements Rotable {
	/*   --- ROTABLE INTERFACE ---   */
	@RelevantBlockData
	private Rotable.Rotation rotation;
	@Override

	public Rotable setRotation(Rotable.Rotation rotation) {
		BirchSign current = new BirchSign(this);
		current.rotation = rotation;
		return current;
	}
	@Override

	public Rotable.Rotation getRotation() {
		return this.rotation;
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
		out.add((byte)((byte)(this.rotation.ordinal()))); // part & rotation
		out.add((byte)(0)); // note
		out.add((byte)(0)); // mode & leaves & lit & locked
		out.add((byte)0); // reserved
		out.add((byte)(0)); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public BirchSign(short id) {
		super(id, "BIRCH_SIGN", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.rotation = Rotable.Rotation.S;
	}

	public BirchSign(int id) {
		this((short) id);
	}

	private BirchSign(BirchSign old) {
		this(old.id);
		this.rotation = old.rotation;
	}

}