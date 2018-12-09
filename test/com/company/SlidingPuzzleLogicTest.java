package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class SlidingPuzzleLogicTest {

    @Test
    public void test() {
        SlidingPuzzleLogic logic = new SlidingPuzzleLogic();
        int result = logic.getNumber(0, 0);
        assertEquals(2,result);
    }

}