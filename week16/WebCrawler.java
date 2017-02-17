package week16;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created on 14.02.17.
 */
public class WebCrawler {
    private String root;
    private ArrayList<String> results;
    private ExecutorService service;
    private int max_depth;

    public WebCrawler(String root, int max_depth) {
        this.root = root;
        this.results = new ArrayList<>();
        this.max_depth = max_depth;
        this.service = Executors.newCachedThreadPool();
    }

    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler("http://www.emag.bg", 2);

        crawler.crawl();
        List<String> results = crawler.getResults();
        results.forEach(System.out::println);
    }

    /**
     * A blocking method - run in a separate thread
     */
    public void crawl() {
        try {
            crawl(new Link(root), 0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                service.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.shutdown();
        }
    }

    private void crawl(Link link, int depth) throws IOException {
        if (depth >= max_depth) {
            return;
        }

        String response = fetchResponse(link);

        String target = getTarget(response);
        if (target != null) {
            addToResult(target.trim());
            return;
        }

        List<Link> parsed = parseLinks(response);

        for (Link parsedLink : parsed) {
            service.execute(() -> {
                try {
                    crawl(parsedLink, depth + 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private synchronized void addToResult(String target) {
        results.add(target);
    }

    private String getTarget(String response) {
        Pattern pattern = Pattern.compile("<h1 class=\"page-title\">([\\s\\S]*)</h1>");
        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    @NotNull
    private List<Link> parseLinks(String response) {
        Pattern pattern = Pattern.compile("href=\"(.*?)\"");
        Matcher matcher = pattern.matcher(response);

        List<Link> links = new ArrayList<>();
        while (matcher.find()) {
            links.add(new Link(matcher.group(1)));
        }
        return links.stream()
                .filter(Link::isAsset)
                .map(s -> Link.toAbsolute(s, root))
                .collect(Collectors.toList());
    }

    @NotNull
    private String fetchResponse(Link link) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(link.getSrc());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

            String response;
            while ((response = reader.readLine()) != null) {
                stringBuilder.append(response);
            }

            reader.close();
        } catch (IOException e) {
            // Swallowing this for now
        }
        return stringBuilder.toString();
    }

    public ArrayList<String> getResults() {
        return results;
    }
}
