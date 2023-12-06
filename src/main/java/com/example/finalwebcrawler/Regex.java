package com.example.finalwebcrawler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
//    private String InternalUrlRegex = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]%s+([\\-\\.]+)\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
//    private String InternalUrlRegex = "(https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)?[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})(\\.[a-zA-Z]{2,})?\\/[a-zA-Z0-9]{2,}|((https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)?[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})(\\.[a-zA-Z]{2,})?)|(https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)?[a-zA-Z0-9]{2,}\\.[a-zA-Z0-9]{2,}\\.[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})?";
//    private String InternalUrlRegex = "^(https?://)?(www\\.)?([^./]+)(?:[:/].*)?(blank\\.org)(?:[/][^./]+)*(?:[:/].*)?$";
    private String InternalUrlRegex = "^(https?://)?(www\\.)?([^./]+)(?:[:/].*)?(%s)(?:[/][^./]+)*(?:[:/].*)?$";
    private String url;

    public Regex(String url){
        try {
            URI uri = new URI(url);
            String domain = uri.getHost().toString();
            String[] s = domain.split("\\.");
            if(s.length == 3) {domain = s[1] + "." + s[2];}
            System.out.println(domain + " THE UPDATED DOMAIN GET PATH");
            domain = domain.replace(".", "\\.");
            this.InternalUrlRegex = String.format(this.InternalUrlRegex, domain);
            System.out.println(InternalUrlRegex);
        }catch(URISyntaxException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isSameDomain(String url) {
        Pattern p = Pattern.compile(InternalUrlRegex);
        Matcher m = p.matcher(url);
        if (m.matches()){
            return true;
        }
        return false;
    }

    public static boolean isInternalUrl(String url, String domain) {
        try {
            URI uri = new URI(url);
            if (uri.isAbsolute()) {
                // Absolute URL
                return uri.getHost().equals(domain);
            } else {
                // Relative URL
                URI baseUri = new URI(domain);
                URI resolvedUri = baseUri.resolve(uri);
                return resolvedUri.getHost().equals(domain);
            }
        } catch (URISyntaxException e) {
            // Handle invalid URL
            return false;
        }
    }
}
