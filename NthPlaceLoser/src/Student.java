public class Student implements Comparable<Student>{
    public int rank;
    public String name;

    public Student(int rank, String name){
        this.rank = rank;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        // TODO implement this yourself
        if(this.rank > o.rank) {return -1;}
        else if(this.rank < o.rank) {return 1;}
        else {return 0;}
    }

}
