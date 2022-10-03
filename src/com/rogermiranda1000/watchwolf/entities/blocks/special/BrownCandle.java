package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BrownCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BrownCandle(short id) {
		super(id, "BROWN_CANDLE");
	}

	public BrownCandle(int id) {
		this((short) id);
	}

	private BrownCandle(BrownCandle old) {
		this(old.id);
	}

}