package com.zaumal.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 *
229. 求众数 II
给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

示例 1:

输入: [3,2,3]
输出: [3]
示例 2:

输入: [1,1,1,3,3,2,2,2]
输出: [1,2]


解答：
1、因为要找出所有出现超过n/3次的元素，所以最多同时有2个元素的出现次数超过n/3.
2、先选出2个候选人，再逐一进行投票。
 *
 */
public class Solution229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        
        if(null == nums || nums.length == 0) {
        	return res;
        }
        
        int m1 = nums[0],m2 = nums[0];
        int c1 = 0,c2 = 0;
        
        for(int i = 0; i < nums.length; i++) {
        	if(m1 == nums[i]) {
        		c1++;
        		continue;
        	}
        	
        	if(m2 == nums[i]) {
        		c2++;
        		continue;
        	}
        	
        	if(c1 == 0) {
        		m1 = nums[i];
        		c1 = 1;
        		continue;
        	}
        	
        	if(c2 == 0) {
        		m2 = nums[i];
        		c2 = 1;
        		continue;
        	}

        	c1--;
        	c2--;
        }
        
        if(c1 > 0 || c2 > 0) {
        	c1 = 0;
        	c2 = 0;
        	for(int num : nums) {
        		if(num == m1) {
        			c1++;
        		}else if(num == m2) {
        			c2++;
        		}
        	}
        	int k = nums.length / 3;
        	if(c1 > k) {
        		res.add(m1);
        	}
        	if(c2 > k) {
        		res.add(m2);
        	}
        }
        
        return res;
    }
    
    public static void main(String[] args) {
    	int[] a1 = {3,2,3};
    	int[] a2 = {1,1,1,3,3,2,2,2};
    	int[] a3 = {1};
    	int[] a4 = {};
    	int[] a5 = {1,2};
    	Solution229 t = new Solution229();
    	System.out.println("{3,2,3} : " + t.majorityElement(a1));
    	System.out.println("{1,1,1,3,3,2,2,2} : " + t.majorityElement(a2));
    	System.out.println("{1} : " + t.majorityElement(a3));
    	System.out.println("{} : " + t.majorityElement(a4));
    	System.out.println("{1,2} : " + t.majorityElement(a5));
	}
}