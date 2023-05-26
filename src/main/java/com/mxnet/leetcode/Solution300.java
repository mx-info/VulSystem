package com.mxnet.leetcode;

import java.util.Arrays;

public class Solution300 {

    public static void main(String[] args) {

        int[] nums = {10,9,2,5,3,7,101,18};
        lengthOfLIS(nums);

    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp,1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
