package org.combs.micro.hc_tests_service.handler;


import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AnswerPointsHandler {
    private Integer rankPoints = 0;
    private Integer scorePoints = 0;


    public void defineAnswerCorrectness(Answer answer) {
        Question question = answer.getQuestion();
        // Если стоит флаг "не проверяемый", то оставляем выдачу очков на усмотрение учителя
        if (question.getCheckType()) {
            answer.setRankPoints(rankPoints);
            answer.setScorePoints(scorePoints);
        }

        if (question.getType().equals(QuestionType.multiple_choice)){
            multiChoiceTypeHandle(answer, question);
        }
        if (question.getType().equals(QuestionType.single_choice)){
            singleChoiceTypeHandle(answer, question);
        }
        if (question.getType().equals(QuestionType.text)){
            textTypeHandle(answer, question);
        }
    }


    private void textTypeHandle(Answer answer, Question question){
        String correctAnswer = (String) question
                .getAnswer()
                .get("correct")
                .get(0);

        String studentAnswer = (String) answer
                .getStudentAnswer()
                .get("answer")
                .get(0);

        if (studentAnswer.isBlank()) {
            return;
        }
        // Если ответ числовой
        if (isNumeric(correctAnswer)) {
            if (Integer.parseInt(correctAnswer) == Integer.parseInt(studentAnswer)){
                this.rankPoints = question.getRankPoints();
                this.scorePoints = question.getScorePoints();
                return;
            };
        }
        // Если ответ строковой
        if (correctAnswer.equalsIgnoreCase(studentAnswer.strip())){
            this.rankPoints = question.getRankPoints();
            this.scorePoints = question.getScorePoints();
            return;
        };
    }
    private void multiChoiceTypeHandle(Answer answer, Question question) {
        int countOfCorrectAnswers;
        int countOfStudentCorrectAnswers = 0;
        Set<Object> correctAnswers = new HashSet<>(question.getAnswer().get("correct"));
        countOfCorrectAnswers = correctAnswers.size();
        Set<Object> studentAnswers = new HashSet<>(answer.getStudentAnswer().get("answer"));

        if (studentAnswers.isEmpty()){
            return;
        }

        for (Object studentAnswer : studentAnswers){
            if (correctAnswers.contains(studentAnswer)){
                countOfStudentCorrectAnswers += 1;
            }
        }
        // Тут отнимаем неправильно указанные ответы
        // То есть если всего 2 ответа, 1 правльный, а студент указал 2, то у него выйдет 0 правльных;
        // Убираем лишние ответы, но не даём уйти в отрицательные значения
        countOfStudentCorrectAnswers = Math.max(0, countOfStudentCorrectAnswers - (studentAnswers.size() - countOfCorrectAnswers));

        calculatePoints(countOfCorrectAnswers, countOfStudentCorrectAnswers, question);
        setPointsToAnswer(answer);

    }
    private void singleChoiceTypeHandle(Answer answer, Question question) {
        Object correctAnswer = question.getAnswer().get("correct").get(0);
        Object studentAnswer = answer.getStudentAnswer().get("answer").get(0);

        if (studentAnswer == null || studentAnswer.toString().isBlank()) {
            return;
        }

        String correctStr = correctAnswer.toString().trim();
        String studentStr = studentAnswer.toString().trim();

        // Попытка сравнения как чисел
        if (isNumeric(correctStr) && isNumeric(studentStr)) {
            try {
                double correctNum = Double.parseDouble(correctStr);
                double studentNum = Double.parseDouble(studentStr);

                if (Double.compare(correctNum, studentNum) == 0) {
                    assignPoints(question);
                    setPointsToAnswer(answer);
                }
                // при неправильном ответе ставим нулевые значения
                setPointsToAnswer(answer);
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
            }
            return;
        }

        // Строковое сравнение
        if (correctStr.equalsIgnoreCase(studentStr)) {
            assignPoints(question);
            setPointsToAnswer(answer);
        }
        setPointsToAnswer(answer);
    }

    private void assignPoints(Question question) {
        this.rankPoints = question.getRankPoints();
        this.scorePoints = question.getScorePoints();
    }


    private void calculatePoints(int countOfCorrectAnswers, int countOfStudentCorrectAnswers, Question question){
        float correctnessOfAnswers = (countOfCorrectAnswers > 0)
                ? (100.0f / countOfCorrectAnswers) * countOfStudentCorrectAnswers
                : 0.0f;

        this.rankPoints = (int)(question.getRankPoints() * (correctnessOfAnswers / 100));
        this.scorePoints = (int)(question.getScorePoints() * (correctnessOfAnswers / 100));
    }

    private void setPointsToAnswer(Answer answer){
        answer.setRankPoints(rankPoints);
        answer.setScorePoints(scorePoints);
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


}
