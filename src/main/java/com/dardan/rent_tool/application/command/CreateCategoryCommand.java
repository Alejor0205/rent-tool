package com.dardan.rent_tool.application.command;

public class CreateCategoryCommand {
    private String name;

    public CreateCategoryCommand() {
    }

    public CreateCategoryCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
