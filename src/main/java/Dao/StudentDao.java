package Dao;

import dbconnection.ConnectionFactory;
import model.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    ConnectionFactory connectionFactory = new ConnectionFactory();

    public List<Student> list() throws IOException {
        List<Student> students = new ArrayList<>();

        try (Connection conn = connectionFactory.connectToDb(connectionFactory.buildConnectionString())) {

            String sql = "SELECT * FROM aluno";

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nome");
                int age = resultSet.getInt("idade");
                String state = resultSet.getString("estado");

                students.add(new Student(id, name, age, state));
            }
        }
        catch (SQLException ex) {
            System.out.println("Listagem de alunos falhou!");
            ex.printStackTrace();
        }
        return students;
    }

    public Student getById(int id) throws IOException {
        Student student = new Student();

        try (Connection conn = connectionFactory.connectToDb(connectionFactory.buildConnectionString())) {

            String sql = "SELECT * FROM aluno WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("nome"));
                student.setAge(resultSet.getInt("idade"));
                student.setState(resultSet.getString("estado"));
            }
        }
        catch (SQLException ex) {
            System.out.println("Listagem de alunos falhou!");
            ex.printStackTrace();
        }
        return student;
    }

    public void create(Student student) {

        try (Connection conn = connectionFactory.connectToDb(connectionFactory.buildConnectionString())) {

            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES (?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getState());

            int affectedRows = statement.executeUpdate();

            System.out.println("Inserção BEM SUCEDIDA! Foi adicionado " + affectedRows + " linha");
        }
        catch (SQLException | IOException ex) {
            System.out.println("Inserção falhou!");
            ex.printStackTrace();
        }
    }

    public void delete(int id) {

        try (Connection conn = connectionFactory.connectToDb(connectionFactory.buildConnectionString())) {

            String sql = "DELETE FROM aluno WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();

            System.out.println("Aluno removido com sucesso! Foi removido " + affectedRows + " linha");
        }
        catch (SQLException | IOException ex) {
            System.out.println("Remoção falhou!");
            ex.printStackTrace();
        }
    }

    public void update(int id, Student student) {

        try (Connection conn = connectionFactory.connectToDb(connectionFactory.buildConnectionString())) {

            String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getState());
            statement.setInt(4, id);

            int affectedRows = statement.executeUpdate();

            System.out.println("Aluno alterado com sucesso! Foi removido " + affectedRows + " linha");
        }
        catch (SQLException | IOException ex) {
            System.out.println("Alteração falhou!");
            ex.printStackTrace();
        }
    }
}
