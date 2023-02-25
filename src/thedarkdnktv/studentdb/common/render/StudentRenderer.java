package thedarkdnktv.studentdb.common.render;

import thedarkdnktv.studentdb.common.model.Student;

import java.util.Collection;

public class StudentRenderer {

    private static final ConsoleListRenderer<Student> renderer;

    static {
        renderer = new ConsoleListRenderer<Student>()
            .addColumn("ФИО", 30, st -> st.getName() + " " + st.getSurname())
            .addColumn("Возраст", 10, Student::getAge)
            .addColumn("Факультет", 30, Student::getFaculty)
            .addColumn("Курс", 8, Student::getCourse)
            .addColumn("Средний балл", 12, Student::getGradePointAverage);
    }

    public static void renderStudentList(Collection<Student> list) {
        renderStudentList(list, false);
    }

    public static void renderStudentList(Collection<Student> list, boolean doIndex) {
        renderer.setRenderHeader(true);
        renderer.setPrintIndex(doIndex);
        renderer.data.addAll(list);
        renderer.render();
        renderer.data.clear();
    }
}
