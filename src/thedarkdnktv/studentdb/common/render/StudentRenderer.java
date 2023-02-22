package thedarkdnktv.studentdb.common.render;

import thedarkdnktv.studentdb.api.IRenderer;
import thedarkdnktv.studentdb.common.model.Student;

public class StudentRenderer implements IRenderer<Student> {

    @Override
    public String renderAsString(Student object) {
        StringBuilder builder = new StringBuilder();

        {
            builder.append(object.getName());
            builder.append(' ');
            builder.append(object.getSurname());
            builder.append(", ");

            builder.append(object.getAge());
            builder.append(" год, ");

            builder.append(object.getFaculty());
            builder.append(", ");

            builder.append(object.getCourse());
            builder.append("-й курс, ");

            builder.append(String.format("средний балл - %.2f", object.getGradePointAverage()));
        }

        return builder.toString();
    }

    @Override
    public boolean isSupported(Class<?> type) {
        return Student.class.isAssignableFrom(type);
    }
}