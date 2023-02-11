package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.BlockModifier;

import java.util.*;
import java.util.function.Function;

/**
 * This is the base class to automatically generate all the `dev.watchwolf.entities.blocks.special`
 * classes.
 * @param <T> One block modifier (Directionable, Orientable, Ageable...)
 * @param <E> Type of all the options that the block modifier (<T>) can take (enum/integer)
 */
public abstract class AbstractTransformer<T extends BlockModifier, E> {
    /**
     * Bytes that the Block packet has. The first 2 specifies the block itself,
     * and the rest adds information to the block. Refer to the
     * <a href="https://github.com/watch-wolf/WatchWolf/blob/9d3a6016b5823aba1ee61187349e13c0edfe9c5f/Standard/Protocols.pdf">API documentation</a>,
     * under subsection 2.4.9. Block.
     */
    public static final int BLOCK_SOCKET_DATA_SIZE = 56;

    /**
     * This should place a block with the `Bukkit.createBlockData` using the param,
     * but in WatchWolf Tester we don't have access to Bukkit.
     */
    private static Function<String,Boolean> isValid = (blockData) -> true;

    /**
     * Sets the `isValid` variable with the new value
     * @param isValid Function to check if the block data is valid
     */
    public static void setValidator(Function<String,Boolean> isValid) {
        AbstractTransformer.isValid = isValid;
    }

    /**
     * Given one material and argument, get all the values that the BlockData can take
     * @param mat Block material
     * @param argument BlockData key
     * @return List of BlockData values
     */
    abstract public List<String> getOptions(String mat, String argument);

    /**
     * Given one material and argument, get all the values that the BlockData can take.
     * In addition, to remove invalid combinations of attributes, the original BlockData is used
     * @param mat Block material
     * @param argument BlockData key
     * @param blockData Base BlockData. The key `argument` will be replaced by each of the returns
     * @return List of BlockData values
     */
    public List<String> getOptions(String mat, final String argument, final String blockData) {
        List<String> r = this.getOptions(mat, argument);
        r.stream().filter(arg -> AbstractTransformer.isValid.apply(blockData.replaceAll(argument + "=[^,\\]]+", argument + "=" + arg)));
        return r;
    }

    /**
     * Convert from all the combinations of key-value to key-values, joining by the first value.
     * Then it returns all the options the block modifier can take.
     * @param options All the attributes that can take this block modifier, with its value
     * @return Given the specified options, it returns all the options the block modifier can take
     */
    public Collection<E> get(String mat, Map<String, String> options) {
        HashMap<String, List<String>> arg = new HashMap<>();
        for (Map.Entry<String,String> option : options.entrySet()) {
            List<String> l = new ArrayList<>();
            l.add(option.getValue());
            arg.put(option.getKey(), l);
        }
        return this.get(mat, arg);
    }

    /**
     * Get all the options the block modifier can take
     * @param options All the attributes that can take this block modifier, with all its values
     * @return Given the specified options, it returns all the options the block modifier can take
     */
    public abstract Collection<E> get(String mat, HashMap<String, List<String>> options);

    /**
     * Convert from all the combinations of key-value to key-values, joining by the first value.
     * Then, get one option the block modifier can take.
     * @param options All the attributes that can take this block modifier, with its value
     * @return Given the specified options, it returns the first option the block modifier can take
     */
    public E getSingle(String mat, Map<String, String> options) {
        return this.get(mat, options).stream().findFirst().orElse(null);
    }

    /**
     * Get one option the block modifier can take
     * @param options All the attributes that can take this block modifier, with all its values
     * @return Given the specified options, it returns the first option the block modifier can take
     */
    public E getSingle(String mat, HashMap<String, List<String>> options) {
        return this.get(mat, options).stream().findFirst().orElse(null);
    }

    /**
     * Check if the block applies to the interface <T>
     * @param options All the attributes that can take this block modifier, with its value
     * @return True or False, depending if there's any value applicable to the block
     */
    public boolean applies(String mat, Map<String, String> options) {
        return this.applies(this.get(mat, options));
    }

    /**
     * Check if the block applies to the interface <T>
     * @param options All the attributes that can take this block modifier, with all its values
     * @return True or False, depending if there's any value applicable to the block
     */
    public boolean applies(String mat, HashMap<String, List<String>> options) {
        return this.applies(this.get(mat, options));
    }

    /**
     * Check if the block applies to the interface <T>
     * @param list All the options the block modifier can take
     * @return True or False, depending if there's any value applicable to the block
     */
    protected boolean applies(Collection<E> list) {
        return list.size() > 0;
    }

    /**
     * Get the code of the interface <T> that needs to be inserted in the special block's class.
     * @param className Class name; name after `new` that generates an instance of that class
     * @param list Return of `get`; all the options the block modifier can take
     * @param listImplements Output list with all the implements of the class
     * @param loadEval Output list that will be copied in the base constructor. It loads the needed
     *                 variables, and sets the default values.
     * @param copyProperties Output list that will be copied in the copy constructor. It contains
     *                       all the variables needed to clone another block of the same type.
     * @param socketData Output array of size AbstractTransformer.BLOCK_SOCKET_DATA_SIZE, that specifies
     *                   the code needed to run in order to get the byte value at that position.
     * @return Methods needed to insert in the class in order to implement the interface <T>
     */
    public abstract String getImplementation(String className, Collection<E> list, List<String> listImplements, List<String> loadEval, List<Function<String,String>> copyProperties, String socketData[]);

    /**
     * Modify the socket data to send this exact block.
     * @param socketData Output array of size AbstractTransformer.BLOCK_SOCKET_DATA_SIZE, that
     *                   specifies the code needed to run in order to get the byte value at that
     *                   position.
     *                   The first 2 bytes are the block enum value.
     *                   If empty, it's the same as `(byte)0`
     */
    protected abstract void getSocketData(String socketData[]);

    /**
     * Given a block without `arguments` loaded into the interface <T>, load them
     * @param base Base block without <T>'s values
     * @param arguments All the attributes that can take this block modifier, with its value
     * @return Base block with the new values loaded
     */
    public abstract T applyPropertiesToBlock(T base, Map<String,String> arguments);

    /**
     * Given the default block data for the interface <T>, change it so the result block
     * equals the specified by `block`'s variables
     * @param block WatchWolf block
     * @param blockData Base block data
     * @return Modified block data
     */
    public abstract String modifyBlockData(T block, String blockData);

    /**
     * Given the original block data, the key, and the desired value, get the modified block data
     * @param blockData Base block data
     * @param property Key to search
     * @param value Value to replace
     * @return Modified block data
     */
    protected static String setBlockDataProperty(String blockData, String property, String value) {
        return blockData.replaceAll("(?<=[,\\[])" + property + "=[^,\\]]+", property + "=" + value);
    }
}
