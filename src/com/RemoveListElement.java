package com;

import java.util.Arrays;

public class RemoveListElement {

    public static void main(String[] args) {
        RemoveListElement test = new RemoveListElement();
        int[] nums = {0,1,2,2,3,0,4,2};
        int result = test.removeElement(nums, 2);
        System.out.println(result);
        System.out.println(Arrays.asList(nums));
    }
    public int removeElement(int[] nums, int val) {
        //特殊情况
        if(nums==null){
            return 0;
        }
        int j = 0;//慢指针，i代表快指针
        for(int i = 0;i<nums.length;i++){
            //正常情况直接赋值给i
            if(nums[i]!=val){
                nums[j]=nums[i];
                j++;
            }
            //如果为需要删除的值时，则快指针移动，慢指针不动。
        }
        return j;
    }
}
