import tester.*;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

class Point {
  int x, y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}

class PointCompare implements Comparator<Point> {
  public int compare(Point a, Point b) {
    if (a.y > b.y) {
      return 1;
    }
    if (a.y < b.y) {
      return -1;
    } else {
      if (a.x > b.x) {
        return 1;
      }
      if (a.x < b.x) {
        return -1;
      } else {
        return 0;
      }
    }
  }
}

class PointDistanceCompare implements Comparator<Point> {

  public int compare(Point a, Point b) {
    Point z = new Point(0, 0);
    if (a.distance(z) < b.distance(z)) {
      return -1;
    } else if (a.distance(z) == b.distance(z)) {
      return 0;
    } else {
      return 1;
    }
  }
}

class StringCompare implements Comparator<String> {
  public int compare(String a, String b) {
    return a.compareTo(b);
  }
}

class StringLengthCompare implements Comparator<String> {

  public int compare(String a, String b) {
    if (a.length() > b.length()) {
      return 1;
    } else if (a.length() == b.length()) {
      return 0;
    } else {
      return 0;
    }
  }
}

class BooleanCompare implements Comparator<Boolean> {

  public int compare(Boolean a, Boolean b) {
    if (a && !b) {
      return 1;
    } else if (b && !a) {
      return -1;
    } else {
      return 0;
    }
  }
}

class CompareLists {

  public <E> E minimum(List<E> l, Comparator<E> c) {
    if (l.isEmpty()) {return null;}
    E min = l.get(0);
    for (E temp : l) {
      if (c.compare(min, temp) == -1) {
        min = temp;
      }
    }
    return min;
  }

  public <E> E minimum(E[] l, Comparator<E> c) {
    if (l.length == 0) {return null;}
    E min = l[0];
    for (E temp : l) {
      if (c.compare(min, temp) == -1) {
        min = temp;
      }
    }
    return min;
  }

  public <E> List<E> greaterThan(List<E> l, Comparator<E> c, E temp) {
    List<E> newList = new ArrayList<>();
    for (E t : l) {
      if (c.compare(t, temp) == 1) {
        newList.add(t);
      }
    }
    return newList;
  }

  public <E> boolean inOrder(List<E> l, Comparator<E> c) {
    if (l.contains(null)) {
      throw new IllegalArgumentException("null value in list");
    }
    for (int i = 1; i < l.size(); i++) {
      if (c.compare(l.get(i - 1), l.get(i)) > 0) {
        return false;
      }
    }
    return true;
  }

  public <E> boolean inOrder(E[] l, Comparator<E> c) {
    for (int i = 1; i < l.length; i++) {
      if ((l[i] == null) || (l[i - 1] == null)) {
        throw new IllegalArgumentException("null value in array");
      }
      if (c.compare(l[i - 1], l[i]) > 0) {
        return false;
      }
    }
    return true;
  }

  public <E> List<E> merge(Comparator<E> c, List<E> l1, List<E> l2) {
    if (l1.contains(null)) {
      throw new IllegalArgumentException("null value in first list");
    }
    if (l2.contains(null)) {
      throw new IllegalArgumentException("null value in second list");
    }
    List<E> newList = new ArrayList<>();
    int j = 0;
    int k = 0;
    for (int i = 0; i < l1.size() + l2.size(); i++) {
      if (c.compare(l1.get(j), l2.get(k)) == 1) {
        newList.add(l1.get(j));
        j++;
      } else if (c.compare(l1.get(j), l2.get(k)) == -1) {
        newList.add(l2.get(k));
        k++;
      } else {
        newList.add(l1.get(j));
        j++;
        newList.add(l2.get(k));
        k++;
      }
    }
    return newList;
  }
}

class Test {

  void testCompare(Tester t) {
    Point a = new Point(1, 2);
    Point b = new Point(2, 1);
    Point c = new Point(1, 3);
    Point d = new Point(1, 2);

    PointCompare pc = new PointCompare();
    t.checkExpect(pc.compare(a, b), -1);
    t.checkExpect(pc.compare(b, c), 1);
    t.checkExpect(pc.compare(c, d), 1);
    t.checkExpect(pc.compare(a, d), 0);

    PointDistanceCompare dc = new PointDistanceCompare();
    t.checkExpect(dc.compare(a, b), 0);
    t.checkExpect(dc.compare(c, b), 1);
    t.checkExpect(dc.compare(d, b), 0);
    t.checkExpect(dc.compare(a, d), 0);

    String f = "a";
    String s = "b";
    String n = "ee";
    String f1 = "gfg";
    String f2 = "gf";

    StringCompare sc = new StringCompare();
    t.checkExpect(sc.compare(f, s), 1);
    t.checkExpect(sc.compare(f, f), 0);
    t.checkExpect(sc.compare(f1, s), -1);
    t.checkExpect(sc.compare(n, s), -1);

    StringLengthCompare lc = new StringLengthCompare();
    t.checkExpect(lc.compare(f, s), 0);
    t.checkExpect(lc.compare(f, n), -1);
    t.checkExpect(lc.compare(f1, f2), 1);
    t.checkExpect(lc.compare(f2, n), 0);

    Boolean i = true;
    Boolean r = true;
    Boolean o = false;
    Boolean p = false;

    BooleanCompare bc = new BooleanCompare();
    t.checkExpect(bc.compare(i, r), 0);
    t.checkExpect(bc.compare(i, o), 1);
    t.checkExpect(bc.compare(o, p), 0);
    t.checkExpect(bc.compare(p, i), -1);
  }

