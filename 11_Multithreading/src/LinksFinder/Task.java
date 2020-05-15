package LinksFinder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Task implements Runnable {

    private String rootAddress;
    private Finder finder;

    public Task(String rootAddress, Finder finder) {
        this.rootAddress = rootAddress;
        this.finder = finder;
    }

    @Override
    public void run() {
        try {
            Set<String> result = getUrls();

            synchronized (finder.getLock()) {
                finder.getLinks().put(rootAddress, result);
                finder.setCount(finder.getCount().get() + result.size());
                for (String link : result) {
                    if (!finder.getLinks().containsKey(link)) {
                        finder.addTask(link);
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    private Set<String> getUrls() throws Exception {
        Document doc = Jsoup.parse(new URL(rootAddress), 5000);
        Set<String> urls = new HashSet<>();

        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String href = link.absUrl("href");
            if (href.contains(rootAddress.substring(0, rootAddress.indexOf("."))) /*&& !href.equals(rootAddress)*/) {
                urls.add(href);
            }
        }
        return urls;
    }
}