package xianzhan.frame.base.util;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

public class PathUtilTest {

    @Test
    public void testList() {
        PathUtil.list(".").forEach(path -> {
            if (Files.isDirectory(path)) {
                System.out.println(path.getFileName().toString());
            } else {
                System.out.println("file: " + path);
            }
        });
    }

    @Test
    public void testCurrentDir() {
        System.out.println(PathUtil.currentDir().toAbsolutePath());
    }
}
