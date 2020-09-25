package com.zaumal.leetcode;

/*
 * 
面试题 17.10. 主要元素
数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。

示例 1：

输入：[1,2,5,9,5,9,5,5,5]
输出：5
 

示例 2：

输入：[3,2]
输出：-1
 

示例 3：

输入：[2,2,1,1,1,2,2]
输出：2
 

说明：
你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？

摩尔投票算法：
假定一定存在多数元素
候选人(cand_num)初始化为nums[0]，票数count初始化为1。
当遇到与cand_num相同的数，则票数count = count + 1，否则票数count = count - 1。
当票数count为0时，更换候选人，并将票数count重置为1。
遍历完数组后，cand_num即为最终答案。

为何这行得通呢？
投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
且“多数元素”的个数> ⌊ n/2 ⌋，其余元素的个数总和<= ⌊ n/2 ⌋。
因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。

无论数组是1 2 1 2 1，亦或是1 2 2 1 1，总能得到正确的候选人。

作者：gfu
链接：https://leetcode-cn.com/problems/majority-element/solution/3chong-fang-fa-by-gfu-2/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 */
public class Solution17_10 {
	//解题思路：摩尔投票算法，1对1抵消剩余的就是超过一半的
    public int majorityElement(int[] nums) {
    	if(nums.length == 0) {
    		return -1;
    	}
    	int m = nums[0],c = 1;
    	for(int i = 1; i < nums.length; i++) {
    		if(nums[i] == m) {
    			c++;
    		}else {
    			c--;
    		}
    		if(c == 0) {
				m = nums[i];
				c = 1;
			}
    	}
    	if(c > 0) {
    		c = 0;
        	for(int num : nums) {
        		if(num == m) {
        			c++;
        		}
        	}
        	if( c > nums.length / 2) {
        		return m;
        	}
    	}
    	return -1;
    }
    
    public int majorityElement2(int[] nums) {
        if(nums.length == 0) {
        	return -1;
        }
        int m = nums[0],c = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == m){
                c++;
            }else {
            	c--;
            }
            if(c == -1){
                m = nums[i];
                c = 1;
            }
        }
        return c > 0 ? m : -1;
    }
    
    public int majorityElement3(int[] nums) {
        int cand_num = nums[0], count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (cand_num == nums[i])
                ++count;
            else if (--count == 0) {
                cand_num = nums[i];
                count = 1;
            }
        }
        return cand_num;
    }
    
    public static void main(String[] args) {
    	int[] a1 = {1,2,5,9,5,9,5,5,5};
    	int[] a2 = {3,2};
    	int[] a3 = {2,2,1,1,1,2,2};
    	int[] a4 = {3,2,3};
    	Solution17_10 t = new Solution17_10();
    	System.out.println("{1,2,5,9,5,9,5,5,5} : " + t.majorityElement3(a1));
    	System.out.println("{3,2} : " + t.majorityElement3(a2));
    	System.out.println("{2,2,1,1,1,2,2} : " + t.majorityElement3(a3));
    	System.out.println("{3,2,3} : " + t.majorityElement3(a4));
	}
}