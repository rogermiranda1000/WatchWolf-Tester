package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class GrayCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public GrayCandle(short id) {
		super(id, "GRAY_CANDLE");
	}

	public GrayCandle(int id) {
		this((short) id);
	}

	private GrayCandle(GrayCandle old) {
		this(old.id);
	}

}