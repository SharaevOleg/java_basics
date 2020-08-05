package main;

import main.model.Form;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static AtomicInteger currentId = new AtomicInteger(1);
    private static ConcurrentHashMap<Integer, Form> forms = new ConcurrentHashMap<>();
//    private static final AtomicReference<HashMap<Integer, Form>> forms = new AtomicReference<>(new HashMap<Integer, Form>());

    public static List<Form> getAllForms() {
        List<Form> formsList = new ArrayList<>();
        formsList.addAll(forms.values());
//        formsList.addAll(forms.get().values());
        return formsList;
    }

    public static int addForm(Form form) {
        int id = currentId.incrementAndGet();
        form.setId(id);
        forms.put(id, form);
//        forms.get().put(id, form);
        return id;
    }

    public static ConcurrentHashMap<Integer, Form> deleteForm(int id) {
        forms.remove(id);
//        forms.get().remove(id);
        return forms;
    }

    public static Form getForm(int formId) {
        if (forms.containsKey(formId)) {
            return forms.get(formId);
        }
//        if (forms.get().containsKey(formId)) {
//            return forms.get().get(formId);
//        }
        return null;
    }
}