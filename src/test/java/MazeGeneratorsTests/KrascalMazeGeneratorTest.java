package MazeGeneratorsTests;

import backend.academy.Generators.MazeGenerators.KrascalMazeGenerator;
import backend.academy.Generators.MazeGenerators.MazeGenerator;

public class KrascalMazeGeneratorTest extends IdealGeneratorTest {
    @Override
    protected MazeGenerator initGenerator() {
        return new KrascalMazeGenerator();
    }
}
