package MazeGeneratorsTests;

import backend.academy.Generators.MazeGenerators.MazeGenerator;
import backend.academy.Generators.MazeGenerators.PrimMazeGenerator;

public class PrimMazeGeneratorTest extends IdealGeneratorTest {
    @Override
    protected MazeGenerator initGenerator() {
        return new PrimMazeGenerator();
    }
}
