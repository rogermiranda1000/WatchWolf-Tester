package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class NoteBlock extends Block implements Playable, Powerable {
	/*   --- PLAYABLE INTERFACE ---   */
	@RelevantBlockData
	private int note;
	@Override

	public Playable setNote(int note) throws IllegalArgumentException {
		if (note < 0 || note > 24) throw new IllegalArgumentException("NoteBlock block allows notes from 0 to 24");
		NoteBlock current = new NoteBlock(this);
		current.note = note;
		return current;
	}
	@Override

	public int getNote() {
		return this.note;
	}
	/*   --- POWERABLE INTERFACE ---   */
	@RelevantBlockData
	private boolean powered;
	@Override
	public boolean isPowered() {
		return this.powered;
	}
	@Override

	public Powerable setPowered(boolean val) {
		NoteBlock current = new NoteBlock(this);
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
		out.add((byte)((byte)(this.note))); // note
		out.add((byte)(0)); // mode & leaves & lit & locked
		out.add((byte)0); // reserved
		out.add((byte)((byte)(this.powered ? 0b00000_1_0_0 : 0))); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public NoteBlock(short id) {
		super(id, "NOTE_BLOCK", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.note = 0;
		this.powered = false;
	}

	public NoteBlock(int id) {
		this((short) id);
	}

	private NoteBlock(NoteBlock old) {
		this(old.id);
		this.note = old.note;
		this.powered = old.powered;
	}

}