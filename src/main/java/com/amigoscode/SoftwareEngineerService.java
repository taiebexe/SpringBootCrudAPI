package com.amigoscode;
import java.util.List;
import java.lang.IllegalStateException;

import org.springframework.stereotype.Service;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;
    public SoftwareEngineerService(
            SoftwareEngineerRepository softwareEngineerRepository
    ) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }
    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }
    public void deleteSoftwareEngineer(Integer id) {
        boolean exists = softwareEngineerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    id + " not found"
            );
        }
        softwareEngineerRepository.deleteById(id);
    }
    public void updateSoftwareEngineer(Integer id, SoftwareEngineer updatedEngineer) {
        SoftwareEngineer existing = softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Software Engineer with id " + id + " not found"));

        existing.setName(updatedEngineer.getName());
        existing.setTechStack(updatedEngineer.getTechStack());

        softwareEngineerRepository.save(existing); // JPA will use merge() internally
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));
    }
}