  public void testCompareLists(Tester t) {
    List<Point> a = new ArrayList<>();
    a.add(new Point(0, 0));
    a.add(new Point(1, 2));
    a.add(new Point(4, 5));
    a.add(new Point(4, 6));
    a.add(new Point(0, 2));
    List<String> b = new ArrayList<>();
    b.add("a");
    b.add("");
    b.add("yyy");
    b.add("7");
    b.add("*");

    Point[] d = { a.get(0), a.get(1), a.get(2) };
    String[] e = { b.get(0), b.get(1), b.get(2) };

    CompareLists com = new CompareLists();
    CompareLists cl = new CompareLists();
    PointCompare pc = new PointCompare();
    PointDistanceCompare dc = new PointDistanceCompare();
    StringLengthCompare lc = new StringLengthCompare();
    t.checkExpect(com.minimum(a, pc), new Point(0, 0));
    t.checkExpect(com.minimum(a, dc), new Point(0, 0));
    t.checkExpect(cl.minimum(b, lc), "");

    t.checkExpect(com.minimum(d, pc), new Point(0, 0));
    t.checkExpect(com.minimum(d, dc), new Point(0, 0));
    t.checkExpect(cl.minimum(e, lc), "");

    List<Point> aa = new ArrayList<>();
    aa.add(new Point(4, 5));
    aa.add(new Point(4, 6));

    List<String> bb = new ArrayList<>();
    bb.add("yyy");

    List<Point> aaa = new ArrayList<>();
    aaa.add(new Point(4, 5));
    aaa.add(new Point(4, 6));

    t.checkExpect(com.greaterThan(a, pc, new Point(1, 2)), aa);
    t.checkExpect(com.greaterThan(a, dc, new Point(1, 2)), aaa);
    t.checkExpect(cl.greaterThan(b, lc, "aa"), bb);

    t.checkExpect(com.inOrder(a, pc), false);
    t.checkExpect(com.inOrder(a, dc), false);
    t.checkExpect(cl.inOrder(b, lc), false);

    t.checkExpect(com.inOrder(d, pc), true);
    t.checkExpect(com.inOrder(d, dc), false);
    t.checkExpect(cl.inOrder(e, lc), true);

    List<Boolean> bool = new ArrayList<>();
    List<Boolean> fa = new ArrayList<>();
    bool.add(true);
    bool.add(true);
    bool.add(false);
    fa.add(false);
    fa.add(false);
    fa.add(true);
    List<Boolean> fi = new ArrayList<>();
    fi.add(true);
    fi.add(true);
    fi.add(false);
    fi.add(false);
    fi.add(false);
    fi.add(true);

    CompareLists bl = new CompareLists();
    BooleanCompare bc = new BooleanCompare();

    List<Point> a1 = new ArrayList<>();
    a1.add(new Point(0, 0));
    a1.add(new Point(1, 2));

    List<Point> a2 = new ArrayList<>();
    a2.add(new Point(0, 0));
    a2.add(new Point(1, 2));

    List<Point> a3 = new ArrayList<>();
    a3.add(new Point(0, 0));
    a3.add(new Point(0, 0));
    a3.add(new Point(1, 2));
    a3.add(new Point(1, 2));

    List<String> b1 = new ArrayList<>();
    b1.add("00");
    b1.add("fgd");
    b1.add("fff");
    List<String> b2 = new ArrayList<>();
    b2.add("00");
    b2.add("fgd");
    b2.add("fff");
    List<String> b3 = new ArrayList<>();
    b3.add("00");
    b3.add("00");
    b3.add("fgd");
    b3.add("fgd");
    b3.add("fff");
    b3.add("fff");
    t.checkExpect(bl.merge(bc, bool, fa), fi);
    t.checkExpect(com.merge(pc, a1, a2), a3);
    t.checkExpect(cl.merge(lc, b1, b2), b3);
  }
}