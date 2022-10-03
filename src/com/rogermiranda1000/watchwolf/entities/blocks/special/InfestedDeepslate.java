package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class InfestedDeepslate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public InfestedDeepslate(short id) {
		super(id, "INFESTED_DEEPSLATE");
	}

	public InfestedDeepslate(int id) {
		this((short) id);
	}

	private InfestedDeepslate(InfestedDeepslate old) {
		this(old.id);
	}

}