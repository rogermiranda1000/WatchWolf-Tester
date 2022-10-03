package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class YellowCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public YellowCandle(short id) {
		super(id, "YELLOW_CANDLE");
	}

	public YellowCandle(int id) {
		this((short) id);
	}

	private YellowCandle(YellowCandle old) {
		this(old.id);
	}

}