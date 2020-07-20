package main;

import main.model.Form;
import main.model.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FormController {

    @Autowired
    private FormRepository formRepository;

    @GetMapping("/forms/")
    public synchronized List<Form> list() {
        Iterable<Form> bookIterable = formRepository.findAll();
        ArrayList<Form> forms = new ArrayList<>();
        for (Form form : bookIterable){
            forms.add(form);
        }
        return forms;
    }

    @PostMapping("/forms/")
    public synchronized int add(Form form) {
        Form newForm = formRepository.save(form);
        return newForm.getId();
    }

    @GetMapping("/forms/{id}")
    public synchronized ResponseEntity get(@PathVariable int id){
        Optional<Form> optionalForm = formRepository.findById(id);
        if (!optionalForm.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalForm.get(), HttpStatus.OK);
    }

    @RequestMapping("/remove/{id}")
    public synchronized String removeForm(@PathVariable("id") int id){
        this.formRepository.deleteById(id);
        return null;
    }
}










