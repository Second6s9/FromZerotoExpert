package com.seconds.utils;

import java.util.HashMap;

public class Trie {

    public TrieNode node;
    /** Initialize your data structure here. */
    public Trie() {
        node = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = node;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
        }
        cur.children.putIfAbsent('#', new TrieNode());
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = node;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!cur.children.containsKey(c)) return false;
            cur = cur.children.get(c);
        }
        return cur.children.containsKey('#');
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = node;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(!cur.children.containsKey(c)) return false;
            cur = cur.children.get(c);
        }
        return true;
    }


}
