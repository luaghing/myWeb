package leetcode;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lys on 2018/9/15.
 *
 * @author lyh
 */
public class Sum {
    @Test
    public void demo() {

        int[] nums = new int[]{2, 7, 9, 10, 12, 13};
        int target = 12;
        for (int i = 0; i < twoSum(nums, target).length; i++) {
            System.out.println(twoSum(nums, target)[i]);
        }
        System.out.println(Arrays.toString(twoSum(nums, target)));
        for (int a : twoSum(nums, target)
        ) {
            System.out.println(a);
        }
        System.out.println(Solution.reverse(321));
        System.out.println(Solution1.removeNthFromEnd(new ListNode(3), 2));

    }
    @Test
    public  void demo2(){
        int[] height = {1,2};
        System.out.println(new Solution2().maxArea(height));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        nums = new int[]{2, 7, 9, 10, 12, 13};
        target = 12;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int dif = target - nums[i];
            if (map.containsKey(dif) && map.get(dif) != i) {
                return new int[]{i, map.get(dif)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}

class Solution {
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

class Solution1 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.nextNode = head;
        int lentgh = 0;
        ListNode first = head;
        while (first != null) {
            lentgh++;
            first = first.nextNode;
        }
        lentgh -= n;
        first = dummy;
        while (lentgh > 0) {
            lentgh--;
            first = first.nextNode;
        }
        first.nextNode = first.nextNode.nextNode;
        return dummy.nextNode;
    }
}

class ListNode {
    int val;
    ListNode nextNode;

    ListNode(int val) {
        this.val = val;
        this.nextNode = null;
    }
}

/* 水面积最大*/
class Solution2 {
    public int maxArea(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxarea = Math.max(maxarea, Math.min(height[i], height[j] * (j - i)));
            }
        }
        return maxarea;
    }
}

