package xianzhan.note.algorithm.sort;

/**
 * @author xianzhan
 * @since 2024-04-29
 */
public class Sorter {

    /**
     * 冒泡排序
     *
     * @param arr 待排序数组
     */
    public static void bubble(int[] arr) {
        for (var i = 0; i < arr.length - 1; i++) {
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    var temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr 待排序数组
     */
    public static void select(int[] arr) {
        for (var i = 0; i < arr.length - 1; i++) {
            var minIndex = i;
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    // 最小索引
                    minIndex = j;
                }
            }

            // 与冒泡排序相比，交换次数少
            var temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * 插入排序
     *
     * @param arr 待排序数组
     */
    public static void insert(int[] arr) {
        for (var i = 0; i < arr.length - 1; i++) {
            var cur = arr[i + 1];
            var curI = i;
            while (curI >= 0 && cur < arr[curI]) {
                // 比当前值大的往后移位
                arr[curI + 1] = arr[curI];
                curI--;
            }
            // 插入
            arr[curI + 1] = cur;
        }
    }
}
