package com.milova.natalia;

import jdk.jshell.spi.ExecutionEnv;

public class Info {
    private String implementationClassName;
    private String packageName;

    private final String author = "Milova Natalia";
    private final String gitHubLink = "https://github.com/MilovaNatalia/test-task-digdes/tree/master";

    public Info(String implementationClassName, String packageName) {
        this.implementationClassName = implementationClassName;
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("author = ").append(author).append("\r\n");
        sb.append("implementationClassName = ").append(implementationClassName).append("\r\n");
        sb.append("packageName = ").append(packageName).append("\r\n");
        sb.append("gitHubLink = ").append(gitHubLink).append("\r\n");
        return sb.toString();
    }
}