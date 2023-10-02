package xianzhan.frame.base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 路径文件工具类
 */
@Slf4j
public class PathUtil {

    public static Stream<Path> list(String dir) {
        try {
            Path path = Path.of(dir);
            return Files.list(path);
        } catch (IOException e) {
            log.error("Path - list: 获取目录列表异常. dir: {}", dir, e);
        }
        return Stream.empty();
    }
}
