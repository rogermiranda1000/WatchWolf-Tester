package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class NoteBlock extends Block {

	/*   --- CONSTRUCTORS ---   */
	public NoteBlock(int id) {
		super(id, "NoteBlock");
	}

	private NoteBlock(NoteBlock old) {
		this(old.id);
	}

}