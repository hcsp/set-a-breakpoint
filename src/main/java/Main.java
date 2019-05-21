import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        a();
    }

    private static void a() throws IOException {
        b();
    }

    private static void b() throws IOException {
        c();
    }

    private static void c() throws IOException {
        d();
    }

    private static String d() throws IOException {
        Stream<String> entries = Stream.of(System.getProperty("java.class.path").split(File.pathSeparator));
        File targetClassDir = entries.filter(entry -> entry.endsWith("target/classes")).findFirst().map(File::new).orElseThrow(IllegalStateException::new);
        File mainDotJava = new File(targetClassDir, "../../src/main/java/Main.java");

        String sanitizedFileContent = IOUtils.toString(new FileReader(mainDotJava)).replaceAll("\\s", "");
        String result = DigestUtils.md5Hex(sanitizedFileContent);
        return result;
    }
}
