package JsonClass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaImage {
    String date,
            explanation,
            hdurl,
            media_type,
            service_version,
            title,
            url;

     public NasaImage(@JsonProperty ("date") String date,
                      @JsonProperty ("explanation") String explanation,
                      @JsonProperty ("hdurl")String hdurl,
                      @JsonProperty ("media_type")String media_type,
                      @JsonProperty ("service_version")String service_version,
                      @JsonProperty ("title")String title,
                      @JsonProperty ("url")String url) {
          this.date = date;
          this.explanation = explanation;
          this.hdurl = hdurl;
          this.media_type = media_type;
          this.service_version = service_version;
          this.title = title;
          this.url = url;
     }

     public String getUrl() {
          return url;
     }

     @Override
     public String toString() {
          return "NasaImage{" +
                  "date = " + date + '\n' +
                  ", explanation = " + explanation + '\n' +
                  ", hdurl = " + hdurl + '\n' +
                  ", media_type = " + media_type + '\n' +
                  ", service_version = " + service_version + '\n' +
                  ", title = " + title + '\n' +
                  ", url = " + url + '\n' +
                  '}';
     }
}
