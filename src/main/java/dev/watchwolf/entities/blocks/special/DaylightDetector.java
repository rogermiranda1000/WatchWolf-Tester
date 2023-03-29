package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class DaylightDetector extends Block implements Invertable {
	/*   --- INVERTABLE INTERFACE ---   */
	@RelevantBlockData
	private boolean inverted;
	@Override
	public boolean isInverted() {
		return this.inverted;
	}
	@Override

	public Invertable setInvert(boolean val) {
		DaylightDetector current = new DaylightDetector(this);
		current.inverted = val;
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
		out.add((byte)((byte)(this.inverted ? 0b00000_0_1_0 : 0))); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public DaylightDetector(short id) {
		super(id, "DAYLIGHT_DETECTOR", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.inverted = false;
	}

	public DaylightDetector(int id) {
		this((short) id);
	}

	private DaylightDetector(DaylightDetector old) {
		this(old.id);
		this.inverted = old.inverted;
	}

}