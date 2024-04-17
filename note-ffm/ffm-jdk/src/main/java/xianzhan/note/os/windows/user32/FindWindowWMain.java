package xianzhan.note.os.windows.user32;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

import static xianzhan.note.os.windows.util.StrUtil.L;

/**
 * @author xianzhan
 * @since 2023-12-19
 */
public class FindWindowWMain {

    public static void main(String[] args) throws Throwable {
        // https://learn.microsoft.com/zh-cn/windows/win32/api/winuser/nf-winuser-findwindoww
        MethodHandle findWindowW_MH = User32.find("FindWindowW", FunctionDescriptor.of(
                // 返回值
                ValueLayout.ADDRESS,
                // className
                ValueLayout.ADDRESS,
                // windowsName
                ValueLayout.ADDRESS
        ));
        try (Arena arena = Arena.ofConfined()) {
            // 需要打开 "记事本"
            var ms = (MemorySegment) findWindowW_MH.invokeExact(MemorySegment.NULL, L(arena, "无标题 - Notepad"));
            System.out.println(ms);

            if (ms.address() != 0) {
                // 当 "记事本" 已打开，则将它关闭

                // https://learn.microsoft.com/zh-cn/windows/win32/api/winuser/nf-winuser-sendmessagew
                var sendMessageW_MH = User32.find("SendMessageW", FunctionDescriptor.of(
                        // 返回值
                        ValueLayout.ADDRESS,
                        // 句柄
                        ValueLayout.ADDRESS,
                        // 操作
                        ValueLayout.JAVA_INT,
                        ValueLayout.ADDRESS,
                        ValueLayout.ADDRESS
                ));

                // https://learn.microsoft.com/zh-cn/windows/win32/winmsg/wm-close
                var VM_CLOSE = 0x10;
                var _ = (MemorySegment) sendMessageW_MH.invokeExact(ms, VM_CLOSE, MemorySegment.NULL, MemorySegment.NULL);
            } else {
                System.out.println("并未有 '记事本' 打开");
            }
        }
    }
}
