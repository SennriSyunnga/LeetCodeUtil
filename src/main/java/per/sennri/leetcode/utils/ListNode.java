package per.sennri.leetcode.utils;

public class ListNode {
    int val;
    ListNode next;
    ListNode pre;
    ListNode(int x) { val = x; }
    ListNode(int x, ListNode node) {
        val = x;
        next = node;
    }
}