package com.github.xianzhan.jolcore;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author xianzhan
 * @since 2020-03-15
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(String.class).toPrintable());
    }
}
