import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 *
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 */
class Solution3 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxSize = 1;
        int maxValue = nums[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxValue = nums[i];
            }
        }

        List<Integer> result = new ArrayList<>();
        if (maxSize == 1) {
            result.add(nums[0]);
            return result;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == maxSize && maxValue % nums[i] == 0) {
                result.add(nums[i]);
                maxValue = nums[i];
                maxSize--;
            }
        }

        // 回溯过程按从大到小加入，统一转换为题目示例使用的升序。
        Collections.reverse(result);
        return result;
    }
}
