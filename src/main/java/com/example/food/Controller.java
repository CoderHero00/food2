package com.example.food;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    private static  String UPLOADED_FOLDER="target/classes/static/uploaded/";

    @Autowired
    private UserModel proModel;

    @Autowired
    private com.example.food.Model myModel;

    //Create**************************************************************************************************
    @RequestMapping(path = "/foo/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("food", new Foods());
        return "Form";
    }

    @RequestMapping(path = "/oooo/create", method = RequestMethod.POST)
    public String addUser(@ModelAttribute @Valid Foods food, BindingResult bindingResult, @RequestParam("myFile")MultipartFile myFile) {
        food.setUrl("_");
        if (bindingResult.hasErrors()&& food.getStatus() != 0) {
            return "Form";
        } else {
            try {
            Path path = Paths.get(UPLOADED_FOLDER + myFile.getOriginalFilename());
                Files.write(path,myFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                Foods food2 = proModel.findById(food.getId()).get();
                food.setSelld(food2.getSelld());
            }catch (Exception e){
                food.setSelldC();
            }
            food.setUrl("/uploaded/"+myFile.getOriginalFilename());
            proModel.save(food);

            return "redirect:/foo/list";
        }

    }

    @RequestMapping(path = "/foo/list", method = RequestMethod.GET)
    public String createUser(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int limit) {

        model.addAttribute("pagination",myModel.findFoodsByStatusOrStatus(1,-1, PageRequest.of(page - 1, limit)));
        model.addAttribute("page", page);
        model.addAttribute("size", limit);

        return "list";
    }


    @RequestMapping(path = "/foo/delete/{id}", method = RequestMethod.GET)
    public String delProduct(@PathVariable long id, Model model) {
        Optional<Foods> optionalFoods = proModel.findById(id);
        int o = proModel.findById(id).get().getStatus();
        if (optionalFoods.isPresent()&& o != 0) {
            Foods food2 = proModel.findById(id).get();
            Foods food = new Foods();
            food.setType(food2.getType());
            food.setName(food2.getName());
            food.setPrice(food2.getPrice());
            food.setDes(food2.getDes());
            food.setUrl(food2.getUrl());
            food.setId(id);
            food.setSelld(food2.getSelld());
            food.setStatus(0);
            proModel.save(food);
            return "redirect:/foo/list";
        } else {
            return "error";
        }
    }


    @RequestMapping(path = "/foo/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable long id, Model model) {
        Optional<Foods> optionalFoods = proModel.findById(id);
        if (optionalFoods.isPresent()) {
            int o = proModel.findById(id).get().getStatus();
            if(o != 0) {
                model.addAttribute("food", optionalFoods.get());
                return "Form";
            }else {
                return "error";
            }
        } else {
            return "error";
        }
    }


}