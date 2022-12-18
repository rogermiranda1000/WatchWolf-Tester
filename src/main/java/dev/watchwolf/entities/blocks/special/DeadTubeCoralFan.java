package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class DeadTubeCoralFan extends Block {

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
	public DeadTubeCoralFan(short id) {
		super(id, "DEAD_TUBE_CORAL_FAN");
	}

	public DeadTubeCoralFan(int id) {
		this((short) id);
	}

	private DeadTubeCoralFan(DeadTubeCoralFan old) {
		this(old.id);
	}

}