package org.example;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ipNode {

    public Map<Integer, ipNode> children;

    @Override
    public String toString() {
        return String.format("children = %s%n", this.children.keySet());
    }
}
