import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.blocks.Block;
import com.rogermiranda1000.watchwolf.entities.blocks.Blocks;
import com.rogermiranda1000.watchwolf.entities.blocks.Orientable;
import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class BlocksTester extends AbstractTest {
    @Override
    public File getConfigFile() {
        return null; // TODO
    }

    @Test
    public void setBlock() throws Exception {
        Position p = new Position("world", 0,0,0);
        TesterTester.connector.setBlock(p, Blocks.SAND);
        assertEquals(TesterTester.connector.getBlock(p), Blocks.SAND);
    }

    @Test
    public void setComplexBlock() throws Exception {
        Position p = new Position("world", 0,0,0);
        TesterTester.connector.setBlock(p, Blocks.ACACIA_SLAB);
        assertEquals(TesterTester.connector.getBlock(p), Blocks.ACACIA_SLAB);
    }

    @Test
    public void setChangedComplexBlock() throws Exception {
        Position p = new Position("world", 0,0,0);
        Block slab = (Block) Blocks.ACACIA_SLAB.set(Orientable.Orientation.U, true);
        TesterTester.connector.setBlock(p, slab);
        assertEquals(TesterTester.connector.getBlock(p), slab);
    }
}
