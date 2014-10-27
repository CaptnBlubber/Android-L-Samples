package s3xy.de.android_lsamples.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

@Expose
private String id;
@Expose
private String owner;
@Expose
private String secret;
@Expose
private String server;
@Expose
private Integer farm;
@Expose
private String title;
@Expose
private Integer ispublic;
@Expose
private Integer isfriend;
@Expose
private Integer isfamily;
@SerializedName("url_l")
@Expose
private String url;
@SerializedName("height_l")
@Expose
private String height;
@SerializedName("width_l")
@Expose
private String width;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getOwner() {
return owner;
}

public void setOwner(String owner) {
this.owner = owner;
}

public String getSecret() {
return secret;
}

public void setSecret(String secret) {
this.secret = secret;
}

public String getServer() {
return server;
}

public void setServer(String server) {
this.server = server;
}

public Integer getFarm() {
return farm;
}

public void setFarm(Integer farm) {
this.farm = farm;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public Integer getIspublic() {
return ispublic;
}

public void setIspublic(Integer ispublic) {
this.ispublic = ispublic;
}

public Integer getIsfriend() {
return isfriend;
}

public void setIsfriend(Integer isfriend) {
this.isfriend = isfriend;
}

public Integer getIsfamily() {
return isfamily;
}

public void setIsfamily(Integer isfamily) {
this.isfamily = isfamily;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getHeight() {
return height;
}

public void setHeight(String height) {
this.height = height;
}

public String getWidth() {
return width;
}

public void setWidth(String width) {
this.width = width;
}

}