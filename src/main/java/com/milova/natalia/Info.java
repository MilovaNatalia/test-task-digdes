package com.milova.natalia;

public class Info {
    private String implementationClassName;
    private String packageName;

    private static final String author = "Milova Natalia";
    private static final String gitHubLink = "";

    public Info(String implementationClassName, String packageName) {
        this.implementationClassName = implementationClassName;
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return author + ", " + implementationClassName + ", " + packageName;
    }
}