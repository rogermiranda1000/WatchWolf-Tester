package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedAcaciaLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public StrippedAcaciaLog(short id) {
		super(id, "STRIPPED_ACACIA_LOG");
	}

	public StrippedAcaciaLog(int id) {
		this((short) id);
	}

	private StrippedAcaciaLog(StrippedAcaciaLog old) {
		this(old.id);
	}

}