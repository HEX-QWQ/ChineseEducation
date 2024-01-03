package com.Bean;

public class TextResource {
    private String title;
    private String id;
    private String type;
    
    public TextResource(String id,String title,String type){
        this.id=id;
        this.title=title;
        this.type=type;
        
    }
    public TextResource(String id,String title){
        this.id=id;
        this.title=title;


    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String page) {
        this.type = page;
    }
}
