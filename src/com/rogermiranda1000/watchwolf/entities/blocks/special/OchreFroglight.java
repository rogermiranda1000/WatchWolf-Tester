package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class OchreFroglight extends Block {

	/*   --- CONSTRUCTORS ---   */
	public OchreFroglight(short id) {
		super(id, "OCHRE_FROGLIGHT");
	}

	public OchreFroglight(int id) {
		this((short) id);
	}

	private OchreFroglight(OchreFroglight old) {
		this(old.id);
	}

}