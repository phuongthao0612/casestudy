package repository;

import dto.TrainerDTO;
import entity.Trainer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerRepository {
    public List<Trainer> getAll() {
        return null;

    }

    public List<TrainerDTO> getAllDTO() {
        List<TrainerDTO> trainerDTO = new ArrayList<>();
        String query = "SELECT trainers.trainer_id, trainers.trainer_name, trainers.specialization, trainers.phone, gym_classes.class_name " +
                "FROM trainers " +
                "JOIN gym_classes ON trainers.trainer_id = gym_classes.trainer_id";

        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("trainer_id");
                String name = resultSet.getString("trainer_name");
                String specialization = resultSet.getString("specialization");
                String phone = resultSet.getString("phone");
                String className = resultSet.getString("class_name");
                trainerDTO.add(new TrainerDTO(id, name, specialization, phone, className));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trainerDTO;
    }

    public void add(Trainer trainer) {

    }


}
