package backend.academy.Generators.EntityGenerators;

import backend.academy.MazeClasses.Entity;

public interface EntityGenerator {
    Entity[][] generate(int height, int width);
}
