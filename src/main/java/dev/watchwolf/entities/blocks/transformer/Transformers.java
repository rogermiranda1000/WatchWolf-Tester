package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;

import java.util.ArrayList;
import java.util.Map;

public class Transformers {
    private static ArrayList<AbstractTransformer<?,?>> transformers = null;

    public static ArrayList<AbstractTransformer<?,?>> getTransformers() {
        if (Transformers.transformers == null) {
            ArrayList<AbstractTransformer<?,?>> r = new ArrayList<>();

            r.add(AgeableTransformer.getInstance());
            r.add(OrientableTransformer.getInstance());
            r.add(DirectionableTransformer.getInstance());
            r.add(GroupableTransformer.getInstance());
            r.add(DelayableTransformer.getInstance());
            r.add(EyeableTransformer.getInstance());
            r.add(HingedTransformer.getInstance());
            r.add(OpenableTransformer.getInstance());
            r.add(StaggeredTransformer.getInstance());
            r.add(SectionableTransformer.getInstance());
            r.add(RotableTransformer.getInstance());
            r.add(PlayableTransformer.getInstance());
            r.add(StatefulTransformer.getInstance());
            r.add(LeavedTransform.getInstance());
            r.add(IgnitableTransformer.getInstance());
            r.add(LockableTransformer.getInstance());
            r.add(ConditionableTransformer.getInstance());
            r.add(InvertableTransformer.getInstance());
            r.add(PowerableTransformer.getInstance());

            Transformers.transformers = r;
        }

        return Transformers.transformers;
    }

    /**
     * Loads the blockData's arguments into a base block
     * @param block Block without arguments
     * @param arguments Arguments to add
     * @return Modified block
     */
    public static Block getBlock(Block block, Map<String,String> arguments) {
        // TODO
        return null;
    }
}
