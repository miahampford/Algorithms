//
//Amelia Hampford
//agh3ft
//Algorithms Spring 2019
//

public class BirdScooters {

    Node root;

    /**
     * Constructor
     */
    public BirdScooters(){
        root = null;
    }

    /**
     * Adds a scooter store at the Node passed in
     * @param scooter - location of the scooter store
     * @return true if added
     */
    public boolean add (Node scooter){
        // TODO implement this yourself
        if (root == null){
            root = scooter;
            return true;}
        else{
            return addIt(root, scooter);
        }

    }

    public boolean addIt(Node iter, Node scooter){

        boolean even = iter.level%2 == 0;

        if(even && scooter.x < iter.x){
            if(iter.left == null){
                iter.left = scooter;
                scooter.level = iter.level+1;
                return true;
            }
            else{addIt(iter.left, scooter);}
        }
        else if (even && scooter.x >= iter.x){
            if(iter.right == null){
                iter.right = scooter;
                scooter.level = iter.level+1;
                return true;
            }
            else{addIt(iter.right, scooter);}
        }
        else if (!even && scooter.y < iter.y){
            if(iter.left == null){
                iter.left = scooter;
                scooter.level = iter.level+1;
                return true;
            }
            else{addIt(iter.left, scooter);}
        }
        else if (!even && scooter.y >= iter.y){
            if(iter.right == null){
                iter.right = scooter;
                scooter.level = iter.level+1;
                return true;
            }
            else{addIt(iter.right, scooter);}
        }
        return false;
    }


    /**
     * Find the closest scooter to the provided location
     * @param location
     * @return closest Node corresponding to the closest scooter store
     */
    public Node closestPoint(Node location){
        Node champ = root;
        double dist = Distance(champ, location);

        // TODO implement this yourself
        return closest(location, champ, champ, dist);
    }

    public Node closest(Node loc, Node champ, Node iter, double dist){

        if(iter == null){return champ;}

        boolean even = iter.level%2 == 0;


        double d = Distance(iter, loc);
        if(d<dist){
            dist = d;
            champ=iter;
        }

        Node cmp;
        if (even){ cmp = new Node(iter.x, loc.y); }
        else{ cmp = new Node(loc.x, iter.y); }
        double d2 = Distance(cmp, loc);
        boolean b = d2 <= dist;

        Node champ1 = null;
        Node champ2 = null;
        if(even && loc.x < iter.x){
            champ1 = closest(loc, champ, iter.left, dist);
            if(b){champ2 = closest(loc, champ, iter.right, dist);}
        }
        else if (even && loc.x >= iter.x){
            champ1 = closest(loc, champ, iter.right, dist);
            if(b){champ2 = closest(loc, champ, iter.left, dist);}
        }
        else if (!even && loc.y < iter.y){
            champ1 = closest(loc, champ, iter.left, dist);
            if(b){champ2 = closest(loc, champ, iter.right, dist);}
        }
        else if (!even && loc.y >= iter.y){
            champ1 = closest(loc, champ, iter.right, dist);
            if(b){champ2 = closest(loc, champ, iter.left, dist);}
        }

        if(champ2 != null && Distance(loc, champ1) > Distance(loc, champ2) && dist > Distance(loc, champ2)){
            return champ2;
        }

        if(champ1 != null && dist > Distance(loc, champ1)){
            return champ1;
        }

        return champ;

    }

    /***************************************************************************
     *  Helper function for Distance Formula
     ***************************************************************************/

    private double Distance(Node node, Node query) {
        return Math.sqrt(Math.pow(node.x - query.x, 2) + Math.pow(node.y - query.y, 2));
    }

    /***************************************************************************
     *  Main method
     ***************************************************************************/

    public static void main(String args[]){

    }

}
