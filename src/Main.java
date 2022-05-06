public class Main {
    public static void main(String[] args){
        Spring[] array={new Spring(1), new Spring(2)};//, new Spring(3), new Spring(4), new Spring(5)};
        String expr="{[][[][]]}";
        System.out.println(SpringArray.equivalentSpring(expr,array, 0, expr.length()-1).getK());
    }
}
