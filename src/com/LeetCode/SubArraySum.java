package com.LeetCode;

import java.util.HashMap;

/**
 * 前缀和
 */
public class SubArraySum {

    public static void main(String[] args) {
        int[] nums = {3, 5, 2 ,-2, 4, 1};
        int res = subarraysum(nums, 5);
        System.out.println("res="+res);
    }

    public static int subarraysum ( int[] nums,int k) {
        int n = nums . length;
        // map:前缀和->该前缀和出现的次数
        HashMap<Integer, Integer> presum = new HashMap<>( );
        //base case
        presum. put(0,1);
        int ans = 0, sume_i = 0;
        for (int i = 0; i < n; i++) {
            sume_i += nums[i];
            //这是我们想找的前缀和nums[0..j]
            int sumo_j = sume_i - k ;
            //如果前面有这个前缀和，则直接更新答案
            if (presum.containsKey ( sumo_j))
                ans += presum . get ( sumo_j);
            //把前缀和nums [o..i]加入并记录出现次数
            presum.put(sume_i, presum.getOrDefault( sume_i,0) +1);
        }
        return ans;
    }

}
