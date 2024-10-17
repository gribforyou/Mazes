package backend.academy.Generators;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KrascalMazeGenerator implements MazeGenerator {
    public KrascalMazeGenerator() {
    }

    @Override
    public Set<Edge> generate(int width, int height) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Invalid height or width");
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                edges.add(new Edge(new Vertex(j, i), new Vertex(j, i - 1)));
                edges.add(new Edge(new Vertex(j, i), new Vertex(j - 1, i)));
            }
        }
        for (int i = 0; i < height - 1; i++) {
            edges.add(new Edge(new Vertex(0, i), new Vertex(0, i + 1)));
        }
        for (int i = 0; i < width - 1; i++) {
            edges.add(new Edge(new Vertex(i, 0), new Vertex(i + 1, 0)));
        }

        Collections.shuffle(edges);
        Set<Edge> result = new HashSet<>();
        List<Set<Vertex>> components = new ArrayList<>();
        Vertex v1;
        Vertex v2;
        int temp1;
        int temp2;
        for (Edge edge : edges) {
            v1 = edge.v1();
            v2 = edge.v2();
            temp1 = -1;
            temp2 = -1;

            for (int i = 0; i < components.size(); i++) {
                if (components.get(i).contains(v1)) {
                    temp1 = i;
                }
                if (components.get(i).contains(v2)) {
                    temp2 = i;
                }
            }
            if (temp1 == -1 && temp2 == -1) {
                result.add(edge);
                Set<Vertex> newComponent = new HashSet<>();
                newComponent.add(v1);
                newComponent.add(v2);
                components.add(newComponent);
            } else if (temp1 == -1) {
                result.add(edge);
                components.get(temp2).add(v1);
            } else if (temp2 == -1) {
                result.add(edge);
                components.get(temp1).add(v2);
            } else if (temp1 != temp2) {
                result.add(edge);
                Set<Vertex> set1 = components.get(temp1);
                Set<Vertex> set2 = components.get(temp2);

                set1.addAll(set2);
                components.remove(temp2);
            }
        }
        return result;
    }
}
