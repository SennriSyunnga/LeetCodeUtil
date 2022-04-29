package per.sennri.leetcode.utils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 用来反序列化常见的Leetcode输入用例
 * 便于本地化测试
 * @author Sennri
 */
public class DeserializeUtil {
    /**
     * 建立链表
     * @param nums
     * @return
     */
    public static ListNode getListNodeFromArray(int[] nums) {
        int len = nums.length;
        ListNode res = null;
        for (int index = len - 1; index >= 0; index--) {
            res = new ListNode(nums[index], res);
        }
        return res;
    }

    /**
     * 从String中反序列化链表
     * @param s 字符串，如"[1,2,3,4, 5, 6,7]"
     * @return
     */
    public static ListNode getListNodeFromString(String s) {
        int[] array = getIntArrayFromString(s);
        return getListNodeFromArray(array);
    }

    final static Pattern COMMA_PATTERN = Pattern.compile("[,]");

    /**
     * 从String中反序列化建立int[]数组
     * @param s 字符串，如"[1,2,3,4, 5, 6,7]"
     * @return
     */
    public static int[] getIntArrayFromString(String s) {
        s = s.trim();
        // 去除前后括号
        s = s.substring(1, s.length() - 1);
        String[] ss = COMMA_PATTERN.split(s);
        int len = ss.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = Integer.parseInt(ss[i].trim());
        }
        return res;
    }

    /**
     * 从String中反序列化建立int[][]的数组
     * @param s 字符串，如"[[1,2,3,4, 5, 6,7]], [1]"
     * @return
     */
    public static int[][] getInt2DimArrayFromString(String s) {
        s = s.trim();
        // 去除前后中括号, 当然，效率起见也能在下面从 1 遍历到 len - 2
        s = s.substring(1, s.length() - 1);
        char[] chars = s.toCharArray();
        // avoid Integer.MIN_VALUE;
        long temp = 0;
        List<List<Integer>> rows = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        boolean preIsNum = false;
        boolean minus = false;
        for (char c : chars) {
            if (c == ']') {
                if (minus) {
                    row.add((int) -temp);
                } else {
                    row.add((int) temp);
                }
                temp = 0;
                preIsNum = false;
                minus = false;
                rows.add(row);
                row = new ArrayList<>();
            } else if (c == '[') {
                preIsNum = false;
            } else {
                if (c != ' ') {
                    if (c == ',') {
                        if (preIsNum) {
                            if (minus) {
                                row.add((int) -temp);
                            } else {
                                row.add((int) temp);
                            }
                            temp = 0;
                            minus = false;
                        }
                        preIsNum = false;
                    } else if (c == '-') {
                        minus = true;
                    } else {
                        temp = temp * 10 + (c - '0');
                        preIsNum = true;
                    }
                }
            }
        }
        int size = rows.size();
        int[][] res = new int[size][];
        for (int i = 0; i < size; i++) {
            res[i] = rows.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
        return res;
    }

    // 几个参考写法

    public static Integer[] transformIntArrayToIntegerArray(int[] array) {
        return Arrays.stream(array).boxed().toArray(Integer[]::new);
    }

    public static List<Integer> transformIntArrayToList(int[] array) {
        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }

    public static Integer[] transformListToIntegerArray(List<Integer> list) {
        return list.toArray(new Integer[0]);
    }

    public static int[] transformListToIntArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }


    /**
     * 序列化树
     * @param root
     * @return
     */
    public static String serializeTree(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        // ArrayDeque不支持null值
        Deque<TreeNode> layer = new LinkedList<>();
        StringBuilder sb = new StringBuilder("[");
        layer.offer(root);
        int size = layer.size();
        while (size > 0) {
            for (int i = 0;i < size;i++){
                TreeNode node = layer.poll();
                if (node == null){
                    sb.append("null").append(",");
                }else{
                    sb.append(node.val).append(",");
                    layer.offer(node.left);
                    layer.offer(node.right);
                }
            }
            size = layer.size();
        }
        int sbLen = sb.length() - 1;
        // remove ','
        sb.deleteCharAt(sbLen);
        sbLen--;
        while (sb.charAt(sbLen) == 'l'){
            sb.delete(sbLen - 4, sbLen + 1);
            sbLen -= 5;
        }
        return sb.append(']').toString();
    }

    /**
     * 反序列化树
     * @param data
     * @return
     */
    public static TreeNode deserializeTree(String data) {
        String[] strings = data.substring(1, data.length() - 1).split("[,]");
        // do sth here
        int len = strings.length;
        String first = strings[0].trim();
        if ("".equals(first) || "null".equals(first)) {
            return null;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode head = new TreeNode(Integer.parseInt(strings[0].trim()));
        deque.offer(head);
        boolean isLeft = true;
        for (int i = 1; i < len; i++) {
            String s = strings[i].trim();
            if ("null".equals(s)) {
                if (!isLeft) {
                    deque.poll();
                }
            } else {
                TreeNode node = new TreeNode(Integer.parseInt(s));
                if (isLeft) {
                    deque.peek().left = node;
                } else {
                    deque.poll().right = node;
                }
                deque.offer(node);
            }
            isLeft = !isLeft;
        }
        return head;
    }
}
