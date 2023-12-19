package xianzhan.frame.os.windows.user32;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import static xianzhan.frame.os.windows.util.StrUtil.L;

/**
 * MessageBoxW demo
 *
 * @author xianzhan
 * @since 2023-12-18
 */
public class MessageBoxWMain {

    public static void main(String[] args) throws Throwable {
        try (var arena = Arena.ofConfined()) {
            // https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-messageboxw
            var messageBoxW_MH = User32.find("MessageBoxW", FunctionDescriptor.of(
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
}

