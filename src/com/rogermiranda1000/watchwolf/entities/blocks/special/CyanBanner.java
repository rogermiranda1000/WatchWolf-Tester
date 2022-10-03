package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CyanBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CyanBanner(short id) {
		super(id, "CYAN_BANNER");
	}

	public CyanBanner(int id) {
		this((short) id);
	}

	private CyanBanner(CyanBanner old) {
		this(old.id);
	}

}