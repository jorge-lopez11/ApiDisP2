package com.grupo04.API_P2;

public class Greeting {
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
    public long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    private final long id;
    private final String content;
}
