package com.denisov26.solution.logParser;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\Иван\\Google Диск\\myMiniProjects\\src\\main\\java\\logParser\\logs"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
    }
}