package xianzhan.frame.os.microsoft.user32;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.nio.charset.StandardCharsets;

/**
 * MessageBoxW demo
 *
 * @author xianzhan
 * @since 2023-12-18
 */
public class MessageBoxWMain {

    public static void main(String[] args) throws Throwable {
        var linker = Linker.nativeLinker();
        try (var arena = Arena.ofConfined()) {
            var user32 = SymbolLookup.libraryLookup("User32", arena);

            // https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-messageboxw
            var messageBoxW_MH = linker.downcallHandle(user32.find("MessageBoxW").get(), FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, // return int
                    ValueLayout.ADDRESS, // HWND hWnd
                    ValueLayout.ADDRESS.withTargetLayout(MemoryLayout.sequenceLayout(ValueLayout.JAVA_BYTE)), // LPCWSTR lpText
                    ValueLayout.ADDRESS.withTargetLayout(MemoryLayout.sequenceLayout(ValueLayout.JAVA_BYTE)), // LPCWSTR lpCaption
                    ValueLayout.JAVA_INT // UINT uType
            ));

            var NULL = MemorySegment.NULL; // HWND hWnd = NULL

            // https://stackoverflow.com/questions/66072117/why-does-windows-use-utf-16le
            var cs = StandardCharsets.UTF_16LE;
            // LPCWSTR lpText = "Hello from Panama! 你好, the world"
//            var lpText = arena.allocateArray(ValueLayout.JAVA_BYTE,
//                    "Hello from Panama! 你好, the world".getBytes(cs));
            var lpText = allocateArray(arena, "Hello from Panama! 你好, the world");
            // LPCWSTR lpCaption = "Demo123 你好，世界"
//            var lpCaption = arena.allocateArray(ValueLayout.JAVA_BYTE, "Demo123 你好，世界".getBytes(cs));
             var lpCaption = allocateArray(arena, "Demo123 你好，世界");

            var MB_OK = 0x0; // UINT uType = MB_OK

            var result = (int) messageBoxW_MH.invokeExact(NULL, lpText, lpCaption, MB_OK);
            System.out.println(STR."MessageBox result: \{result}");
        }
    }

    private static MemorySegment allocateArray(Arena arena, String str) {
        var bs = str.getBytes(StandardCharsets.UTF_16LE);
        var ms = arena.allocate(bs.length + 2);
        var i = 0;
        for (; i < bs.length; i++) {
            ms.set(ValueLayout.JAVA_BYTE, i, bs[i]);
        }
        // fix: 乱码 UTF_16LE 双字节
        ms.set(ValueLayout.JAVA_BYTE, i++, (byte) '\0');
        ms.set(ValueLayout.JAVA_BYTE, i, (byte) '\0');

        return ms;
    }
}

