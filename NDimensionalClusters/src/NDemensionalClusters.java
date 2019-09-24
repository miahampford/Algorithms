//
//Amelia Hampford
//agh3ft
//Algorithms Spring 2019
//

// Uses code from Princeton Algorithms Code PortFolio

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class NDemensionalClusters {

    ArrayList<Point> points = new ArrayList<Point>();
    private boolean[] marked; // MST vertices
    private PriorityQueue<Edge> mst; // MST edges
    private PriorityQueue<Edge> pq; // PQ of edges

    /**
     * Adds a point to the dataSet being considered
     * @param point
     */
    public void add(Point point) {
        // TODO: Implement this method.
        points.add(point);

    }

    /**
     * The Total sum on the edges in a cluster.
     * @param clusters the number of clusters to consider
     * @return sum
     */
    public double getSum(int clusters) {
        // TODO: Implement this method

        pq = new PriorityQueue<Edge>(Collections.reverseOrder());
        mst = new PriorityQueue<Edge>();
        marked = new boolean[points.size()];
        visit(0);

        //createEdges(points);

        while (!pq.isEmpty() && mst.size() < points.size() - 1)
        {
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.add(e);
            if (!marked[v]) visit(v);
            if (!marked[w]) visit(w);
        }

        double sum = 0;
        for(int i = 1; i<clusters; i++){
            mst.poll();
        }
        int j = mst.size();
        for(int i = 0; i<j; i++){
            sum += mst.poll().weight();
        }

        return sum;
    }

    private void visit(int v)
    {
        marked[v] = true;

        for(int w=0; w<marked.length; w++){
            if(!marked[w]){
                Edge e = new Edge(v, w, points.get(v).distance(points.get(w)));
                pq.add(e);
            }
        }

    }

    public static void main(String[] args) {

    }
}
