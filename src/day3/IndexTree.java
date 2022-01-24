package day3;

import java.util.Arrays;

public class IndexTree {

    int[] nodes;    // 노드의 값(부분합)
    int[] nums; // 실제 값
    int height, leafCount;  // 높이, 리프노드 개수

    public IndexTree(int[] nums) {
        this.nums = nums;
        int len = nums.length - 1;
        this.height = 0;
        while (len != 0) {  // log(len)이 높이
            len /= 2;
            this.height++;
        }
        this.leafCount = (int) Math.pow(2, this.height - 1);    // 리프노드 개수는 (2^(H-1))
        this.nodes = new int[(int) Math.pow(2, this.height)];   // 전체 노드의 개수는 (2^H)-1 (0번 인덱스 사용안함)
    }

    public void makeTree() {
        makeSubTree(1, 1, leafCount);
    }

    private int makeSubTree(int node, int left, int right) {
        if (left == right) {    // 탐색 끝
            if (left < nums.length) // nums인 리프노드 일 때
                return nodes[node] = nums[left];
            else
                return nodes[node] = 0;
        }
        int mid = (left + right) / 2;
        nodes[node] = makeSubTree(node * 2, left, mid); // 왼쪽 자식
        nodes[node] += makeSubTree(node * 2 + 1, mid + 1, right);   // 오른쪽 자식
        return nodes[node];
    }

    public int getPartialSum(int targetLeft, int targetRight) {
        return target(1, 1, leafCount, targetLeft, targetRight);
    }

    private int target(int node, int left, int right, int tLeft, int tRight) {
        // target 구간과 관련 없는 경우
        if (left > tRight || right < tLeft) {
            return 0;
        }

        // target 구간 포함하는 경우
        if (tLeft <= left && right <= tRight) {
            return nodes[node];
        }
        int mid = (left + right) / 2;
        return target(node * 2, left, mid, tLeft, tRight) +
                target(node * 2 + 1, mid + 1, right, tLeft, tRight);
    }

    public void update(int targetIndex, int targetValue) {
        subUpdate(1, 1, leafCount, targetIndex, targetValue - nums[targetIndex]);
        nums[targetIndex] = targetValue;
    }

    public void subUpdate(int node, int left, int right, int t_i, int diff) {
        // 해당 부분합이 targetIndex 포함일 때
        if (t_i >= left && t_i <= right) {
            nodes[node] += diff;
            if (left == right) {
                return;
            }
            int mid = (left + right) / 2;
            subUpdate(node * 2, left, mid, t_i, diff);
            subUpdate(node * 2 + 1, mid + 1, right, t_i, diff);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("indexTree {")
                .append("\n\tnode = ").append(Arrays.toString(nodes))
                .append("\n\tnums = ").append(Arrays.toString(nums))
                .append("\n\theight = ").append(height)
                .append("\n\tleafCount = ").append(leafCount)
                .append("\n}");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] numbers = {0, 8, 3, 26, 1, 7, 2, 4, 10};
        System.out.println(Arrays.toString(numbers));
        IndexTree it = new IndexTree(numbers);
        System.out.println("Before making tree");
        System.out.println(it.toString());
        it.makeTree();
        System.out.println("After making tree");
        System.out.println(it.toString());
        System.out.print(2 + " ~ " + 5 + " Sum: ");
        System.out.println(it.getPartialSum(2, 5));
        System.out.print(1 + " ~ " + 6 + " Sum: ");
        System.out.println(it.getPartialSum(1, 6));
        it.update(3, 0);
        System.out.println("index 3: 26, update -> 0");
        System.out.println(it.toString());
    }
}
