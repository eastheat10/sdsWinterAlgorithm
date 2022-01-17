package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A1759 {

    static int l, c, vCnt = 0, cCnt = 0;
    static char[] words, tmp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        process();

    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        words = new char[c];
        tmp = new char[l];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            words[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(words);
    }

    static void process() {
        rec(0, 0);
        System.out.println(sb);
    }

    static void rec(int depth, int idx) {
        if (depth == l) {
            if (vCnt >= 1 && cCnt >= 2) {
                sb.append(String.valueOf(tmp)).append('\n');
            }
            return;
        }

        for (int i = idx; i < c; i++) {
            tmp[depth] = words[i];
            upCount(words[i]);
            rec(depth + 1, i + 1);
            tmp[depth] = ' ';
            downCount(words[i]);
        }

    }

    static void upCount(char c) {
        if (isVowel(c)) {
            vCnt++;
        } else {
            cCnt++;
        }
    }

    static void downCount(char c) {
        if (isVowel(c)) {
            vCnt--;
        } else {
            cCnt--;
        }
    }

    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

}
