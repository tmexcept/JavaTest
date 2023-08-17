package com.maze;

/**
 * 迷宫
 */
public class MazeDataTest {
    public static final char ROAD = ' ';
    public static final char WALL = '#';
    public static final int N = 101;
    public static final int M = N;
    public static final int[][] d ={{-1,0},{0,1},{1, 0},{0,-1}};

    private char[][] maze;
    public boolean[][] path;
    public boolean[][] visited;

    private boolean inArea(int x, int y){
        return x >=0 && x<N && y>=0 && y<M;
    }

    private void go(int x, int y) {
        if(!inArea(x, y))
            return;

        visited[x][y] = true;
        setData(x, y);

        for(int i = 0 ; i < 4 ; i ++){
            int newX = x + d[i][0]*2;
            int newY = y + d[i][1]*2;
            if(inArea(newX, newY) && !visited[newX][newY]){
                setData(x + d[i][0], y + d[i][1]);
                go(newX, newY);
            }
        }

    }

    private void setData(int x, int y){
        if(inArea(x, y))
            maze[x][y] = MazeData.ROAD;
    }

    private void initData(){
        visited = new boolean[N][M];
        path = new boolean[N][M];
        maze = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int i1 = 0; i1 < M; i1++) {
                if(i % 2 == 1 && i1 % 2 == 1){
                    maze[i][i1] = ROAD;
                } else {
                    maze[i][i1] = WALL;
                }
            }
        }
    }
    public static void main(String[] args){
        MazeDataTest mazeData = new MazeDataTest();
        mazeData.initData();

//        for (int i = 0; i < N; i++) {
//            for (int i1 = 0; i1 < M; i1++) {
//                System.out.print(mazeData.maze[i][i1]);
//            }
//            System.out.println();
//        }

        mazeData.go(1, 0);


        for (int i = 0; i < N; i++) {
            for (int i1 = 0; i1 < M; i1++) {
                System.out.print(mazeData.maze[i][i1]);
            }
            System.out.println();
        }
    }
}
