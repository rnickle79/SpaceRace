package com.example.state;

public enum GameState {
    MENU,
    HELP,
    RUNNING,
    GAME_OVER,
    RESET,
    QUIT,
    PAUSE;

    public static GameState status = MENU;
}
