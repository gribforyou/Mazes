package backend.academy.Generators.EntityGenerators;

import backend.academy.MazeClasses.Entity;
import java.util.Random;

public class BasicEntityGenerator implements EntityGenerator {
    private final int coinProbability = 5;
    private final int forestProbability = 6;
    private final int seaProbability = 7;
    private final int maxProbability = 100;
    private final Random rand = new Random();

    @Override
    public Entity[][] generate(int height, int width) {
        validate(height, width);
        Entity[][] entities = new Entity[height][width];
        int temp;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                temp = rand.nextInt(maxProbability);
                if (temp <= coinProbability) {
                    entities[y][x] = Entity.Coin;
                } else if (temp <= forestProbability + coinProbability) {
                    entities[y][x] = Entity.Forest;
                } else if (temp <= seaProbability + forestProbability + coinProbability) {
                    entities[y][x] = Entity.Sea;
                } else {
                    entities[y][x] = Entity.Default;
                }
            }
        }
        return entities;
    }

    private void validate(int height, int width) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Height and width should not less than 2");
        }
    }
}
