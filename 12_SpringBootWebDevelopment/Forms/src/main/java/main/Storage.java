package main;

import main.model.Form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Storage {
    private static AtomicInteger currentId = new AtomicInteger(1);
    private static final AtomicReference<HashMap<Integer, Form>> forms = new AtomicReference<>(new HashMap<Integer, Form>());

    public static List<Form> getAllForms() {
        List<Form> formsList = Collections.synchronizedList(new ArrayList<>());
        synchronized (formsList) {
            formsList.addAll(forms.get().values());
        }
        return formsList;
    }

    public static int addForm(Form form) {
        int id = currentId.incrementAndGet();
        form.setId(id);
        forms.get().put(id, form);
        return id;
    }

    public static AtomicReference<HashMap<Integer, Form>> deleteForm(int id) {
        forms.get().remove(id);
        return forms;
    }

    public static Form getForm(int formId) {
        if (forms.get().containsKey(formId)) {
            return forms.get().get(formId);
        }
        return null;
    }
}