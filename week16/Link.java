package week16;

/**
 * Created on 15/02/2017.
 */
public class Link {
    private static String[] ASSETS = {".js", ".css", ".png", ".jpg", ".ico"};
    private String src;

    public static Link toAbsolute(Link link, String root) {
        if (link.isRelative()) {
            return new Link(root + link.src);
        }

        return link;
    }

    Link(String src) {
        this.src = src;
    }


    public boolean isRelative() {
        return src.startsWith("/");
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
