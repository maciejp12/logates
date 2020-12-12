package test;

import logates.sceneobject.Position;
import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

    @Test
    public void testContains() {
        Position p = new Position(32, 32, 16, 16);
        int[] pointT1 = {40, 40};
        int[] pointT2 = {32, 32};
        int[] pointF1 = {16, 49};
        int[] pointF2 = {64, 64};

        boolean expected = true;
        boolean actual = p.contains(pointT1[0], pointT1[1], 0, 0);

        Assert.assertEquals(expected, actual);

        actual = p.contains(pointT2[0], pointT2[1], 0, 0);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = p.contains(pointF1[0], pointF1[1], 0, 0);

        Assert.assertEquals(expected, actual);

        actual = p.contains(pointF2[0], pointF2[1], 0, 0);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testContainsBounds() {
        Position p = new Position(32, 32, 16, 16);
        int dx = 8;
        int dy = 8;

        int[] pointT1 = {40, 40};
        int[] pointT2 = {24, 24};
        int[] pointF1 = {16, 49};
        int[] pointF2 = {64, 64};

        boolean expected = true;
        boolean actual = p.contains(pointT1[0], pointT1[1], dx, dy);

        Assert.assertEquals(expected, actual);

        actual = p.contains(pointT2[0], pointT2[1], dx, dy);

        Assert.assertEquals(expected, actual);

        expected = false;
        actual = p.contains(pointF1[0], pointF1[1], dx, dy);

        Assert.assertEquals(expected, actual);

        actual = p.contains(pointF2[0], pointF2[1], dx, dy);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testResize() {
        Position p = new Position(32, 32, 16, 16);

        int preWidth = p.getWidth();
        int resizeX = 10;

        p.addWidth();

        int actual = p.getWidth();
        int expected = preWidth + resizeX;

        Assert.assertEquals(expected, actual);

        int preHeight = p.getHeight();
        int resizeY = 10;

        p.addHeight();

        actual = p.getHeight();
        expected = preHeight + resizeY;

        Assert.assertEquals(expected, actual);

        preWidth = p.getWidth();
        int dx = -9;

        p.addWidth(dx);

        actual = p.getWidth();
        expected = preWidth + dx;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testResizeLimits() {
        Position p = new Position(32, 32, 16, 16);

        int minWidth = 16;
        int dx = -100;

        p.addWidth(dx);

        int actual = p.getWidth();
        int expected = minWidth;

        Assert.assertEquals(expected, actual);
    }

}
