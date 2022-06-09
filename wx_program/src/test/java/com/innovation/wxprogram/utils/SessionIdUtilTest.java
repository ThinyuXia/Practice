package com.innovation.wxprogram.utils;

import junit.framework.TestCase;

public class SessionIdUtilTest extends TestCase {

    public void testGenUniqueKey() {
        System.out.println(SessionIdUtil.genUniqueKey());
    }
}