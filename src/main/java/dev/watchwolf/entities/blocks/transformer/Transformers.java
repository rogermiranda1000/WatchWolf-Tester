package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.*;

import java.util.ArrayList;
import java.util.Map;

public class Transformers {
    private static AbstractTransformer<?,?> firstTransformer = null;

    public static AbstractTransformer<?,?> getInstance() {
        if (Transformers.firstTransformer == null) {
            Transformers.firstTransformer = new AgeableTransformer();

            AbstractTransformer<?,?> pre = Transformers.firstTransformer,
                                    next = new OrientableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new DirectionableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new GroupableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new DelayableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new EyeableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new HingedTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new OpenableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new StaggeredTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new SectionableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new RotableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new PlayableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new StatefulTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new LeavedTransform();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new IgnitableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new LockableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new ConditionableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new InvertableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous

            next = new PowerableTransformer();
            pre.setNext(next);
            pre = next; // now this is the previous
        }

        return Transformers.firstTransformer;
    }
}
