package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class DeadBubbleCoralFan extends Block {

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
	public DeadBubbleCoralFan(short id) {
		super(id, "DEAD_BUBBLE_CORAL_FAN");
	}

	public DeadBubbleCoralFan(int id) {
		this((short) id);
	}

	private DeadBubbleCoralFan(DeadBubbleCoralFan old) {
		this(old.id);
	}

}