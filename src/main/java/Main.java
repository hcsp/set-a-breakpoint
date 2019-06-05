import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class Main {
    public static void main(String[] args) throws IOException {
        a();
    }

    public static void a() throws IOException {
        b();
    }

    public static void b() throws IOException {
        c();
    }

    public static void c() throws IOException {
        d();
    }

    public static String d() throws IOException {
        // Please set a breakpoint here to catch the value of result
        // 请在这里设置一个断点，捕捉result的值
        String result = DigestUtils.md5Hex(readFileContent());
        return result;
    }

    public static String readFileContent() throws IOException {
        Stream<String> entries =
                Stream.of(System.getProperty("java.class.path").split(File.pathSeparator));
        File targetClassDir =
                entries.filter(
                                entry ->
                                        entry.endsWith("target/classes")
                                                || entry.endsWith("target\\classes"))
                        .findFirst()
                        .map(File::new)
                        .orElseThrow(IllegalStateException::new);
        File mainDotJava = new File(targetClassDir, "../../src/main/java/Main.java");

        return IOUtils.toString(new FileReader(mainDotJava)).replaceAll("\\s", "");
    }
}
