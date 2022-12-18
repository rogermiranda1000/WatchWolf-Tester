package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class DeadBrainCoralFan extends Block {

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
	public DeadBrainCoralFan(short id) {
		super(id, "DEAD_BRAIN_CORAL_FAN");
	}

	public DeadBrainCoralFan(int id) {
		this((short) id);
	}

	private DeadBrainCoralFan(DeadBrainCoralFan old) {
		this(old.id);
	}

}