package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BrainCoralFan extends Block {

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)0);
		out.add((byte)0);
		out.add((byte)0);
		SocketHelper.fill(out, 51);
	}

	/*   --- CONSTRUCTORS ---   */
	public BrainCoralFan(short id) {
		super(id, "BRAIN_CORAL_FAN");
	}

	public BrainCoralFan(int id) {
		this((short) id);
	}

	private BrainCoralFan(BrainCoralFan old) {
		this(old.id);
	}

}