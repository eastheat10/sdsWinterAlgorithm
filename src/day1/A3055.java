package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A3055 {

    static int r, c;
    static int[] start, end;
    static int[][] dist, waterDist, dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static char[][] map;
    static boolean[][] visit;
    final static String KAKTUS = "KAKTUS";

    public static void main(String[] args) throws IOException {
        input();
        process();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r + 1][c + 1];
        dist = new int[r + 1][c + 1];
        waterDist = new int[r + 1][c + 1];
        String s = "";
        for (int i = 1; i <= r; i++) {
            s = br.readLine();
            for (int j = 1; j <= c; j++) {
                map[i][j] = s.charAt(j - 1);
                if (map[i][j] == 'S') {
                    start = new int[]{i, j};
                }
                if (map[i][j] == 'D') {
                    end = new int[]{i, j};
                }
            }
        }
    }

    static void process() {
        waterBfs();
        bfs();

        if (dist[end[0]][end[1]] == -1)
            System.out.println(KAKTUS);
        else
            System.out.println(dist[end[0]][end[1]]);
    }

    static void waterBfs() {
        visit = new boolean[r + 1][c + 1];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                visit[i][j] = false;
                waterDist[i][j] = -1;
                dist[i][j] = -1;
                if (map[i][j] == '*') {
                    q.offer(new int[]{i, j});
                    visit[i][j] = true;
                    waterDist[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if (nx < 1 || ny < 1 || nx > r || ny > c) continue;
                if (map[nx][ny] == 'D' || map[nx][ny] == 'X') continue;
                if (visit[nx][ny]) continue;

                q.offer(new int[]{nx, ny});
                visit[nx][ny] = true;
                waterDist[nx][ny] = waterDist[x][y] + 1;
            }
        }
    }

    static void bfs() {
        visit = new boolean[r + 1][c + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1]});
        visit[start[0]][start[1]] = true;
        dist[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if (nx < 1 || ny < 1 || nx > r || ny > c) continue;
                if (map[nx][ny] == 'X') continue;
                if (waterDist[nx][ny] != -1 && dist[x][y] + 1 >= waterDist[nx][ny]) continue;
                if (visit[nx][ny]) continue;

                q.offer(new int[]{nx, ny});
                visit[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
            }
        }
    }

}
