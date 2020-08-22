import redis.clients.jedis.Jedis;

public class Main {
    public static void main(String[] args) {

        Jedis client = new Jedis("localhost", 6379);
        MeetingWebsite log = new MeetingWebsite(client);
        log.start();
    }
}
