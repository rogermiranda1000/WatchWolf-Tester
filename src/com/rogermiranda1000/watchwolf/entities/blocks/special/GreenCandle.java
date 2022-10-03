package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class GreenCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public GreenCandle(short id) {
		super(id, "GREEN_CANDLE");
	}

	public GreenCandle(int id) {
		this((short) id);
	}

	private GreenCandle(GreenCandle old) {
		this(old.id);
	}

}