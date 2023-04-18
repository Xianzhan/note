package xianzhan.frame.ffi.jdk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

/**
 * <a href="https://openjdk.org/projects/panama/">Project Panama</a>
 *
 * @author xianzhan
 * @since 2023-04-18
 */
public class FFIJdkTest {

    /**
     * <pre>
     * struct Point2d {
     *     double x;
     *     double y;
     * } point = {3.0, 4.0}
     * </pre>
     */
    @Test
    public void testAllocateStruct() {
        try (Arena arena = Arena.openConfined()) {
            MemorySegment point = arena.allocate(Double.BYTES * 2);
            point.set(ValueLayout.JAVA_DOUBLE, 0, 3.0);
            point.set(ValueLayout.JAVA_DOUBLE, 8, 4.0);

            System.out.println(point);
            Assertions.assertEquals(3.0, point.get(ValueLayout.JAVA_DOUBLE, 0));
            Assertions.assertEquals(4.0, point.get(ValueLayout.JAVA_DOUBLE, 8));
        } // free
    }

    /**
     * <pre>
     * struct Point2d {
     *     double x;
     *     double y;
     * } point = {3.0, 4.0}
     * </pre>
     */
    @Test
    public void testAllocateStructWithName() {
        StructLayout point2dStruct = MemoryLayout.structLayout(
                ValueLayout.JAVA_DOUBLE.withName("x"),
                ValueLayout.JAVA_DOUBLE.withName("y")
        );
        VarHandle xHandle = point2dStruct.varHandle(MemoryLayout.PathElement.groupElement("x"));
        VarHandle yHandle = point2dStruct.varHandle(MemoryLayout.PathElement.groupElement("y"));

        try (Arena arena = Arena.openConfined()) {
            MemorySegment point = arena.allocate(point2dStruct);
            xHandle.set(point, 3.0);
            yHandle.set(point, 4.0);

            System.out.println(point);
            Assertions.assertEquals(3.0, xHandle.get(point));
            Assertions.assertEquals(3.0, point.get(ValueLayout.JAVA_DOUBLE, 0));

            Assertions.assertEquals(4.0, yHandle.get(point));
            Assertions.assertEquals(4.0, point.get(ValueLayout.JAVA_DOUBLE, 8));
        } // free
    }

}
