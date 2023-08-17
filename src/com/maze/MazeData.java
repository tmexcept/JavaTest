package com.maze;


public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int entranceX, entranceY;
    private int exitX, exitY;

    private int N, M;
    public char[][] maze;
    public boolean[][] path;
    public boolean[][] visited;

    public MazeData(String filename){
        this.N =101;
        this.M = 101;

        visited = new boolean[N][M];
        path = new boolean[N][M];
        maze = new char[N][M];

        for(int i = 0 ; i < N ; i ++)
            for(int j = 0 ; j < M ; j ++)
                if(i%2 == 1 && j%2 == 1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;

        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;
    }

    public int N(){ return N; }
    public int M(){ return M; }
    public int getEntranceX(){return entranceX;}
    public int getEntranceY(){return entranceY;}
    public int getExitX(){return exitX;}
    public int getExitY(){return exitY;}
    public char getMaze(int i, int j){
        if(!inArea(i,j))
            throw new IllegalArgumentException("i or j is out of index in getMaze!");
        return maze[i][j];
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print(){
        System.out.println(N + " " + M);
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
        return;
    }

}