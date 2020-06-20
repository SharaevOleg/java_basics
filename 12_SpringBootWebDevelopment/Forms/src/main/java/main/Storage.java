package main;

import main.model.Form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage
{
    private static int currentId = 1;
    private static HashMap<Integer, Form> forms = new HashMap<Integer, Form>();

    public static List<Form> getAllForms()
    {
        ArrayList<Form> formsList = new ArrayList<Form>();
        formsList.addAll(forms.values());
        return formsList;
    }

    public static int addForm(Form form)
    {
        int id = currentId++;
        form.setId(id);
        forms.put(id, form);
        return id;
    }

    public static Form getForm(int formId)
    {
        if(forms.containsKey(formId)) {
            return forms.get(formId);
        }
        return null;
    }
}