import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");

        try {
            String name = components[0] + " " + components[1];
            String sPhoneNumber = components[3];
            String sEmail = components[2];
            Pattern pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
            Pattern patternEmail = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]" +
                    "+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
            Matcher matcher = pattern.matcher(sPhoneNumber);
            Matcher matcherEmail = patternEmail.matcher(sEmail);
            if (matcher.matches() && matcherEmail.matches()) {
                storage.put(name, new Customer(name, components[3], components[2]));
            } else {
                throw new IllegalArgumentException("Phone Number or Email not valid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}