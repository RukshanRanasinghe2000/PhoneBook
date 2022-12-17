import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenderDao {
    public GenderDao() {
    }

    public static Gender getById(int id){
        Gender gender = new Gender();
        try {
            ResultSet result = CommonDao.get("select * from gender where id=" + id);
            while (result.next()) {
                gender.setId(result.getInt("id"));
                gender.setName(result.getString("name"));
            }
        }catch (SQLException e){
            System.out.println("Can't Get Results as : " + e.getMessage());
        }

        return gender;
    }

    public static List<Gender> getAll() {
        List<Gender> genders = new ArrayList<>();
        try {
            ResultSet result = CommonDao.get("select * from gender");
            while (result.next()) {
                Gender gender = new Gender();
                gender.setId(result.getInt("id"));
                gender.setName(result.getString("name"));
                genders.add(gender);
            }
        } catch (SQLException Ex) {
            System.out.println("Can't Get Results as : " + Ex.getMessage());

        }
        return genders;

    }

}
