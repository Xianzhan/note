package xianzhan.note.os.windows.user32;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;

/**
 * @author xianzhan
 * @since 2023-12-19
 */
class User32 {

    private static class Holder {
        private static final SymbolLookup USER32 = SymbolLookup.libraryLookup("User32", Arena.global());
    }

    public static MethodHandle find(String name, FunctionDescriptor fd) {
        return Linker.nativeLinker()
                .downcallHandle(Holder.USER32.find(name)
                        .orElseThrow(() -> new RuntimeException(STR."\{name} Not Found")), fd);
    }
}
