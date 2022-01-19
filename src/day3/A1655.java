package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class A1655 {

    static int n;
    static Heap heap = new Heap();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        process();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            heap.insert(Integer.parseInt(br.readLine()));
        }
    }

    static void process() {
        for (int i = 0; i < n; i++) {
            sb.append()
        }
    }
}

class Heap {
    private ArrayList<Integer> heap;

    public Heap() {
        heap = new ArrayList<>();
        heap.add(0);
    }

    public void insert(int x) {
        heap.add(x);
        int p = heap.size() - 1;
        while (p > 1 && heap.get(p / 2) > heap.get(p)) {
            int tmp = heap.get(p / 2);
            heap.set(p / 2, x);
            heap.set(p, tmp);
            p /= 2;
        }
    }

    public int delete() {
        if (heap.size() - 1 < 1) {
            return 0;
        }

        int del = heap.get(1);
        int pos = 1;

        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        while ((pos * 2) < heap.size()) {
            int min = heap.get(pos * 2);
            int minP = pos * 2;

            if (((pos * 2 + 1) < heap.size()) && min > heap.get(pos * 2 + 1)) {
                min = heap.get(pos * 2 + 1);
                minP = pos * 2 + 1;
            }

            if (min > heap.get(pos)) {
                break;
            }

            int tmp = heap.get(pos);
            heap.set(pos, heap.get(minP));
            heap.set(minP, tmp);
            pos = minP;
        }

        return del;
    }

    public int get() {
        if (this.heap.size() % 2 == 0) {
            return this.heap.get((heap.size() / 2) - 1);
        }
        return this.heap.get(heap.size() / 2);
    }
}
