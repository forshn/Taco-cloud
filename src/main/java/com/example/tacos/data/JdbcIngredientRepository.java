package com.example.tacos.data;

import com.example.tacos.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbc;

    /*
    Когда Spring создает bean JdbcIngredientRepository, он внедряет  JdbcTemplate
    через аннотированную конструкцию @Autowired. Конструктор назначает JdbcTemplate
     переменной экземпляра, которая будет использоваться в других методах для запроса
     и вставки в базу данных.
     */
    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject("SELECT id, name, type FROM Ingredient WHERE id=?",
                this::mapRowToIngredient, id);
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type"));
        )

    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update(
                "INSERT INTO Ingredient (id, name, type) values(?,?,?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    /*
    findAll() и findById() используют JdbcTemplate аналогичным образом. Метод findAll(), ожидающий
    возврата коллекции объектов, использует JdbcTemplate метод запроса. Метод query() принимает SQL
     для запроса, а также реализацию Spring-овского RowMapper с целью сопоставления каждой строки в
      результирующем наборе объекту. findAll() также принимает в качестве окончательного аргумента
      (аргументов) список любых параметров, необходимых в запросе. Но, в данном случае, нет никаких
      обязательных параметров.
Метод findById() возвращает только один объект ингредиента, поэтому он использует метод
queryForObject() JdbcTemplate вместо query(). queryForObject() работает так же, как query(),
 за исключением того, что возвращает один объект вместо списка объектов. В этом случае ему дается
 запрос для выполнения, RowMapper и id Ingredient для выборки, который используется вместо ?
  в запросе.

     */
}
