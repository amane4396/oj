package org.oj.vo;

/**
 * 前端提交的排序信息
 *
 * @author DH
 * @create 2020-12-11
 */
public class Sort {
    private String property;
    private boolean asc;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean getAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public void setAsc(String direction) {
        this.asc = "ascending".equals(direction);
    }
}
