class Point {
    int x, y;
    Point(int x, int y) { this.x = x; this.y = y; }
    boolean belowLeftOf(Point p) { return this.x < p.x && this.y < p.y; }
    boolean aboveRightOf(Point p) { return this.x > p.x && this.y > p.y; }
    double distance(Point p) {
        int dx = p.x - this.x;
        int dy = p.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
  
interface Region {
    boolean contains(Point p);
    // Task 3: toText method
    // Your code here
    String toText();
}
  
class RectRegion implements Region {

    Point lowerLeft, upperRight;
  
    RectRegion(Point lowerL, Point upperR) {
        this.lowerLeft = lowerL;
        this.upperRight = upperR;
    }

    public boolean contains(Point p) { 
        return this.lowerLeft.belowLeftOf(p) && this.upperRight.aboveRightOf(p); 
    }

    // Task 3: toText method
    // Your code here
    @Override
    public String toText() {
        return "Rectangle";
    }
}
  
class CircleRegion implements Region {

    Point center;
    int radius;
    
    CircleRegion(Point center, int radius) { 
        this.center = center; 
        this.radius = radius; 
    }
    
    public boolean contains(Point p) { 
        return this.center.distance(p) < this.radius; 
    }

    // Task 3: toText method
    // Your code here
    @Override
    public String toText() {
        return "Circle";
    }
}
  
class UnionRegion implements Region {

    Region r1, r2;
    
    UnionRegion(Region r1, Region r2) { 
        this.r1 = r1; 
        this.r2 = r2; 
    }
    
    public boolean contains(Point p) { 
        return this.r1.contains(p) || this.r2.contains(p); 
    }

    // Task 3: toText method
    // Your code here
    @Override
    public String toText() {
        if (r1 instanceof RectRegion) {
            if (r2 instanceof RectRegion) {
                return "Union(Rectangle, Rectangle)";
            } else if (r2 instanceof CircleRegion) {
                return "Union(Rectangle, Circle)";
            } else if (r2 instanceof UnionRegion) {
                return "Union(Rectangle, " + r2.toText() +")"; 
            } else {
                return "Union(Rectangle, " + r2.toText() +")"; 
            }
        } else if (r1 instanceof CircleRegion) {
            if (r2 instanceof RectRegion) {
                return "Union(Circle, Rectangle)";
            } else if (r2 instanceof CircleRegion) {
                return "Union(Circle, Circle)";
            } else {
                return "Union(Circle, " + r2.toText() +")"; 
            }
        } else {
            if (r2 instanceof RectRegion) {
                return "Union(" + r1.toText() + ", Rectangle)";
            } else if (r2 instanceof CircleRegion) {
                return "Union(" + r1.toText() + ", Circle)";
            } else {
                return "Union(" + r1.toText() + ", " + r2.toText() +")"; 
            }
        }
    }
}


class IntersectRegion implements Region {

    Region r1, r2;
  
    IntersectRegion(Region r1, Region r2) {
        this.r1 = r1;
        this.r2 = r2;
    }
  
    public boolean contains(Point p) {
        return this.r1.contains(p) && this.r2.contains(p);
    }

    // Task 3: toText method
    // Your code here
    @Override
    public String toText() {
        if (r1 instanceof RectRegion) {
            if (r2 instanceof RectRegion) {
                return "Intersect(Rectangle, Rectangle)";
            } else if (r2 instanceof CircleRegion) {
                return "Intersect(Rectangle, Circle)";
            } else if (r2 instanceof UnionRegion) {
                return "Intersect(Rectangle, " + r2.toText() +")"; 
            } else {
                return "Intersect(Rectangle, " + r2.toText() +")"; 
            }
        } else if (r1 instanceof CircleRegion) {
            if (r2 instanceof RectRegion) {
                return "Intersect(Circle, Rectangle)";
            } else if (r2 instanceof CircleRegion) {
                return "Intersect(Circle, Circle)";
            } else {
                return "Intersect(Circle, " + r2.toText() +")"; 
            }
        } else {
            if (r2 instanceof RectRegion) {
                return "Intersect(" + r1.toText() + ", Rectangle)";
            } else if (r2 instanceof CircleRegion) {
                return "Intersect(" + r1.toText() + ", Circle)";
            } else {
                return "Intersect(" + r1.toText() + ", " + r2.toText() +")"; 
            }
        }
    }
}