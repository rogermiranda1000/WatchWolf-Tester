package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.*;

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
        if (block instanceof Directionable) block = (Block) DirectionableTransformer.getInstance().applyPropertiesToBlock((Directionable) block, arguments);
        if (block instanceof Orientable) block = (Block) OrientableTransformer.getInstance().applyPropertiesToBlock((Orientable) block, arguments);
        if (block instanceof Ageable) block = (Block) AgeableTransformer.getInstance().applyPropertiesToBlock((Ageable) block, arguments);
        if (block instanceof Groupable) block = (Block) GroupableTransformer.getInstance().applyPropertiesToBlock((Groupable) block, arguments);
        if (block instanceof Delayable) block = (Block) DelayableTransformer.getInstance().applyPropertiesToBlock((Delayable) block, arguments);
        if (block instanceof Eyeable) block = (Block) EyeableTransformer.getInstance().applyPropertiesToBlock((Eyeable) block, arguments);
        if (block instanceof Hinged) block = (Block) HingedTransformer.getInstance().applyPropertiesToBlock((Hinged) block, arguments);
        if (block instanceof Openable) block = (Block) OpenableTransformer.getInstance().applyPropertiesToBlock((Openable) block, arguments);
        if (block instanceof Staggered) block = (Block) StaggeredTransformer.getInstance().applyPropertiesToBlock((Staggered) block, arguments);
        if (block instanceof Sectionable) block = (Block) SectionableTransformer.getInstance().applyPropertiesToBlock((Sectionable) block, arguments);
        if (block instanceof Rotable) block = (Block) RotableTransformer.getInstance().applyPropertiesToBlock((Rotable) block, arguments);
        if (block instanceof Playable) block = (Block) PlayableTransformer.getInstance().applyPropertiesToBlock((Playable) block, arguments);
        if (block instanceof Stateful) block = (Block) StatefulTransformer.getInstance().applyPropertiesToBlock((Stateful) block, arguments);
        if (block instanceof Leaved) block = (Block) LeavedTransform.getInstance().applyPropertiesToBlock((Leaved) block, arguments);
        if (block instanceof Ignitable) block = (Block) IgnitableTransformer.getInstance().applyPropertiesToBlock((Ignitable) block, arguments);
        if (block instanceof Lockable) block = (Block) LockableTransformer.getInstance().applyPropertiesToBlock((Lockable) block, arguments);
        if (block instanceof Conditionable) block = (Block) ConditionableTransformer.getInstance().applyPropertiesToBlock((Conditionable) block, arguments);
        if (block instanceof Invertable) block = (Block) InvertableTransformer.getInstance().applyPropertiesToBlock((Invertable) block, arguments);
        if (block instanceof Powerable) block = (Block) PowerableTransformer.getInstance().applyPropertiesToBlock((Powerable) block, arguments);

        return block;
    }

    public static String getBlockData(Block block, String blockData) {
        if (block instanceof Orientable) blockData = OrientableTransformer.getInstance().modifyBlockData((Orientable) block, blockData);
        if (block instanceof Directionable) blockData = DirectionableTransformer.getInstance().modifyBlockData((Directionable) block, blockData);
        if (block instanceof Ageable) blockData = AgeableTransformer.getInstance().modifyBlockData((Ageable) block, blockData);
        if (block instanceof Groupable) blockData = GroupableTransformer.getInstance().modifyBlockData((Groupable) block, blockData);
        if (block instanceof Delayable) blockData = DelayableTransformer.getInstance().modifyBlockData((Delayable) block, blockData);
        if (block instanceof Eyeable) blockData = EyeableTransformer.getInstance().modifyBlockData((Eyeable) block, blockData);
        if (block instanceof Hinged) blockData = HingedTransformer.getInstance().modifyBlockData((Hinged) block, blockData);
        if (block instanceof Openable) blockData = OpenableTransformer.getInstance().modifyBlockData((Openable) block, blockData);
        if (block instanceof Staggered) blockData = StaggeredTransformer.getInstance().modifyBlockData((Staggered) block, blockData);
        if (block instanceof Sectionable) blockData = SectionableTransformer.getInstance().modifyBlockData((Sectionable) block, blockData);
        if (block instanceof Rotable) blockData = RotableTransformer.getInstance().modifyBlockData((Rotable) block, blockData);
        if (block instanceof Playable) blockData = PlayableTransformer.getInstance().modifyBlockData((Playable) block, blockData);
        if (block instanceof Stateful) blockData = StatefulTransformer.getInstance().modifyBlockData((Stateful) block, blockData);
        if (block instanceof Leaved) blockData = LeavedTransform.getInstance().modifyBlockData((Leaved) block, blockData);
        if (block instanceof Ignitable) blockData = IgnitableTransformer.getInstance().modifyBlockData((Ignitable) block, blockData);
        if (block instanceof Lockable) blockData = LockableTransformer.getInstance().modifyBlockData((Lockable) block, blockData);
        if (block instanceof Conditionable) blockData = ConditionableTransformer.getInstance().modifyBlockData((Conditionable) block, blockData);
        if (block instanceof Invertable) blockData = InvertableTransformer.getInstance().modifyBlockData((Invertable) block, blockData);
        if (block instanceof Powerable) blockData = PowerableTransformer.getInstance().modifyBlockData((Powerable) block, blockData);

        return blockData;
    }
}
