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
    public Optional<ExampleModel> retrieve(@PathVariable Long exampleId){
        return examples.stream().filter(e -> e.getId().equals(exampleId)).findFirst();
    }

    @PutMapping("/{exampleId}")
    public Optional<ExampleModel> update(@PathVariable Long exampleId, @RequestBody ExampleModel payload){
        var newExamples = examples.stream().map(e -> {
            if (e.getId().equals(exampleId)) {
                payload.setId(e.getId());
                return payload;
            }
            return e;
        });
        examples = new ArrayList<ExampleModel>(newExamples.toList());
        return examples.stream().filter(e -> e.getId().equals(exampleId)).findFirst();
    }

    @DeleteMapping("/{exampleId}")
    public void delete(@PathVariable Long exampleId){
        var newExamples = examples.stream().filter(e -> !e.getId().equals(exampleId));
        examples = new ArrayList<ExampleModel>(newExamples.toList());
    }
}
