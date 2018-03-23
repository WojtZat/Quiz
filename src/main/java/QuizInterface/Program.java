package QuizInterface;

public class Program {

    public static void main(String[] args) {
        Quiz quiz = new MemoryQuiz();
        quiz.add("asd");
        quiz.add("ddd");
        for(Question q: quiz.drawQuestionSet(1))
            System.out.println(q.questionText);
    }
}
