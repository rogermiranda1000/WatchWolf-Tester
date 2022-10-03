package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class LightGrayCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public LightGrayCandleCake(short id) {
		super(id, "LIGHT_GRAY_CANDLE_CAKE");
	}

	public LightGrayCandleCake(int id) {
		this((short) id);
	}

	private LightGrayCandleCake(LightGrayCandleCake old) {
		this(old.id);
	}

}