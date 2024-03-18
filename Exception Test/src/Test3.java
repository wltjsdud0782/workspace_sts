import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test3 {
    public static void main(String[] args) { // m1,m2 : 예외처리 부탁
        try {
            m1();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void m1()throws Exception{ // m2:호출한 사람이 예외 처리 해라 m1 : ㄴㄴ 나도 예외 처리 회피할래
        m2();
    }

    public static void m2() throws IOException { // throws 예외 처리 회피
        Path file = Paths.get("C:\\javastudy\\text.txt");
        BufferedWriter writer = null;

        writer = Files.newBufferedWriter(file);
        writer.write('a');
        writer.write('b');
    }
}
