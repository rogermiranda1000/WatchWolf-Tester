package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class OakLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public OakLog(short id) {
		super(id, "OAK_LOG");
	}

	public OakLog(int id) {
		this((short) id);
	}

	private OakLog(OakLog old) {
		this(old.id);
	}

}