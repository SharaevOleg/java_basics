package main;

import main.model.FormRepository;
import main.model.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class FormController {

//    @Autowired
//    private FormRepository formRepository;

    @GetMapping("/forms/")
    public List<Form> list() {
        return Storage.getAllForms();
    }

//    @GetMapping("/forms/")
//    public List<Form> list() {
//        Iterable<Form> bookIterable = formRepository.findAll();
//        List<Form> forms = Collections.synchronizedList(new ArrayList<>());
//        synchronized (forms) {
//            for (Form form : bookIterable) {
//                forms.add(form);
//            }
//        }
//        return forms;
//    }

    @PostMapping("/forms/")
    public int add(Form form) {
        return Storage.addForm(form);
    }

    @GetMapping("/forms/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Form form = Storage.getForm(id);
        if (form == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(form, HttpStatus.OK);
    }

    @RequestMapping("/remove/{id}")
    public String removeForm(@PathVariable("id") int id){
        Storage.deleteForm(id);
        return null;
    }


//    @PostMapping("/forms/")
//    public int add(Form form) {
//        Form newForm = formRepository.save(form);
//        return newForm.getId();
//    }

//    @GetMapping("/forms/{id}")
//    public ResponseEntity get(@PathVariable int id) {
//        Optional<Form> optionalForm = formRepository.findById(id);
//        return optionalForm.map(form -> new ResponseEntity(form, HttpStatus.OK)).orElseGet(() ->
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
//    }

}










