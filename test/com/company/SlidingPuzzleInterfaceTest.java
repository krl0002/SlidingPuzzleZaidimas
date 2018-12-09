package com.company;

import org.junit.Test;

import java.awt.event.MouseEvent;

import static org.junit.Assert.*;

public class SlidingPuzzleInterfaceTest {

    @Test
    public void test() {
        SlidingPuzzleInterface iface = new SlidingPuzzleInterface();
        int result = iface.getMouseClicks();
        assertEquals(0,result);
    }

}