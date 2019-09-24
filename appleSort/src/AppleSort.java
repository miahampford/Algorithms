//
//Amelia Hampford
//agh3ft
//Algorithms Spring 2019
//

import java.util.ArrayList;

public class AppleSort {


    /**
     * Implement this method that takes in an arraylist of Apples and sorts them into one giant array of Apples.
     * You are free to implement any helper methods you need.
     * @param apples
     * @return
     */
    public static Comparable[] sort(ArrayList<Apple[]> apples){

        int N = 0;
        for (int i = 0; i < apples.size(); i++){
            N = N + apples.get(i).length;
        }

        int i = 0;
        while (apples.size() > 1 && i < apples.size()){
            merge(apples, i);
            i++;
        }

        return apples.get(0);
    }

    private static void merge(ArrayList<Apple[]> apples, int num){

        Apple[]c = new Apple[apples.get(num).length + apples.get(num+1).length];
        Apple[]d = new Apple[apples.get(num).length + apples.get(num+1).length];
        for(int i = 0; i<apples.get(num).length; i++){
            c[i] = apples.get(num)[i];
            d[i] = apples.get(num)[i];
        }
        for(int i = 0; i<apples.get(num+1).length; i++){
            c[apples.get(num).length + i] = apples.get(num+1)[i];
            d[apples.get(num).length + i] = apples.get(num+1)[i];
        }

        int i = 0, j = apples.get(num).length, mid = apples.get(num).length, hi = c.length-1;
        for(int k = 0; k<= hi; k++){
            if(i>mid) c[k] = d[j++];
            else if(j > hi) c[k] = d[i++];
            else if(less(d[j], d[i])) c[k] = d[j++];
            else c[k] = d[i++];
        }

        apples.remove(apples.get(num+1));
        apples.remove(apples.get(num));
        apples.add(d);
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {

    }

}

