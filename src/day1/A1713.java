package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Frame {
    int idx;
    int cnt;

    public Frame(int idx, int cnt) {
        this.idx = idx;
        this.cnt = cnt;
    }
}

public class A1713 {

    static int n, t;
    static int[] recommend;
    static HashMap<Integer, Frame> frame = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(process());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        t = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        recommend = new int[t];
        for (int i = 0; i < t; i++) {
            recommend[i] = Integer.parseInt(st.nextToken());
        }
    }

    static StringBuilder process() {
        for (int i = 0; i < t; i++) {
            int tmp = recommend[i];

            if (frame.containsKey(tmp)) {
                frame.get(tmp).cnt++;
                continue;
            }

            if (frame.size() < n) {
                frame.put(tmp, new Frame(i, 1));
            } else {
                ArrayList<Integer> list = new ArrayList<>(frame.keySet());
                list.sort((f1, f2) -> {
                    if (frame.get(f1).cnt == frame.get(f2).cnt) {
                        return frame.get(f2).idx - frame.get(f1).idx;
                    }
                    return frame.get(f2).cnt - frame.get(f1).cnt;
                });
                frame.remove(list.get(list.size() - 1));
                frame.put(tmp, new Frame(i, 1));
            }
        }

        ArrayList<Integer> list = new ArrayList<>(frame.keySet());
        StringBuilder sb = new StringBuilder();
        list.sort(Integer::compare);

        for (Integer i : list) {
            sb.append(i).append(" ");
        }

        return sb;
    }

}
