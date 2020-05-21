import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Encoder {

    public static void main(String[] args) throws Exception {
        Path path = FileSystems.getDefault().getPath(".").toAbsolutePath();
//        System.out.println(path);
        Path fileBomb = Paths.get(path.toString(), "target/classes/Bomb.class");
        byte[] classBytes = Files.readAllBytes( fileBomb);
        byte[] encodedBytes = Base64.getEncoder().encode(classBytes);
        System.out.println(new String(encodedBytes));
    }


}