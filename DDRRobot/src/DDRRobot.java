//
//Amelia Hampford
//agh3ft
//Algorithms Spring 2019
//

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;

public class DDRRobot {

    public Digraph G = null;
    private int Demension;
    private boolean[] marked; // reached vertices
    private boolean[] marked2; // reached vertices
    private int[] id; // component identifiers
    private int count = 0; // number of strong components
    private Stack<Integer> reversePost; // vertices in reverse postorder

    /**
     * Initalizes the DDRobot Board
     * @param squares
     */
    public DDRRobot(int squares){
        // TODO: implement this method
        G = new Digraph(squares);
        Demension = (int)Math.sqrt(squares);
        marked = new boolean[G.V()];
        marked2 = new boolean[G.V()];
        id = new int[G.V()];
        reversePost = new Stack<Integer>();
    }

    /**
     * key:
     * → 1
     * ↑ 2
     * ← 3
     * ↓ 4
     * @param square  based o the square number
     * @param arrow based on tile arrow number
     */

    //Add tiles, checking whether or not the arrow points off the grid
    public void addTile(int square, int arrow){
        // TODO: implement this method
        if(arrow ==1 && ((square+1)%Demension!=0)){
            G.addEdge(square,square+1);
        }
        if(arrow == 2 && square >= Demension){
            G.addEdge(square,square-Demension);
        }
        if(arrow ==3 && !(square % Demension == 0)){
            G.addEdge(square,square-1);
        }
        if(arrow ==4 && square < G.V()-Demension){
            G.addEdge(square, square+Demension);
        }
    }

    /**
     * Retuns the list of Tiles
     */
    public ArrayList<Integer> getPlayOptions(){
        // TODO: implement this method

        //reverse G before the for loop
        Digraph Gr = G.reverse();

        //create reversePost stack
        for (int v = 0; v < G.V(); v++){
            if (!marked[v]) dfs(Gr, v);}

        //pop items from reversePost and then call other dfs function
        while(!reversePost.empty()) {
            int s = reversePost.pop();
            if (!marked2[s]) {
                Rdfs(G, s);
                count++;
            }
        }

        return findGroup();

    }

    private void dfs(Digraph G, int v)
    {
        marked[v] = true;
        for (int w : G.adj(v)){
            if (!marked[w]) {dfs(G, w);}}
        reversePost.push(v);
    }

    private void Rdfs(Digraph G, int v)
    {
        marked2[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked2[w]) {
                Rdfs(G, w);
            }
        }

    }

    //use hash map to find most common id
    //place all numbers corresponding to that id in the return list
    private ArrayList<Integer> findGroup(){
        ArrayList<Integer> lst = new ArrayList<>();

        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        int bestid = 0;
        int max  = 1;

        for(int i = 0; i < id.length; i++) {
            if (hash.get(id[i]) != null) {
                int c = hash.get(id[i]);
                hash.put(id[i], c++);
                if(c > max) {
                    bestid = id[i];
                    max  = c;
                }
            }
            else
                hash.put(id[i],1);
        }
        for(int i =0; i<id.length; i++){
            if(id[i] == bestid){
                lst.add(i);
            }
        }
        return lst;
    }

    public static void main(String[] args) {

    }

}