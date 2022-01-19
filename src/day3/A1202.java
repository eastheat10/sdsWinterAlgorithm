package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1202 {

    static int n, k;
    static Jewel[] jewels;
    static int[] bags;

    public static void main(String[] args) throws IOException {
        input();
        process();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        jewels = new Jewel[n];
        bags = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, value);
        }

        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(jewels, (i, j) -> {
            if (i.weight == j.weight)
                return j.price - i.price;
            return i.weight - j.weight;
        });
        Arrays.sort(bags);
    }

    static void process() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long totalPrice = 0;

        for (int i = 0, j = 0; i < k; i++) {
            while (j < n && jewels[j].weight <= bags[i]) {
                pq.offer(jewels[j++].price);
            }

            if (!pq.isEmpty()) {
                totalPrice += pq.poll();
            }
        }

        System.out.println(totalPrice);
    }
}

class Jewel {
    int weight;
    int price;

    public Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

}
