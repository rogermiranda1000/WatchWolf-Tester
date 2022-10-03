package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CaveVinesPlant extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CaveVinesPlant(short id) {
		super(id, "CAVE_VINES_PLANT");
	}

	public CaveVinesPlant(int id) {
		this((short) id);
	}

	private CaveVinesPlant(CaveVinesPlant old) {
		this(old.id);
	}

}