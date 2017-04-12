package ru.easmith.FileChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by eku on 06.04.17.
 */
public class WordSet<String> extends HashSet {
    static volatile boolean isDuplicateFound = false;
    final Lock lock = new ReentrantLock();
}
