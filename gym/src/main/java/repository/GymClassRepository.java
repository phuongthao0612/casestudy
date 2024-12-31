package repository;

import entity.GymClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GymClassRepository {

    public List<GymClass> getAll() {
        List<GymClass> gymClass = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement("select * from gym_classes");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int classId = resultSet.getInt("class_id");
                String className = resultSet.getString("class_name");
                gymClass.add(new GymClass(classId, className));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gymClass;
    }
}
