package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A2143 {

    static int t, n, m;
    static long cnt = 0;
    static int[] arrA, arrB;

    public static void main(String[] args) throws IOException {
        input();
        process();
        System.out.println(cnt);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arrA = new int[n];
        for (int i = 0; i < n; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arrB = new int[m];
        for (int i = 0; i < m; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void process() {
        List<Integer> listA = sub(arrA);
        listA.sort(Integer::compare);
        List<Integer> listB = sub(arrB);
        listB.sort((i, j) -> j - i);

        int lenA = listA.size();
        int lenB = listB.size();

        int pa = 0, pb = 0;
        long cntA, cntB;
        while (pa < lenA && pb < lenB) {
            cntA = 0;
            cntB = 0;
            long curA = listA.get(pa);
            long target = t - curA;

            if (listB.get(pb) == target) {
                while (pa < lenA && curA == listA.get(pa)) {
                    pa++;
                    cntA++;
                }
                while (pb < lenB && target == listB.get(pb)) {
                    pb++;
                    cntB++;
                }
                cnt += (cntA * cntB);
            } else if (listB.get(pb) > target) {
                pb++;
            } else {
                pa++;
            }
        }
    }

    static List<Integer> sub(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int sum;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                list.add(sum);
            }
        }
        return list;
    }
}
