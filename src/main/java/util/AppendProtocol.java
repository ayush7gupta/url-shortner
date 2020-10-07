package util;

import org.springframework.stereotype.Component;

@Component
public class AppendProtocol {

    public static String appendProtocolToUrl(String url){
        if(url.contains("http://") || url.contains("https://")){
            return url;
        }
        return "http://" + url;
    }
}
