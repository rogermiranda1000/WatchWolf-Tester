package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class JunglePressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public JunglePressurePlate(short id) {
		super(id, "JUNGLE_PRESSURE_PLATE");
	}

	public JunglePressurePlate(int id) {
		this((short) id);
	}

	private JunglePressurePlate(JunglePressurePlate old) {
		this(old.id);
	}

}