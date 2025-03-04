package backend.academy.Generators.MazeGenerators;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Vertex;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PrimMazeGenerator implements MazeGenerator {

    public PrimMazeGenerator() {
    }

    @Override
    public Set<Edge> generate(int height, int width) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Invalid height or width");
        }

        Random rand = new Random();
        Set<Edge> edges = new HashSet<>(height * width * 2);
        Set<Vertex> first = new HashSet<>(height * width);
        Set<Vertex> second = new HashSet<>(height * width);
        Set<Edge> between = new HashSet<>(height * width - 1);
        Set<Vertex> neighbours;

        first.add(new Vertex(0, 0));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                second.add(new Vertex(i, j));
            }
        }
        second.remove(new Vertex(0, 0));

        Vertex tempV1 = new Vertex(0, 0);
        Vertex tempV2 = new Vertex(0, 1);
        Edge tempEdge = new Edge(tempV1, tempV2);
        between.add(tempEdge);

        tempV2 = new Vertex(1, 0);
        tempEdge = new Edge(tempV1, tempV2);
        between.add(tempEdge);

        while (!second.isEmpty()) {
            tempEdge = new ArrayList<Edge>(between).get(rand.nextInt(between.size()));

            if (first.contains(tempEdge.v1())) {
                tempV2 = tempEdge.v2();
            } else {
                tempV2 = tempEdge.v1();
            }

            first.add(tempV2);
            second.remove(tempV2);
            edges.add(tempEdge);

            neighbours = tempV2.getNeighbors();

            for (Vertex vertex : neighbours) {
                between.remove(new Edge(tempV2, vertex));
            }

            neighbours.retainAll(second);
            for (Vertex vertex : neighbours) {
                between.add(new Edge(tempV2, vertex));
            }
        }
        return edges;
    }
}
