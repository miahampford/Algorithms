//
//Amelia Hampford
//agh3ft
//Algorithms Spring 2019
//

import java.util.Arrays;

public class FriendCircles {

    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i

    /**
     * @param M the adjacency matrix of friends
     * @return number of friend circles
     */
    public int findCircleNum(int[][] M) {
        // TODO: implement this method
        parent = new int[M.length];
        rank = new byte[M.length];
        Arrays.fill(rank, (byte)1);
        for (int i = 0; i < M.length; i++) {
            parent[i] = i;}

        for (int i = 0; i < M.length; i++) {
            for (int j = i; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        int count = 0;

        for (int i = 0; i<rank.length; i++){
            if(rank[i] == 1){count +=1;}
        }


        return count;
    }


    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        // TODO: implement this method
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }


    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {

        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (rank[i] < rank[j]) {
            parent[i] = j;
            rank[i] += rank[j];
        } else {
            parent[j] = i;
            rank[j] += rank[i];
        }
    }



    public static void main(String[] args) {

    }

}
