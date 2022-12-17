package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class TubeCoralFan extends Block {

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
	public TubeCoralFan(short id) {
		super(id, "TUBE_CORAL_FAN");
	}

	public TubeCoralFan(int id) {
		this((short) id);
	}

	private TubeCoralFan(TubeCoralFan old) {
		this(old.id);
	}

}