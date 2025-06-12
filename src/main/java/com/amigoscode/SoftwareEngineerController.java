package com.amigoscode;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {
    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineer> getEngineers(){

        return softwareEngineerService.getAllSoftwareEngineers();
    }
    @GetMapping("{id}")
    public SoftwareEngineer getEngineerById(@PathVariable Integer id){

        return softwareEngineerService.getSoftwareEngineerById(id);
    }
    @DeleteMapping(path ="{id}")
    public void deleteEngineerbyId(@PathVariable("id") Integer id){
        softwareEngineerService.deleteSoftwareEngineer(id);


    }
    @PutMapping(path = "{id}")
    public void updateSoftwareEngineer(
            @PathVariable("id") Integer id,
            @RequestBody SoftwareEngineer updatedEngineer
    ) {
        softwareEngineerService.updateSoftwareEngineer(id, updatedEngineer);
    }
    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }
}
