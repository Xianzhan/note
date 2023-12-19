package xianzhan.frame.os.microsoft.user32;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
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
            var messageBoxW_MH = linker.downcallHandle(user32.find("MessageBoxW")
                    .orElseThrow(() -> new RuntimeException("MessageBoxW Not Found")), FunctionDescriptor.of(
                    // return int
                    ValueLayout.JAVA_INT,
                    // HWND hWnd
                    ValueLayout.ADDRESS,
                    // LPCWSTR lpText
                    ValueLayout.ADDRESS,
                    // LPCWSTR lpCaption
                    ValueLayout.ADDRESS,
                    // UINT uType
                    ValueLayout.JAVA_INT
            ));

            // HWND hWnd = NULL
            var NULL = MemorySegment.NULL;
            // LPCWSTR lpText = "Hello from Panama! 你好, the world"
            var lpText = L(arena, "Hello from Panama! 你好, the world");
            // LPCWSTR lpCaption = "Demo123 你好，世界"
            var lpCaption = L(arena, "Demo123 你好，世界");
            // UINT uType = MB_OK
            var MB_OK = 0x0;

            var result = (int) messageBoxW_MH.invokeExact(NULL, lpText, lpCaption, MB_OK);
            System.out.println(STR."MessageBox result: \{result}");
        }
    }

    private static MemorySegment L(Arena arena, String str) {
        // https://stackoverflow.com/questions/66072117/why-does-windows-use-utf-16le
        var bs = str.getBytes(StandardCharsets.UTF_16LE);
        var ms = arena.allocate(bs.length + Short.BYTES);
        MemorySegment.copy(bs, 0, ms, ValueLayout.JAVA_BYTE, 0, bs.length);
        // fix: 乱码 UTF_16LE 双字节
        ms.set(ValueLayout.JAVA_SHORT, bs.length, (short) '\0');

        return ms;
    }
}

