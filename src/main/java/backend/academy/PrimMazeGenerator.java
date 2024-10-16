package backend.academy;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;

public class PrimMazeGenerator implements MazeGenerator {

    @Override
    public Set<Edge> generate(int height, int width) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Invalid height or width");
        }

        Random rand = new Random();
        Set<Edge> edges = new HashSet<>();
        Set<Vertex> first, second;
        first = new HashSet<>();
        second = new HashSet<>();
        Set<Edge> between = new HashSet<>();
        Set<Vertex> neighbours;

        first.add(new Vertex(0, 0));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                second.add(new Vertex(i, j));
            }
        }
        second.remove(new Vertex(0, 0));

        Edge tempEdge;
        Vertex tempV1, tempV2;

        tempV1 = new Vertex(0, 0);
        tempV2 = new Vertex(0, 1);
        tempEdge = new Edge(tempV1, tempV2);
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

            neighbours = new HashSet<>();
            neighbours.add(new Vertex(tempV2.x() + 1, tempV2.y()));
            neighbours.add(new Vertex(tempV2.x() - 1, tempV2.y()));
            neighbours.add(new Vertex(tempV2.x(), tempV2.y() + 1));
            neighbours.add(new Vertex(tempV2.x(), tempV2.y() - 1));

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
