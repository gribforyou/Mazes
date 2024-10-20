package backend.academy.Solvers;

import backend.academy.MazeClasses.Entity;

public class AStarMazeSolver extends DijkstraMazeSolver {
    public AStarMazeSolver() {
    }

    public AStarMazeSolver(int defaultPrice, int coinPrice, int forestPrice, int seaPrice) {
        super(defaultPrice, coinPrice, forestPrice, seaPrice);
    }

    @Override
    protected long priority(VertexWithPrevAndWeight current, Entity[][] matrix) {
        long pr = super.priority(current, matrix);
        pr += Math.abs(current.v.x() - endVertex.x()) + Math.abs(current.v.y() - endVertex.y());

        if (current.prev != null) {
            pr -= Math.abs(current.prev.v.x() - endVertex.x()) + Math.abs(current.prev.v.y() - endVertex.y());
        }

        return pr;
    }
}
