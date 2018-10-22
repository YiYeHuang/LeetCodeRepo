package leetcode.dp;

import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.LinkedIn;
import leetcode.tag.company.Microsoft;
import leetcode.tag.type.ArrayTag;
import leetcode.tag.type.DP;

/**
 Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:

 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */

@Microsoft
@Bloomberg
@LinkedIn
public class MaxSubArray
{
    /**
     * this is a optimization problem So when it comes to DP, the first thing for us to figure out
     * is the format of the sub problem.
     * 
     * At first, means the maxSubArray for A[i: j].
     * 
     * In this way, our goal is to figure out what
     *  maxSubArray(A, 0, A.length - 1) is.
     * 
     * However, if we define the format of the sub problem in this way, it's hard to find the
     * connection from the sub problem to the original problem(at least for me). In other words, I
     * can't find a way to divided the original problem into the sub problems and use the solutions
     * of the sub problems to somehow create the solution of the original one.
     * 
     * So I change the format of the sub problem into something like: 
     * maxSubArray(int A[], int i),
     * which means the maxSubArray for A[0:i] which must has A[i] as the end element. Note that now
     * the sub problem's format is less flexible and less powerful than the previous one because
     * there's a limitation that A[i] should be contained in that sequence and we have to keep track
     * of each solution of the sub problem to update the global optimal value. However, now the
     * connect between the sub problem & the original one becomes clearer:
     * 
     * 
     * maxSubArray(A, i) = maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0 + A[i];
     *
     * int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
     *          dp: {-2, 1, -2, 4,  3, 5, 6,  1, 5}
     */

    @DP
    @ArrayTag
    public static int maxSubArray(int[] nums)
    {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        
        for(int i = 1; i < n; i++){
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }

    public static void main(String[] args)
    {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}
