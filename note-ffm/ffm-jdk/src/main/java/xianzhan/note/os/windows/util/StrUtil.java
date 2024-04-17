package xianzhan.note.os.windows.util;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.charset.StandardCharsets;

/**
 * @author xianzhan
 * @since 2023-12-19
 */
public class StrUtil {

    public static MemorySegment L(Arena arena, String str) {
        // https://stackoverflow.com/questions/66072117/why-does-windows-use-utf-16le
        var bs = str.getBytes(StandardCharsets.UTF_16LE);
        var ms = arena.allocate(bs.length + Short.BYTES);
        MemorySegment.copy(bs, 0, ms, ValueLayout.JAVA_BYTE, 0, bs.length);
        // fix: 乱码 UTF_16LE 双字节
        ms.set(ValueLayout.JAVA_SHORT, bs.length, (short) '\0');

        return ms;
    }
}
