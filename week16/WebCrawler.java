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

    public WebCrawler(String root) {
        this.root = root;
        this.results = new ArrayList<>();
        this.service = Executors.newCachedThreadPool();
    }

    public static void main(String[] args) throws IOException {
        WebCrawler crawler = new WebCrawler("http://www.emag.bg/");

        crawler.crawl();
    }

    public void crawl() throws IOException {
        crawl(new Link(root));
    }

    private void crawl(Link link) throws IOException {
        String response = fetchResponse(link);

        String target = getTarget(response);
        if (target != null) {
            System.out.println(target);
            return;
        }

        List<Link> parsed = parseLinks(response);

        System.out.println(parsed);

        /*for (Link parsedLink : parsed) {
            if (parsedLink.isValid() && !parsedLink.recursive(link)) {
                String l = parsedLink.getSrc();
                if (link.isRelative()) {
                    l = root + l;
                }

                Link newLink = new Link(l);
                service.execute(() -> {
                    try {
                        crawl(newLink);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }*/
    }

    private String getTarget(String response) {
        //ToDo Write the regex
        Pattern pattern = Pattern.compile("<h1 class=\"page-title\".*?>(.*)</h1>");
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
            System.out.println(matcher.group(1));
        }
        return links.stream()
                .filter(Link.isValid)
                .map(s -> Link.toAbsolute(s, root))
                .collect(Collectors.toList());
    }

    @NotNull
    private String fetchResponse(Link link) throws IOException {
        URL url = new URL(link.getSrc());
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String response;
        while ((response = reader.readLine()) != null) {
            stringBuilder.append(response);
        }

        reader.close();
        return stringBuilder.toString();
    }
}
