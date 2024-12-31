package service.impl;

import entity.GymClass;
import repository.GymClassRepository;
import service.IGymClassService;
import service.IService;

import java.util.List;

public class GymClassService implements IGymClassService {
    private static GymClassRepository gymClassRepository = new GymClassRepository();

    @Override
    public List<GymClass> getAll() {
        return gymClassRepository.getAll();
    }

    @Override
    public void add(GymClass gymClass) {

    }

    @Override
    public void update(GymClass gymClass) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public GymClass getById(int id) {
        return null;
    }
}
