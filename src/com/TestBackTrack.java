package com;

import java.util.*;

public class TestBackTrack {

    public static void main(String[] args) {
//        int[] nums = {1,2,3};
//        TestBackTrack backTrack = new TestBackTrack();
//        List<List<Integer>> items = backTrack.permute(nums);
//        for (int i = 0; i < items.size(); i++) {
//            System.out.println(Arrays.toString(items.get(i).toArray()));
//        }

//        int nums = TestBackTrack.coinChange(new int[]{1, 2, 5}, 11);
//        System.out.println("nums="+nums);

//        int t = superEggDrop(2, 100);
//        System.out.println("t="+t);
//        t = superEggDrop2(2, 100);
//        System.out.println("t="+t);
//        t = superEggDrop3(2, 100);
//        System.out.println("t="+t);

//        int len = lengthOfLIS(new int[]{0,6,8,7,8,2});
//        int len = lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
//        int len2 = lengthOfLIS2(new int[]{0,1,0,3,2,3});
//        System.out.println("len2="+len2);
//        int len = lengthOfLIS(new int[]{0,1,0,3,2,3});

        int left = left_bound(new int[]{2,3,5,7}, 4);
        System.out.println(left);
    }
    // 判断 s 中是否存在 t 的排列
//    private static boolean checkInclusion(string t, string s) {
//        Map<char, int> need, window;
//        for (char c : t) need[c]++;
//
//        int left = 0, right = 0;
//        int valid = 0;
//        while (right < s.size()) {
//            char c = s[right];
//            right++;
//            // 进行窗口内数据的一系列更新
//            if (need.count(c)) {
//                window[c]++;
//                if (window[c] == need[c])
//                    valid++;
//            }
//
//            // 判断左侧窗口是否要收缩
//            while (right - left >= t.size()) {
//                // 在这里判断是否找到了合法的子串
//                if (valid == need.size())
//                    return true;
//                char d = s[left];
//                left++;
//                // 进行窗口内数据的一系列更新
//                if (need.count(d)) {
//                    if (window[d] == need[d])
//                        valid--;
//                    window[d]--;
//                }
//            }
//        }
//        // 未找到符合条件的子串
//        return false;
//    }
    // 搜索左侧边界的二分搜索
    private static int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left;
    }

    private static int lengthOfLIS2(int[] nums) {
        int i=1, n=nums.length;
        if (n == 0) {
            return 0;
        }
        int len = 1;
        int[] dp = new int[n+1];
        dp[len] = nums[0];
        for(;i<n;i++) {
            if(nums[i]>dp[len]){
                dp[++len] = nums[i];
                System.out.println(Arrays.toString(dp));
            } else {
                int l=1,r=len,pos=0;
                while(l<=r){
                    int mid = (l + r) >> 1;
                    if(dp[mid]<nums[i]){
                        pos=mid;
                        l=mid+1;
                    } else {
                        r=mid-1;
                    }
                }
                dp[pos + 1]=nums[i];
                System.out.println("i="+i+"   "+Arrays.toString(dp)+"  pos="+pos);

            }
        }

        return len;
    }

    private static int lengthOfLIS(int[] nums) {
        //https://leetcode.cn/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
                System.out.println("i="+i+"   "+Arrays.toString(d));
            }
        }
        return len;
    }

    private static int superEggDrop3(int K, int N) {
        int[][] middleResults = new int[K + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            middleResults[1][i] = i; // only one egg
            middleResults[0][i] = 0; // no egg
        }
        for (int i = 1; i <= K; i++) {
            middleResults[i][0] = 0; // zero floor
        }

        for (int k = 2; k <= K; k++) { // start from two egg
            for (int n = 1; n <= N; n++) {
                int tMinDrop = N * N;
                for (int x = 1; x <= n; x++) {
                    tMinDrop = Math.min(tMinDrop, 1 + Math.max(middleResults[k - 1][x - 1], middleResults[k][n - x]));
                }
                middleResults[k][n] = tMinDrop;
            }
        }

        return middleResults[K][N];
    }

    //扔鸡蛋
    private static int calcF(int K, int T) {
        if (T == 1 || K == 1) return T + 1;
        return calcF(K - 1, T - 1) + calcF(K, T - 1);
    }
    private static int superEggDrop(int K, int N) {
        int T = 1;
        while (calcF(K, T) < N + 1) T++;
        return T;
    }

    private static Map<Integer, Integer> memory = new HashMap<Integer, Integer>();
    private static int superEggDrop2(int k, int n) {
        return dp(k, n);
    }
    private static int dp(int k, int n) {
        if (!memory.containsKey(n * 100 + k)) {
            int ans;
            if (n == 0) {
                ans = 0;
            } else if (k == 1) {
                ans = n;
            } else {
                int lo = 1, hi = n;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(k - 1, x - 1);
                    int t2 = dp(k, n - x);

                    if (t1 < t2) {
                        lo = x;
                    } else if (t1 > t2) {
                        hi = x;
                    } else {
                        lo = hi = x;
                    }
                }

                ans = 1 + Math.min(Math.max(dp(k - 1, lo - 1), dp(k, n - lo)),
                                   Math.max(dp(k - 1, hi - 1), dp(k, n - hi)));
            }

            memory.put(n * 100 + k, ans);
        }

        return memory.get(n * 100 + k);
    }

    //找零钱
    private static int[] memo;
    private static int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        // 备忘录初始化为⼀个不会被取到的特殊值，代表还未被计算
        Arrays.fill(memo, -666);
        list = new LinkedList<>();
        return dp(coins, amount);
    }
    private static LinkedList<Integer> list = new LinkedList<>();
    private static int dp(int[] coins, int amount) {
        if (amount == 0) {
            System.out.println(Arrays.toString(list.toArray()));
            return 0;
        }
        if (amount < 0) return -1;
        // 查备忘录，防⽌重复计算
        if (memo[amount] != -666) {
            System.out.println("**memo= "+memo[amount]+"   amount="+amount);
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            list.add(coin);
            // 计算⼦问题的结果
            int subProblem = dp(coins, amount - coin);
            System.out.println("** "+Arrays.toString(list.toArray()));
            // ⼦问题⽆解则跳过
            if (subProblem == -1) {
                list.removeLast();
                continue;
            }
            list.removeLast();
            System.out.println("**res= "+res+"   "+subProblem);
            // 在⼦问题中选择最优解，然后加⼀
            res = Math.min(res, subProblem + 1);
        }
        System.out.println("res= "+res+"   amount="+amount);
        // 把计算结果存⼊备忘录
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }


    List<List<Integer>> res = new LinkedList<>();
    /* 主函数，输⼊⼀组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        // 「路径」中的元素会被标记为 true，避免重复使⽤
        boolean[] used = new boolean[nums.length];

        backtrack(nums, track, used);
        return res;
    }
    // 路径：记录在 track 中
// 选择列表：nums 中不存在于 track 的那些元素（used[i] 为 false）
// 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // 排除不合法的选择
            if (used[i]) {
                // nums[i] 已经在 track 中，跳过
                continue;
            }
            // 做选择
            track.add(nums[i]);
//            System.out.print(nums[i]);
            used[i] = true;
            // 进⼊下⼀层决策树
            backtrack(nums, track, used);
            // 取消选择
            track.removeLast();
            used[i] = false;
            System.out.println(Arrays.toString(track.toArray()));
        }
    }
}
