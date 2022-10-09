package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class AcaciaPressurePlate extends Block {

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
	public AcaciaPressurePlate(short id) {
		super(id, "ACACIA_PRESSURE_PLATE");
	}

	public AcaciaPressurePlate(int id) {
		this((short) id);
	}

	private AcaciaPressurePlate(AcaciaPressurePlate old) {
		this(old.id);
	}

}