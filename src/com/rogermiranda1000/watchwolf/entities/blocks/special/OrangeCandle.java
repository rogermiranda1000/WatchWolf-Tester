package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class OrangeCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public OrangeCandle(short id) {
		super(id, "ORANGE_CANDLE");
	}

	public OrangeCandle(int id) {
		this((short) id);
	}

	private OrangeCandle(OrangeCandle old) {
		this(old.id);
	}

}