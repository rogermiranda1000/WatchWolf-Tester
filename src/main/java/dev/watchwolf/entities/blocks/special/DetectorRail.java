package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class DetectorRail extends Block implements Orientable, Sectionable, Powerable {
	/*   --- ORIENTABLE INTERFACE ---   */
	@RelevantBlockData
	private final Map<Orientable.Orientation,Boolean> orientation = new HashMap<>();
	@Override
	public boolean isOrientationSet(Orientable.Orientation o) throws IllegalArgumentException {
		Boolean result = this.orientation.get(o);
		if (result == null) throw new IllegalArgumentException("DetectorRail block doesn't contain orientation " + o.name());
		return result;
	}
	@Override

	public Orientable setOrientation(Orientable.Orientation o, boolean value) throws IllegalArgumentException {
		if (!this.orientation.containsKey(o)) throw new IllegalArgumentException("DetectorRail block doesn't contain orientation " + o.name());
		DetectorRail current = new DetectorRail(this);
		current.orientation.put(o, value);
		return current;
	}
	@Override

	public Set<Orientable.Orientation> getValidOrientations() {
		return this.orientation.keySet();
	}
	/*   --- DIRECTIONABLE INTERFACE ---   */
	@RelevantBlockData
	private Sectionable.Section section;
	private final HashSet<Sectionable.Section> allowedSections = new HashSet<>();
	@Override
	public Sectionable.Section getSection() {
		return this.section;
	}
	@Override

	public Sectionable setSection(Sectionable.Section section) throws IllegalArgumentException {
		if (!this.allowedSections.contains(section)) throw new IllegalArgumentException("DetectorRail block doesn't allow the section " + section.name());
		DetectorRail current = new DetectorRail(this);
		current.section = section;
		return current;
	}
	@Override
	public Set<Sectionable.Section> getValidSections() {
		return (HashSet)this.allowedSections.clone();
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
		DetectorRail current = new DetectorRail(this);
		current.powered = val;
		return current;
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)(0)); // age
		out.add((byte)((byte)((Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.U)) ? 0b00_000001 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.D)) ? 0b00_000010 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.N)) ? 0b00_000100 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.S)) ? 0b00_001000 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.E)) ? 0b00_010000 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.W)) ? 0b00_100000 : 0x00)))); // directionable & orientable
		out.add((byte)0); // reserved
		out.add((byte)(0)); // group_count & delay & eye & hinge & open
		out.add((byte)(0)); // stage
		out.add((byte)((byte)(this.section.getSendData() << 4))); // part & rotation
		out.add((byte)(0)); // note
		out.add((byte)(0)); // mode & leaves & lit & locked
		out.add((byte)0); // reserved
		out.add((byte)((byte)(this.powered ? 0b00000_1_0_0 : 0))); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public DetectorRail(short id) {
		super(id, "DETECTOR_RAIL", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.orientation.put(Orientable.Orientation.E, false);
		this.orientation.put(Orientable.Orientation.W, false);
		this.orientation.put(Orientable.Orientation.S, false);
		this.orientation.put(Orientable.Orientation.U, false);
		this.orientation.put(Orientable.Orientation.N, false);
		this.allowedSections.add(Sectionable.Section.INNER_RIGHT);
		this.allowedSections.add(Sectionable.Section.OUTER_RIGHT);
		this.allowedSections.add(Sectionable.Section.OUTER_LEFT);
		this.allowedSections.add(Sectionable.Section.STRAIGHT);
		this.allowedSections.add(Sectionable.Section.INNER_LEFT);
		this.section = Sectionable.Section.INNER_RIGHT;
		this.powered = false;
	}

	public DetectorRail(int id) {
		this((short) id);
	}

	private DetectorRail(DetectorRail old) {
		this(old.id);
		this.orientation.putAll(old.orientation);
		this.section = old.section;
		this.powered = old.powered;
	}

}