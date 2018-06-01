package com.example.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class FoodEndPoint {

    @Autowired
    private Model productModel;

    @RequestMapping(path = "/endpoint/product/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id) {
        Optional<Foods> optionalProduct = productModel.findById(id);
        if (optionalProduct.isPresent()) {
            Foods product = optionalProduct.get();
            product.setStatus(0);
            productModel.save(product);
            return new ResponseEntity<Foods>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/endpoint/product/delete-many", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody String ids) throws UnsupportedEncodingException {
        String[] splittedIds = java.net.URLDecoder.decode(ids, "UTF-8").split(",");
        Long[] arrayIds = new Long[splittedIds.length];
        for (int i = 0; i < splittedIds.length; i++) {
            arrayIds[i] = new Long(splittedIds[i]);
        }
        Iterable<Foods> list = productModel.findAllById(Arrays.asList(arrayIds));
        for (Foods p :
                list) {
            p.setStatus(0);
        }
        productModel.saveAll(list);
        return new ResponseEntity<Foods>(HttpStatus.NO_CONTENT);
    }
}
