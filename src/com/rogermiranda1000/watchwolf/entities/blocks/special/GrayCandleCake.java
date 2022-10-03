package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class GrayCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public GrayCandleCake(short id) {
		super(id, "GRAY_CANDLE_CAKE");
	}

	public GrayCandleCake(int id) {
		this((short) id);
	}

	private GrayCandleCake(GrayCandleCake old) {
		this(old.id);
	}

}