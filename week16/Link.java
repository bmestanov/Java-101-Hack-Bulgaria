package week16;

import java.util.function.Predicate;

/**
 * Created on 15/02/2017.
 */
public class Link {
    private static String[] ASSETS = {".js", ".css", ".png", ".jpg", ".ico"};
    public static Predicate<Link> isValid;
    private String src;

    static {
        isValid = link -> {
            for (String extension : ASSETS) {
                if (link.src.endsWith(extension))
                    return true;
            }
            return false;
        };
    }

    public static Link toAbsolute(Link link, String root) {
        if (link.src.startsWith("/")) {
            return new Link(link.src + root);
        }

        return link;
    }

    Link(String src) {
        this.src = src;
    }

    public boolean isAbsolute() {
        return src.contains("http://");
    }

    public boolean isRelative() {
        return src.contains("/");
    }

    public boolean isAsset() {
        for (String extension : ASSETS) {
            if (src.contains(extension))
                return true;
        }

        return false;
    }

    public String getSrc() {
        return src;
    }

    @Override
    public String toString() {
        return src;
    }
}
