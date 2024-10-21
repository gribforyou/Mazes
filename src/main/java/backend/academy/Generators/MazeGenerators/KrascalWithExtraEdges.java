package backend.academy.Generators.MazeGenerators;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class KrascalWithExtraEdges implements MazeGenerator {
    @Override
    public Set<Edge> generate(int width, int height) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Invalid height or width");
        }
        KrascalMazeGenerator generator = new KrascalMazeGenerator();
        Set<Edge> edges = generator.generate(width, height);
        List<Edge> full = new ArrayList<>();
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                full.add(new Edge(new Vertex(j, i), new Vertex(j, i - 1)));
                full.add(new Edge(new Vertex(j, i), new Vertex(j - 1, i)));
            }
        }
        for (int i = 0; i < height - 1; i++) {
            full.add(new Edge(new Vertex(0, i), new Vertex(0, i + 1)));
        }
        for (int i = 0; i < width - 1; i++) {
            full.add(new Edge(new Vertex(i, 0), new Vertex(i + 1, 0)));
        }
        full.removeAll(edges);
        Random rand = new Random();
        int temp = rand.nextInt(full.size());
        Collections.shuffle(full);
        for (int i = 0; i < temp; i++) {
            edges.add(full.get(i));
        }
        return edges;
    }
}
