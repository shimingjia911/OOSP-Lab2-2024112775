import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * 测试用例总体设计原则：
 * 1. 等价类划分：覆盖一般示例、完整整除链、无整除关系、乱序输入和单元素输入 ；
 * 2. 边界值：覆盖数组长度为 1 和数组元素较大的情况；
 * 3. 对存在多个合法答案的输入，只校验子集长度、元素来源和任意两元素之间的整除关系。
 */
class L2024112775_3_Test {

    /**
     * 测试目的：验证题目示例 1，存在多个等价最优答案。
     * 测试用例：nums = [1, 2, 3]，最大子集长度为 2。
     */
    @Test
    void testExample1() {
        int[] nums = {1, 2, 3};
        List<Integer> result = new Solution3().largestDivisibleSubset(nums);

        assertValidDivisibleSubset(nums, result, 2);
    }

    /**
     * 测试目的：验证完整整除链。
     * 测试用例：nums = [1, 2, 4, 8]，期望结果为 [1, 2, 4, 8]。
     */
    @Test
    void testCompleteChain() {
        int[] nums = {1, 2, 4, 8};
        List<Integer> result = new Solution3().largestDivisibleSubset(nums);

        assertEquals(List.of(1, 2, 4, 8), result);
    }

    /**
     * 测试目的：验证乱序输入经过排序后仍能得到完整整除链。
     * 测试用例：nums = [8, 1, 4, 2]。
     */
    @Test
    void testUnsortedInput() {
        int[] nums = {8, 1, 4, 2};
        List<Integer> result = new Solution3().largestDivisibleSubset(nums);

        assertEquals(List.of(1, 2, 4, 8), result);
    }

    /**
     * 测试目的：验证单元素边界情况。
     * 测试用例：nums = [7]，期望结果为 [7]。
     */
    @Test
    void testSingleElementBoundary() {
        int[] nums = {7};
        List<Integer> result = new Solution3().largestDivisibleSubset(nums);

        assertEquals(List.of(7), result);
    }

    /**
     * 测试目的：验证不存在整除关系时返回任意一个单元素子集。
     * 测试用例：nums = [2, 3, 5, 7]。
     */
    @Test
    void testNoDivisiblePair() {
        int[] nums = {2, 3, 5, 7};
        List<Integer> result = new Solution3().largestDivisibleSubset(nums);

        assertValidDivisibleSubset(nums, result, 1);
    }

    /**
     * 测试目的：验证较大整数不会产生溢出，并确认长度为 1 的输入不会越界。
     * 测试用例：[1000000000, 500000000, 250000000]。
     */
    @Test
    void testLargeValues() {
        int[] nums = {1000000000, 500000000, 250000000};
        List<Integer> result = new Solution3().largestDivisibleSubset(nums);

        assertEquals(List.of(250000000, 500000000, 1000000000), result);
    }

    /**
     * 校验返回结果确为输入中的元素、数量正确、无重复，且任意两元素互相整除。
     */
    private void assertValidDivisibleSubset(
            int[] nums,
            List<Integer> result,
            int expectedSize) {

        assertEquals(expectedSize, result.size());
        assertEquals(result.size(), new HashSet<>(result).size());

        Set<Integer> allowed = new HashSet<>();
        for (int value : nums) {
            allowed.add(value);
        }
        assertTrue(allowed.containsAll(result));

        for (int i = 0; i < result.size(); i++) {
            for (int j = i + 1; j < result.size(); j++) {
                int a = result.get(i);
                int b = result.get(j);
                assertTrue(
                        a % b == 0 || b % a == 0,
                        "Elements " + a + " and " + b + " are not divisible");
            }
        }
    }
}
