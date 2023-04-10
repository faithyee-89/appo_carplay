package com.appotronics.carplay_app.bean;

/**
 * @desc:
 * @author: yewei
 * @data: 2023/3/22 14:41
 */
public class RequestBean {
    private String contentName;
    private String url;
    private String type;

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
