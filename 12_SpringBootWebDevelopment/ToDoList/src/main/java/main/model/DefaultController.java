package main.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    FormRepository formRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Form> formIterable = formRepository.findAll();
        ArrayList<Form> forms = new ArrayList<>();
        for (Form form : formIterable) {
            forms.add(form);
        }
        model.addAttribute("forms", forms);
        return "index";
    }

}
