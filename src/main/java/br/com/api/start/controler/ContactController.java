package br.com.api.start.controler;

import br.com.api.start.model.Contact;
import br.com.api.start.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {

    private ContactRepository repository;
    public ContactController(ContactRepository contactRepository) {
        this.repository = contactRepository;
    }

    //lista todos contatos
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List findAll(){
        Page<Contact> page = repository.findAll(PageRequest.of(0,4));//paginacao simples
        return page.getContent();
    }
/*
 @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List findAll(){// sem paginacao
        return repository.findAll();
    }
*/

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/paginacao-com-parametros")
    public List paginacaoPorParamtroFindAll(Pageable pageable){
        Page<Contact> page = repository.findAll(pageable);
        return page.getContent();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/paginacao-com-parametros-default")
    public List paginacaoPorParamtrosEOrdenacao(@PageableDefault(direction = Sort.Direction.DESC, sort = "nome") Pageable pageable){
        Page<Contact> page = repository.findAll(pageable);
        return page.getContent();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/query-dinamica")
    public String paginacaoPorParamtrosEOrdenacao(Contact buscaContact){
        return repository.findAll(buscaContact.toSpec()).toString();
    }

    //lista apenas um contato pelo id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path={"/{id}"})
    public ResponseEntity<Contact> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    //novo registro
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public Contact create(@RequestBody Contact contact){
        return repository.save(contact);
    }

    //atualiza um contato existente
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Contact contact){
        return repository.findById(id).map(record -> {
            record.setName(contact.getName());
            record.setEmail(contact.getEmail());
            record.setPhone(contact.getPhone());

            Contact updated = repository.save(record);
            return  ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    //remover um registro
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
