package com.seconds.utils;

import java.util.HashMap;

public class TrieNode {
    public HashMap<Character, TrieNode> children;
    public TrieNode(){
        children = new HashMap<>();
    }
}
