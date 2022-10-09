import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.blocks.Block;
import com.rogermiranda1000.watchwolf.entities.blocks.Blocks;
import com.rogermiranda1000.watchwolf.entities.blocks.Directionable;
import com.rogermiranda1000.watchwolf.entities.blocks.Orientable;
import com.rogermiranda1000.watchwolf.entities.blocks.special.Bell;
import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BlocksTester extends AbstractTest {
    @Override
    public File getConfigFile() {
        return null; // TODO
    }

    @Test
    public void setBlock() throws Exception {
        Position p = new Position("world", 0,0,0);
        TesterTester.connector.setBlock(p, Blocks.IRON_BLOCK);
        assertEquals(TesterTester.connector.getBlock(p), Blocks.IRON_BLOCK);
    }

    @Test
    public void setComplexBlock() throws Exception {
        Position p = new Position("world", 0,0,0);
        TesterTester.connector.setBlock(p, Blocks.OAK_SLAB);
        assertEquals(TesterTester.connector.getBlock(p), Blocks.OAK_SLAB);
    }

    @Test
    public void setChangedOrientableBlock() throws Exception {
        Position p = new Position("world", 0,0,0);
        Block slab = (Block) Blocks.ACACIA_SLAB.setOrientation(Orientable.Orientation.U, true);
        TesterTester.connector.setBlock(p, slab);
        Block get = TesterTester.connector.getBlock(p);

        ArrayList<Byte> originalData = new ArrayList<>(),
                gettedData = new ArrayList<>();
        slab.sendSocketData(originalData);
        get.sendSocketData(gettedData);
        assertEquals(originalData, gettedData);
    }

    @Test
    public void setChangedOrientableDirectionableBlock() throws Exception {
        Position p = new Position("world", 0,0,0);
        Bell bell = (Bell) Blocks.BELL.setOrientation(Orientable.Orientation.W, true);
        bell = (Bell) bell.setDirection(Directionable.Direction.SINGLE_WALL);
        TesterTester.connector.setBlock(p, bell);
        Block get = TesterTester.connector.getBlock(p);

        ArrayList<Byte> originalData = new ArrayList<>(),
                gettedData = new ArrayList<>();
        bell.sendSocketData(originalData);
        get.sendSocketData(gettedData);
        assertEquals(originalData, gettedData);
    }
}
