package com.denisov26.solution.logParser.query;

import java.util.Set;

public interface QLQuery {
    Set<Object> execute(String query);
}