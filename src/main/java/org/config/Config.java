package org.config;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private List<String> inputFiles = new ArrayList<>();
    private String outputPath = ".";
    private String prefix = "";
    private boolean appendMark = false;
    private boolean shortStatistic = false;
    private boolean fullStatistic = false;


    public List<String> getInputFiles() {return inputFiles;}
    public String getOutputPath() {return outputPath;}
    public void setOutputPath(String outputPath) {this.outputPath = outputPath;}
    public String getPrefix() {return prefix;}
    public void setPrefix(String prefix) {this.prefix = prefix;}
    public boolean isAppendMark() {return appendMark;}
    public void setAppendMark(boolean appendMark) {this.appendMark = appendMark;}
    public boolean isShortStatistic() {return shortStatistic;}
    public void setShortStatistic(boolean shortStatistic) {this.shortStatistic = shortStatistic;}
    public boolean isFullStatistic() {return fullStatistic;}
    public void setFullStatistic(boolean fullStatistic) {this.fullStatistic = fullStatistic;}

}
