package cat.itacademy.s04.t02.n03.controller;

import cat.itacademy.s04.t02.n03.model.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Autowired
    private cat.itacademy.s04.t02.n03.service.FruitService fruitService;

    @PostMapping("/add")
    public ResponseEntity<Fruit> createFruit(@RequestBody Fruit fruit) {
        return new ResponseEntity<>(fruitService.createFruit(fruit), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Fruit>> getAllFruits() {
        return new ResponseEntity<>(fruitService.getAllFruits(), HttpStatus.FOUND);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable int id) {
        Fruit fruit = fruitService.getFruitById(id);
        if(fruit != null){
            return new ResponseEntity<>( fruitService.getFruitById(id), HttpStatus.FOUND);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable int id, @RequestBody Fruit fruit) {
        return new ResponseEntity<>(fruitService.updateFruit(id, fruit), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFruit(@PathVariable int id) {
        Fruit fruit = fruitService.getFruitById(id);
        if(fruit != null){
            fruitService.deleteFruit(id);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}