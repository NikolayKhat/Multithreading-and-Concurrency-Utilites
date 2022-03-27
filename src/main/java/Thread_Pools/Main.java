package Thread_Pools;

public class Main {
    public static void main(String[] args) throws Exception {

        Program program = new Program(3, 3, 10);

        program.run();
    }
}