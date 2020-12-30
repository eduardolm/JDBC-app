import Dao.StudentDao;
import dbconnection.ConnectionFactory;
import model.Student;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory();

    public static void main(String[] args) throws IOException {

        StudentDao studentDao = new StudentDao();

        /** Lista todos os alunos */
        List<Student> students = studentDao.list();

        students.stream().forEach(student -> {
            System.out.println(student.getId() + " - " + student.getName() + " - " + student.getAge() + " - " + student.getState());
        });

        /** Lista aluno por id */
        Student student = studentDao.getById(1);
        System.out.println(student.getId() + " - " + student.getName() + " - " + student.getAge() + " - " + student.getState());

        /** Criar aluno */
        Student student1 = new Student();
        student1.setName("Eduardo");
        student1.setAge(42);
        student1.setState("SC");

        studentDao.create(student1);

        /** Apagar aluno */
        studentDao.delete(1);

        /** Alterar aluno */
        Student student2 = new Student();
        student2.setName("Marcelo");
        student2.setAge(25);
        student2.setState("AM");

        studentDao.update(2, student2);
    }
}
