package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class LightBlueCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public LightBlueCandle(short id) {
		super(id, "LIGHT_BLUE_CANDLE");
	}

	public LightBlueCandle(int id) {
		this((short) id);
	}

	private LightBlueCandle(LightBlueCandle old) {
		this(old.id);
	}

}