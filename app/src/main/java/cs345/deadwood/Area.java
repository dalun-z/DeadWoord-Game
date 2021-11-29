package cs345.deadwood;

public class Area {
    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;

    public Area() {

    }

    public Area(Integer x, Integer y, Integer w, Integer h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setX(int x) {
        this.x = x;
    } 

    public void setY(int y) {
        this.y = y;
    } 

    public void setW(int w) {
        this.w = w;
    } 

    public void setH(int h) {
        this.h = h;
    } 

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public Integer getW() {
        return this.w;
    }

    public Integer getH() {
        return this.h;
    }

    public boolean isWithin(int mouseX, int mouseY) {
        if (mouseX >= this.x && mouseX <= this.x + this.w &&
            mouseY >= this.y && mouseY <= this.y + this.h) {
                return true;
        } else return false;
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y + ", " + this.w + ", " + this.h;
    }

}
