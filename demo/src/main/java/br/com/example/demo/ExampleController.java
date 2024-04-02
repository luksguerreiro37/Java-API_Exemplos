package br.com.example.demo;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/example")
public class ExampleController {
    private List<ExampleModel> examples = new ArrayList<>();

    @PostMapping
    public ExampleModel create(@RequestBody ExampleModel payload){
        examples.add(payload);
        return payload;
    }

    @GetMapping
    public List<ExampleModel> read(){
        return examples;
    }

    @GetMapping("/{exampleId}")
    public Optional<ExampleModel> retrieve(@PathVariable Long exampleID){
        return examples.stream().filter(e -> e.getId().equals(exampleID)).findFirst();
    }

    @PutMapping("/{exampleId}")
    public Optional<ExampleModel> update(@PathVariable Long exampleId, @RequestBody ExampleModel payload){
        examples.stream().map(e -> {
            if (e.getId().equals(exampleId)) {
                payload.setId(e.getId());
                return payload;
            }
            return e;
        });
        return examples.stream().filter(e -> e.getId().equals(exampleId)).findFirst();
    }

    @DeleteMapping("/{exampleId}")
    public void delete(@PathVariable Long exampleId){
        examples.stream().filter(e -> !e.getId().equals(exampleId));
    }
}
